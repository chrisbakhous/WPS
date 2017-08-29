package org.example.wps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.ws.Endpoint;

public class Radial {
	
	private Double longueur;
	private Double pas  ;
	private Boolean sens  ;

	public Radial(Double pas, Double longueur,Boolean sens ) {
		this.pas = pas;
		this.longueur = longueur;
		this.sens = sens ;
	}

	public Double getPas() {
		return longueur;
	}

	public void setPas(Double pas) {
		this.longueur = pas;
	} 
	

	public Double getLongueur() {
		return longueur;
	}

	public void setLongueur(Double longueur) {
		this.longueur = longueur;
	}

	public Boolean getSens() {
		return sens;
	}

	public void setSens(Boolean sens) {
		this.sens = sens;
	}

	public  ArrayList<Point> GetRadPoints(Segment segment,Double distance)
	{
		ArrayList<Point> radPoints = new ArrayList<Point>();
		
		Point firstPoint = new Point(0.0, 0.0);
		Point secondPoint = new Point(0.0, 0.0);
		Double slope = segment.Slope();
		System.out.println("Slope --- > " + slope);
		
		
		if(slope == 0) //Segment parallèle à l'axe des abscisses
		{
			if(segment.getStartPoint().getX()<segment.getEndPoint().getX())
			{
				firstPoint.setX(segment.getStartPoint().getX()+distance);
				firstPoint.setY(segment.getEndPoint().getY());
				radPoints.add(firstPoint);
				if(this.sens == false)
				{
					secondPoint.setX(firstPoint.getX());
					secondPoint.setY(firstPoint.getY()+this.longueur);
				}
				else
				{
					secondPoint.setX(firstPoint.getX());
					secondPoint.setY(firstPoint.getY()-this.longueur);
				}
			}
			else
			{
				firstPoint.setX(segment.getStartPoint().getX()-distance);
				firstPoint.setY(segment.getEndPoint().getY());
				radPoints.add(firstPoint);
				if(this.sens == false)
				{
					secondPoint.setX(firstPoint.getX());
					secondPoint.setY(firstPoint.getY()+this.longueur);
				}
				else
				{
					secondPoint.setX(firstPoint.getX());
					secondPoint.setY(firstPoint.getY()-this.longueur);
				}
			}
			
			
			radPoints.add(secondPoint);
		}
		else if ((slope == Double.NEGATIVE_INFINITY) || (slope == Double.POSITIVE_INFINITY) ) //Segments parallèle à l'ordonné
		{
			System.out.println("slope == Double.NEGATIVE_INFINITY");
			if(segment.getStartPoint().getY()>segment.getEndPoint().getY())
			{
				firstPoint.setY(segment.getStartPoint().getY()-distance);
				firstPoint.setX(segment.getEndPoint().getX());
				radPoints.add(firstPoint);
				if(this.sens == false)
				{
					secondPoint.setY(firstPoint.getY());
					secondPoint.setX(firstPoint.getX()+this.longueur);
				}
				else
				{
					secondPoint.setY(firstPoint.getY());
					secondPoint.setX(firstPoint.getX()-this.longueur);
				}
			}
			else
			{
				firstPoint.setY(segment.getStartPoint().getY()+distance);
				firstPoint.setX(segment.getEndPoint().getX());
				radPoints.add(firstPoint);
				if(this.sens == false)
				{
					secondPoint.setY(firstPoint.getY());
					secondPoint.setX(firstPoint.getX()+this.longueur);
				}
				else
				{
					secondPoint.setY(firstPoint.getY());
					secondPoint.setX(firstPoint.getX()-this.longueur);
				}
			}
			
			
			radPoints.add(secondPoint);
			
		}
		else
		{
			//(1) pr.y-startPoint.y = slope*(pr.x-startPoint.x); equation du segment 
			//(2) Math.pow((pr.y-startPoint.y ), 2)+Math.pow((pr.x-startPoint.x ),2) = distance*distance ; la distance entre le point du début de segement et le point de la radial		
			//Les coordonnées du point de la radiale doit satisfaire les deux equations 
			//De 1) 
			//pr.y = startPoint.y+slope*(pr.x-startPoint.x); En remplacant pr.y dans l'équation (2) on obtient : 
				if(segment.getStartPoint().getX() > segment.getEndPoint().getX())//27/08/2017
				{
					firstPoint.setX(-Math.sqrt((Math.pow(distance,2))/(Math.pow(slope, 2)+1))+segment.getStartPoint().getX());
					//OU firstPoint.setX(distance/Math.sqrt((Math.pow(slope, 2))+1)+segment.getStartPoint().getX());
					firstPoint.setY(-slope*(Math.sqrt((Math.pow(distance, 2))/(Math.pow(slope, 2)+1)))+segment.getStartPoint().getY());
					//OU firstPoint.setY(slope*(firstPoint.getX()-segment.getStartPoint().getX())+segment.getStartPoint().getY());
				}
				else
				{
					firstPoint.setX(Math.sqrt((Math.pow(distance,2))/(Math.pow(slope, 2)+1))+segment.getStartPoint().getX());					
					//OU firstPoint.setX(distance/Math.sqrt((Math.pow(slope, 2))+1)+segment.getStartPoint().getX());
					firstPoint.setY(slope*(Math.sqrt((Math.pow(distance, 2))/(Math.pow(slope, 2)+1)))+segment.getStartPoint().getY());
					//OU firstPoint.setY(slope*(firstPoint.getX()-segment.getStartPoint().getX())+segment.getStartPoint().getY());
				}
				
		


			radPoints.add(firstPoint);
			slope = -1/slope;
			boolean b =this.sens;
			if (slope<0) b  = !this.sens;
			if(b == false)
			{
				
				secondPoint.setX(Math.sqrt((Math.pow(longueur,2))/(Math.pow(slope, 2)+1))+firstPoint.getX());
				secondPoint.setY(firstPoint.getY()+(slope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(slope, 2)+1)))));
			}
			else 
			{
				secondPoint.setX(firstPoint.getX()-Math.sqrt((Math.pow(longueur,2))/(Math.pow(slope, 2)+1)));
				secondPoint.setY(firstPoint.getY()-(slope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(slope, 2)+1)))));
			}
			
			radPoints.add(secondPoint);
		}
		
		return radPoints;
	}

	
