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

public class Create extends BasicGameState{

	Image createImage = null;
	Rectangle createButton,backButton;
	TextField portTF;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		createImage = new Image("res/CreateScreen.png");
		
		backButton = new Rectangle(440,440,200,60);
		createButton = new Rectangle(130,440,180,60);
		
		Font font = new Font("Showcard Gothic", Font.BOLD, 20);
		portTF = new TextField(container, new TrueTypeFont(font, true), 223, 240, 336, 33);
		portTF.setBackgroundColor(Color.white);
		portTF.setBorderColor(Color.gray);
		portTF.setTextColor(Color.black);
		portTF.setMaxLength(5);
		portTF.setText(Main.DEFAULT_PORT);
		portTF.setAcceptingInput(false);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		createImage.draw();
		portTF.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		portTF.setAcceptingInput(true);
		if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			Input input = container.getInput();
			//System.out.println(input.getMouseX()+" "+input.getMouseY());
			if(backButton.contains(input.getMouseX(),input.getMouseY())){
				portTF.setAcceptingInput(false);
				game.enterState(0);
			}else if(createButton.contains(input.getMouseX(), input.getMouseY())){
				portTF.setAcceptingInput(false);
				game.addState(new SearchCreate(Integer.parseInt(portTF.getText())));
				game.enterState(4);
			}
		}
	}

	@Override
	public int getID() {
		return 2;
	}
}
