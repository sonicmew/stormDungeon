package com.giventime.stormcoming.core.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.giventime.stormcoming.core.utils.StormConfig.ContentType;

public class StormSprite {

	private Texture img;
	private Rectangle rect;
	private int xOffset = 0;
	private int yOffset = 0;
	
	public StormSprite(ContentType contentType) {
		this.img = new Texture(Gdx.files.internal(contentType.getTexturePath()));
		rect = new Rectangle();
		rect.width = contentType.getSize();
		rect.height = contentType.getSize();
		this.xOffset = contentType.getXOffset();
		this.yOffset = contentType.getYOffset();
	}
	
	public StormSprite(String texturePath, int size) {
		this.img = new Texture(Gdx.files.internal(texturePath));
		rect = new Rectangle();		
		rect.width = size;
		rect.height = size;
	}
	
	public StormSprite(String texturePath, int posX, int posY, int size) {
		this.img = new Texture(Gdx.files.internal(texturePath));
		rect = new Rectangle();
		rect.x = posX;
		rect.y = posY;
		rect.width = size;
		rect.height = size;		
	}
	
	public Texture getImg() {
		return img;
	}
	
	public Rectangle getRect() {
		return rect;
	}

	public int getXOffset() {
		return xOffset;
	}

	public void setXOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getYOffset() {
		return yOffset;
	}

	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	
}
