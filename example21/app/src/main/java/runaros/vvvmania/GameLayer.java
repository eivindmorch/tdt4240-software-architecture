package runaros.vvvmania;

import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;
import android.graphics.Canvas;

public class GameLayer extends Layer {

	private Captain captain;
	private Level level;

	public GameLayer() {
		captain = new Captain(new Image(R.drawable.person));
		level = new Level();
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		captain.draw(canvas);
		level.draw(canvas);
	}

	@Override
	public void update(float dt) {
		captain.update(dt);
	}

	public Captain getCaptain() {
		return captain;
	}

	public Level getLevel() {
		return level;
	}
}