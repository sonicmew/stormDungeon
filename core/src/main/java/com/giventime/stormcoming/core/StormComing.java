package com.giventime.stormcoming.core;

import java.util.LinkedList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class StormComing extends Game {
	
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	
	private SplashScreen splashScreen;
	private GameScreen gameScreen;
	private HeroScreen heroScreen;
	private TestScreen testScreen;
	public static LinkedList<String> GAME_MESSAGES = new LinkedList<String>();
	
	@Override
	public void create() {		
		splashScreen = new SplashScreen(this);
		testScreen = new TestScreen(this);
		heroScreen = new HeroScreen(this);
		goToSplashScreen();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {		
		getScreen().render(0);
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	public static void addMessage(String message) {
		if (GAME_MESSAGES.size() > 7) {
			GAME_MESSAGES.removeFirst();
		}
		GAME_MESSAGES.add(message);		
	}
	
	public static void clearMessages() {
		GAME_MESSAGES.clear();
	}

	/**
	 * @return the splash
	 */
	public Screen getSplashScreen() {
		return splashScreen;
	}

	/**
	 * @param splash the splash to set
	 */
	public void setSplashScreen(SplashScreen splash) {
		this.splashScreen = splash;
	}

	/**
	 * @return the game
	 */
	public Screen getGameScreen() {
		return gameScreen;
	}

	/**
	 * @param game the game to set
	 */
	public void setGameScreen(GameScreen game) {
		this.gameScreen = game;
	}
	
	/**
	 * @return the heroScreen
	 */
	public HeroScreen getHeroScreen() {
		return heroScreen;
	}

	/**
	 * @param heroScreen the heroScreen to set
	 */
	public void setHeroScreen(HeroScreen heroScreen) {
		this.heroScreen = heroScreen;
	}

	/**
	 * 
	 */
	public void goToSplashScreen() {
		this.setScreen(splashScreen);
	}
	
	/**
	 * 
	 */
	public void goToGameScreen() {
		this.setScreen(gameScreen);
	}
	
	/**
	 * 
	 */
	public void goToHeroScreen() {
		this.setScreen(heroScreen);
	}
	
	public void goToTestScreen() {
		this.setScreen(testScreen);
	}
}
