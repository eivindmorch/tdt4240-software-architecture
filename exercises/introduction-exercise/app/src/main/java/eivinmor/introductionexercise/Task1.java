package eivinmor.introductionexercise;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

import android.graphics.Canvas;
import android.graphics.Color;


public class Task1 extends State {
    private Image heliImage = new Image(R.drawable.heli1_east);
    private Sprite heliSprite;
    private Float spriteWidth;
    private Float spriteHeight;


    public Task1() {
        heliSprite = new Sprite(heliImage);
        heliSprite.setSpeed(160, 120);
        spriteWidth = heliImage.getWidth();
        spriteHeight = heliImage.getHeight();

    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(254, 0, 254));
        heliSprite.draw(canvas);


    }


    public void update(float dt) {

        if (heliSprite.getX() > getGame().getWidth() - (spriteWidth/2)) {
            heliSprite.setScale(-1, 1);
            heliSprite.setPosition(getGame().getWidth() - (spriteWidth/2), heliSprite.getY());
            heliSprite.setXSpeed(-heliSprite.getSpeed().getX());
        }
        else if (heliSprite.getX() < 0 + (spriteWidth/2)) {
            heliSprite.setScale(1, 1);
            heliSprite.setPosition(0  + (spriteWidth/2), heliSprite.getY());
            heliSprite.setXSpeed(-heliSprite.getSpeed().getX());
        }
        if (heliSprite.getY() > getGame().getHeight() - (spriteHeight/2)) {
            heliSprite.setPosition(heliSprite.getX(), getGame().getHeight() - (spriteHeight/2));
            heliSprite.setYSpeed(-heliSprite.getSpeed().getY());
        }
        else if (heliSprite.getY() < 0 + (spriteHeight/2)) {
            heliSprite.setPosition(heliSprite.getX(), 0 + (spriteHeight/2));
            heliSprite.setYSpeed(-heliSprite.getSpeed().getY());
        }

        heliSprite.update(dt);
    }



}
