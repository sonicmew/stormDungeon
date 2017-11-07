package com.giventime.stormcoming.core.dungeon;

import com.giventime.stormcoming.core.utils.StormConfig.ContentType;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

public interface CellContent {
	
	public DisplayOrder getDisplayOrder();
	
	public ContentType getCellContentType();
	
	public void setHolderCell(StormDungeonCell holderCell);
	
}
