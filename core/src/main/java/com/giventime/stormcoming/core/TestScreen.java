/**
 * 
 */
package com.giventime.stormcoming.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author P35010
 *
 */
public class TestScreen extends BaseScreen implements Screen {

	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS = 4, FRAME_ROWS = 1;

	// Objects used
	Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
	Texture walkSheet;
	SpriteBatch spriteBatch;

	// A variable for tracking elapsed time for the animation
	float stateTime;
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	public TestScreen(StormComing game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, StormComing.WIDTH, StormComing.HEIGHT);
		
		walkSheet = new Texture(Gdx.files.internal("kramer_sprites.png"));

		// Use the split utility method to create a 2D array of TextureRegions. This is 
		// possible because this sprite sheet contains frames of equal size and they are 
		// all aligned.
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, 
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);

		// Place the regions into a 1D array in the correct order, starting from the top 
		// left, going across first. The Animation constructor requires a 1D array.
		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}

		// Initialize the Animation with the frame interval and array of frames
		walkAnimation = new Animation<TextureRegion>(0.25f, walkFrames);

		// Instantiate a SpriteBatch for drawing and reset the elapsed animation
		// time to 0
		spriteBatch = new SpriteBatch();
		stateTime = 0f;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.5f, 0.7f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT); // Clear screen
		camera.update();
		
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		
		// Get current frame of animation for the current stateTime
		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 50, 50); // Draw current frame at (50, 50)
		spriteBatch.end();
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
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
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
