//package org.example.wps;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.util.ArrayList;
//
//public class Principale3 {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		
////		Point p1 = new Point(152058.46,6863511.5);
////		Point p2 = new Point(152062.39,6863502.37);
////		Point p3 = new Point(152062.62,6863485.26);
////		Point p4 = new Point(152056.26,6863463.84);
////		Point p5 = new Point(152044.39,6863435.91);
////		Point p6 = new Point(152018.66,6863404.25);
////		Point p7 = new Point(151958.31,6863354.76);   
////		Point p8 = new Point(151883.74,6863313.96);  		
////		Point p9 = new Point(151790.64,6863274.73);  
////		Point p10 = new Point(151668.83,6863217.17);  
////		Point p11 = new Point(151615.76,6863186.19);  
////		Point p12 = new Point(151566.86,6863140.9);  
////		Point p13 = new Point(151496.05,6863032.97);  
////		Point p14 = new Point(151392.02,6862878.19);  
////		Point p15 = new Point(151303.95,6862754.7);  		
////		Point p16 = new Point(151226.14,6862663.95);  
////		Point p17 = new Point(151116.8,6862559.18);  
////		Point p18 = new Point(150980.43,6862464.63);  
////		Point p19 = new Point(150875.72,6862402.65); 		
////		Point p20 = new Point(150811.03,6862347.49);  
//		
//		Point p1 = new Point(58.46,11.5);
//		Point p2 = new Point(62.39,02.37);
//		Point p3 = new Point(62.62,85.26);
//		Point p4 = new Point(56.26,63.84);
//		Point p5 = new Point(44.39,35.91);
//		Point p6 = new Point(18.66,04.25);
//		Point p7 = new Point(58.31,54.76);   
//		Point p8 = new Point(83.74,13.96);  		
//		Point p9 = new Point(90.64,74.73);  
//		Point p10 = new Point(68.83,17.17);  
//		Point p11 = new Point(15.76,86.19);  
//		Point p12 = new Point(66.86,40.9);  
//		Point p13 = new Point(96.05,32.97);  
//		Point p14 = new Point(92.02,78.19);  
//		Point p15 = new Point(03.95,54.7);  		
//		Point p16 = new Point(26.14,63.95);  
//		Point p17 = new Point(16.8,59.18);  
//		Point p18 = new Point(80.43,64.63);  
//		Point p19 = new Point(75.72,02.65); 		
//		Point p20 = new Point(11.03,47.49);  
//		
//		ArrayList<Point> RefPointsCoordinates = new ArrayList<Point>();
//		RefPointsCoordinates.add(p1);
//		RefPointsCoordinates.add(p2);
//		RefPointsCoordinates.add(p3);
//		RefPointsCoordinates.add(p4);
//		RefPointsCoordinates.add(p5);
//		RefPointsCoordinates.add(p6);
//		RefPointsCoordinates.add(p7);
//		RefPointsCoordinates.add(p8);
//		RefPointsCoordinates.add(p9);
//		RefPointsCoordinates.add(p10);
//		RefPointsCoordinates.add(p11);
//		RefPointsCoordinates.add(p12);
//		RefPointsCoordinates.add(p13);
//		RefPointsCoordinates.add(p14);
//		RefPointsCoordinates.add(p15);
//		RefPointsCoordinates.add(p16);
//		RefPointsCoordinates.add(p17);
//		RefPointsCoordinates.add(p18);
//		RefPointsCoordinates.add(p19);
//		RefPointsCoordinates.add(p20);
//		
//		
//		ArrayList<Segment> RefSegments = new ArrayList<Segment>();
//
//		RefSegments.add(new Segment(p1, p2));
//		RefSegments.add(new Segment(p2, p3));
//		RefSegments.add(new Segment(p3, p4));
//		RefSegments.add(new Segment(p4, p5));
//		RefSegments.add(new Segment(p5, p6));
//		RefSegments.add(new Segment(p6, p7));
//		RefSegments.add(new Segment(p7, p8));
//		RefSegments.add(new Segment(p8, p9));
//		RefSegments.add(new Segment(p9, p10));
//		RefSegments.add(new Segment(p10, p11));
//		RefSegments.add(new Segment(p11, p12));
//		RefSegments.add(new Segment(p12, p13));
//		RefSegments.add(new Segment(p13, p14));
//		RefSegments.add(new Segment(p14, p15));
//		RefSegments.add(new Segment(p15, p16));
//		RefSegments.add(new Segment(p16, p17));
//		RefSegments.add(new Segment(p17, p18));
//		RefSegments.add(new Segment(p18, p19));
//		RefSegments.add(new Segment(p19, p20));
//		
//		/*for(int i=0;i<RefPointsCoordinates.size()-1;i++)
//		{
//			RefSegments.add(new Segment(RefPointsCoordinates.get(i), RefPointsCoordinates.get(i+1)));
//		}*/
//		
//		Line reference = new Line(RefSegments);
//		
//		Radial rad2 = new Radial(0.5,10.0,true);
//		
//		ArrayList<Segment> res = new ArrayList<Segment>();
//		// TEST RadialsFirstPointCoordinates de la classe Radial
//		/*ArrayList<Segment> res = rad2.RadialsFirstPointCoordinates (reference);
//		for (int i = 0 ; i<res.size();i++)
//		{
//			System.out.print("Point 1 : ");
//			res.get(i).getStartPoint().DisplayCoordinates();
//			System.out.print("Point 2 : ");
//			res.get(i).getEndPoint().DisplayCoordinates();	
//		}*/
//		
//		JCanvas myPanel = new JCanvas(RefSegments,res);
//		myPanel.setBackground(Color.WHITE);
//		myPanel.setPreferredSize(new Dimension(800,800));
//		GUIHelper.showOnFrame(myPanel,"myPanel");
//
//	}
//
//}
