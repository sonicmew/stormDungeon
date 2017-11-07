package com.giventime.stormcoming.core.renderers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.giventime.stormcoming.core.StormComing;
import com.giventime.stormcoming.core.dungeon.CellContent;
import com.giventime.stormcoming.core.dungeon.StormDungeonCell;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

public class StormDungeonCellRenderer extends Renderer<StormDungeonCell> {

	private BitmapFont font = new BitmapFont();

	@Override
	public void render(SpriteBatch batch, DisplayOrder displayOrder, StormDungeonCell cell) {
//		batch.begin();
//		displayInfo(batch);
		if (!cell.isEmpty()) {
			List<CellContent> contents = cell.getCellContents(displayOrder);
			if (contents != null) {
				for (CellContent content : contents) {
					if (content != null) {
						if (content instanceof Renderable) {
							((Renderable) content).render(batch, displayOrder);
						}
					}
				}
			}
		}
//		batch.end();
	}

	/**
	 * 
	 * @param batch
	 */
	private void displayInfo(SpriteBatch batch) {
		int i = 0;
		for (String message : StormComing.GAME_MESSAGES) {
			font.draw(batch, message, 400, 60 - i);
			i += 20;
		}
	}

}
