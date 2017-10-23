package runaros.vvvmania;

import sheep.game.Camera;
import sheep.game.State;
import sheep.game.World;
import sheep.input.KeyboardListener;
import sheep.math.Vector2;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;

public class GameState extends State implements KeyboardListener {

	private World gameWorld;
	private GameLayer gameLayer;
	private DebugInfo debugInfo;

	public GameState() {
		gameWorld = new World();
		gameLayer = new GameLayer();
		gameWorld.addLayer(gameLayer);
		debugInfo = new DebugInfo(gameLayer);
		this.addKeyboardListener(this);
	}


	@Override
	public void draw(Canvas canvas) {
		moveCamera(canvas);
		canvas.drawColor(Color.BLACK);
		gameWorld.draw(canvas);
		debugInfo.draw(canvas);
	}

	public void moveCamera(Canvas canvas) {
		int screenWidth = canvas.getWidth();
		int screenHeight = canvas.getHeight();
		int captainPositionX = (int)(gameLayer.getCaptain().getPosition().getX());
		int captainPositionY = (int)(gameLayer.getCaptain().getPosition().getY());
		int screenPositionX = captainPositionX - (screenWidth / 2);
		int screenPositionY = captainPositionY - (screenHeight / 2);
		int levelWidth = gameLayer.getLevel().getWidth();
		int levelHeight = gameLayer.getLevel().getHeight();

        screenPositionX = (screenPositionX >= 0) ? screenPositionX : 0;
        screenPositionY = (screenPositionY >= 0) ? screenPositionY : 0;
        screenPositionX = (screenPositionX + screenWidth >= levelWidth) ? levelWidth - screenWidth : screenPositionX;
        screenPositionY = (screenPositionY + screenHeight >= levelHeight) ? levelHeight - screenHeight : screenPositionY;

        Camera camera = gameWorld.getCamera();
        camera.setSize(new Vector2(screenWidth, screenHeight));
		camera.setPosition(new Vector2(screenPositionX, screenPositionY));
	}

	@Override
	public void update(float dt) {
		gameWorld.update(dt);
		debugInfo.update(dt);
	}

	public boolean onKeyDown(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
			gameLayer.getCaptain().startMovingLeft();
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
			gameLayer.getCaptain().startMovingRight();
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
			gameLayer.getCaptain().startMovingUp();
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
			gameLayer.getCaptain().startMovingDown();
		}
		return true;
	}

	public boolean onKeyUp(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
			gameLayer.getCaptain().stopMovingLeft();
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
			gameLayer.getCaptain().stopMovingRight();
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
			gameLayer.getCaptain().stopMovingUp();
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
			gameLayer.getCaptain().stopMovingDown();
		}
		return true;
	}

}