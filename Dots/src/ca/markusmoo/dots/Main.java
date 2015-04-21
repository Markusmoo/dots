package ca.markusmoo.dots;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import ca.markusmoo.dots.states.Create;
import ca.markusmoo.dots.states.Join;
import ca.markusmoo.dots.states.Menu;

public class Main extends StateBasedGame{
	
	/* TODO list
	 * 
	 * Remember last IP/Port entered.
	 * Statistics.
	 * Log in/out system.
	 */
	
	/* Known Bugs
	 * 
	 * Client doesn't receive a pong.
	 */
	
	//Server and Client
	public static GameServer SERVER;
	public static GameClient CLIENT;
	
	//Global Final Game Dimensions/Settings
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final boolean FULLSCREEN = false;
	public static final String VERSION = "Version 1.0";
	
	//Global Defaults
	public static String DEFAULT_PORT = "26656";
	public static String DEFAULT_IP = "localhost";
	
	//Global Settings Value
	public static Music MUSIC_PLAYER = null;
	public static float MUSIC_VOLUME = 0.5f;
	public static float SOUND_EFFECTS_VOLUME = 0.5f;
	public static boolean USE_VSYNC = false;
	public static boolean SHOW_FPS = false;
	
	//Global Temporary Game Over Values
	public static String GAME_WINNER = "";
	public static int[] GAME_TIME = {0,0,0};
	
	//Game Applet
	public static AppGameContainer app = null;
	

	public Main(String title) {
		super(title);
	}

	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new Main("Dots "+VERSION));
		app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
		app.setAlwaysRender(true);
		app.setVSync(USE_VSYNC);
		app.setShowFPS(SHOW_FPS);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new Menu());
		this.addState(new Create());
		this.addState(new Join());
	}
	
	public boolean closeRequested(){
		if(SERVER != null){
			SERVER.stopServer();
		}
		return super.closeRequested();
	}
}
