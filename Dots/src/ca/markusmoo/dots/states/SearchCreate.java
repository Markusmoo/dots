package ca.markusmoo.dots.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.markusmoo.dots.GameServer;
import ca.markusmoo.dots.Main;

public class SearchCreate extends BasicGameState{

	Image searchImage = null;
	int port;
	
	GameServer gameServer;
	Game game;
	
	public SearchCreate(int port) throws SlickException{
		
		this.port = port;
		searchImage = new Image("res/SearchingForPlayerScreen.png");
		searchImage.draw();
		gameServer = new GameServer(port);
		Main.SERVER = gameServer;
		gameServer.startServer();
		this.game = new Game(gameServer);
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
	}

	@Override
	public int getID() {
		return 4;
	}
}
