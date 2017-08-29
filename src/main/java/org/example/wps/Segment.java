package org.example.wps;

public class Segment {
	
	private Point startPoint; 
	private Point endPoint;
	
	
	public Segment(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Double Slope ()
	{
		Double m;
		m = (getEndPoint().getY() - getStartPoint().getY()) / (getEndPoint().getX() - getStartPoint().getX()) ;
		return m;
	}
	
	public  Double segmentLength () 
	{		
		return Math.sqrt(Math.pow(getEndPoint().getY()-getStartPoint().getY(),2)+Math.pow(getEndPoint().getX()-getStartPoint().getX(),2));
	}

	public Point getStartPoint() {
		return startPoint;
	}


	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}


	public Point getEndPoint() {
		return endPoint;
	}


	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	

}
