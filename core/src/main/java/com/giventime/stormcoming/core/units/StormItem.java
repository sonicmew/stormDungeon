/**
 * 
 */
package com.giventime.stormcoming.core.units;

/**
 * @author ANDROUTA
 *
 */
public class StormItem {

	private String itemName;
	private int damage = 0;
	private int accuracy = 0;
	private int avoidance = 0;
	private int resistance = 0;
	private int health = 0;

	public StormItem(String itemName) {
		super();
		this.itemName = itemName;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @return the accuracy
	 */
	public int getAccuracy() {
		return accuracy;
	}

	/**
	 * @param accuracy the accuracy to set
	 */
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * @return the avoidance
	 */
	public int getAvoidance() {
		return avoidance;
	}

	/**
	 * @param avoidance the avoidance to set
	 */
	public void setAvoidance(int avoidance) {
		this.avoidance = avoidance;
	}

	/**
	 * @return the resistance
	 */
	public int getResistance() {
		return resistance;
	}

	/**
	 * @param resistance the resistance to set
	 */
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
}
