package org.example.wps;

import java.util.List;
import org.geotools.data.DataUtilities;
import org.geotools.feature.SchemaException;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class TestCreateSimpleFeatureAndSimpleFeatureType {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SchemaException, NoSuchAuthorityCodeException, FactoryException {
		
	//	SimpleFeatureType lineType = DataUtilities.createType("LINE", "centerline:LineString,name:\"\",id:0");
		SimpleFeatureType RadialType = DataUtilities.createType("RADIAL", "geometry:LineString,name:\"\",id:0");

		System.out.println("FeatureType: " + DataUtilities.spec(RadialType));
		SimpleFeature feature = DataUtilities.template( RadialType );
		feature.setAttribute("geometry", "LINESTRING (152058.46 6863511.5, 152062.39 6863502.37, 152062.62 6863485.26, 152056.26 6863463.84, 152044.39 6863435.91, 152018.66 6863404.25, 151958.31 6863354.76, 151883.74 6863313.96, 151790.64 6863274.73, 151668.83 6863217.17, 151615.76 6863186.19, 151566.86 6863140.9, 151496.05 6863032.97, 151392.02 6862878.19, 151303.95 6862754.7, 151226.14 6862663.95, 151116.8 6862559.18, 150980.43 6862464.63, 150875.72 6862402.65, 150811.03 6862347.49)");
		feature.setAttribute("name","nameRadRef");
		feature.setAttribute("id",1);
		List<Object> AttributesList = feature.getAttributes();
		for(Object Attribute :AttributesList )
		{
		  System.out.println(Attribute);
		}
		

			CRSAuthorityFactory   factory = CRS.getAuthorityFactory(true);
			CoordinateReferenceSystem crs = CRS.decode("EPSG:2154");
			CoordinateReferenceSystem crs2 = factory.createCoordinateReferenceSystem("EPSG:2154");
			int crsCode = CRS.lookupEpsgCode(crs,true);

			System.out.println("crs = " + crs);
			System.out.println("crsName = " + crs.getName());
			System.out.println("crs2Name = " + crs2.getName());
			System.out.println("crsCode = " + crsCode);
	}

}
