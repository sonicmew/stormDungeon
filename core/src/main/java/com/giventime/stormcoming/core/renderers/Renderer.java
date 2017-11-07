/**
 * 
 */
package com.giventime.stormcoming.core.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

/**
 * @author ANDROUTA
 *
 */
public abstract class Renderer<T extends Renderable> {

	public Renderer() {
		
	}
	
	public void render(SpriteBatch spriteBatch, T renderable) {
		
	}
	
	public void render(SpriteBatch spriteBatch, DisplayOrder displayOrder, T renderable) {
		
	}
	
}
