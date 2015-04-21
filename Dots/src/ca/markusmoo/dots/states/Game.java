package ca.markusmoo.dots.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.markusmoo.dots.GameClient;
import ca.markusmoo.dots.GameServer;

public class Game extends BasicGameState{

	Image menuImage = null;
	GameClient gameClient = null;
	GameServer gameServer = null;
	
	boolean useServer;
	
	public Game(GameClient gameClient){
		useServer = false;
		this.gameClient = gameClient;
	}
	
	public Game(GameServer gameServer){
		useServer = true;
		this.gameServer = gameServer;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		menuImage = new Image("res/GameScreen.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if(menuImage!=null)
		menuImage.draw();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return 5;
	}
}
