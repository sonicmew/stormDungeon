/**
 * 
 */
package com.giventime.stormcoming.core;

import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.giventime.stormcoming.core.dungeon.CellContent;
import com.giventime.stormcoming.core.dungeon.DungeonMapLayout;
import com.giventime.stormcoming.core.dungeon.StormDungeon;
import com.giventime.stormcoming.core.dungeon.StormDungeonCell;
import com.giventime.stormcoming.core.fights.Fight;
import com.giventime.stormcoming.core.units.StormEnemy;
import com.giventime.stormcoming.core.units.StormHero;
import com.giventime.stormcoming.core.units.StormUnit;
import com.giventime.stormcoming.core.utils.StormConfig;
import com.giventime.stormcoming.core.utils.StormConfig.ContentType;
import com.giventime.stormcoming.core.utils.StormConfig.DisplayOrder;
import com.giventime.stormcoming.core.utils.StormConfig.EnemyClass;
import com.giventime.stormcoming.core.utils.StormConfig.HeroClass;

/**
 * @author ANDROUTA
 *
 */
public class GameScreen extends BaseScreen implements Screen {

//	private BitmapFont font;
	
	StormDungeon dungeon;
	StormUnit thief;
	StormUnit warrior;
	StormUnit sorcerer;
	StormUnit smallOrc;
	StormUnit smallOrc2;
	StormUnit orc;
	StormUnit selected;
	
	long keyPressTime = 0;
	
	/**
	 * 
	 */
	private void initGameScreen() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, StormComing.WIDTH, StormComing.HEIGHT);
		spriteBatch = new SpriteBatch();
		dungeon = new StormDungeon(15, 15, new DungeonMapLayout());
		thief = new StormHero("Gar Bano", HeroClass.THIEF, ContentType.THIEF);
		warrior = new StormHero("Jango Loca", HeroClass.WARRIOR, ContentType.WARRIOR);
		sorcerer = new StormEnemy("Gal Baroth", EnemyClass.SORCERER, ContentType.SORCERER);
		smallOrc = new StormEnemy("Gourgoul", EnemyClass.ORC, ContentType.ORC);
		smallOrc2 = new StormEnemy("Korgon", EnemyClass.ORC, ContentType.ORC);
		orc = new StormEnemy("Jan Le Gould", EnemyClass.ORC, ContentType.ORC);
		
		smallOrc.setLvl(1);
		smallOrc2.setLvl(2);
		sorcerer.setLvl(4);
		orc.setLvl(10);
	}
	
	/**
	 * 
	 */
	private void positionUnits() {
		dungeon.addToArea(0, 3, 0, 3, thief);
		dungeon.addToArea(0, 3, 0, 3, warrior);
		dungeon.addToArea(7, 10, 7, 10, sorcerer);
		dungeon.addToArea(13, 14, 1, 5, smallOrc);
		dungeon.addToArea(2, 5, 2, 5, smallOrc2);
		dungeon.addToArea(10, 12, 10, 12, orc);
		dungeon.addToArea(0, 14, 0, 14, StormConfig.getTreasure());
	}
	
	/**
	 * 
	 * @param game
	 */
	public GameScreen(StormComing game) {
		this.game = game;		
		initGameScreen();
		positionUnits();
		//dungeon.getDungeonCell(1, 0).getCellContent().add(thief);
		
//			dungeon.addToArea(10, 12, 10, 12, sorcerer);
		selected = warrior;
	}	
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float arg0) {				
		Gdx.gl.glClearColor(0, 0.5f, 0.7f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);		
		camera.update();		
		spriteBatch.setProjectionMatrix(camera.combined);
		
		for (DisplayOrder order : DisplayOrder.values()) {
			dungeon.render(spriteBatch, order);
		}
		
		if (Gdx.input.isTouched()) {
			if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
				Vector3 touchPos = new Vector3();			
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				
				// use the mouse to select the hero you want to move
				StormDungeonCell clickedCell = dungeon.getCellForPosition(Gdx.input.getX(), StormComing.HEIGHT-Gdx.input.getY());
				List<CellContent> contents = clickedCell.getCellContents().getAllContents();
				for (CellContent content : contents) {
					if (content instanceof StormHero) {
						if (selected != null) {
							selected.setSelected(false);
						}
						((StormHero) content).setSelected(true);
						selected = ((StormHero) content);
						StormComing.addMessage(selected.getName()+" selected!");
						break;
					}
				}
			} else if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
				Vector3 touchPos = new Vector3();			
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				
				// use the mouse to select the hero you want to move
				StormDungeonCell clickedCell = dungeon.getCellForPosition(Gdx.input.getX(), StormComing.HEIGHT-Gdx.input.getY());
				List<CellContent> contents = clickedCell.getCellContents().getAllContents();
				for (CellContent content : contents) {
					if (content instanceof StormHero) {
						game.getHeroScreen().setHero((StormHero)content);
						game.goToHeroScreen();
						break;
					}
				}
			}
		}		
		
		// handle keyboard events and move accordingly
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			if (keyPressTime == 0) {
				keyPressTime = System.currentTimeMillis();
				selected.cellLeft();
				selected.search();
			} else if (System.currentTimeMillis() - keyPressTime > 300) {
				keyPressTime = 0;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (keyPressTime == 0) {
				keyPressTime = System.currentTimeMillis();
				selected.cellRight();
				selected.search();
			} else if (System.currentTimeMillis() - keyPressTime > 300) {
				keyPressTime = 0;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			if (keyPressTime == 0) {
				keyPressTime = System.currentTimeMillis();
				selected.cellDown();
				selected.search();
			} else if (System.currentTimeMillis() - keyPressTime > 300) {
				keyPressTime = 0;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			if (keyPressTime == 0) {
				keyPressTime = System.currentTimeMillis();
				selected.cellUp();
				selected.search();
			} else if (System.currentTimeMillis() - keyPressTime > 300) {
				keyPressTime = 0;
			}
		}
		
		performActions();
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		
	}
	
	private void performActions() {
		runFights();
	}
	
	private void runFights() {
		dungeon.updateConflicts();
		for (Entry<String, Fight> entry : dungeon.getFights().entrySet()) {			
			if (entry.getValue().run()) {
				dungeon.removeFight(entry.getKey());
			}
		}
		if (thief.isDead() && warrior.isDead()) {
			game.goToSplashScreen();			
		}
	}

}
