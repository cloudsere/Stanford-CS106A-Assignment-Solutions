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
		// You fill in the rest //
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		this.removeAll();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		String name = entry.getName();
		
	}
	
	/* decade is 0-based index, 0 stands for 1900 and so on */
	private double[] rankToCoordinate(int decade, int rank, double width, double height) {
		/* A two-elements array, x coordinate is stored at index 0, y coordinate is stored at index 1 */
		double[] coordinate = new double[2];
		
		double lineHeight = height - 2 * GRAPH_MARGIN_SIZE;
		
		double interval = (double)width / (NDECADES + 1);
		coordinate[0] = interval * (decade + 1);
		coordinate[1] = rank * lineHeight / 1000;
		return coordinate;
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		clear();
		drawLinesAndLabels(this.getWidth(), this.getHeight());
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
			
			int decade = 1900 + i;
			GLabel label = new GLabel(decade + " ");
			add(label, startPointX, height-DECADE_LABEL_MARGIN_SIZE);
		}
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
