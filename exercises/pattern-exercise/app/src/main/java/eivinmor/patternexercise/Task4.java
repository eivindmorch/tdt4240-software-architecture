package eivinmor.patternexercise;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/*
*********************************
1. Lacking support for multi-touch
*********************************
*/

public class Task4 extends State implements WidgetListener {

    // ------------- SINGLETON -------------
    private static Task4 firstInstance = null;

    public static Task4 getInstance() {
        if (firstInstance == null) {
            firstInstance = new Task4();
        }
        return firstInstance;
    }
    // ----------- SINGLETON END -----------

    private Image paddleImg = new Image(R.drawable.paddle);
    private Image ballImg = new Image(R.drawable.ball);
    private Image wallImg = new Image(R.drawable.wall);
    private Image lineImg = new Image(R.drawable.line);

    private Sprite paddle1, paddle2, ball, wallL, wallR, line;
    private int p1Score =0, p2Score=0;
    private String winner;
    private boolean running = false;

    private Paint[] buttonPaint = {Font.BLUE_SANS_BOLD_20, Font.WHITE_SANS_BOLD_20};
    private TextButton startButton;



    private Task4() {
        paddle1 = new Sprite(paddleImg);
        paddle2 = new Sprite(paddleImg);
        ball = new Sprite(ballImg);
        wallL = new Sprite(wallImg);
        wallR = new Sprite(wallImg);
        line = new Sprite(lineImg);

        buttonPaint[0].setTextSize(70);
        buttonPaint[1].setTextSize(70);
    }


    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        if (running) {
            line.draw(canvas);
            wallL.draw(canvas);
            wallR.draw(canvas);
            paddle1.draw(canvas);
            paddle2.draw(canvas);
            ball.draw(canvas);
        }
        else if (!running && startButton != null) {
            startButton.draw(canvas);
            if (winner != null) {
                canvas.drawText("PLAYER " + winner + " WINS!", getGame().getWidth() / 2 - 250, getGame().getHeight() / 2 - 100, Font.WHITE_SANS_BOLD_20);
            }
        }
        canvas.drawText(Integer.toString(p1Score), getGame().getWidth() - 100, 250, Font.WHITE_SANS_BOLD_20);
        canvas.drawText(Integer.toString(p2Score), getGame().getWidth() - 100, getGame().getHeight() - 250, Font.WHITE_SANS_BOLD_20);

    }


    public void update(float dt) {
        if (running) {

            if (paddle1.collides(ball)) {
                ball.setPosition(ball.getX(), 248);
                if (ball.getSpeed().getY() > 0) {
                    ball.setYSpeed(-ball.getSpeed().getY() - 50);
                }
                else {
                    ball.setYSpeed(-ball.getSpeed().getY() + 50);
                }
                ball.setXSpeed((ball.getX() - paddle1.getX())/4*Math.abs(ball.getX() - paddle1.getX()));
            }
            else if (paddle2.collides(ball)) {
                ball.setPosition(ball.getX(), getGame().getHeight() - 248);
                if (ball.getSpeed().getY() > 0) {
                    ball.setYSpeed(-ball.getSpeed().getY() - 50);
                }
                else {
                    ball.setYSpeed(-ball.getSpeed().getY() + 50);
                }
                ball.setXSpeed((ball.getX() - paddle2.getX())/4 *Math.abs(ball.getX() - paddle2.getX()));
            }
            if (ball.getSpeed().getX() > 750) {
                ball.setXSpeed(750);
            }
            else if (ball.getSpeed().getX() < -750) {
                ball.setXSpeed(-750);
            }

            if (wallL.collides(ball) || wallR.collides(ball)) {
                ball.setXSpeed(-ball.getSpeed().getX());
            }

            if (ball.getY() > 1701) {
                ball.setPosition(540, 850);
                initBallSpeed();
                p1Score++;
                if (p1Score == 21) {
                    running = false;
                }
            }
            else if (ball.getY() < 0) {
                ball.setPosition(540, 850);
                initBallSpeed();
                p2Score++;
                if (p2Score == 21) {
                    running = false;
                }
            }
        }
        else {
            if (startButton == null) {
                startButton = new TextButton(getGame().getWidth()/2 - 200, getGame().getHeight()/2 +150, ">NEW GAME<", buttonPaint);
                addTouchListener(startButton);
                startButton.addWidgetListener(this);
            }
            if (p1Score == 21) {
                p1Score = 0;
                p2Score = 0;
                winner = "1";
                running = false;
            }
            else if (p2Score == 21 ) {
                p1Score = 0;
                p2Score = 0;
                winner = "2";
                running = false;
            }

        }
        paddle1.update(dt);
        paddle2.update(dt);
        ball.update(dt);
        wallL.update(dt);
        wallR.update(dt);
        line.update(dt);
    }

    private void initBallSpeed() {
        if (Math.random()<0.5) {
            ball.setSpeed(Math.round(Math.random() * 300), 950);
        }
        else {
            ball.setSpeed(Math.round(Math.random() * 300), -950);
        }
    }


    @Override
    public boolean onTouchDown (MotionEvent event) {
        if (event.getY() > 0 && event.getY() < 200) {
            paddle1.setPosition(event.getX(), paddle1.getY());
        }
        else if (event.getY() > (getGame().getHeight() - 200) && event.getY() < getGame().getHeight()) {
            paddle2.setPosition(event.getX(), paddle2.getY());
        }
        return true;
    }

    @Override
    public boolean onTouchMove (MotionEvent event) {
        if (event.getY() > 0 && event.getY() < 200) {
            paddle1.setPosition(event.getX(), paddle1.getY());
        }
        else if (event.getY() > (getGame().getHeight() - 200) && event.getY() < getGame().getHeight()) {
            paddle2.setPosition(event.getX(), paddle2.getY());
        }
        return true;
    }