//	public ArrayList<Point> RadialsFirstPointCoordinates (Line ref)
//	{
//		System.out.println("Initialisation des variable");
//		Double pasTemp = this.pas ;
//		ArrayList<Point> res = new ArrayList<Point>();
//		Point startPoint = ref.getSegments().get(0).getStartPoint(); 
//		res.add(startPoint);
//		Double d = ref.getSegments().get(0).segmentLength();
//		System.out.print("point du départ  : ");
//		startPoint.DisplayCoordinates();
//		System.out.println("d - > " + d);
//		System.out.println("pasTemp - > " + pasTemp);
//		System.out.println("*** Positionnement des radiales sur les segments de la ligne de référence ***");
//		for(int i=0 ; i<ref.segmentsNumber() ; i++)
//		{
//			d = ref.getSegments().get(i).segmentLength();
//			
//			System.out.println("Segment numéro " + i);
//			System.out.println("		d - > " + d);
//			System.out.println("		pasTemp - > " + pasTemp);
//			Segment s = ref.getSegments().get(i);
//			System.out.println("		information du segment " );
//			System.out.print("       			Premier point  :"); s.getStartPoint().DisplayCoordinates();
//			System.out.print("       			Dexuieme point  :"); s.getEndPoint().DisplayCoordinates();
//			
//
//	
//			while (pasTemp<=d) 
//			{
//				System.out.println("		La radiale est sur le segment en cours");
//				startPoint = this.GetRadPoints(s, pasTemp).get(0); //le point de départ 
//				System.out.print("		le nouveau point de départ/le point de la radiale ");
//				startPoint.DisplayCoordinates();
//				System.out.println("		pasTemp ->" + pasTemp);
//				System.out.println("		d ->" + d);
//			
//				res.add(startPoint); 
//				d = Util.distance(startPoint,s.getEndPoint());
//				System.out.println("		le nouveau d est : " + d);
//				pasTemp = this.pas ;
//				Point SegmentEndPoint = s.getEndPoint();
//				s = new Segment(startPoint,SegmentEndPoint);
//				System.out.println("		information du sous Segment " );
//				System.out.print("       			Premier point  :"); s.getStartPoint().DisplayCoordinates();
//				System.out.print("       			Dexuieme point  :"); s.getEndPoint().DisplayCoordinates();
//				
//				//System.out.println("longeur ? d " + Util.distance(s.getEndPoint(), s.getStartPoint()));
//				//ArrayList<Segment> cloneRef = ref.getSegments();
//				//cloneRef.remove(i);
//				//Line newRef = new Line(cloneRef);
//				//this.RadialsFirstPointCoordinates(newRef);
//				
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//					
//			}
//
//			if(pasTemp>d)
//			{
//					System.out.println("		La radiale est sur le segment suivant");
//					pasTemp = Math.abs(pasTemp - d) ;
//					System.out.println("		nouveau pas - > " + pasTemp );
//					
//			}			
//
//		}
//		return res;
//			
//		
//		
//	}
//	
	
	public ArrayList<Segment> RadialsFirstPointCoordinates (Line ref)
	{
		System.out.println("Initialisation des variable");
		Double pasTemp = this.pas ;
		//ArrayList<Point> res = new ArrayList<Point>();
		ArrayList<Segment> res = new ArrayList<Segment>();
		Point startPoint = ref.getSegments().get(0).getStartPoint(); 
		Segment firstSeg = ref.getSegments().get(0); //premier segment de la ref
		Point SecondePointRad = this.GetRadPoints(firstSeg, 0.0).get(1);
		Segment segRad = new Segment( this.GetRadPoints(firstSeg, 0.0).get(0),  this.GetRadPoints(firstSeg, 0.0).get(1));
		res.add(segRad);
		Double d = ref.getSegments().get(0).segmentLength();
		System.out.print("point du départ  : ");
		startPoint.DisplayCoordinates();
		System.out.println("d - > " + d);
		System.out.println("pasTemp - > " + pasTemp);
		System.out.println("*** Positionnement des radiales sur les segments de la ligne de référence ***");
		for(int i=0 ; i<ref.segmentsNumber() ; i++)
		{
			d = ref.getSegments().get(i).segmentLength();
			
			System.out.println("Segment numéro " + i);
			System.out.println("		d - > " + d);
			System.out.println("		pasTemp - > " + pasTemp);
			Segment s = ref.getSegments().get(i);
			System.out.println("		information du segment " );
			System.out.print("       			Premier point  :"); s.getStartPoint().DisplayCoordinates();
			System.out.print("       			Dexuieme point  :"); s.getEndPoint().DisplayCoordinates();
			

	
			while (pasTemp<=d) 
			{
				System.out.println("		La radiale est sur le segment en cours");
				startPoint = this.GetRadPoints(s, pasTemp).get(0); //le point de départ 
				SecondePointRad = this.GetRadPoints(s, pasTemp).get(1);
				Segment segRad2 = new Segment(startPoint, SecondePointRad);
				System.out.print("		le nouveau point de départ/le point de la radiale ");
				startPoint.DisplayCoordinates();
				System.out.println("		pasTemp ->" + pasTemp);
				System.out.println("		d ->" + d);
			
				res.add(segRad2); 
				d = Util.distance(startPoint,s.getEndPoint());
				System.out.println("		le nouveau d est : " + d);
				pasTemp = this.pas ;
				Point SegmentEndPoint = s.getEndPoint();
				s = new Segment(startPoint,SegmentEndPoint);
				System.out.println("		information du sous Segment " );
				System.out.print("       			Premier point  :"); s.getStartPoint().DisplayCoordinates();
				System.out.print("       			Dexuieme point  :"); s.getEndPoint().DisplayCoordinates();
				
				//System.out.println("longeur ? d " + Util.distance(s.getEndPoint(), s.getStartPoint()));
				//ArrayList<Segment> cloneRef = ref.getSegments();
				//cloneRef.remove(i);
				//Line newRef = new Line(cloneRef);
				//this.RadialsFirstPointCoordinates(newRef);
				
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
					
			}

			if(pasTemp>d)
			{
					System.out.println("		La radiale est sur le segment suivant");
					pasTemp = Math.abs(pasTemp - d) ;
					System.out.println("		nouveau pas - > " + pasTemp );
					
			}			

		}
		return res;
			
		
		
	}
	
	
	

	
