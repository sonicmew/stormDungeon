package com.giventime.stormcoming.core.commands;

public class MoveDestination {

	private int row;
	private int column;
	
	public MoveDestination(int row, int column) {
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
	
}
