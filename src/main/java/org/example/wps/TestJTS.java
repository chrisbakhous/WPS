package org.example.wps;


import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

public class TestJTS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeometryFactory gf = new GeometryFactory();
		double x1 = 10.342211513452, y1 = 11.342211514232, x2 = 0.342211513926, y2 = 1.342211513111,
			   x3 = 0.342211513898, y3 = 13.342211513101, x4 = 11.342211513921, y4 = 2.342211513878;
		
	
		
		
		Coordinate coord1 = new Coordinate(x1,y1);
		Coordinate coord2 = new Coordinate(x2,y2);
		Coordinate coord3 = new Coordinate(x3,y3);
		Coordinate coord4 = new Coordinate(x4,y4);
		
		Point point1 = gf.createPoint(coord1);
		Point point2 = gf.createPoint(coord2);
		Point point3 = gf.createPoint(coord3);
		Point point4 = gf.createPoint(coord4);
		
		
		Coordinate[] coords1 = new Coordinate[]{coord1,coord2};
		Coordinate[] coords2 = new Coordinate[]{coord2,coord3};
		
		LineString line1 = gf.createLineString(coords1);
		LineString line2 = gf.createLineString(coords2);
		
		Point intersectionPoint = (Point) line2.intersection(line1);

		System.out.println(line2.intersects(line1));
		System.out.println(intersectionPoint.getX()+"@"+intersectionPoint.getY());
		

		


	}

}
