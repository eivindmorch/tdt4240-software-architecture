package com.example.magnus.sheep20playground;

import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by Magnus on 26/01/15.
 */
public class TopLayerCollision2 extends CollisionLayer implements CollisionListener {

    Sprite sprite1, sprite2;

    public TopLayerCollision2() {
        sprite1 = new Sprite(new Image(R.drawable.helicopter));
        sprite1.addCollisionListener(this);
        sprite1.setParent(this);
        sprite1.setPosition(0, 400);
        addSprite(sprite1);

        sprite2 = new Sprite(new Image(R.drawable.helicopter));
        sprite2.addCollisionListener(this);
        sprite2.setParent(this);
        sprite2.setPosition(300, 400);
        addSprite(sprite2);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        sprite1.setPosition(sprite1.getX() + 1, sprite1.getY());
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
        System.out.println("TopLayerCollision2 in Layer");
    }
}
