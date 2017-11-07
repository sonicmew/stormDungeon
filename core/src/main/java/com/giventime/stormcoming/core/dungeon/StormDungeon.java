package com.giventime.stormcoming.core.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.giventime.stormcoming.core.fights.Fight;
import com.giventime.stormcoming.core.renderers.Renderable;
import com.giventime.stormcoming.core.utils.StormConfig.ContentType;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

/**
 * 
 * @author ANDROUTA
 *
 */
public class StormDungeon implements Renderable {

	private StormDungeonCell[][] dungeonMap;
	private DungeonMapLayout mapLayout;
	private int rows;
	private int columns;
	
	/**
	 * Constructor.
	 * 
	 * @param rows
	 * @param columns
	 */
	public StormDungeon(int rows, int columns, DungeonMapLayout mapLayout) {
		this.rows = rows;
		this.columns = columns;
		this.mapLayout = mapLayout;
		dungeonMap = new StormDungeonCell[rows][columns];
		createDungeonMap();
	}
	
	@Override
	public void render(SpriteBatch batch, DisplayOrder displayOrder) {		
		for (int r=0; r < getRows(); r++) {
			for (int c=0; c < getColumns(); c++) {
				StormDungeonCell cell = getDungeonCell(r, c);
				cell.render(batch, displayOrder);
			}
		}		
	}
	
	/**
	 * 
	 */
	private void createDungeonMap() {
		mapLayout.build(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public StormDungeonCell[][] getDungeonMap() {
		return dungeonMap;
	}
	
	/**
	 * Add the supplied CellContent object to the {@StormDungeonCell} at (row, column).
	 * 
	 * @param row The supplied row value.
	 * @param column The supplied column value.
	 * @param content The CellContent object to add.
	 */
	public void addToCell(int row, int column, CellContent content) {
		getDungeonCell(row, column).addCellContent(content);
		//content.setHolderCell(getDungeonCell(row, column));
	}
	
	/**
	 * 
	 * @param rowStart
	 * @param rowEnd
	 * @param colStart
	 * @param colEnd
	 * @param content
	 */
	public void addToArea(int rowStart, int rowEnd, int colStart, int colEnd, CellContent content) {
		int r = rowStart;
		int c = colStart;
		boolean added = false;
		while (r <= rowEnd && !added) {			
			while (c <= colEnd && !added) {
//				System.out.println("Checking cell at ["+r+"]["+c+"]...");
				if (isAccessible(r, c) && !getDungeonCell(r, c).containsUnit()) {
					if (content.getCellContentType().equals(ContentType.TREASURE)) {
						System.out.println("adding treasure to ["+r+"]["+c+"]...");
					}
					addToCell(r, c, content);
					added = true;
					break;
				} else {
					System.out.println("Inaccessible cell at ["+r+"]["+c+"]...");
				}
				c++;
			}
			r++;
			c = colStart;
		}
	}
	
	/**
	 * Returns true if the StormDungeonCell at (row, column) can be accessed by a {@StormUnit}.
	 * 
	 * @param row The supplied row value.
	 * @param column The supplied column value.
	 * 
	 * @return True if the StormDungeonCell at (row, column) can be accessed.
	 */
	public boolean isAccessible(int row, int column) {
		boolean accessible = true;
		try {
		List<CellContent> contents = getDungeonCell(row, column).getCellContents().getAllContents();
			for (CellContent content : contents) {
				if (ContentType.WALL.equals(content.getCellContentType())) {
					accessible = false;
					break;
				}
			}
		} catch (RuntimeException e) {
			System.out.println("Boom!");
		}
		return accessible;
	}
	
	public void removeFight(String key) {
		fights.remove(key);
	}
	
	public void updateConflicts() {		
		for (int r=0; r < rows; r++) {
			for (int c=0; c < columns; c++) {
				if (getDungeonCell(r, c).isInConflict()) {
					String fightKey = r+""+""+c;
					if (!fights.containsKey(fightKey)) {
						this.fights.put(fightKey, getDungeonCell(r, c).getFight());						
					}
				}
			}
		}		
	}
	
	private Map<String, Fight> fights = new HashMap<String, Fight>();
	
	public Map<String, Fight> getFights() {
		return fights;
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public StormDungeonCell getDungeonCell(int row, int column) {		
		return dungeonMap[row][column];
	}

	/**
	 * 
	 * @param dungeonMap
	 */
	public void setDungeonMap(StormDungeonCell[][] dungeonMap) {
		this.dungeonMap = dungeonMap;
	}

	/**
	 * 
	 * @return
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * 
	 * @param rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 
	 * @return
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * 
	 * @param columns
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * Return the StormDungeonCell for the given x,y position (in pixels, relative to 0,0).
	 * 
	 * @param posX event position x .
	 * @param posY event position y.
	 * 
	 * @return the StormDungeonCell at given position;
	 */
	public StormDungeonCell getCellForPosition(int posX, int posY) {
		int column = posX / 40;
		int row = posY / 40;
		return dungeonMap[row][column];
	}
	
}
