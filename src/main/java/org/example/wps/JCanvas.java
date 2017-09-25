package org.example.wps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class JCanvas  extends JPanel{
	
	private ArrayList<Segment> SegmentsLine;
	private ArrayList<Segment> Radials;
	
	public ArrayList<Segment> getRadials() {
		return Radials;
	}


	public void setRadials(ArrayList<Segment> radials) {
		Radials = radials;
	}


	public JCanvas (ArrayList<Segment> SegmentsLine, ArrayList<Segment> Radials)
	{
		this.SegmentsLine = SegmentsLine;
		this.Radials = Radials;
	}
	
	
	public ArrayList<Segment> getSegmentsLine() {
		return SegmentsLine;
	}


	public void setSegmentsLine(ArrayList<Segment> segmentsLine) {
		SegmentsLine = segmentsLine;
	}


	
	public void paint (Graphics g){ //HEIGHT pour regler le probleme du systeme de coordonnees java 
		super.paint(g);
		ArrayList<Segment> SegmentsLine = this.getSegmentsLine();  
		ArrayList<Segment> Radials = this .getRadials();
		Double x1,x2,y1,y2;
		int zoom = 25 ;
		Integer H = this.getHeight();
		Double HEIGHT = H.doubleValue();
		
		for(int i = 0 ; i<SegmentsLine.size();i++)
		{
			 x1 = SegmentsLine.get(i).getStartPoint().getX()*zoom;//.intValue()*zoom;
			 x2 = SegmentsLine.get(i).getEndPoint().getX()*zoom;//.intValue()*zoom;
			 y1 = HEIGHT - SegmentsLine.get(i).getStartPoint().getY()*zoom;//.intValue()*zoom;
			 y2 = HEIGHT -SegmentsLine.get(i).getEndPoint().getY()*zoom;//.intValue()*zoom;
			 
			 
			 g.setColor(Color.red);
			 g.drawLine((int)Math.round(x1),(int)Math.round(y1),(int)Math.round(x2),(int)Math.round(y2));
			 
		}
		for(int i = 0 ; i<Radials.size();i++)
		{
			 x1 = Radials.get(i).getStartPoint().getX()*zoom;//.intValue()*zoom;
			 x2 = Radials.get(i).getEndPoint().getX()*zoom;//intValue()*zoom;
			 y1 = HEIGHT - Radials.get(i).getStartPoint().getY()*zoom;//.intValue()*zoom;
			 y2 = HEIGHT -Radials.get(i).getEndPoint().getY()*zoom;//.intValue()*zoom;
			 
			 g.setColor(Color.black);
			 g.drawLine((int)Math.round(x1),(int)Math.round(y1),(int)Math.round(x2),(int)Math.round(y2));
			 
			 
			 
		}

		
	}
	



	
	
}
