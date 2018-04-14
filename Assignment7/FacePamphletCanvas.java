/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		if(label != null) remove(label);
		label = new GLabel(msg);
		label.setFont(MESSAGE_FONT);
		double x = (this.getWidth() - label.getWidth()) / 2;
		double y = this.getHeight() - BOTTOM_MESSAGE_MARGIN - label.getAscent();
		add(label,x,y);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		
		String name = profile.getName();
		String status = profile.getStatus();
		GImage image = profile.getImage();
		
		displayProfileName(name);
		
		drawPictureBorder();
		drawProfilePicture(image);
		
		drawProfileStatus(name, status);
	}
	
	private void displayProfileName(String name) {
		GLabel label = new GLabel(name);
		label.setFont(PROFILE_NAME_FONT);
		add(label, LEFT_MARGIN, TOP_MARGIN);
	}
	
	private void drawPictureBorder() {
		GRect rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
		add(rect, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
	}
	
	private void drawProfilePicture(GImage image) {
		if(image != null) {
			image.setBounds(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
		}
	}
	
	private void drawProfileStatus(String name, String status) {
		String text = "";
		if(status.equals("")) {
			text = "No current status";
		}else {
			text = name + " is " + status;
		}
		GLabel label = new GLabel(text);
		label.setFont(PROFILE_STATUS_FONT);
		
		double x = LEFT_MARGIN;
		double y = STATUS_MARGIN + TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT;
		add(label, x, y);
	}
	
	private GLabel label;
	
}
