/**
 * 
 */
package com.giventime.stormcoming.core.dungeon;

import java.util.ArrayList;
import java.util.List;

import com.giventime.stormcoming.core.units.StormItem;
import com.giventime.stormcoming.core.utils.StormConfig.ContentType;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

/**
 * @author ANDROUTA
 *
 */
public class StormDungeonLoot extends StormDungeonItem {

	private List<StormItem> lootItems = new ArrayList<StormItem>();
	
	/**
	 * @param contentType
	 * @param displayOrder
	 */
	public StormDungeonLoot(ContentType contentType, DisplayOrder displayOrder, List<StormItem> lootItems) {
		super(contentType, displayOrder);
		this.lootItems = lootItems;
	}

	/**
	 * @return the lootItems
	 */
	public List<StormItem> getLootItems() {
		return lootItems;
	}

	/**
	 * @param lootItems the lootItems to set
	 */
	public void setLootItems(List<StormItem> lootItems) {
		this.lootItems = lootItems;
	}

}
