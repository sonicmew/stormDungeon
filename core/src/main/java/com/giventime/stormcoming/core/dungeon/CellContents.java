/**
 * 
 */
package com.giventime.stormcoming.core.dungeon;

import java.util.ArrayList;
import java.util.List;

import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

/**
 * @author ANDROUTA
 *
 */
public class CellContents {

	private List<CellContent> cellContents;
	
	/**
	 * 
	 */
	public CellContents() {
		this.cellContents = new ArrayList<CellContent>();
	}

	/**
	 * @return the cellContents
	 */
	public List<CellContent> getAllContents() {
		return cellContents;
	}
	
	public List<CellContent> getContents(DisplayOrder displayOrder) {
		List<CellContent> selected = new ArrayList<CellContent>();
		for (CellContent c : cellContents) {
			if (c.getDisplayOrder().equals(displayOrder)) {
				selected.add(c);
			}
		}
		return selected;
	}
	
	public void add(CellContent cellContent) {
		cellContents.add(cellContent);
	}
	
	public boolean isEmpty() {
		return cellContents.isEmpty();
	}
	
	public void clear() {
		cellContents.clear();
	}
	
}
