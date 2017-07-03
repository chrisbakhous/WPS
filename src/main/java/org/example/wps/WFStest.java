package org.example.wps;



import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.FeatureCollection;
import org.geotools.process.factory.DescribeParameter;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;
import org.geotools.process.factory.StaticMethodsProcessFactory;
import org.geotools.text.Text;
import org.opengis.feature.simple.SimpleFeature;







public class WFStest extends StaticMethodsProcessFactory<WFStest> {

	  public WFStest() {
	    super(Text.text("WFStest"), "custom3", WFStest.class);
	  } 
	  
	  @DescribeProcess(title = "CountFeatures", description = "Retroune le nombre de features")
	  @DescribeResult(description = "un entier indiquant le nombre des features")
	  public static int CountFeatures(
	      @DescribeParameter(name = "parametre1", description = "features") FeatureCollection parametre1) {
		  //Feature[] features = parametre1.getFeatures();
		  /*FeatureIterator i =  parametre1.features();
		  Feature feature = null;
		  while( i.hasNext()  ){
	          feature = i.next();
	          System.out.println(feature);
		  }*/
		  SimpleFeatureIterator iterator = (SimpleFeatureIterator) parametre1.features();
		  //int count = 0;
		    /*try {
		        while( iterator.hasNext() ){
		        	//count = count + 1 ;
		            SimpleFeature feature = iterator.next();
		            return feature;
		        }
		       
		    }
		    finally {
		    	 
		        iterator.close();
		       
		    }
		    return null;*/
		   return 10;
	  }
	  
	  @DescribeProcess(title = "CopyFeatures", description = "Retroune les features")
	  @DescribeResult(description = "les features")
	  public static FeatureCollection CopyFeatures(
	      @DescribeParameter(name = "parametre1", description = "features") FeatureCollection parametre1) {
		  //Feature[] features = parametre1.getFeatures();
		  /*FeatureIterator i =  parametre1.features();
		  Feature feature = null;
		  while( i.hasNext()  ){
	          feature = i.next();
	          System.out.println(feature);
		  }*/
		  SimpleFeatureIterator iterator = (SimpleFeatureIterator) parametre1.features();
		  //int count = 0;
		    /*try {
		        while( iterator.hasNext() ){
		        	//count = count + 1 ;
		            SimpleFeature feature = iterator.next();
		            return feature;
		        }
		       
		    }
		    finally {
		    	 
		        iterator.close();
		       
		    }
		    return null;*/
		   return parametre1;
	  }
	  
	  
	  
	 

	 
	

}
