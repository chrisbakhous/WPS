package org.example.wps;



import java.util.ArrayList;
import org.geotools.data.DataUtilities;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.process.factory.DescribeParameter;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;
import org.geotools.process.factory.StaticMethodsProcessFactory;
import org.geotools.text.Text;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

 


public class WFStest extends StaticMethodsProcessFactory<WFStest> {

	  public WFStest() {
	    super(Text.text("WFStest"), "TDC", WFStest.class);
	  } 
	  
	  public static  ArrayList<Line>  getWFSData (FeatureCollection<SimpleFeatureType, SimpleFeature> wfs) 
	  {
		  ArrayList<Line> result = new ArrayList<Line>();
		  FeatureIterator<SimpleFeature> iterator = wfs.features();
		  ArrayList<SimpleFeature> simpleFeautres = new ArrayList<SimpleFeature>();
		  try 
		  {
			  while( iterator.hasNext()  ){
				  SimpleFeature feature = iterator.next();
				  simpleFeautres.add(feature); 
			  }
		  }
		  finally {
			     iterator.close();
		  }
		  
		  int numberOfFeatures = simpleFeautres.size(); 
		  ArrayList<ArrayList<String>> geometryPointsCoordinates = new ArrayList<ArrayList<String>>();

		  for(int i = 0 ; i<numberOfFeatures;i++)
		  {
			  String geometry = simpleFeautres.get(i).getDefaultGeometry().toString();			  
			  String[] geometryPoints = geometry.split(",");
			  int j = 0 ;
			  for(String e :geometryPoints)
			  {
				  geometryPoints[j] = e.replaceAll("[^0-9 .]", ""); // Pour ne garder que les chiffres et les points coorespondant aux coordonnees 
				  j++;
			  }

			  for(String e :geometryPoints)
			  {
					String[] pointCoordinates = e.split(" ");
					ArrayList<String> pointCoordinatesArrayList = new ArrayList<String>();
					for(String coord : pointCoordinates)
					{
						if ((coord.isEmpty() == false) ||  ( coord.equals("") == false ))
						{
							pointCoordinatesArrayList.add(coord);
						}						
					}			
					geometryPointsCoordinates.add(pointCoordinatesArrayList);
			  }
			  ArrayList<Segment> ReferenceSegments = new ArrayList<Segment>();
				
			  for(int a = 0 ; a<geometryPointsCoordinates.size()-1;a++)
			  {
				  Point p1 = new Point(Double.parseDouble(geometryPointsCoordinates.get(a).get(0)),Double.parseDouble(geometryPointsCoordinates.get(a).get(1)));
				  Point p2 = new Point(Double.parseDouble(geometryPointsCoordinates.get(a+1).get(0)),Double.parseDouble(geometryPointsCoordinates.get(a+1).get(1)));
				  Segment seg = new Segment(p1, p2);
				  ReferenceSegments.add(seg);
			  }
			  
			  result.add(new Line(ReferenceSegments));
			  
		  }	
		 
		  return result;
		  
	  }
	  
	  
	  @DescribeProcess(title = "AjoutRadiales", description = "ce processus consiste a tracer des lignes perpendiculaires sur les segments definissant la ligne de reference passee en parametre selon une distance, une longueur et un sens, passes en parametre, egalement definit par le chercheur. Ces radiales ont pour objectif de faciliter le calcul de l avancement et du recul de la plage.")
	  @DescribeResult(description = "Une featureCollection coorespondant a la ligne de reference et ses radiales")
	  public static FeatureCollection<SimpleFeatureType, SimpleFeature> AjoutRadiales(
	      @DescribeParameter(name = "Ref", description = " La ligne de reference ") FeatureCollection<SimpleFeatureType, SimpleFeature> refLine,
	      @DescribeParameter(name = "TDC", description = " Les traits de cotes d interet ") FeatureCollection<SimpleFeatureType, SimpleFeature> tdc,
	  	  @DescribeParameter(name = "Distance", description = " Distance entre deux radiales ") Double pas,
		  @DescribeParameter(name = "Longueur", description = " Longueur de la radiale ") Double longueur,
		  @DescribeParameter(name = "Sens", description = "Sens de la radiale") Boolean sens) throws Exception{
		  		  
		  Line reference =  WFStest.getWFSData(refLine).get(0);
		  Radial rad = new Radial(pas,longueur,sens);
		  
		  ArrayList<Segment> RadialesSegments = rad.RadialsCoordinates (reference);

		  String RES = " "; int id = 0;
		  
		  SimpleFeatureType featureType = DataUtilities.createType("RADIAL", "geometry:LineString,id:0");//Notre feature doit avoir un membre geometry et un membre id
		  DefaultFeatureCollection featureCollection = new DefaultFeatureCollection("RefAndRadialesSegments",featureType);	
				
		  
		  for(int i=0; i<RadialesSegments.size();i++)
		  {
					SimpleFeature feature = DataUtilities.template( featureType );
					String lineString = "LINESTRING ("+RadialesSegments.get(i).getStartPoint().getX()+" "+RadialesSegments.get(i).getStartPoint().getY()+", "+RadialesSegments.get(i).getEndPoint().getX()+" "+RadialesSegments.get(i).getEndPoint().getY()+")";
					feature.setAttribute("geometry",lineString);
					feature.setAttribute("id",id);
					featureCollection.add(feature);
					id++;
		  }
		  
		  for(int i=0; i<reference.getSegments().size();i++)
		  {
					SimpleFeature feature = DataUtilities.template( featureType );
					String lineString = "LINESTRING ("+reference.getSegments().get(i).getStartPoint().getX()+" "+reference.getSegments().get(i).getStartPoint().getY()+", "+reference.getSegments().get(i).getEndPoint().getX()+" "+reference.getSegments().get(i).getEndPoint().getY()+")";
					feature.setAttribute("geometry",lineString);
					feature.setAttribute("id",id);
					featureCollection.add(feature);
					id++;
		  }
				
		  
		  	
		  // Ajout des features trait de cotes 
		  FeatureIterator<SimpleFeature> iteratorTDC = tdc.features();
		  try {
				while( iteratorTDC.hasNext()  ){
					SimpleFeature feature = iteratorTDC.next();
					featureCollection.add(feature); 
				}
			}	finally {
					  iteratorTDC.close();
				}
		  //Fin de l ajout des feature trait de cotes 
		  ArrayList<Line> traitdecote =  WFStest.getWFSData(tdc);
		  
		  System.out.println("Segments trait de cote ");
		  for(int i = 0 ; i<traitdecote.size();i++)
		  {
			  System.out.println("trait de cote numero " + i + " : ");
			  for(int j = 0 ; j<traitdecote.get(i).getSegments().size();j++)
			  {
				  System.out.println("Segment numero " + j + ": ");
				  System.out.print("Start Point :  ");
				  System.out.println(traitdecote.get(i).getSegments().get(j).getStartPoint().getX()+"@"+traitdecote.get(i).getSegments().get(j).getStartPoint().getY());
				  System.out.print("End Point :  ");
				  System.out.println(traitdecote.get(i).getSegments().get(j).getEndPoint().getX()+"@"+traitdecote.get(i).getSegments().get(j).getEndPoint().getY());
			  }
		  }
		  
		  //Utilisation de la classe  Coordinate, , , de la libraire JTS pensez a la precisez dans le fichier POM
		  GeometryFactory geometryFactory = new GeometryFactory();
		  ArrayList<ArrayList<LineString>> tdcSegmentsJTS = new ArrayList<ArrayList<LineString>>();
		  ArrayList<LineString> radSegmentsJTS = new ArrayList<LineString>();
		  Double xStart,yStart,xEnd,yEnd ; 
		  for(int i = 0 ; i<traitdecote.size();i++)
		  {
			  ArrayList<LineString> ls = new ArrayList<LineString>();
			  for(int j = 0 ; j<traitdecote.get(i).getSegments().size();j++)
			  {
				  Segment s = traitdecote.get(i).getSegments().get(j);
				  xStart = s.getStartPoint().getX();
				  yStart = s.getStartPoint().getY();
				  xEnd = s.getEndPoint().getX();
				  yEnd = s.getEndPoint().getY(); 
				  Coordinate coord1 = new Coordinate(xStart,yStart);
				  Coordinate coord2 = new Coordinate(xEnd,yEnd);
				  Coordinate[] SegmentCoords  = new Coordinate[]{coord1,coord2};
				  LineString seg = geometryFactory.createLineString(SegmentCoords);
				  ls.add(seg);  
			  } 
			  tdcSegmentsJTS.add(ls);
		  }
		  
		  for(int i = 0 ; i<RadialesSegments.size();i++)
		  {
			  Segment s = RadialesSegments.get(i); 
			  xStart = s.getStartPoint().getX();
			  yStart = s.getStartPoint().getY();
			  xEnd = s.getEndPoint().getX();
			  yEnd = s.getEndPoint().getY(); 
			  Coordinate coord1 = new Coordinate(xStart,yStart);
			  Coordinate coord2 = new Coordinate(xEnd,yEnd);
			  Coordinate[] SegmentCoords  = new Coordinate[]{coord1,coord2};
			  LineString seg = geometryFactory.createLineString(SegmentCoords);
			  radSegmentsJTS.add(seg);
		  }
		  
		  //Test intersections radiales/traits de cote 
		  for(int i = 0 ; i<tdcSegmentsJTS.size();i++)
		  {
			  ArrayList<LineString> traitdecoteJTS = tdcSegmentsJTS.get(i);
			  for(int j = 0 ; j<traitdecoteJTS.size();j++)
			  {
				  for(int k = 0; k<radSegmentsJTS.size();k++)
				  {
					  	
					  	if(traitdecoteJTS.get(j).intersects(radSegmentsJTS.get(k)))
						{
					  		System.out.println("trait de cote numero "+ i);
					  		System.out.println("intersection segment trait de cote numero " + j + "avec la radiale numero " + k);
						}
					  	
				  }
			  }
		  }
  
		  //Debut Calcule des intersections (radiales / traits de cote)
//				  ArrayList<Segment> RefSegments = reference.getSegments();
//				  ArrayList<Segment> radSegments = RadialesSegments;
//				  Double xStart,yStart,xEnd,yEnd ; 
//		   		  GeometryFactory geometryFactory = new GeometryFactory();
//		   		  ArrayList<com.vividsolutions.jts.geom.LineString> RefSegmentsJTS = new ArrayList<com.vividsolutions.jts.geom.LineString>();
//				  for(int k=0;k<RefSegments.size();k++)
//				  {
//					  Segment s = RefSegments .get(k);
//					  xStart = s.getStartPoint().getX();
//					  yStart = s.getStartPoint().getY();
//					  xEnd = s.getEndPoint().getX();
//					  yEnd = s.getEndPoint().getY();
//					  Coordinate coord1 = new Coordinate(xStart,yStart);
//					  Coordinate coord2 = new Coordinate(xEnd,yEnd);
////		   			  com.vividsolutions.jts.geom.Point startPointJTS = geometryFactory.createPoint(coord1);
////		   			  com.vividsolutions.jts.geom.Point endPointJTS = geometryFactory.createPoint(coord2);
//		   			  Coordinate[] SegmentCoords  = new Coordinate[]{coord1,coord2};
//		   			  com.vividsolutions.jts.geom.LineString seg = geometryFactory.createLineString(SegmentCoords);
//		   			  RefSegmentsJTS.add(seg);		  
//				  }
				  
				  
				  //Fin Calcule des intersections (radiales / traits de cote)
		  //}
		  
		  return featureCollection;	 
		   
	  }
	  
	  
	  
	  
	 

	 
	

}
