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
	
	/**
	 * Constructor which build the entire navigation layout. The {@link NavNode}s
	 * must also extend {@link Pane} for this to work.
	 * 
	 * @param home The home node
	 * @param content The content nodes.
	 */
	public SideTab(NavNode home, ArrayList<NavNode> content){
		super(new AnchorPane());
		
		this.getStylesheets().add(SideTabRes.class.getResource("menu-default.css")
				.toExternalForm());
		
		AnchorPane root = (AnchorPane)this.getRoot();
		
		//setup the navigation
		mNav = new Navigation(home, content);
		mNav.addObserver(this);
		
		//set up the components
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
		
		//remove old pane
		((AnchorPane)this.getRoot()).getChildren().remove(mContent);
		
		//set new pane to content variable
		mContent = (Pane)mNav.getCurrentNode();
		
		//set anchors again
		AnchorPane.setTopAnchor(mContent, mHeader.getPrefHeight());
		AnchorPane.setLeftAnchor(mContent, mMenu.getPrefWidth());
		AnchorPane.setRightAnchor(mContent, 0.0);
		AnchorPane.setBottomAnchor(mContent, 0.0);
		
		//add new node to layout
		((AnchorPane)this.getRoot()).getChildren().add(mContent);
		
		//set the title and update the menu
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
	
	/**
	 * Class which represents the header bar of this navigation scheme.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class Header extends VBox{
		
		public static final double HEIGHT = 60.0;
		
		Label mTitle;
		
		/**
		 * Constructor which builds a basic header for this class
		 * 
		 * @param title The starting title to give this header, usually the home
		 * node's title
		 */
		public Header(String title){
			super();
			
			//set height
			this.setPrefHeight(HEIGHT);
			//set ID
			this.setId("header");
			
			//add label to display title
			mTitle = new Label(title);
			this.getChildren().add(mTitle);
		}
		
		/**
		 * Change the title which is displayed on the header.
		 * 
		 * @param text The test to display on the header bar.
		 */
		public void setTitle(String text){
			mTitle.setText(text);
		}
	}
	
	/**
	 * Class which represents the Menu of this navigation scheme.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class MenuList extends VBox{
		
		public static final double CONTENT_SIZE = 60.0;
		
		private MenuButton mSelected;
		
		/**
		 * Constructor which builds the menu based on the components found in
		 * the Navigation module of this navigation scheme.
		 */
		public MenuList(){
			super();
			
			//set it
			this.setId("menu-list");

			
			//add home to list
			this.getChildren().add(new MenuButton(mNav.getHome()));
			
			//let the width of the element be the same as the size of the home
			//button (a guaranteed element in the navigation scheme)
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
		
		/**
		 * Returns the menu button which corresponds to the give node
		 * 
		 * @param nNode The node to look up
		 * @return The Menu button which is linked to the give node.
		 */
		public MenuButton getMenuItem(NavNode nNode){
		
			//iterate through all the buttons
			for(Node n : this.getChildren()){
				if(((MenuButton)n).getBase().equals(nNode)){
					//end the method and return the menu button if found
					return (MenuButton)n;
				}
			}
		
			//return null if not found
			return null;
		}
	
		/**
		 * Checks whether the menu button is already selected or not.
		 * 
		 * @param b The button to check
		 * @return If the given button is selected or not
		 */
		public boolean isSelected(MenuButton b){
			if(b.equals(mSelected)){
				return true;
		}
		
		return false;
	}
		
		
	// Private Inner Inner Classes ----------------------------------------- //
		
		/**
		 * Class representing the buttons in the menu. These buttons set
		 * the hint as the text of the {@link NavNode} the are linked to and
		 * they set the icon as the main identifier for the button.
		 * 
		 * @author David Boivin (Spit)
		 */
		private class MenuButton extends ToggleButton{
			
			private NavNode mBase;
			
			/**
			 * Constructor which initializes the button and assigns the base 
			 * {@link NavNode} which the button is supposed to represent.
			 * 
			 * @param n The node to assign to this button.
			 */
			public MenuButton(NavNode n){
				super();
				
				//set the preffered size and what to do when clicked
				this.setPrefHeight(CONTENT_SIZE);
				this.setPrefWidth(CONTENT_SIZE);
				this.setOnAction(new MenuButtonHandler());
				
				//set the id and the base
				this.setId("menu-item");
				mBase = n;
				
				//get the image url and build the image
				Image i = new Image(n.getIcon());
				
				//sets the elements of this button to the corresponding elements in the node
				this.setGraphic(new ImageView(i));
				this.setTooltip(new Tooltip(n.getTitle()));
			}
			
			public NavNode getBase(){
				return mBase;
			}
			
			/**
			 * Handler which checks if the button is already "pressed" which means
			 * that the current node is this button and the user has clicked it 
			 * more than once and if not navigates to the new selected node. Which
			 * will then send a notification to all observers updating the entire
			 * navigation scheme.
			 * 
			 * @author David Boivin (Spit)
			 */
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


