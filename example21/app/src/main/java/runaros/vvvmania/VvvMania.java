package runaros.vvvmania;

import sheep.game.Game;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class VvvMania extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Game game = new Game(this, null);
		game.pushState(new GameState());
		setContentView(game);
	}
}