/**
 * 
 */
package com.giventime.stormcoming.core.fights;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.giventime.stormcoming.core.units.StormUnit;

/**
 * @author ANDROUTA
 *
 */
public class Fight {

	private StormUnit unitA;
	private StormUnit unitB;
	private boolean running = false;
	private boolean complete = false;
	private float stateTime = 0;
	private int damageDealtToA = 0;
	private int damageDealtToB = 0;
	
	public Fight(StormUnit unitA, StormUnit unitB) {
		this.unitA = unitA;
		this.unitB = unitB;
	}	
	
	public boolean run() {
		if (!running) {					
			stateTime = 2; // start immediately
			running = true;
		} else if (running) {
			stateTime += Gdx.graphics.getDeltaTime();
//			System.out.println("stateTime: "+stateTime);
			if (stateTime > 2) {
				damageDealtToA = 0;
				damageDealtToB = 0;
				damageDealtToB = unitA.performAttacks(unitB);
				if (unitB.isDead()) {										
					unitA.victory(new FightResult(unitA, unitB));
					running = false;
					complete = true;
				}
				else {
					damageDealtToA = unitB.performAttacks(unitA);
					if (unitA.isDead()) {
						running = false;
						complete = true;
						unitB.victory(new FightResult(unitB, unitA));						
					}
				}
				stateTime = 0;
			}
			if (damageDealtToB > 0) {
				unitB.displayDamage(damageDealtToB, Color.YELLOW, 30, 30);
			}
			if (damageDealtToA > 0) {
				unitA.displayDamage(damageDealtToA, Color.RED, -30, 30);
			}
		}
		return complete;
	}

	public StormUnit getUnitA() {
		return unitA;
	}

	public void setUnitA(StormUnit unitA) {
		this.unitA = unitA;
	}

	public StormUnit getUnitB() {
		return unitB;
	}

	public void setUnitB(StormUnit unitB) {
		this.unitB = unitB;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unitA == null) ? 0 : unitA.hashCode());
		result = prime * result + ((unitB == null) ? 0 : unitB.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fight other = (Fight) obj;
		if (unitA == null) {
			if (other.unitA != null)
				return false;
		} else if (!unitA.equals(other.unitA))
			return false;
		if (unitB == null) {
			if (other.unitB != null)
				return false;
		} else if (!unitB.equals(other.unitB))
			return false;
		return true;
	}
	
}
