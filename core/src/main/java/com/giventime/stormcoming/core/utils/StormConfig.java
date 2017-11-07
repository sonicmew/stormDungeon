package com.giventime.stormcoming.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.giventime.stormcoming.core.dungeon.CellContent;
import com.giventime.stormcoming.core.dungeon.StormDungeonItem;
import com.giventime.stormcoming.core.dungeon.StormDungeonLoot;
import com.giventime.stormcoming.core.units.Ability;
import com.giventime.stormcoming.core.units.StormItem;

public class StormConfig {
	
	public static int COL_SIZE = 40;
	public static int ROW_SIZE = 40;
	
	private static List<Ability> warriorAbilities;
	private static List<Ability> thiefAbilities;
	private static List<Ability> sorcererAbilities;
	private static List<Ability> orcAbilities;
	private static List<Ability> trollAbilities;

	public enum DisplayOrder {
		ORDER_0(0),
		ORDER_1(1),
		ORDER_2(2),
		ORDER_3(3);
		
		private int displayOrder;
		
		private DisplayOrder(int displayOrder) {
			this.displayOrder = displayOrder;
		}
		
		public int getDisplayOrder() {
			return this.displayOrder;
		}
	}
	
	public enum HeroClass {
		WARRIOR(20, 14, 14, 10, 20, StormConfig.getWarriorAbilities()),
		THIEF(12, 22, 18, 12, 14, StormConfig.getThiefAbilities());
		
		private int damage;
		private int accuracy;
		private int avoidance;
		private int resistance;
		private int health;
		private List<Ability> abilities;
		
		HeroClass(int damage, int critical, int avoidance, int resistance, int health, List<Ability> abilities) {
			this.damage = damage;
			this.accuracy = critical;
			this.avoidance = avoidance;
			this.resistance = resistance;
			this.health = health;
			this.abilities = abilities;
		}

		public int getDamage() {
			return damage;
		}

		public int getAccuracy() {
			return accuracy;
		}

		/**
		 * @return the avoidance
		 */
		public int getAvoidance() {
			return avoidance;
		}

		public int getResistance() {
			return resistance;
		}

		public int getHealth() {
			return health;
		}
		
		public List<Ability> getAbilities() {
			return abilities;
		}
	}
	
	public enum EnemyClass {
		SORCERER(10, 12, 14, 24, 14, getSorcererAbilities()),
		ORC(18, 14, 14, 10, 18, getOrcAbilities()),
		TROLL(12, 22, 18, 12, 14, getTrollAbilities());
		
		private int damage;
		private int accuracy;
		private int avoidance;
		private int resistance;
		private int health;
		private List<Ability> abilities;
		
		EnemyClass(int damage, int critical, int avoidance, int resistance, int health, List<Ability> abilities) {
			this.damage = damage;
			this.accuracy = critical;
			this.resistance = resistance;
			this.avoidance = avoidance;
			this.health = health;
			this.abilities = abilities;
		}

		public int getDamage() {
			return damage;
		}

		public int getAccuracy() {
			return accuracy;
		}
		
		public int getAvoidance() {
			return avoidance;
		}

		public int getResistance() {
			return resistance;
		}

		public int getHealth() {
			return health;
		}
		
		public List<Ability> getAbilities() {
			return abilities;
		}
	}
	
	public enum ContentType {
		WARRIOR("warriorTransparent.png", 30, 35, 35),
		THIEF("thiefTransparent.png", 30, 35, 35),
		MAGE("mageTransparent.png", 30, 35, 35),
		SORCERER("mageTransparent.png", 30, 35, 35),
		ORC("orcTransparent.png", 30, 35, 35),
//		MONSTER("monsterTransparent.png", 30, 35, 35),
		FLOOR("dungeonFloor.jpg", 40, 40, 40),
		WALL("wallBrown.png", 40, 40, 40),
		TREASURE("treasure.png", 40, 40, 40);
		
		private final String texturePath;
		private final Texture texture;
		private final int size;
		private final int xOffset;
		private final int yOffset;
		
		/**
		 * 
		 * @param texturePath
		 * @param size
		 * @param xOffset
		 * @param yOffset
		 */
		ContentType(String texturePath, int size, int xOffset, int yOffset) {
			this.texturePath = texturePath;
			this.texture = new Texture(Gdx.files.internal(texturePath));
			this.size = size;
			this.xOffset = xOffset;
			this.yOffset = yOffset;
		}

		public String getTexturePath() {
			return texturePath;
		}
		
		public Texture getTexture() {
			return texture;
		}

		public int getSize() {
			return size;
		}

		public int getXOffset() {
			return xOffset;
		}

		public int getYOffset() {
			return yOffset;
		}
		
	}
	
	public static CellContent getFloor() {
		return new StormDungeonItem(ContentType.FLOOR, DisplayOrder.ORDER_0);
	}
	
	public static CellContent getDungeonWall() {
		return new StormDungeonItem(ContentType.WALL, DisplayOrder.ORDER_1);
	}
	
	public static CellContent getTreasure() {
		return new StormDungeonLoot(ContentType.TREASURE, DisplayOrder.ORDER_2, getRandomLoot());
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<StormItem> getRandomLoot() {
		List<StormItem> loot = new ArrayList<StormItem>();
		StormItem goonsFang = new StormItem("Goon's Fang");
		goonsFang.setDamage(10);
		goonsFang.setAccuracy(5);
		loot.add(goonsFang);
		return loot;
	}
	
	/**
	 * 
	 * @return
	 */
	private static List<Ability> getWarriorAbilities() {
		if (warriorAbilities == null) {
			warriorAbilities = new ArrayList<Ability>();
			warriorAbilities.add(new Ability("Hack", 12, 1));
			warriorAbilities.add(new Ability("Smash", 18, 1.2));
		}
		return warriorAbilities;
	}
	
	/**
	 * 
	 * @return
	 */
	private static List<Ability> getThiefAbilities() {
		if (thiefAbilities == null) {
			thiefAbilities = new ArrayList<Ability>();
			thiefAbilities.add(new Ability("Stab", 10, 1));
			thiefAbilities.add(new Ability("BackStab", 15, 2));
		}
		return thiefAbilities;
	}
	/**
	 * 
	 * @return
	 */
	private static List<Ability> getSorcererAbilities() {
		if (sorcererAbilities == null) {
			sorcererAbilities = new ArrayList<Ability>();
			sorcererAbilities.add(new Ability("Magic wand", 8, 1));
			sorcererAbilities.add(new Ability("Firebolt", 25, 1));
		}
		return sorcererAbilities;
	}
	/**
	 * 
	 * @return
	 */
	private static List<Ability> getOrcAbilities() {
		if (orcAbilities == null) {
			orcAbilities = new ArrayList<Ability>();
			orcAbilities.add(new Ability("Slash", 10, 1.2));
			orcAbilities.add(new Ability("Hack", 12, 1.3));
		}
		return orcAbilities;
	}
	
	/**
	 * 
	 * @return
	 */
	private static List<Ability> getTrollAbilities() {
		if (trollAbilities == null) {
			trollAbilities = new ArrayList<Ability>();
			trollAbilities.add(new Ability("Spear jab", 12, 1));
			trollAbilities.add(new Ability("Spear thrust", 18, 1.7));
		}
		return trollAbilities;
	}
	
}
