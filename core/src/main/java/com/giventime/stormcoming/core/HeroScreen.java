/**
 * 
 */
package com.giventime.stormcoming.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.giventime.stormcoming.core.units.StormHero;

/**
 * @author ANDROUTA
 *
 */
public class HeroScreen extends BaseScreen implements Screen {

	private StormHero hero;
	
	public HeroScreen(StormComing game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, StormComing.WIDTH, StormComing.HEIGHT);
		spriteBatch = new SpriteBatch();
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float arg0) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        spriteBatch.begin();
//        spriteBatch.draw(splash, 0, 0);
//        spriteBatch.end();
        
        if(Gdx.input.justTouched()) {
        	game.goToGameScreen();
        }
        
        
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the hero
	 */
	public StormHero getHero() {
		return hero;
	}

	/**
	 * @param hero the hero to set
	 */
	public void setHero(StormHero hero) {
		this.hero = hero;
	}

}
