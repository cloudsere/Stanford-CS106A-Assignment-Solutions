/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
		addMainGraph();
		prepareInteractors();
		loadData();
	}
	
	private void loadData() {
		dataBase = new NameSurferDataBase("names-data.txt");
	}
	
	private void addMainGraph() {
		mainGraph = new NameSurferGraph();
		add(mainGraph);
		
	}
	private void prepareInteractors() {
		JLabel nameLabel = new JLabel("Name:");
		add(nameLabel,NORTH);
		textSearchField= new JTextField(TEXT_FIELD_WIDTH);
		textSearchField.addActionListener(this);
		add(textSearchField,NORTH);
		add(new JButton("Graph"),NORTH);
		add(new JButton("Clear"),NORTH);
		
		addActionListeners();
	}
	

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == textSearchField || e.getActionCommand().equals("Graph")){
			String searchTerm = textSearchField.getText();
			NameSurferEntry entry=  dataBase.findEntry(searchTerm);
			println(entry);
		  }
	}
	
	private NameSurferGraph mainGraph;
	private JTextField textSearchField;
	private NameSurferDataBase dataBase;
}
