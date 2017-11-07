/**
 * 
 */
package com.giventime.stormcoming.core.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.giventime.stormcoming.core.commands.MoveCommand;
import com.giventime.stormcoming.core.units.StormUnit;

/**
 * @author ANDROUTA
 *
 */
public class StormUnitRenderer extends Renderer<StormUnit> {

	private BitmapFont font = new BitmapFont();
	private ShapeRenderer sr = new ShapeRenderer();
	
	/**
	 * 
	 */
	@Override
	public void render(SpriteBatch spriteBatch, StormUnit renderable) {
		int rowStep = 0;
		int colStep = 0;
		if (renderable.isMoving()) {			
			MoveCommand mc = renderable.getMoveCommand();
			if (mc.isExecuting()) {
				if (mc.isComplete()) {
					renderable.moveComplete(mc.getRow(), mc.getColumn());
				} else {
					float deltaTime = Gdx.graphics.getDeltaTime();
					colStep = mc.getStep(renderable.getHolderCell().getDungeonCoordinates().getColumn(), mc.getColumn(), deltaTime);
					rowStep = mc.getStep(renderable.getHolderCell().getDungeonCoordinates().getRow(), mc.getRow(), deltaTime);			
				}
			} else {
				mc.go();
			}
		}
		int posX = renderable.getXPosition() + colStep;
		int posY = renderable.getYPosition() + rowStep;
		if (renderable.isSelected()) {
			displaySelected(posX, posY);
		}
		spriteBatch.begin();
		spriteBatch.draw(renderable.getCellContentType().getTexture(), posX, posY);
		displayLevel(spriteBatch, String.valueOf(renderable.getLvl()), posX, posY);
		displayHitPoints(spriteBatch, String.valueOf(renderable.getHitPoints()), posX, posY);
		spriteBatch.end();	
	}
	
	private void displaySelected(int posX, int posY) {
		sr.scale(1.0f, 1.0f, 1.0f);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);		
		long randomizer = Math.round(Math.random()*2);
		sr.ellipse(posX, posY, 30+randomizer, 20+randomizer);
		sr.end();
	}
	
	/**
	 * 
	 * @param batch
	 */
	private void displayLevel(SpriteBatch spriteBatch, String lvl, int posX, int posY) {
		font.draw(spriteBatch, lvl, 35 + posX, 35 + posY);
	}
	
	private void displayHitPoints(SpriteBatch spriteBatch, String hitPoints, int posX, int posY) {
		font.draw(spriteBatch, hitPoints, posX - 5, posY);
	}

}
