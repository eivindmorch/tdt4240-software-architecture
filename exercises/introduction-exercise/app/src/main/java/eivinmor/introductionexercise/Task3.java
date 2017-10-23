package eivinmor.introductionexercise;

import sheep.game.State;
import sheep.game.World;

import android.graphics.Canvas;
import android.graphics.Color;


/*
*********************************
1. Lacking sprite animation
2. Lacking timing on animation
*********************************
*/

public class Task3 extends State{

    World world;
    Task3CollisionLayer layer;


    public Task3() {

        world = new World();
        layer = new Task3CollisionLayer(this);
        world.addLayer(layer);



    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(254, 0, 254));
        world.draw(canvas);
    }


    public void update(float dt) {
        world.update(dt);
    }

}
