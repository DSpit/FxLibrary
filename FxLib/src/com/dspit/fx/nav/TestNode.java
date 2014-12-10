package com.dspit.fx.nav;

import javafx.scene.layout.Pane;

import com.dspit.nav.Navigatable.NavNode;

public class TestNode extends Pane implements NavNode {
	
	private String mTitle;
	private String mIcon = "com/dspit/fx/nav/resources/Temp_Icon.png";
	
	public TestNode(String title, String c){
		super();
		mTitle = title;
		
		this.setStyle("-fx-background-color:" + c + ";");
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
