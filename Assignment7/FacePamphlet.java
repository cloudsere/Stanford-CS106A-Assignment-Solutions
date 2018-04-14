/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		prepareInteractor();
		add(profileCanvas);
    }
	
	private void prepareInteractor() {
		JLabel nameLabel = new JLabel("Name:");
		add(nameLabel,NORTH);
		addTextField(nameField, NORTH);
		add(new JButton(addNameCmd),NORTH);
		add(new JButton(deleteNameCmd),NORTH);
		add(new JButton(lookUpNameCmd),NORTH);
		
		addTextField(changeStatus, WEST);
		add(new JButton(changeStatusCmd),WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		addTextField(changePic, WEST);
		add(new JButton(changePictureCmd),WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		addTextField(addFriend, WEST);
		add(new JButton(addFriendCmd),WEST);
		
		addActionListeners();
	}
	
	private void addTextField(JTextField textField, Object constraints) {
		textField.addActionListener(this);
		add(textField, constraints);
	}
	
	private void setProfilePic(String fileName) {
		GImage image = null;
		try {
			image = new GImage(fileName);
			currentProfile.setImage(image);
		}catch(ErrorException ex) {
			throw ex;
		}
	}
	
	private void addFriendToCurrentProfile(String name) {
		if(dataBase.containsProfile(name)) {
			if(currentProfile.addFriend(name)) {
				dataBase.getProfile(name).addFriend(currentProfile.getName());
			}else {
				profileCanvas.showMessage("Friend already exits.");
			}
		}else {
			profileCanvas.showMessage("No profile with the name of " + name);
		}
	}
	
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		Object obj =  e.getSource();
		String name = nameField.getText();
		if(cmd.equals(addNameCmd) && name != null) {
			FacePamphletProfile prf = new FacePamphletProfile(name);
			dataBase.addProfile(prf);
			profileCanvas.displayProfile(prf);
			currentProfile = prf;
			
		}else if(cmd.equals(deleteNameCmd) && name != null) {
			dataBase.deleteProfile(name);
			currentProfile = null;
			
		}else if(cmd.equals(lookUpNameCmd) && name != null) {
			dataBase.containsProfile(name);
			currentProfile = null;
			
		}else if(obj == changeStatus || cmd.equals(changeStatusCmd)) {
			if(currentProfile != null) {
				currentProfile.setStatus(changeStatus.getText());
				profileCanvas.displayProfile(currentProfile);
			}else {
				profileCanvas.showMessage("Please select a profile to change status.");
			}
		}else if(obj == changePic || cmd.equals(changePictureCmd)) {
			String filename = changePic.getText();
			if(currentProfile != null) {
				setProfilePic(filename);
				profileCanvas.displayProfile(currentProfile);
			}else {
				profileCanvas.showMessage("Please select a profile to change picture.");
			}
		}else if(obj == addFriend || cmd.equals(addFriendCmd)) {
			if(currentProfile != null) {
				String friendName = addFriend.getText();
				addFriendToCurrentProfile(friendName);
				profileCanvas.displayProfile(currentProfile);
			}else {
				profileCanvas.showMessage("Please select a profile to add friend.");
			}
		}		
	}
    
    private JTextField nameField = new JTextField(TEXT_FIELD_SIZE);
    private JTextField changeStatus = new JTextField(TEXT_FIELD_SIZE);
    private JTextField changePic = new JTextField(TEXT_FIELD_SIZE);
    private JTextField addFriend = new JTextField(TEXT_FIELD_SIZE);
    
    private final String changeStatusCmd = "Change Status";
    private final String changePictureCmd = "Change Picture";
    private final String addFriendCmd = "Add Friend";
    private final String addNameCmd = "Add";
    private final String deleteNameCmd = "Delete";
    private final String lookUpNameCmd = "Lookup";
    
    private FacePamphletDatabase dataBase = new FacePamphletDatabase();
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas profileCanvas = new FacePamphletCanvas();
}
