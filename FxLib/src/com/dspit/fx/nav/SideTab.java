package com.dspit.fx.nav;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
	
	public SideTab(NavNode home, ArrayList<NavNode> content){
		super(new AnchorPane());
		
		mMenu = new MenuList();
		
		
		
		mNav = new Navigation(home, content);
	}
	
// Private Inner Classes --------------------------------------------------- //
	
	private class MenuList extends HBox{
		
	//Constructor ---------------------------------------------------------- //
		
		public MenuList(){
			
		}
		
		
		// Private Inner Classes ------------------------------------------- //
		
		private class MenuButton extends Label{
			
			public MenuButton(String title){
				super(title);
				
				
			}
		}
	}
	
	private class Header extends VBox{
		
	}
}