/*  NOTE: Having difficulties implementing multi-touch due to inexperience with the Sheep2.1 lib.

    @Override
    public boolean onTouchDown (MotionEvent event) {
        mActivePointerId = event.getPointerId(0);
        action = MotionEventCompat.getActionMasked(event);


        if (mActivePointerId == 0) {
            if (event.getY() > 0 && event.getY() < 200) {
                paddle1.setPosition(event.getX(), paddle1.getY());
            }
            else if (event.getY() > (getGame().getHeight() - 200) && event.getY() < getGame().getHeight()) {
                paddle2.setPosition(event.getX(), paddle2.getY());
            }
        }
        if (mActivePointerId == 1) {
            if (event.getY() > 0 && event.getY() < 200) {
                paddle1.setPosition(event.getX(), paddle1.getY());
            }
            else if (event.getY() > (getGame().getHeight() - 200) && event.getY() < getGame().getHeight()) {
                paddle2.setPosition(event.getX(), paddle2.getY());
            }
        }

        return true;
    }
    public boolean onTouchMove (MotionEvent event) {

        int pointerCount = event.getPointerCount();
        for(int i = 0; i < pointerCount; ++i)
        {
            pointerIndex = i;
            mActivePointerId = event.getPointerId(pointerIndex);
            if(mActivePointerId == 0) {
                if (event.getY() > 0 && event.getY() < 200) {
                    paddle1.setPosition(event.getX(), paddle1.getY());
                }
                else if (event.getY() > 1500 && event.getY() < 1920) {
                    paddle2.setPosition(event.getX(), paddle2.getY());
                }
            }
            if(mActivePointerId == 1) {
                if (event.getY() > 0 && event.getY() < 200) {
                    paddle1.setPosition(event.getX(), paddle1.getY());
                }
                else if (event.getY() > 1500 && event.getY() < 1920) {
                    paddle2.setPosition(event.getX(), paddle2.getY());
                }
            }
        }
        return true;
    }
*/
    @Override
    public void actionPerformed (WidgetAction widgetAction) {
        if (widgetAction.getSource() == startButton) {
            running = true;
            initBallSpeed();
            paddle1.setPosition(getGame().getWidth()/2, 200);
            paddle2.setPosition(getGame().getWidth()/2, getGame().getHeight() - 200);
            ball.setPosition(getGame().getWidth()/2, getGame().getHeight()/2);
            wallL.setPosition(0, getGame().getHeight()/2);
            wallR.setPosition(getGame().getWidth(), getGame().getHeight()/2);
            line.setPosition(getGame().getWidth()/2, getGame().getHeight()/2);
        }
    }

}
