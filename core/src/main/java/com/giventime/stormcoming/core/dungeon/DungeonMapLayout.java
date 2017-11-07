package com.giventime.stormcoming.core.dungeon;
import java.util.List;
import java.util.Random;

import com.giventime.stormcoming.core.utils.StormConfig;
import com.giventime.stormcoming.core.utils.StormConfig.ContentType;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;

/**
 * Class used to build a random map layout.
 * This is essentially a conceptual view of the map,
 * ie it specifies what elements are in which place.
 * 
 * @author ANDROUTA
 *
 */
public class DungeonMapLayout {

	private CellContent[][] mapElements;
	private int wallCount;
	
	/**
	 * Constructor.
	 */
	public DungeonMapLayout() {
		mapElements = new CellContent[15][15];
		wallCount = 15*15;
	}
	
	/**
	 * Receives a StormDungeon object which it populates 
	 * with wall squares, thus creating the layout.
	 * @param dungeon
	 */
	public void build(StormDungeon dungeon) {
		int rows = dungeon.getRows();
		int columns = dungeon.getColumns();
		StormDungeonCell[][] dungeonMap = dungeon.getDungeonMap();
		// fill all empty squares with walls
		for (int r=0; r < rows; r++) {
			for (int c=0; c < columns; c++) {
				StormDungeonCell cell = new StormDungeonCell(dungeon, r, c);
				cell.addCellContent(StormConfig.getDungeonWall());
				dungeonMap[r][c] = cell;
				
				if (mapElements[r][c] != null) {
					CellContent content = mapElements[r][c];
					cell.addCellContent(content);
				}
			}
		}

		// Let's rethink the map generation
		
		// General parameters:
		Random randomizer = new Random();		
		boolean reserveOuterWall = true;
		boolean gopher = true;
		int minCol, maxCol;
		int minRow, maxRow;
		
		//Set the dig limits depending on whether
		// we want a closed outer wall
		if (reserveOuterWall) {
			minCol = 1;
			minRow = 1;
			maxCol = columns-2;
			maxRow = rows	-2;
		} else {
			minCol = 0;
			minRow = 0;
			maxCol = columns-1;
			maxRow = rows	-1;			
		}
		wallCount = maxCol*maxRow;
		
//		System.out.println("wallCount: " + wallCount);
//		System.out.println("rows: "+rows+", columns: "+columns);
//		System.out.println("minRow: "+minRow+", minCol: "+minCol);
//		System.out.println("maxRow: "+maxRow+", maxCol: "+maxCol);		

		// A. Gopher: Run around digging consequent tiles
		if (gopher) {
			
			// Number of walls to leave in the dungeon
			int wallsLeft = (wallCount/3)+0;
			
			// How many steps to take in one direction before considering a turn
			int minSteps = 2;
			int maxSteps = 10;
			
			// start from a random location
			int initialRow		= randomizer.nextInt(maxRow-minRow+1)+minRow;
			int initialColumn	= randomizer.nextInt(maxCol-minCol+1)+minCol;
			
			// initialise some more crap
			int currentRow 		= initialRow;
			int currentColumn	= initialColumn;
			int stepsRemaining	= 1;
			int direction		= 0;			
			
			// Dig only as long as there are more walls left than the target
			while (wallCount > wallsLeft) {
				// Don't dig if you haven't found a wall
				List<CellContent> contents = dungeon.getDungeonCell(currentRow, currentColumn).getCellContents(DisplayOrder.ORDER_1);
				if (contents != null && !contents.isEmpty() && contents.get(0).getCellContentType() == ContentType.WALL) {
//					System.out.println(dungeon.getDungeonCell(currentRow, currentColumn).getCellContent().get(0).getCellContentType());
					gopherDig(dungeon, currentRow, currentColumn);
					wallCount--;					
				}
//				boolean dugTile = false;
				stepsRemaining--;
				
				//If I'm done digging in that direction
				//decide a new heading and number of steps
				if(stepsRemaining==0) {
					direction = randomizer.nextInt(4);
					stepsRemaining = randomizer.nextInt(maxSteps-minSteps+1)+minSteps;
				}
				
				System.out.println("Direction: " + direction + ", steps remaining: " + stepsRemaining);				

//				while (!dugTile) {					
					if (direction == 0)	{
						if (currentRow > minRow) {
							currentRow--;
//							dugTile = true;
						}
						else {
							currentRow = initialRow;
							currentColumn = initialColumn;
						}
					}
					else if (direction == 1) {
						if (currentRow < maxRow) {
							currentRow++;
//							dugTile = true;
						}
						else {
							currentRow = initialRow;
							currentColumn = initialColumn;
						}						
					}
					else if (direction == 2) {
						if (currentColumn > minCol) {
							currentColumn--;
//							dugTile = true;
						}
						else {
							currentRow = initialRow;
							currentColumn = initialColumn;
						}						
					}
					else if (direction == 3) {
						if (currentColumn < maxCol) {
							currentColumn++;
//							dugTile = true;
						}
						else {
							currentRow = initialRow;
							currentColumn = initialColumn;
						}						
					}
					else {
						System.out.println("WTF OMG direction out of bounds:" + direction);
					}
//				}
					
			}
		}
		else {
			// B. Draw a number of rooms and connect them with corridors		
			// Some basic parameters
//			int minRooms = 3;
//			int maxRooms = 7;
//			int minSize = 1;
//			int maxSize = 5;
	
			//		TODO
		}
	}

	
	/**
	 * 
	 * @param dungeon
	 * @param row
	 * @param column
	 */
	private void gopherDig(StormDungeon dungeon, int row, int column) {
		dungeon.getDungeonCell(row, column).clearCellContent();
		System.out.println("Adding floor to ("+row+","+column+")");
		dungeon.getDungeonCell(row, column).addCellContent(StormConfig.getFloor());				
	}	
}
	
