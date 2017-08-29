package org.example.wps;



import java.util.ArrayList;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.process.factory.DescribeParameter;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;
import org.geotools.process.factory.StaticMethodsProcessFactory;
import org.geotools.text.Text;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;







public class WFStest extends StaticMethodsProcessFactory<WFStest> {

	  public WFStest() {
	    super(Text.text("WFStest"), "custom3", WFStest.class);
	  } 
	  
	  @DescribeProcess(title = "retrieveCoordinates", description = "Retroune les coordonnées d'une lineString passée en parametre sous frome WFS")
	  @DescribeResult(description = " les coordonées")
	  public static String retrieveCoordinates(
	      @DescribeParameter(name = "wfs", description = " ") FeatureCollection<SimpleFeatureType, SimpleFeature> features,
	  	  @DescribeParameter(name = "pas", description = " ") Double pas,
		  @DescribeParameter(name = "longueur", description = " ") Double longueur)
	  	  
	  {
//		  Feature[] features = parametre1.getFeatures();
//		  FeatureIterator<SimpleFeature>  i =  parametre1.features();
//		  Feature feature = null;
//		  SimpleFeatureIterator iterator = (SimpleFeatureIterator) features.features();
		  FeatureIterator<SimpleFeature> iterator = features.features();
		  ArrayList<SimpleFeature> retrieveData = new ArrayList<SimpleFeature>();
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
		  
		  String referenceCoordinates = retrieveData.get(0).getAttribute(2).toString(); //Avec des test j'ai conclu que l'argument 2 est celui des coordonées 
		  																				//0  parceque dans le wfs il y a qu'un seul feature. penser a modifier ca.
		  //String referenceCoordinates = retrieveData.get(0).getAttribute("geometry").toString();
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
			//Line reference = new Line(ReferenceSegments);
			//Radial rad = new Radial(longueur,pas,true);
//			ArrayList<Segment> res = rad.RadialsFirstPointCoordinates (reference);
//			String RES = " " ;
//			for (int n = 0; n<res.size();n++)
//			{
//				RES = RES + " Segment numéro  " + n +"\n"+res.get(n).getStartPoint().getX()+"@"+res.get(n).getStartPoint().getY()+"|| "+res.get(n).getEndPoint().getX()+"@"+res.get(n).getEndPoint().getY()+"\n" ;
//			}
			
			String COORDONNES = "" ;
			for(int bb=0; bb<ReferenceSegments.size();bb++)
			{
				COORDONNES  = COORDONNES + "Segment numero " + bb+ " : ";
				COORDONNES =  COORDONNES + ReferenceSegments.get(bb).getStartPoint().getX().toString()+"@";
				COORDONNES =  COORDONNES +ReferenceSegments.get(bb).getStartPoint().getY().toString()+"\n";		
			}
			
		  return COORDONNES;
		 // return test.get(param1).getAttribute(param2).toString();
	
		  
		 
		   
	  }
	  
	  
	  
	  
	 

	 
	

}
