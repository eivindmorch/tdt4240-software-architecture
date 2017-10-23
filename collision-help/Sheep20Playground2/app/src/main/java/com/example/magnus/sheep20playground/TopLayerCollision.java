package com.example.magnus.sheep20playground;

import android.graphics.Canvas;

import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by Magnus on 26/01/15.
 */
public class TopLayerCollision extends CollisionLayer {

    Sprite sprite1, sprite2;

    public TopLayerCollision(CollisionListener cl) {
        sprite1 = new Sprite(new Image(R.drawable.helicopter));
        sprite1.addCollisionListener(cl);
        sprite1.setParent(this);
        sprite1.setPosition(0, 200);
        addSprite(sprite1);

        sprite2 = new Sprite(new Image(R.drawable.helicopter));
        sprite2.addCollisionListener(cl);
        sprite2.setParent(this);
        sprite2.setPosition(300, 200);
        addSprite(sprite2);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        sprite1.setPosition(sprite1.getX() + 1, sprite1.getY());
    }

}
