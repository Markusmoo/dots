package ca.markusmoo.dots.states;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.markusmoo.dots.Main;

public class Join extends BasicGameState{

	Image joinImage = null;
	Rectangle backButton,joinButton;
	TextField portTF;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		joinImage = new Image("res/JoinScreen.png");
		
		backButton = new Rectangle(440,440,200,60);
		joinButton = new Rectangle(150,440,150,60);
		
		Font font = new Font("Showcard Gothic", Font.BOLD, 20);
		portTF = new TextField(container, new TrueTypeFont(font, true), 223, 240, 336, 33);
		portTF.setBackgroundColor(Color.white);
		portTF.setBorderColor(Color.gray);
		portTF.setTextColor(Color.black);
		portTF.setMaxLength(20);
		portTF.setText(Main.DEFAULT_IP);
		portTF.setAcceptingInput(false);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		joinImage.draw();
		portTF.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		portTF.setAcceptingInput(true);
		if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			Input input = container.getInput();
			//System.out.println(input.getMouseX()+" "+input.getMouseY());
			if(joinButton.contains(input.getMouseX(),input.getMouseY())){
				portTF.setAcceptingInput(false);
				game.addState(new SearchJoin(portTF.getText()));
				game.enterState(3);
			}else if(backButton.contains(input.getMouseX(), input.getMouseY())){
				portTF.setAcceptingInput(false);
				game.enterState(0);
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}
}
