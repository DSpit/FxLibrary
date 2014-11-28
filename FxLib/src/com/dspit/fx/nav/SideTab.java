package com.dspit.fx.nav;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import com.dspit.nav.Navigatable.NavNode;
import com.dspit.nav.NavigationAdapter;

public class SideTab extends Scene {

	
// Members ----------------------------------------------------------------- //
	
	private NavigationAdapter mNav;
	private MenuList mMenu;
	private Header mHeader;
	private Pane mContent;
	
// Constructor ------------------------------------------------------------- //
	
	public SideTab(NavNode home, ArrayList<NavNode> content){
		super(new AnchorPane());
		
		
		
		mNav = new NavigationAdapter(home, content);
	}
	
// Private Inner Classes --------------------------------------------------- //
	
	private class MenuList extends HBox{
		
	//Constructor ---------------------------------------------------------- //
		
		public MenuList(ArrayList<NavNode> content){
			
		}
		
		
		// Private Inner Classes ------------------------------------------- //
		
		private class MenuButton extends Label{
			
		}
	}
	
	private class Header extends 
}


