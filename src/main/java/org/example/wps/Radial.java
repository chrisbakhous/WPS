package org.example.wps;
import java.util.ArrayList;


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

	private  ArrayList<Point> GetRadPoints(Segment segment,Double distance)
	{
		ArrayList<Point> radPoints = new ArrayList<Point>();
		
		Point firstPoint = new Point(0.0, 0.0);
		Point secondPoint = new Point(0.0, 0.0);
		Double slope = segment.Slope();
		Double radSlope = -1/slope; //slope de la radiale (segment perpendiculaire)
		System.out.println("Slope = " + slope);	
		if(slope == 0) //segment parallèle à l'axe des abscisses 
		{
			System.out.println("segment parallele à l'axe des abscisses ");
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
		else if ((slope == Double.NEGATIVE_INFINITY) || (slope == Double.POSITIVE_INFINITY) ) //segment parallele à l'axe des ordonnees
		{
			System.out.println("segment parallele à l'axe des ordonnees");
			if(segment.getStartPoint().getY()>segment.getEndPoint().getY())
			{
				System.out.println("getStartPoint().getY > getEndPoint().getY");
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
				System.out.println("getEndPoint().getY > getStartPoint().getY ");
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
			System.out.println("Segment 3ième cas");
				if( segment.getStartPoint().getX() > segment.getEndPoint().getX())
				{
					System.out.println("1");
					firstPoint.setX(-Math.sqrt((Math.pow(distance,2))/(Math.pow(slope, 2)+1))+segment.getStartPoint().getX());
					firstPoint.setY(-slope*(Math.sqrt((Math.pow(distance, 2))/(Math.pow(slope, 2)+1)))+segment.getStartPoint().getY());
					radPoints.add(firstPoint);
					
					 if (segment.getStartPoint().getY() < segment.getEndPoint().getY())
					 {
						 //System.out.println("1.1");
						 if(this.sens == true)
						 {			
							//System.out.println("1.1.1");
							secondPoint.setX(Math.sqrt((Math.pow(longueur,2))/(Math.pow(radSlope, 2)+1))+firstPoint.getX());
							secondPoint.setY(firstPoint.getY()+(radSlope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(radSlope, 2)+1)))));	
						 }
						 else 
						 {
							//System.out.println("1.1.2");
							secondPoint.setX(firstPoint.getX()-Math.sqrt((Math.pow(longueur,2))/(Math.pow(radSlope, 2)+1)));
							secondPoint.setY(firstPoint.getY()-(radSlope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(radSlope, 2)+1)))));
						 }				
					 }
					 else 
					 {
						 //System.out.println("1.2");
						 if(this.sens == false)
						 {			
							//System.out.println("1.2.1");
							secondPoint.setX(Math.sqrt((Math.pow(longueur,2))/(Math.pow(radSlope, 2)+1))+firstPoint.getX());
							secondPoint.setY(firstPoint.getY()+(radSlope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(radSlope, 2)+1)))));	
						 }
						 else 
						 {
							System.out.println("1.2.2");
							secondPoint.setX(firstPoint.getX()-Math.sqrt((Math.pow(longueur,2))/(Math.pow(radSlope, 2)+1)));
							secondPoint.setY(firstPoint.getY()-(radSlope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(radSlope, 2)+1)))));
						 }			
					 }										
					radPoints.add(secondPoint);
				}
				else
				{
					//System.out.println("2");
					firstPoint.setX(Math.sqrt((Math.pow(distance,2))/(Math.pow(slope, 2)+1))+segment.getStartPoint().getX());					
					firstPoint.setY(slope*(Math.sqrt((Math.pow(distance, 2))/(Math.pow(slope, 2)+1)))+segment.getStartPoint().getY());
					radPoints.add(firstPoint);
					
					if (segment.getStartPoint().getY() > segment.getEndPoint().getY())
					 {
						System.out.println("2.1");
						 if(this.sens == false)
						 {			
							//System.out.println("2.1.1");
							secondPoint.setX(Math.sqrt((Math.pow(longueur,2))/(Math.pow(radSlope, 2)+1))+firstPoint.getX());
							secondPoint.setY(firstPoint.getY()+(radSlope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(radSlope, 2)+1)))));	
						 }
						 else 
						 {
							//System.out.println("2.1.2");
							secondPoint.setX(firstPoint.getX()-Math.sqrt((Math.pow(longueur,2))/(Math.pow(radSlope, 2)+1)));
							secondPoint.setY(firstPoint.getY()-(radSlope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(radSlope, 2)+1)))));
						 }				
					 }
					 else 
					 {
						 System.out.println("2.2");
						 if(this.sens == true)
						 {			
							//System.out.println("2.2.1");
							secondPoint.setX(Math.sqrt((Math.pow(longueur,2))/(Math.pow(radSlope, 2)+1))+firstPoint.getX());
							secondPoint.setY(firstPoint.getY()+(radSlope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(radSlope, 2)+1)))));	
						 }
						 else 
						 {
							//System.out.println("2.2.2");
							secondPoint.setX(firstPoint.getX()-Math.sqrt((Math.pow(longueur,2))/(Math.pow(radSlope, 2)+1)));
							secondPoint.setY(firstPoint.getY()-(radSlope*(Math.sqrt((Math.pow(longueur, 2))/(Math.pow(radSlope, 2)+1)))));
						 }			
					 }				
					radPoints.add(secondPoint);
				}
		}
		
		return radPoints;
	}
	
	public ArrayList<Segment> RadialsCoordinates (Line ref)
	{
		System.out.println("Initialisation des variable");
		Double pasTemp = this.pas ;
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
			
//			System.out.println("Segment numéro " + i);
//			System.out.println("		d - > " + d);
//			System.out.println("		pasTemp - > " + pasTemp);
			Segment s = ref.getSegments().get(i);
//			System.out.println("		information du segment " );
//			System.out.print("       			Premier point  :"); s.getStartPoint().DisplayCoordinates();
//			System.out.print("       			Dexuieme point  :"); s.getEndPoint().DisplayCoordinates();
		
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

}
