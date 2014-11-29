package com.dspit.fx.nav;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import com.dspit.nav.Navigatable.NavNode;

public class NavPane extends Pane implements NavNode {
	
	Image mIcon = new Image("src/com/dspit/fx/nav/resources/Temp_Icon.png");

	@Override
	public String getTitle() {
		return this.getTitle();
	}

	@Override
	public Image getIcon() {
		return mIcon;
	}

	@Override
	public void setTitle(String title) {
		this.setTitle(title);

	}

	@Override
	public void setIcon(Image iconImage) {
		mIcon = iconImage;
	}

}
