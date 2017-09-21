//package org.example.wps;
//
//import java.util.ArrayList;
//
//public class Principale2 {
//	
//	
//	public static void displayArrayList(ArrayList<String> array)
//	{
//		int sizeArray = array.size();
//		System.out.print("La taille du tableau est : " + sizeArray + " et il contient : ");
//		
//		for(int i = 0 ; i<sizeArray;i++)
//		{
//			System.out.print("\""+array.get(i)+"\" ");
//		}
//		System.out.println();
//	}
//	
//	public static  void displayArray(String[] array)
//	{
//		int sizeArray = array.length;
//		System.out.print("La taille du tableau  est : " + sizeArray + " et il contient : ");
//		
//		for(int i = 0 ; i<sizeArray;i++)
//		{
//			System.out.print("\""+array[i]+"\" ");
//		}
//		System.out.println();
//	}
//	public static  void displayArraysList(ArrayList<ArrayList<String>> array)
//	{
//		int sizeArray = array.size();
//		System.out.println("ce tableau contient " + sizeArray + " tableau : ");
//		
//		for(int i = 0 ; i<sizeArray;i++)
//		{
//			displayArrayList((array.get(i)));
//		}
//		System.out.println();
//	}
//	public static  void displayArrays(ArrayList<String[]> array)
//	{
//		int sizeArray = array.size();
//		System.out.print("ce tableau contient " + sizeArray + " tableau : ");
//		
//		for(int i = 0 ; i<sizeArray;i++)
//		{
//			displayArray((array.get(i)));
//		}
//		System.out.println("");
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		
//		
//
//		
//		   
//
//		
//	
//		
//		String referenceCoordinates = "LINESTRING (152058.46 6863511.5, 152062.39 6863502.37, 152062.62 6863485.26, 152056.26 6863463.84, 152044.39 6863435.91, 152018.66 6863404.25, 151958.31 6863354.76, 151883.74 6863313.96, 151790.64 6863274.73, 151668.83 6863217.17, 151615.76 6863186.19, 151566.86 6863140.9, 151496.05 6863032.97, 151392.02 6862878.19, 151303.95 6862754.7, 151226.14 6862663.95, 151116.8 6862559.18, 150980.43 6862464.63, 150875.72 6862402.65, 150811.03 6862347.49)";
//		String[] pointsCoordinates = referenceCoordinates.split(",");
//		ArrayList<ArrayList<String>> pointsCoordinates4 = new ArrayList<ArrayList<String>>();
//
//		int i = 0 ;
//		for(String e :pointsCoordinates)
//		{
//			pointsCoordinates[i] = e.replaceAll("[^0-9 .]", "");
//			i++;
//		}
//
//		
//		for(String e :pointsCoordinates)
//		{
//			String[] pointsCoordinates2 = e.split(" ");
//			ArrayList<String> pointsCoordinates3ArrayList = new ArrayList<String>();
//				for(String ele : pointsCoordinates2)
//				{
//					if ((ele.isEmpty() == false) ||  ( ele.equals("") == false ))
//					{
//						pointsCoordinates3ArrayList.add(ele);
//					}
//				
//				}
//	
//			pointsCoordinates4.add(pointsCoordinates3ArrayList);
//		}
//		System.out.println("*******************************pointsCoordinates4***************************************");
//		displayArraysList(pointsCoordinates4);
//		
//		ArrayList<Segment> ReferenceSegments = new ArrayList<Segment>();
//		for(int a = 0 ; a<pointsCoordinates4.size()-1;a++)
//		{
//			Point p1 = new Point(Double.parseDouble(pointsCoordinates4.get(a).get(0)),Double.parseDouble(pointsCoordinates4.get(a).get(1)));
//			Point p2 = new Point(Double.parseDouble(pointsCoordinates4.get(a+1).get(0)),Double.parseDouble(pointsCoordinates4.get(a+1).get(1)));
//			Segment seg = new Segment(p1, p2);
//			
//			ReferenceSegments.add(seg);
//		}
//		
//		for(int pp = 0; pp<ReferenceSegments.size();pp++)
//		{
//			System.out.println("START");
//			System.out.println(ReferenceSegments.get(pp).getStartPoint().getX());
//			System.out.println(ReferenceSegments.get(pp).getStartPoint().getY());
//			System.out.println("END");
//			System.out.println(ReferenceSegments.get(pp).getEndPoint().getX());
//			System.out.println(ReferenceSegments.get(pp).getEndPoint().getY());
//		}
//		
//		
////		String s = " ";
////		for (int n = 0; n<pointsCoordinates4.size();n++)
////		{
////			s = s + " point numero  " + n +"\n"+pointsCoordinates4.get(n).get(0)+"@"+pointsCoordinates4.get(n).get(1)+"\n" ;
////		}
////		System.out.println(s);
//
//		
//		
//		
//		
//		
//		
//		
//		
//		
//
//		 
//		  
//		  
//	
//	}
//
//}
