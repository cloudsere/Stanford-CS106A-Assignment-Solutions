/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		nameInGraph.clear();
		nameColor.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public Boolean addEntry(NameSurferEntry entry) {
		int index = indexOfNameSurfer(entry.getName());
		/* Prevent from adding a same name multiple times */
		if(index < 0) {
			nameInGraph.add(entry);
			nameColor.add(generateColor());
			return true;
		}
		return false;
	}
	
	public void deleteEntry(NameSurferEntry entry) {
		int index = indexOfNameSurfer(entry.getName());
		nameInGraph.remove(index);
		nameColor.remove(index);
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		this.removeAll();
		drawLinesAndLabels(this.getWidth(), this.getHeight());
		drawNameSurferEntries();
	}
	
	private int indexOfNameSurfer(String name) {
		for(int i = 0; i< nameInGraph.size(); i++) {
			if(name.equals(nameInGraph.get(i).getName())) {
				return i;
			}
		}
		return -1;
	}
	
	/* Keep track of color of each name. 
	 * Using last color in the nameColor arraylist to generate a new one.*/
	private Color generateColor() {
		int length = nameColor.size();
		if(length == 0) return colorArray[0];
		
		Color lastColor = nameColor.get(length-1);
		int lastColorIndex = 0;
		for(int i = 0; i<colorArray.length; i++) {
			if(lastColor == colorArray[i]) {
				lastColorIndex = i;
				break;
			}
		}
		Color curColor = colorArray[(lastColorIndex+1)%3];
		return curColor;
	}
	
	private void drawNameSurferEntries() {
		for(int i = 0; i<nameInGraph.size(); i++) {
			NameSurferEntry curName = nameInGraph.get(i);
			Color curColor = nameColor.get(i);
			drawOneNameOnCanvas(curName,curColor);
		}
	}
	
	/* Takes in a NameSurferEntry and draws it on the canvas */
	private void drawOneNameOnCanvas(NameSurferEntry entry, Color color) {
		double width = this.getWidth();
		double height = this.getHeight();
		
		String name = entry.getName();
		for(int i = 0; i< NDECADES-1; i++) {
			int leftRank = entry.getRank(i);
			double[] leftCoordinate = rankToCoordinate(i, leftRank, width, height);
			
			int rightRank = entry.getRank(i+1);
			double[] rightCoordinate = rankToCoordinate(i+1, rightRank, width, height);
			
			drawNameLabel(leftRank, name, leftCoordinate, color, rightCoordinate);
			drawNameCircle(leftCoordinate, color);
			drawConnectLine(leftCoordinate, rightCoordinate, color);
		}
		
		int lastRank = entry.getRank(NDECADES-1);
		double[] lastCoordinate = rankToCoordinate(NDECADES-1, lastRank, width, height);
		drawNameLabel(lastRank, name, lastCoordinate, color, null);
	}
	
	private void drawNameCircle(double[] point, Color color) {
		double x = point[0] - RADIUS_LABEL_CIRCLE;
		double y = point[1] - RADIUS_LABEL_CIRCLE;
		GOval oval = new GOval(2*RADIUS_LABEL_CIRCLE, 2* RADIUS_LABEL_CIRCLE);
		oval.setColor(color);
		oval.setFilled(true);
		add(oval, x, y);
	}
	
	private void drawNameLabel(int rank, String name, double[] point, Color color, double[] nextPoint) {
		GLabel label;
		if(rank == 0) {
			label = new GLabel(name + " *");
		}else {
			label = new GLabel(name + " " + rank);
		}
		label.setColor(color);
		
		Boolean up;
		if(nextPoint != null && nextPoint[1] < point[1] && rank!=0) {
			up = true;
		}else {
			up = false;
		}
		double labelX = point[0];
		double labelY;
		if(up) {
			labelY = 2 * label.getAscent() + point[1];
		}else {
			labelY = label.getHeight() - 2 * label.getAscent() + point[1];
		}
		add(label, labelX, labelY);
	}
	
	/* Convert rank of each decade to coordinate of each point.
	 * decade is 0-based index, 0 stands for 1900, 1 stands for 1901 and so on */
	private double[] rankToCoordinate(int decade, int rank, double width, double height) {
		/* Coordinate is a two-elements array, x coordinate is stored at index 0, y coordinate is stored at index 1 */
		double[] coordinate = new double[2];
		
		double lineHeight = height - 2 * GRAPH_MARGIN_SIZE;
		double interval = (double)width / (NDECADES + 1);
		
		coordinate[0] = interval * (decade + 1);
		if(rank == 0) {
			coordinate[1] = lineHeight + GRAPH_MARGIN_SIZE;
		}else {
			coordinate[1] = rank * lineHeight / 1000;
		}
		return coordinate;
	}
	
	/* Draw connect line between two adjacent points. */
	private void drawConnectLine(double[] startPoint, double[] endPoint, Color color) {
		double startX = startPoint[0];
		double startY = startPoint[1];
		
		double endX = endPoint[0];
		double endY = endPoint[1];
		
		GLine line = new GLine();
		line.setStartPoint(startX,  startY);
		line.setEndPoint(endX, endY);
		line.setColor(color);
		add(line);
	}
	
	private void drawLinesAndLabels(int width, int height) {
		double endPointY = height - GRAPH_MARGIN_SIZE;

		double interval = (double)width / (NDECADES + 1);
		for(int i = 0; i < NDECADES; i++) {
			double startPointX = interval * (i + 1);
			double startPointY = GRAPH_MARGIN_SIZE;
			
			GLine line = new GLine();
			line.setStartPoint(startPointX, startPointY);
			line.setEndPoint(startPointX, endPointY);
			add(line);
			
			int decade = 1900 + i*10;
			GLabel label = new GLabel(decade + " ");
			add(label, startPointX, height-DECADE_LABEL_MARGIN_SIZE);
		}
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	/* Stores all the entries drown on the canvas, remove all the entries if clear() is called*/
	private ArrayList<NameSurferEntry> nameInGraph = new ArrayList<NameSurferEntry>();
	
	private Color[] colorArray = new Color[] {Color.BLACK, Color.RED, Color.BLUE};
	private ArrayList<Color> nameColor = new ArrayList<Color>();
}
