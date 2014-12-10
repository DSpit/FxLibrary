package com.dspit.fx.nav;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import com.dspit.fx.nav.resources.SideTabRes;
import com.dspit.nav.Navigatable.NavNode;
import com.dspit.nav.Navigation;

public class SideTab extends Scene implements Observer{

	
// Members ----------------------------------------------------------------- //
	
	private Navigation mNav;
	private MenuList mMenu;
	private Header mHeader;
	private Pane mContent;
	
// Constructor ------------------------------------------------------------- //
	
	public SideTab(NavNode home, ArrayList<NavNode> content){
		super(new AnchorPane());
		
		this.getStylesheets().add(SideTabRes.class.getResource("menu-default.css")
				.toExternalForm());
		
		AnchorPane root = (AnchorPane)this.getRoot();
		
		//setup the navigation
		mNav = new Navigation(home, content);
		mNav.addObserver(this);
		
		mMenu = new MenuList();
		mHeader = new Header(home.getTitle());
		mContent = (Pane)mNav.getHome();
		
		//set fixed locations
		AnchorPane.setRightAnchor(mHeader, 0.0);
		AnchorPane.setTopAnchor(mHeader, 0.0);
		AnchorPane.setLeftAnchor(mHeader, 0.0);
		AnchorPane.setTopAnchor(mMenu, 0.0);
		AnchorPane.setLeftAnchor(mMenu, 0.0);
		AnchorPane.setBottomAnchor(mMenu, 0.0);
		AnchorPane.setTopAnchor(mContent, mHeader.getPrefHeight());
		AnchorPane.setLeftAnchor(mContent, mMenu.getPrefWidth());
		AnchorPane.setRightAnchor(mContent, 0.0);
		AnchorPane.setBottomAnchor(mContent, 0.0);


		//add the content
		root.getChildren().addAll(mHeader, mMenu, mContent);
	}
	
// Private Methods --------------------------------------------------------- //
	
	/**
	 * Updates every component of the of the navigation scheme to the correct
	 * value based on what is selected.
	 */
	private void changeContent(){
		
		((AnchorPane)this.getRoot()).getChildren().remove(mContent);
		
		mContent = (Pane)mNav.getCurrentNode();
		
		AnchorPane.setTopAnchor(mContent, mHeader.getPrefHeight());
		AnchorPane.setLeftAnchor(mContent, mMenu.getPrefWidth());
		AnchorPane.setRightAnchor(mContent, 0.0);
		AnchorPane.setBottomAnchor(mContent, 0.0);
		
		((AnchorPane)this.getRoot()).getChildren().add(mContent);
		
		mHeader.setTitle(((NavNode)mContent).getTitle());
		mMenu.update();
	}
	
// Overrides --------------------------------------------------------------- //
	
	/**
	 * Method which changes the current Content Pane to the one that 
	 * is currently in focus, according to the navigation module.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o == mNav){
			this.changeContent();
		}
	}
	
// Private Inner Classes --------------------------------------------------- //
	
	private class Header extends VBox{
		
		
		public static final double HEIGHT = 60.0;
		
		
		Label mTitle;
		
		public Header(String title){
			super();
			
			this.setPrefHeight(HEIGHT);
			
			mTitle = new Label(title);
			this.setId("header");
			
			this.getChildren().add(mTitle);
		}
		
		public void setTitle(String text){
			mTitle.setText(text);
		}
	}
	
	
	
	private class MenuList extends VBox{
		
		
		public static final double CONTENT_SIZE = 60.0;
		
		
		private MenuButton mSelected;
		
		
		public MenuList(){
			super();
			
			this.setId("menu-list");

			
			//add home to list
			this.getChildren().add(new MenuButton(mNav.getHome()));
			
			this.setPrefWidth(((MenuButton)this.getChildren().get(0)).getPrefWidth());
			
			//Add nodes to list
			for(NavNode n : mNav.getContent()){
				this.getChildren().add(new MenuButton(n));
			}
			
			//set the starting position
			this.update();
			
		}
		
		/**
		 * this method should be called every time the user selects 
		 * a new node in the menu
		 */
		public void update(){
			
			MenuButton menuItem = this.getMenuItem(mNav.getCurrentNode());
			
			//checks if this is the first time this method is called (in constructor)
			if(mSelected == null && menuItem!= null){
				mSelected = menuItem;
				mSelected.setSelected(true);
			}
			
			//assign new selection if the node exists within the menu and is 
			//not already selected
			if(menuItem != null && !this.isSelected(menuItem)){
				//change the selected node back to normal
				mSelected.setSelected(false);
				
				//change selected 
				mSelected = menuItem;
				
				//set selected to selected xP
				mSelected.setSelected(true);
				
				
			}
		}
		
		public MenuButton getMenuItem(NavNode nNode){
		
			for(Node n : this.getChildren()){
				if(((MenuButton)n).getBase().equals(nNode)){
					return (MenuButton)n;
				}
			}
		
			return null;
		}
	
		public boolean isSelected(MenuButton b){
			if(b.equals(mSelected)){
				return true;
		}
		
		return false;
	}
		
		
	// Private Inner Inner Classes ----------------------------------------- //
		
		private class MenuButton extends ToggleButton{
			
			private NavNode mBase;
			
			public MenuButton(NavNode n){
				super();
				
				this.setPrefHeight(CONTENT_SIZE);
				this.setPrefWidth(CONTENT_SIZE);
				this.setOnAction(new MenuButtonHandler());
				
				this.setId("menu-item");
				mBase = n;
				Image i = new Image(n.getIcon());
				
				this.setGraphic(new ImageView(i));
				this.setTooltip(new Tooltip(n.getTitle()));
			}
			
			public NavNode getBase(){
				return mBase;
			}
			
			private class MenuButtonHandler implements 
											EventHandler<ActionEvent>{

				@Override
				public void handle(ActionEvent event) {

					if(mNav.getCurrentNode().equals(
								((MenuButton)event.getSource()).getBase())){
						((MenuButton)event.getSource()).setSelected(true);
					}else{
						mNav.nav(((MenuButton)event.getSource()).getBase());
					}
				}
			}
		}
	}
}


