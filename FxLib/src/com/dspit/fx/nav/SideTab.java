package com.dspit.fx.nav;

import java.awt.Dimension;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import com.dspit.nav.Navigatable.NavNode;
import com.dspit.nav.Navigation;
import com.dspit.nav.NavigationAdapter;

public class SideTab extends Scene {

	
// Members ----------------------------------------------------------------- //
	
	private NavigationAdapter mNav;
	private MenuList mMenu;
	private Header mHeader;
	private Pane mContent;
	
// Constructor ------------------------------------------------------------- //
	
	public SideTab(NavPane home, ArrayList<NavNode> content){
		super(new AnchorPane());
		
		this.getStylesheets().add(SideTab.class
				.getResource("com/dspit/fx/nav/resources/menuList-default.css")
				.toExternalForm());
		
		AnchorPane root = (AnchorPane)this.getRoot();
		
		mMenu = new MenuList();
		mContent = home;
		
		mNav = new Navigation(home, content);
		

		
		root.getChildren().addAll(mHeader, mMenu, mContent);
	}
	
// Private Inner Classes --------------------------------------------------- //
	
	private class MenuList extends HBox{
		
	//Constructor ---------------------------------------------------------- //
		
		public MenuList(){
			super();
			
			this.setId("menu-list");
			
			this.getStylesheets().add("");
		}
		
		
		// Private Inner Classes ------------------------------------------- //
		
		private class MenuButton extends Button{
			
			public MenuButton(NavNode n, Dimension size){
				super();
				
				this.setId("menu-item");
				ImageView icon = new ImageView(n.getIcon());
				this.setGraphic(icon);
				this.setHeight(size.getHeight());
				this.setWidth(size.getWidth());
				
				
			}
		}
	}
	
	private class Header extends StackPane{
		
	// Members ------------------------------------------------------------- //
		
		Label mTitle;
		Pane mAnimation;
		
		public Header(String title, Pane animation){
			super();
			
			mTitle = new Label(title);
			
			mAnimation = animation;
			
			this.setId("header");
		}
		
		public void setTitle(String text){
			mTitle.setText(text);
		}
	}
}


