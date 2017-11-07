package com.giventime.stormcoming.core.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.giventime.stormcoming.core.StormComing;
import com.giventime.stormcoming.core.commands.MoveCommand;
import com.giventime.stormcoming.core.dungeon.CellContent;
import com.giventime.stormcoming.core.dungeon.StormDungeonCell;
import com.giventime.stormcoming.core.fights.FightResult;
import com.giventime.stormcoming.core.renderers.Renderable;
import com.giventime.stormcoming.core.renderers.RendererProvider;
import com.giventime.stormcoming.core.utils.StormConfig;
import com.giventime.stormcoming.core.utils.StormConfig.ContentType;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

public abstract class StormUnit implements CellContent, Moveable, Renderable, Selectable {

	private ContentType contentType;
	private String name;
	private int lvl = 1;
	MoveCommand moveCommand = null;
	protected boolean moving = false;
	protected boolean selected = false;
	protected int hitPoints;
	protected boolean dead = false;
	private StormDungeonCell holderCell;
	private Inventory inventory;
	
	public StormUnit(String name, ContentType contentType) {
		this.name = name;
		this.contentType = contentType;
		this.inventory = new Inventory();
	}
	
	/**
	 * 
	 */
	@Override
	public void render(SpriteBatch batch, DisplayOrder displayOrder) {
		RendererProvider.getSURenderer().render(batch, this);
	}
	
	public int getXPosition() {
		return getCellContentType().getXOffset() - getCellContentType().getSize() + (getHolderCell().getDungeonCoordinates().getColumn() * StormConfig.COL_SIZE);
	}
	
	public int getYPosition() {
		return getCellContentType().getYOffset() - getCellContentType().getSize() + (getHolderCell().getDungeonCoordinates().getRow() * StormConfig.ROW_SIZE);
	}
	
	public boolean isDead() {
		return dead;
	}
	
	/**
	 * 
	 */
	public void die() {
		System.out.println(name+" has died!");
		holderCell.removeCellContent(this);
		dead = true;
	}
	
	/**
	 * 
	 */
	public abstract void reset();
	
	/**
	 * 
	 */
	public void victory(FightResult result) {
		// do nothing, override if needed
	}
	
	@Override
	public void cellLeft() {
		if (holderCell != null) {
			if (holderCell.getDungeonCoordinates().getColumn() == 0) {
				return;
			}			
			move(holderCell.getDungeonCoordinates().getRow(), holderCell.getDungeonCoordinates().getColumn()-1);
		}
	}

	@Override
	public void cellRight() {
		if (holderCell != null) {
			if (holderCell.getDungeonCoordinates().getColumn() == 14) {
				return;
			}
			move(holderCell.getDungeonCoordinates().getRow(), holderCell.getDungeonCoordinates().getColumn()+1);
		}
	}

	@Override
	public void cellDown() {
		if (holderCell != null) {
			if (holderCell.getDungeonCoordinates().getRow() == 0) {
				return;
			}
			move(holderCell.getDungeonCoordinates().getRow()-1, holderCell.getDungeonCoordinates().getColumn());			
		}
	}

	@Override
	public void cellUp() {
		if (holderCell != null) {
			if (holderCell.getDungeonCoordinates().getRow() == 14) {
				return;
			}
			move(holderCell.getDungeonCoordinates().getRow()+1, holderCell.getDungeonCoordinates().getColumn());
		}
	}

	/**
	 * @return the holderCell
	 */
	public StormDungeonCell getHolderCell() {
		return holderCell;
	}

	@Override
	public void setHolderCell(StormDungeonCell holderCell) {
		this.holderCell = holderCell;
	}

	@Override
	public ContentType getCellContentType() {
		return contentType;
	}

	public String getName() {
		return name;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	/**
	 * @return the hitPoints
	 */
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * @param hitPoints the hitPoints to set
	 */
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * 
	 * @param row
	 * @param column
	 */
	private void move(int row, int column) {
		if (moveCommand == null) {
			if (holderCell.getDungeon().isAccessible(row, column)) {
				moving = true;
				moveCommand = new MoveCommand(row, column);				
			}			
		} else {
			System.out.println("Still moving, please wait...");
		}
	}
	
	public void moveComplete(int row, int column) {
		moveCommand = null;
		moving = false;
		holderCell.removeCellContent(this);
		holderCell.getDungeon().addToCell(row, column, this);
	}
	
	@Override
	public boolean isSelected() {
		return selected;
	}
	
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
		
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public MoveCommand getMoveCommand() {
		return moveCommand;
	}
	
	public void search() {
		
	}
	
	public abstract int performAttacks(StormUnit enemy);
	
	public abstract boolean evade(int enemyAccuracy);
	
	public abstract int calculateDamage(int damage, double coefficient); 
	
	public abstract int calculateResistances();
	
	private BitmapFont damageFont = new BitmapFont();
	
	public void displayDamage(int damage, Color color, int xOffset, int yOffset) {
		SpriteBatch spriteBatch = new SpriteBatch();
		spriteBatch.begin();		
		damageFont.setColor(color);		
		damageFont.draw(spriteBatch, "-"+String.valueOf(damage), getXPosition() + xOffset, getYPosition()+yOffset);
		spriteBatch.end();
		spriteBatch.flush();
		spriteBatch.dispose();
	}
	
	/**
	 * 
	 * @param damage
	 * @param accuracy
	 */
	public int receiveDamage(int damage, int accuracy) {
		if (!evade(accuracy)) {
			int resisted = calculateResistances();
			int taken = damage - resisted;			
//			StormComing.addMessage(name+" resisted "+resisted+" points of damage!");
			StormComing.addMessage(name+" received "+taken+" points of damage!");
//			System.out.println(name+" resisted "+resisted+" points of damage!");
			System.out.println(name+" received "+taken+" points of damage!");
			hitPoints -= taken;
			if (hitPoints <= 0) {
				die();
			}
			return damage;
		} else {
			System.out.println(name+" evaded the attack!");
			return 0;
		}
	}

}
