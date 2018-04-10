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
		
		JLabel deleteLabel = new JLabel("Click on Name to Remove Line:");
		this.add(deleteLabel,SOUTH);
		
		addActionListeners();
	}
	
	private void loadData() {
		dataBase = new NameSurferDataBase("names-data.txt");
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(e.getSource() == textSearchField || cmd.equals("Graph")){
			String searchTerm = textSearchField.getText();
			NameSurferEntry entry = dataBase.findEntry(searchTerm);
			if(entry != null && mainGraph.addEntry(entry)) {
				mainGraph.update();
				addTag(entry);
			}
			return;
		  }
		
		if(cmd.equals("Clear")) {
			mainGraph.clear();
			mainGraph.update();
			return;
		}
		
		NameSurferEntry cmdEntry = dataBase.findEntry(cmd);
		if(cmdEntry != null) {
			mainGraph.deleteEntry(cmdEntry);
			mainGraph.update();
			removeTag(e);
		}
	}
	
	/* Question here: have to update the whole window, such as resize the window to see the newly added button */
	private void addTag(NameSurferEntry entry) {
		JButton btn = new JButton(entry.getName());
		add(btn, SOUTH);
		btn.addActionListener(this);
		this.revalidate();
		this.repaint();
	}
	
	private void removeTag(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		btn.getParent().remove(btn);
		this.revalidate();
		this.repaint();
	}
	
	private NameSurferGraph mainGraph;
	private JTextField textSearchField;
	private NameSurferDataBase dataBase;
}
