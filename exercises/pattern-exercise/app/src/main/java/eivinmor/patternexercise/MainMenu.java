package eivinmor.patternexercise;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

public class MainMenu extends State implements WidgetListener {

    private TextButton task1Button, task2Button, task3Button, task4Button;
    private Paint[] buttonPaint = {Font.BLUE_SANS_BOLD_20, Font.WHITE_SANS_BOLD_20};


    public MainMenu() {
        buttonPaint[0].setTextSize(70);
        buttonPaint[1].setTextSize(70);

        task1Button = new TextButton(450, 600, "Task 1", buttonPaint);
        task2Button = new TextButton(450, 700, "Task 2", buttonPaint);
        task3Button = new TextButton(450, 800, "Task 3", buttonPaint);
        task4Button = new TextButton(450, 900, "Task 4", buttonPaint);

        addTouchListener(task1Button);
        addTouchListener(task2Button);
        addTouchListener(task3Button);
        addTouchListener(task4Button);
        task1Button.addWidgetListener(this);
        task2Button.addWidgetListener(this);
        task3Button.addWidgetListener(this);
        task4Button.addWidgetListener(this);


    }

    public void draw (Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);
        task1Button.draw(canvas);
        task2Button.draw(canvas);
        task3Button.draw(canvas);
        task4Button.draw(canvas);

    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == task1Button) {
            getGame().pushState(Task1.getInstance());
        }
        else if (widgetAction.getSource() == task2Button) {
            getGame().pushState(Task2.getInstance());
        }
        else if (widgetAction.getSource() == task3Button) {
            getGame().pushState(Task3.getInstance());
        }
        else if (widgetAction.getSource() == task4Button) {
            getGame().pushState(Task4.getInstance());
        }
    }
}
