package ca.markusmoo.dots.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.markusmoo.dots.GameClient;
import ca.markusmoo.dots.Main;

public class SearchJoin extends BasicGameState{

	Image searchImage = null;
	String ipAddress;
	
	GameClient gameClient;
	Game game;
	
	public SearchJoin(String ip) throws SlickException{
		ipAddress = ip;
		searchImage = new Image("res/SearchingForPlayerScreen.png");
		searchImage.draw();
		gameClient = new GameClient(ip);
		Main.CLIENT = gameClient;
		this.game = new Game(gameClient);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		searchImage.draw();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(!gameClient.isConnected()){
			if(gameClient.connect()){
				game.addState(this.game);
				game.enterState(5);
			}
		}
	}

	@Override
	public int getID() {
		return 3;
	}
}
