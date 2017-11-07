package com.giventime.stormcoming.core.renderers;

import com.giventime.stormcoming.core.dungeon.StormDungeonCell;
import com.giventime.stormcoming.core.units.StormUnit;



public class RendererProvider<T extends Renderable> {

	private static Renderer<StormDungeonCell> sdcRenderer;
	private static Renderer<StormUnit> suRenderer;
	
	private RendererProvider() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static Renderer<StormDungeonCell> getSDCRenderer() {
		if (sdcRenderer == null) {
			sdcRenderer = new StormDungeonCellRenderer();
		}
		return sdcRenderer;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Renderer<StormUnit> getSURenderer() {
		if (suRenderer == null) {
			suRenderer = new StormUnitRenderer();
		}
		return suRenderer;
	}
	
}
