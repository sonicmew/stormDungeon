package com.giventime.stormcoming.core.units;

import com.giventime.stormcoming.core.StormComing;
import com.giventime.stormcoming.core.utils.StormConfig.ContentType;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;
import com.giventime.stormcoming.core.utils.StormConfig.EnemyClass;

public class StormEnemy extends StormUnit {

	private DisplayOrder displayOrder; 
	private EnemyClass enemyClass;
	
	public StormEnemy(String name, EnemyClass enemyClass, ContentType contentType) {
		super(name, contentType);
		this.enemyClass = enemyClass;
		this.displayOrder = DisplayOrder.ORDER_3;
		hitPoints = enemyClass.getHealth()*10;
	}
	
	public void reset() {
		hitPoints = enemyClass.getHealth()*10;
		dead = false;
	}

	public EnemyClass getEnemyClass() {
		return enemyClass;
	}

	@Override
	public int performAttacks(StormUnit enemy) {
		int damageDealt = 0;
		for (Ability ability : enemyClass.getAbilities()) {
			StormComing.addMessage(getName()+" casts "+ability.getName());
			damageDealt += enemy.receiveDamage(calculateDamage(ability.getDamage(), ability.getCoefficient()), enemyClass.getAccuracy());
		}
		return damageDealt;
	}

	@Override
	public int calculateDamage(int damage, double coefficient) {
		return (int) (damage+(Math.random()*enemyClass.getDamage()*coefficient));
	}
	
	@Override
	public int calculateResistances() {
		return (int) (enemyClass.getResistance()*0.3);
	}

	@Override
	public boolean evade(int accuracy) {
		int evade = (int) Math.round(Math.random()*enemyClass.getAvoidance())*(getLvl()/2);
		int toHit = (int) Math.round(Math.random()*accuracy);		
		return evade > toHit;
	}

	/**
	 * @return the displayOrder
	 */
	public DisplayOrder getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(DisplayOrder displayOrder) {
		this.displayOrder = displayOrder;
	}
	
}
