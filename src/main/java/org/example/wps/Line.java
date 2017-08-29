package org.example.wps;
import java.util.ArrayList;

public class Line {
	
	private  ArrayList<Segment> segmentsList; 
	
	public Line ( ArrayList<Segment> segmentsList)
	{
		this.segmentsList = segmentsList ;
	}
	
	public int segmentsNumber()
	{
		return segmentsList.size();
	}
	
	
	public Double lineLength () //Cette méthode calcule la somme des longeurs des segments formant la ligne
	{
		Double res = 0.0;
		for(int i=0;i<this.segmentsList.size();i++)
		{
			res = res + segmentsList.get(i).segmentLength();
		}
		return res;		
	}
	
	public ArrayList<Segment> getSegments() {
		return segmentsList;
	}

	public void setSegments(ArrayList<Segment> segmentsList) {
		this.segmentsList = segmentsList;
	}
	
}
