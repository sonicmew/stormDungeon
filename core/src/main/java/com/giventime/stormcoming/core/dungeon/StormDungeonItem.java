package com.giventime.stormcoming.core.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.giventime.stormcoming.core.renderers.Renderable;
import com.giventime.stormcoming.core.utils.StormConfig.ContentType;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

public class StormDungeonItem implements CellContent, Renderable {

	private DisplayOrder displayOrder = DisplayOrder.ORDER_0;
	private final ContentType contentType;
	private StormDungeonCell holderCell;
	
	public StormDungeonItem(ContentType contentType, DisplayOrder displayOrder) {
		this.contentType = contentType;
		this.displayOrder = displayOrder;
	}
	
	public StormDungeonItem(ContentType contentType) {
		this.contentType = contentType;
	}

	/**
	 * 
	 * @return
	 */
	public StormDungeonCell getHolderCell() {
		return holderCell;
	}
	
	@Override
	public void setHolderCell(StormDungeonCell holderCell) {
		this.holderCell = holderCell;
	}

	@Override
	public ContentType getCellContentType() {
		return contentType;
	}

	/**
	 * @return the contentLevel
	 */
	@Override
	public DisplayOrder getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param contentLevel the contentLevel to set
	 */
	public void setDisplayOrder(DisplayOrder displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Override
	public void render(SpriteBatch batch, DisplayOrder displayOrder) {
		batch.begin();
		batch.draw(getCellContentType().getTexture(), 
				getCellContentType().getXOffset() - getCellContentType().getSize() + (getHolderCell().getDungeonCoordinates().getColumn() * 40), 
				getCellContentType().getYOffset() - getCellContentType().getSize() + (getHolderCell().getDungeonCoordinates().getRow() * 40));
		batch.end();
	}

}
