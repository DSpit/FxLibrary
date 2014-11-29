package com.dspit.fx.main;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.dspit.fx.nav.NavPane;
import com.dspit.fx.nav.SideTab;

public class MainFrameTester extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setScene(new SideTab(new NavPane(), null));
		primaryStage.setTitle("Test");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
