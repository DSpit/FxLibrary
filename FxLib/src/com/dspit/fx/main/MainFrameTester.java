package com.dspit.fx.main;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

import com.dspit.fx.nav.SideTab;
import com.dspit.fx.nav.TestNode;
import com.dspit.nav.Navigatable.NavNode;

public class MainFrameTester extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		
		ArrayList<NavNode> list = new ArrayList<NavNode>();
		list.add(new TestNode("Content Text", "black"));
		
		primaryStage.setScene(new SideTab(new TestNode("Test", "orange"), list));
		primaryStage.setTitle("Test");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
