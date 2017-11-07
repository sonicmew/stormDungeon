package com.giventime.stormcoming.core.units;

import java.util.List;

import com.giventime.stormcoming.core.StormComing;
import com.giventime.stormcoming.core.dungeon.CellContent;
import com.giventime.stormcoming.core.dungeon.StormDungeonLoot;
import com.giventime.stormcoming.core.fights.FightResult;
import com.giventime.stormcoming.core.utils.StormConfig.ContentType;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;
import com.giventime.stormcoming.core.utils.StormConfig.HeroClass;

public class StormHero extends StormUnit {

	private DisplayOrder displayOrder;
	private HeroClass heroClass;
	private int experience;
	
	/**
	 * 
	 * @param name
	 * @param heroClass
	 * @param contentType
	 */
	public StormHero(String name, HeroClass heroClass, ContentType contentType) {
		super(name, contentType);
		this.heroClass = heroClass;
		this.displayOrder = DisplayOrder.ORDER_3;
		this.fullHitPoints();
		experience = 1;
	}

	public void fullHitPoints() {
		float foo = 1+(getLvl()/10f);
		hitPoints = Math.round(heroClass.getHealth()*10*foo);
	}
	
	/**
	 * 
	 */
	public void reset() {
		this.fullHitPoints();
		dead = false;
	}
	
	/**
	 * 
	 * @return
	 */
	public HeroClass getHeroClass() {
		return heroClass;
	}
	
	/**
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * @param experience the experience to set
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public void search() {
		List<CellContent> contents = getHolderCell().getCellContents(DisplayOrder.ORDER_2);
		if (contents != null && !contents.isEmpty()) {
			for (CellContent content : contents) {
				if (content instanceof StormDungeonLoot) {
					StormDungeonLoot loot = (StormDungeonLoot) content;
					for (StormItem item : loot.getLootItems()) {
						StormComing.addMessage("Hero "+getName()+" looted a "+item.getItemName());
						this.getInventory().addItem(item);						
					}
					this.getHolderCell().removeCellContent(content);
				}
			}
		}
	}
	
	@Override
	public int performAttacks(StormUnit enemy) {
		int damageDealt = 0;
		for (Ability ability : heroClass.getAbilities()) {
			StormComing.addMessage("Hero "+getName()+" casts "+ability.getName());
			damageDealt += enemy.receiveDamage(calculateDamage(ability.getDamage(), ability.getCoefficient()), heroClass.getAccuracy());
		}
		return damageDealt;
	}

	/*
	 * (non-Javadoc)
	 * @see com.giventime.stormcoming.units.StormUnit#calculateDamage(int, double)
	 */
	@Override
	public int calculateDamage(int damage, double coefficient) {
		int critical = (int) ((Math.round(Math.random()*(heroClass.getAccuracy()+getItemAccuracy())))*(getLvl()*1.1));
		System.out.println("Hero "+getName()+" gained "+critical+" extra damage due to a well placed strike!");
		return (int) (damage+critical+((heroClass.getDamage()+getItemDamage())*coefficient));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.giventime.stormcoming.units.StormUnit#calculateResistances()
	 */
	@Override
	public int calculateResistances() {
		return (int) (heroClass.getResistance()*0.5*getLvl());
	}

	/*
	 * (non-Javadoc)
	 * @see com.giventime.stormcoming.units.StormUnit#evade(int)
	 */
	@Override
	public boolean evade(int accuracy) {
		int evade = (int) Math.round(Math.random()*heroClass.getAvoidance())*(getLvl()/2);
		int toHit = (int) Math.round(Math.random()*accuracy);		
		return evade > toHit;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.giventime.stormcoming.units.StormUnit#victory(com.giventime.stormcoming.fights.FightResult)
	 */
	@Override
	public void victory(FightResult result) {
		StormComing.addMessage(getName()+" is victorious!");
		int experienceGained = (result.getFallen().getLvl()/2)*10;
		int newExperience = getExperience()+experienceGained;
		System.out.println("calculating experience..."+newExperience);		
		while (newExperience > getLvl()*10) {
			int expToLevel = getLvl()*10;
			System.out.println("Woot, we gained a lvl!");
			newExperience -= expToLevel;
			setLvl(getLvl()+1);
			fullHitPoints();
			StormComing.addMessage(getName()+" has reached a new level: "+getLvl());
			
		}
		StormComing.addMessage(getName()+" gained "+experienceGained+" experience!");
		setExperience(experienceGained);
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

	private int getItemDamage() {
		int dmg = 0;
		for (StormItem i : getInventory().getItems()) {
			dmg += i.getDamage();
		}
		return dmg;
	}
	
	private int getItemAccuracy() {
		int accuracy = 0;
		for (StormItem i : getInventory().getItems()) {
			accuracy += i.getAccuracy();
		}
		return accuracy;
	}
}
