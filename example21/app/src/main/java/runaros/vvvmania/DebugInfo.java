package runaros.vvvmania;

import java.util.ArrayList;

import sheep.graphics.Color;
import android.graphics.Canvas;

public class DebugInfo {

	private ArrayList<String> strings;
	private GameLayer gameLayer;
	private int framesLastSecond;
	private int framesThisSecond;
	private int msCounter;

	public DebugInfo(GameLayer gameLayer) {
		strings = new ArrayList<String>();
		this.gameLayer = gameLayer;
		framesLastSecond = 0;
		framesThisSecond = 0;
		msCounter = 0;
	}

	public void update(float dt) {
		int millisecondsSinceLastFrame = (int) (dt * 1000);
		framesThisSecond++;
		msCounter += millisecondsSinceLastFrame;
		if (msCounter >= 1000) {
			msCounter -= 1000;
			framesLastSecond = framesThisSecond;
			framesThisSecond = 0;
		}
	}

	public void draw(Canvas canvas) {
		strings.add("FPS: " + framesLastSecond);
		strings.add("Cap-X:" + gameLayer.getCaptain().getX());
		strings.add("Cap-Y:" + gameLayer.getCaptain().getY());


		for ( int i = 0; i < strings.size(); i++ ) {
			String string = strings.get(i);
			int y = (i+1)*15;
			canvas.drawText(string, 15, y, Color.GREEN);
		}
		strings = new ArrayList<String>();
	}
}