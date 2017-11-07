/**
 * 
 */
package com.giventime.stormcoming.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author ANDROUTA
 *
 */
public class SplashScreen extends BaseScreen implements Screen {

	private Texture splash;
	
    public SplashScreen(StormComing game) {
    	this.game = game;
    }
    
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(splash, 0, 0);
        spriteBatch.end();
        
        if(Gdx.input.justTouched()) {
        	if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
        		game.setGameScreen(new GameScreen(game));
        		game.goToGameScreen();        		
        	} else if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
        		game.goToTestScreen();       		
        	}
        }
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
        splash = new Texture(Gdx.files.internal("splash.jpg"));		
	}

}
