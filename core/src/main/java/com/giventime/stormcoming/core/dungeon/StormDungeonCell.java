package com.giventime.stormcoming.core.dungeon;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.giventime.stormcoming.core.fights.Fight;
import com.giventime.stormcoming.core.renderers.Renderable;
import com.giventime.stormcoming.core.renderers.RendererProvider;
import com.giventime.stormcoming.core.units.StormEnemy;
import com.giventime.stormcoming.core.units.StormHero;
import com.giventime.stormcoming.core.units.StormUnit;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;


public class StormDungeonCell implements Renderable {
	
	private CellContents cellContents;
	private StormDungeon dungeon;
	private DungeonCoordinates dungeonCoordinates;

	/**
	 * 
	 * @param dungeon
	 * @param row
	 * @param column
	 */
	public StormDungeonCell(StormDungeon dungeon, int row, int column) {		
		this.dungeonCoordinates = new DungeonCoordinates(row, column);
		this.dungeon = dungeon;
		cellContents = new CellContents();
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 */
	public StormDungeonCell(int row, int column) {
		this.dungeonCoordinates = new DungeonCoordinates(row, column);
		cellContents = new CellContents();
	}
	
	@Override
	public void render(SpriteBatch batch, DisplayOrder displayOrder) {		
		RendererProvider.getSDCRenderer().render(batch, displayOrder, this);
	}
	
	/**
	 * 
	 * @return
	 */
	public CellContents getCellContents() {
		return cellContents;
	}
	
	public List<CellContent> getCellContents(DisplayOrder displayOrder) {
		return cellContents.getContents(displayOrder);
	}
	
	/**
	 * 
	 * @param toAdd
	 */
	public void addCellContent(CellContent toAdd) {		
		getCellContents().add(toAdd);
		toAdd.setHolderCell(this);
	}
	
	public void clearCellContent() {
		getCellContents().clear();
	}
	
	public void removeCellContent(CellContent toRemove) {		
		getCellContents().getAllContents().remove(toRemove);
	}
	
	public boolean isEmpty() {
		return getCellContents().getAllContents().isEmpty();
	}
	
	public StormDungeon getDungeon() {
		return dungeon;
	}
	
	public DungeonCoordinates getDungeonCoordinates() {
		return dungeonCoordinates;
	}

	
	/**
	 * 
	 * @return
	 */
	public boolean containsUnit() {
		boolean contains = false;
		List<CellContent> contents = getCellContents().getAllContents();
		for (CellContent content : contents) {
			if (content instanceof StormUnit) {
				System.out.println("Found unit in cell: "+((StormUnit)content).getName());
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	/**
	 * Returns true if a StormHero and a StormEnemy are found in the same cell.
	 * @return true if a StormHero and a StormEnemy are found in the same cell.
	 */
	public boolean isInConflict() {
		boolean heroFound = false;
		boolean enemyFound = false;
		List<CellContent> contents = getCellContents().getAllContents();
		for (CellContent content : contents) {
			if (content instanceof StormHero) {
				heroFound = true;
			}
			if (content instanceof StormEnemy) {
				enemyFound = true;
			}
		}
		return heroFound && enemyFound;
	}
	
	/**
	 * Returns a FightPremise object contains all the necessary info 
	 * for performing a fight between opposing units on the same tile.
	 * @return
	 */
	public Fight getFight() {
		StormUnit unitA = null;
		StormUnit unitB = null;
		List<CellContent> contents = getCellContents().getAllContents();
		for (CellContent content : contents) {
			if (content instanceof StormHero) {
				unitA = (StormHero) content;
			}
			if (content instanceof StormEnemy) {
				unitB = (StormEnemy) content;
			}
		}
		return new Fight(unitA, unitB);
	}
}
