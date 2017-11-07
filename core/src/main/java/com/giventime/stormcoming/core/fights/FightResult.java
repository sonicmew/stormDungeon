/**
 * 
 */
package com.giventime.stormcoming.core.fights;

import com.giventime.stormcoming.core.units.StormUnit;

/**
 * @author ANDROUTA
 *
 */
public class FightResult {

	private final StormUnit victor;
	private final StormUnit fallen;
	
	/**
	 * 
	 * @param victor
	 * @param fallen
	 */
	public FightResult(StormUnit victor, StormUnit fallen) {
		this.victor = victor;
		this.fallen = fallen;
	}

	/**
	 * @return the victor
	 */
	public StormUnit getVictor() {
		return victor;
	}

	/**
	 * @return the fallen
	 */
	public StormUnit getFallen() {
		return fallen;
	}
	
}
