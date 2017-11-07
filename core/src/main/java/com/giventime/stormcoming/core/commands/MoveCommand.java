/**
 * 
 */
package com.giventime.stormcoming.core.commands;

/**
 * @author P35010
 *
 */
public class MoveCommand extends BaseCommand {
	
	private float duration = 0.3f; // seconds
	private boolean executing = false;
	private int row;
	private int column;
	private float stateTime = 0f; 
	
	public MoveCommand(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public boolean isExecuting() {
		return executing;
	}
	
	public void go() {		
		executing = true;
	}	
	
	public boolean isComplete() {
		return stateTime > duration;
	}
	
	public int getStep(int currPos, int destPos, float deltaTime) {
		int distance;
		if (destPos > currPos) {
			distance = 40;
		} else if (destPos < currPos){
			distance = -40;
		} else {
			return 0;
		}
		stateTime += deltaTime;
		float timePercentage = (stateTime/duration);
		return Math.round(distance*timePercentage);		
	}	
}
