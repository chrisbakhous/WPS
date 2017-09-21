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

 


public class WFStest extends StaticMethodsProcessFactory<WFStest> {

	  public WFStest() {
	    super(Text.text("WFStest"), "TDC", WFStest.class);
	  } 
	  
	  @DescribeProcess(title = "AjoutRadiales", description = "ce processus consiste a tracer des lignes perpendiculaires sur les segments definissant la ligne de reference passee en parametre selon une distance, une longueur et un sens, passes en parametre, egalement definit par le chercheur. Ces radiales ont pour objectif de faciliter le calcul de l avancement et du recul de la plage.")
	  @DescribeResult(description = "Une featureCollection coorespondant a la ligne de reference et ses radiales")
	  public static FeatureCollection<SimpleFeatureType, SimpleFeature> AjoutRadiales(
	      @DescribeParameter(name = "Ref", description = " La ligne de reference ") FeatureCollection<SimpleFeatureType, SimpleFeature> refLine,
	      @DescribeParameter(name = "TDC", description = " Les traits de cotes d interet ") FeatureCollection<SimpleFeatureType, SimpleFeature> tdc,
	  	  @DescribeParameter(name = "Distance", description = " Distance entre deux radiales ") Double pas,
		  @DescribeParameter(name = "Longueur", description = " Longueur de la radiale ") Double longueur,
		  @DescribeParameter(name = "Sens", description = "Sens de la radiale") Boolean sens) throws Exception
	  	  
