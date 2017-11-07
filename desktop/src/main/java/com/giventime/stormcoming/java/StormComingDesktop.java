package com.giventime.stormcoming.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.giventime.stormcoming.core.StormComing;

public class StormComingDesktop {

	public static void main(String[] argv) {
	    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	    cfg.title = "Storm Coming";
	    cfg.useGL30 = true;
	    cfg.width = 600;
	    cfg.height = 600;
	    new LwjglApplication(new StormComing(), cfg);
	}
	
}
