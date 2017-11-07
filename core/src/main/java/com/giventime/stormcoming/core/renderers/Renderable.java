package com.giventime.stormcoming.core.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

public interface Renderable {

	public void render(SpriteBatch batch, DisplayOrder displayOrder);
	
}