//	public void PointsRadiale(Line l) //une radiale sur chaque segment 
//	{
//		ArrayList<Point> linePoints = new ArrayList<Point>();
//		linePoints = l.getCoordonnees();
//		ArrayList<lineInfo> lf = l.informations();
//		Iterator<lineInfo> it = lf.iterator();
//		int cptSeg = 0;
//		int numSeg = lf.size();
//		while(it.hasNext())
//		{
//			lineInfo lineInfo = it.next();
//			Line segment = lineInfo.segment;
//			Double longeurDuSegEnCours = Line.distance(segment.segments.get(0), segment.segments.get(1));
//
//			if(this.pas<=longeurDuSegEnCours)
//			{
//				System.out.println("le point radiale est sur le segment");
//				//this.RadialCoordinate(startPoint, endPoint, pas)
//				ArrayList<Point> newLinePoints = (ArrayList<Point>) linePoints.subList(cptSeg+1,numSeg);
//				Line newLine = new Line(newLinePoints);
//				this.PointsRadiale(newLine);
//				cptSeg ++;
//			}
//			else 
//			{
//				System.out.println("le point radiale est sur le segment suivant");
//				Double newPas = pas - lineInfo.longeurSegement;
//				//ArrayList<Point> newLinePoints = linePoints.subList(arg0, arg1)
//				cptSeg++;
//			}
//			
//		}
//		
//		
//	}

}
