package eivinmor.patternexercise;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import sheep.game.Game;

public class MyGame extends Activity{

    private Game game;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        game = new Game(this, null);
        game.pushState(new MainMenu());
        setContentView(game);
    }

    @Override
    public void onBackPressed()
    {
        if (game.getPreviousState()!= null) {
            game.popState();
    }
        else {
            finish();
        }
    }
}
