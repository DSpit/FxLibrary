package com.dspit.fx.nav.resources;

import java.awt.Dimension;

public interface SideTabRes {
	
	public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	public static final Dimension HEADER_DEFAULT_SIZE = new Dimension(
										(int)Math.round(DEFAULT_SIZE.getWidth()), 
										75);
	public static final Dimension MENU_DEFAULT_SIZE = new Dimension(
											(int)Math.round((1/6)*DEFAULT_SIZE.getWidth()),
											(int)Math.round(DEFAULT_SIZE.getHeight() - 
											HEADER_DEFAULT_SIZE.getHeight()));

}
