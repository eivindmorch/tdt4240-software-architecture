package runaros.vvvmania;

import sheep.game.Sprite;
import sheep.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Wall extends Sprite {

	private Rect rectangle;
	private Color color;

	public Wall(int left, int top, int right, int bottom, Color color) {
		rectangle = new Rect(left, top, right, bottom);
		this.color = color;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(rectangle, color);
	}
}