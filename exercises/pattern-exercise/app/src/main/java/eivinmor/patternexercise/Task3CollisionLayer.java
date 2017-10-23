package eivinmor.patternexercise;


import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.game.State;

public class Task3CollisionLayer extends CollisionLayer implements CollisionListener{
    private Image heliImage = new Image(R.drawable.heli1_east);
    private Sprite heliSprite1, heliSprite2, heliSprite3;
    private Float spriteWidth;
    private Float spriteHeight;
    private Random random = new Random();
    private State parState;

    private ArrayList<Sprite> heliArray = new ArrayList<>();


    public Task3CollisionLayer(State state) {
        parState = state;
        heliSprite1 = new Sprite(heliImage);
        heliSprite1.setPosition(random.nextInt(250), random.nextInt(300));
        heliArray.add(heliSprite1);

        heliSprite2 = new Sprite(heliImage);
        heliSprite2.setPosition(random.nextInt(250)+300, random.nextInt(300)+500);
        heliArray.add(heliSprite2);

        heliSprite3 = new Sprite(heliImage);
        heliSprite3.setPosition(random.nextInt(250)+600, random.nextInt(300)+1000);
        heliArray.add(heliSprite3);

        for (int i=0; i < heliArray.size(); i++) {
            heliArray.get(i).setSpeed(random.nextInt(600) -300, random.nextInt(400)-200);
            if (heliArray.get(i).getSpeed().getX() > 0) {
                heliArray.get(i).setScale(1,-1);
            }
            heliArray.get(i).addCollisionListener(this);
            heliArray.get(i).setParent(this);
        }

        spriteWidth = heliImage.getWidth();
        spriteHeight = heliImage.getHeight();

        addSprite(heliSprite1);
        addSprite(heliSprite2);
        addSprite(heliSprite3);


    }



    @Override
    public void update(float dt) {
        super.update(dt);
        for (int i=0; i < heliArray.size(); i++) {
            if (heliArray.get(i).getX() > parState.getGame().getWidth() - (spriteWidth/2)) {
                heliArray.get(i).setPosition(parState.getGame().getWidth() - (spriteWidth / 2), heliArray.get(i).getY());
                heliArray.get(i).setXSpeed(-heliArray.get(i).getSpeed().getX());
            }
            else if (heliArray.get(i).getX() < 0 + (spriteWidth/2)) {
                heliArray.get(i).setPosition(0 + (spriteWidth / 2), heliArray.get(i).getY());
                heliArray.get(i).setXSpeed(-heliArray.get(i).getSpeed().getX());
            }
            if (heliArray.get(i).getY() > parState.getGame().getHeight() - (spriteHeight/2)) {
                heliArray.get(i).setPosition(heliArray.get(i).getX(), parState.getGame().getHeight() - (spriteHeight / 2));
                heliArray.get(i).setYSpeed(-heliArray.get(i).getSpeed().getY());
            }
            else if (heliArray.get(i).getY() < 0 + (spriteHeight/2)) {
                heliArray.get(i).setPosition(heliArray.get(i).getX(), 0 + (spriteHeight / 2));
                heliArray.get(i).setYSpeed(-heliArray.get(i).getSpeed().getY());
            }
            if (heliArray.get(i).getSpeed().getX() > 0) {
                heliArray.get(i).setScale(1,1);
            }
            else {
                heliArray.get(i).setScale(-1,1);
            }
            heliArray.get(i).update(dt);
        }

    }


    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
/*        float spriteXSpeed = sprite.getSpeed().getX();
        float sprite2XSpeed = sprite.getSpeed().getX();
        float spriteYSpeed = sprite.getSpeed().getY();
        float sprite2YSpeed = sprite.getSpeed().getY();


        if (spriteXSpeed >= 0 && sprite2XSpeed >= 0) {
            if (spriteXSpeed > sprite2XSpeed) {
                sprite.setXSpeed(-spriteXSpeed);
            }
            else {
                sprite2.setXSpeed(-sprite2XSpeed);
            }
        }
        else if (spriteXSpeed < 0 && sprite2XSpeed < 0){
            if (spriteXSpeed < sprite2XSpeed) {
                sprite.setXSpeed(-spriteXSpeed);
            }
            else {
                sprite2.setXSpeed(-sprite2XSpeed);
            }
        }
        else {
            sprite.setXSpeed(-spriteXSpeed);
            sprite2.setXSpeed(-sprite2XSpeed);
        }


        if (spriteYSpeed > 0 && sprite2YSpeed > 0) {
            if (spriteYSpeed > sprite2YSpeed) {
                sprite.setYSpeed(-spriteYSpeed);
            }
            else {
                sprite2.setYSpeed(-sprite2YSpeed);
            }
        }
        else if (spriteYSpeed < 0 && sprite2YSpeed < 0){
            if (spriteYSpeed < sprite2YSpeed) {
                sprite.setYSpeed(-spriteYSpeed);
            }
            else {
                sprite2.setYSpeed(-sprite2YSpeed);
            }
        }
        else {
            sprite.setYSpeed(-spriteYSpeed);
            sprite2.setYSpeed(-sprite2YSpeed);
        }

*/

        sprite.setSpeed(- sprite.getSpeed().getX(),-sprite.getSpeed().getY() );
        sprite2.setSpeed(- sprite2.getSpeed().getX(),-sprite2.getSpeed().getY() );
    }
}
