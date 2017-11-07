/**
 * 
 */
package com.giventime.stormcoming.core.units;

/**
 * @author ANDROUTA
 *
 */
public class Ability {

	private String name;
	private int damage;
	private double coefficient;
	
	/**
	 * Constructor 
	 * @param name
	 * @param coefficient
	 */
	public Ability(String name, int damage, double coefficient) {
		this.name = name;
		this.damage = damage;
		this.coefficient = coefficient;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the coefficient
	 */
	public double getCoefficient() {
		return coefficient;
	}
	
	/**
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	
}
