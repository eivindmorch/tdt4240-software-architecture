package eivinmor.introductionexercise;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;


public class Task2 extends State {
    private Image heliImage = new Image(R.drawable.heli1_east);
    private Sprite heliSprite;
    private Float spriteWidth;
    private Float spriteHeight;
    private int cX;
    private int cY;


    public Task2() {

        heliSprite = new Sprite(heliImage);
        heliSprite.setSpeed(5, 5);
        spriteWidth = heliImage.getWidth();
        spriteHeight = heliImage.getHeight();

    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(254, 0, 254));
        heliSprite.draw(canvas);
        canvas.drawText(Integer.toString(cX) + ", " + Integer.toString(cY), 2, 52, Font.WHITE_SANS_BOLD_20);
    }


    public void update(float dt) {
        if (heliSprite.getX() > getGame().getWidth() - (spriteWidth/2)) {
            heliSprite.setScale(-1, 1);
            heliSprite.setPosition(getGame().getWidth() - (spriteWidth / 2), heliSprite.getY());
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
        if (heliSprite.getSpeed().getX() > 500) {
            heliSprite.setXSpeed(500);
        }
        if (heliSprite.getSpeed().getY() > 500) {
            heliSprite.setYSpeed(500);
        }
        cX = Math.round(heliSprite.getX());
        cY = Math.round(heliSprite.getY());
        heliSprite.update(dt);
    }

    public boolean onTouchDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (x > heliSprite.getX()) {
            heliSprite.setXSpeed(heliSprite.getSpeed().getX() + 40);
            heliSprite.setScale(1, 1);
        }
        else {
            heliSprite.setXSpeed(heliSprite.getSpeed().getX() - 40);
            heliSprite.setScale(-1, 1);
        }
        if (y > heliSprite.getY()) {
            heliSprite.setYSpeed(heliSprite.getSpeed().getY() + 40);
        }
        else {
            heliSprite.setYSpeed(heliSprite.getSpeed().getY() - 40);
        }
        return true;
    }

    public boolean onTouchMove(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (x > heliSprite.getX()) {
            heliSprite.setXSpeed(heliSprite.getSpeed().getX() + 40);
            heliSprite.setScale(1, 1);
        }
        else {
            heliSprite.setXSpeed(heliSprite.getSpeed().getX() - 40);
            heliSprite.setScale(-1, 1);
        }
        if (y > heliSprite.getY()) {
            heliSprite.setYSpeed(heliSprite.getSpeed().getY() + 40);
        }
        else {
            heliSprite.setYSpeed(heliSprite.getSpeed().getY() - 40);
        }
        return true;
    }


}
