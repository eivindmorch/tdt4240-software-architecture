package com.example.magnus.sheep20playground;

import android.graphics.Canvas;
import android.graphics.Color;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.game.World;

/**
 * Created by Magnus on 21/01/15.
 */
public class FirstState extends State implements CollisionListener {

    World world;
    TopLayerCollision layer;
    TopLayerCollision2 layer2;

    public FirstState() {
        world = new World();
        layer = new TopLayerCollision(this);
        layer2 = new TopLayerCollision2();
        world.addLayer(layer);
        world.addLayer(layer2);
    }

    @Override
    public void update(float dt) {
        world.update(dt);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        world.draw(canvas);
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
        System.out.println("FirstState collision listener");
    }
}
