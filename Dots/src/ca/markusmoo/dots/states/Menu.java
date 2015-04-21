package ca.markusmoo.dots.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{

	Image menuImage = null;
	Rectangle createButton,joinButton;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		menuImage = new Image("res/MenuScreen.png");
		createButton = new Rectangle(440,440,200,60);
		joinButton = new Rectangle(150,440,150,60);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		menuImage.draw();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			Input input = container.getInput();
			if(joinButton.contains(input.getMouseX(),input.getMouseY())){
				game.enterState(1);
			}else if(createButton.contains(input.getMouseX(), input.getMouseY())){
				game.enterState(2);
			}
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}
