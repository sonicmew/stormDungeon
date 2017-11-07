/**
 * 
 */
package com.giventime.stormcoming.core.units;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ANDROUTA
 *
 */
public class Inventory {

	private List<StormItem> items;
	
	/**
	 * Constructor
	 */
	public Inventory() {
		items = new ArrayList<StormItem>();
	}
	
	public List<StormItem> getItems() {
		return items;
	}
	
	/**
	 * Adds the given item to the inventory.
	 * @param item
	 */
	public void addItem(StormItem item) {
		items.add(item);
	}
	
	/**
	 * Removes the given item from the inventory.
	 * @param item
	 */
	public void removeItem(StormItem item) {
		items.remove(item);
	}
	
}