	  {
		  
		  SimpleFeatureType featureType = DataUtilities.createType("RADIAL", "geometry:LineString,id:0");//Notre feature doit avoir un membre geometry et un membre id
		  DefaultFeatureCollection featureCollection = new DefaultFeatureCollection("RefAndRadialesSegments",featureType);
		  
		  ArrayList<SimpleFeature> retrieveData = new ArrayList<SimpleFeature>();
		  FeatureIterator<SimpleFeature> iterator = refLine.features();
				  
		  try 
		  {
			  while( iterator.hasNext()  ){
				  SimpleFeature feature = iterator.next();
				  retrieveData.add(feature); 
			  }
		  }
		  finally {
			     iterator.close();
		  }
		  
		  //	DEBUT TEST 17/09/2017
		  
		  int featuresNumber = retrieveData.size();
		  
//		  for(int i = 0 ; i<featuresNumber;i++)
//		  {
//			  System.out.println("Information sur la feature numero " + i);
//			  String featureID = retrieveData.get(i).getID();
//			  String featureID2 = retrieveData.get(i).getAttribute("id").toString();
//			  String featureGeometry = retrieveData.get(i).getAttribute("geometry").toString();
//			  String featureDefaultGeometry = retrieveData.get(i).getDefaultGeometry().toString();
//			  String featureType = retrieveData.get(i).getFeatureType().toString();
//			  Collection<Property> featureProperties = retrieveData.get(i).getProperties();
//			  
//			  System.out.println("featuresNumber  = " +featuresNumber );
//			  System.out.println("featureID  = " +featureID );
//			  System.out.println("featureID2  = " + featureID2);
//			  System.out.println("featureGeometry  = " +featureGeometry );
//			  System.out.println("featureDefaultGeometry  = " +featureDefaultGeometry );
//			  System.out.println("featureType  = " +featureType );
//			  
//			  for(Property p : featureProperties)
//			  {
//				  System.out.println("Property Name = "+ p.getName().toString());
//			  }			  
//		  }
		 	  
		  //	FIN TEST 17/09/2017
		  
		
		 
			
		  for(int i = 0 ; i<featuresNumber;i++)
		  {
			  String referenceCoordinates = retrieveData.get(i).getDefaultGeometry().toString();
				 
//			  CoordinateReferenceSystem crs = features.getBounds().crs();
//			  String crsName = crs.getName().toString();
//			  int crsCode = CRS.lookupEpsgCode(crs,true);
			
//			  String referenceCoordinates = retrieveData.get(0).getAttribute("geometry").toString();
//			  List<Object> retrieveAttributes = retrieveData.get(0).getAttributes();
//			  for(Object Attribute :retrieveAttributes )
//			  {
//				  System.out.println(Attribute.toString());
//			  }
//			  
			  
			  String[] pointsCoordinates = referenceCoordinates.split(",");
	   		  ArrayList<ArrayList<String>> pointsCoordinates4 = new ArrayList<ArrayList<String>>();

			  int j = 0 ;
			  for(String e :pointsCoordinates)
			  {
				 pointsCoordinates[j] = e.replaceAll("[^0-9 .]", "");
				 j++;
			  }

				for(String e :pointsCoordinates)
				{
					String[] pointsCoordinates2 = e.split(" ");
					ArrayList<String> pointsCoordinates3ArrayList = new ArrayList<String>();
						for(String ele : pointsCoordinates2)
						{
							if ((ele.isEmpty() == false) ||  ( ele.equals("") == false ))
							{
								pointsCoordinates3ArrayList.add(ele);
							}
						
						}
			
					pointsCoordinates4.add(pointsCoordinates3ArrayList);
				}
				
				ArrayList<Segment> ReferenceSegments = new ArrayList<Segment>();
				for(int a = 0 ; a<pointsCoordinates4.size()-1;a++)
				{
					Point p1 = new Point(Double.parseDouble(pointsCoordinates4.get(a).get(0)),Double.parseDouble(pointsCoordinates4.get(a).get(1)));
					Point p2 = new Point(Double.parseDouble(pointsCoordinates4.get(a+1).get(0)),Double.parseDouble(pointsCoordinates4.get(a+1).get(1)));
					Segment seg = new Segment(p1, p2);
					ReferenceSegments.add(seg);
				}
				Line reference = new Line(ReferenceSegments);
				Radial rad = new Radial(pas,longueur,sens);
				ArrayList<Segment> res = rad.RadialsCoordinates (reference);
				String RES = " " ;
				
				for (int n = 0; n<res.size();n++)
				{
					RES = RES + " Segment numero  " + n +"\n"+res.get(n).getStartPoint().getX()+"@"+res.get(n).getStartPoint().getY()+"|| "+res.get(n).getEndPoint().getX()+"@"+res.get(n).getEndPoint().getY()+"\n" ;
				}
				
				int id = 0 ;
				  //18/09/2017
				  @SuppressWarnings("deprecation")
				CoordinateReferenceSystem crs = refLine.getBounds().crs();
				String crsName = crs.getName().toString();
				System.out.println("crsName : "+crsName);
				  
				for(int bb=0; bb<res.size();bb++)
				{
					SimpleFeature feature = DataUtilities.template( featureType );
					String lineString = "LINESTRING ("+res.get(bb).getStartPoint().getX()+" "+res.get(bb).getStartPoint().getY()+", "+res.get(bb).getEndPoint().getX()+" "+res.get(bb).getEndPoint().getY()+")";
					feature.setAttribute("geometry",lineString);
					feature.setAttribute("id",id);
					featureCollection.add(feature);
					id++;
				}
				for(int bb=0; bb<ReferenceSegments.size();bb++)
				{
					SimpleFeature feature = DataUtilities.template( featureType );
					String lineString = "LINESTRING ("+ReferenceSegments.get(bb).getStartPoint().getX()+" "+ReferenceSegments.get(bb).getStartPoint().getY()+", "+ReferenceSegments.get(bb).getEndPoint().getX()+" "+ReferenceSegments.get(bb).getEndPoint().getY()+")";
					feature.setAttribute("geometry",lineString);
					feature.setAttribute("id",id);
					featureCollection.add(feature);
					id++;
				}
				
				//Debut recuperation des donnees des traits de cotes d'interet
				  FeatureIterator<SimpleFeature> iteratorTDC = tdc.features();
				  try 
				  {
					  while( iteratorTDC.hasNext()  ){
						  SimpleFeature feature = iteratorTDC.next();
						  //feature.setAttribute("id", id++);
						  featureCollection.add(feature); 
					  }
				  }
				  finally {
					  iteratorTDC.close();
				  }
				  //Fin recuperation des donnees des trait de cotes d interet
		  }


		  return featureCollection;	 
		   
	  }
	  
	  
	  
	  
	 

	 
	

}
