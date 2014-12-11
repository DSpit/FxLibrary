package com.dspit.fx.nav;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import com.dspit.nav.Navigatable.NavNode;

/**
 * A testing node which allows for different colors to be displayed
 * with a test label.
 * 
 * @author David Boivin (Spit)
 */
public class TestNode extends StackPane implements NavNode {
	
	private String mTitle;
	private String mIcon = "com/dspit/fx/nav/resources/Temp_Icon.png";
	
	public TestNode(String title, String c){
		super();
		mTitle = title;
		
		Label l = new Label("This is a test frame");
		
		this.setStyle("-fx-background-color:" + c + ";");
		this.getChildren().add(l);
	}

	@Override
	public String getTitle() {
		return mTitle;
	}

	@Override
	public String getIcon() {
		return mIcon;
	}

	@Override
	public void setTitle(String title) {
		mTitle = title;

	}

	@Override
	public void setIcon(String iconImage) {
		mIcon = iconImage;
	}

}
