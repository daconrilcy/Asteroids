package board;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Board extends JFrame {

    public static int width = 1000;
    public static int heigth = 1000;
    public Dimension dimension;

    public Board (){
        this.setSize(width,heigth);
        this.dimension = new Dimension(width , heigth+20);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setResizable(false);
        this.setTitle("Asteroids Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameDrawingPanel gamePanel = new GameDrawingPanel();
        this.setBackground(Color.BLACK);
        this.add(gamePanel , BorderLayout.CENTER);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(8);

        executor.scheduleAtFixedRate(new RepaintTheBoard(this) , 0L , 37L , TimeUnit.MILLISECONDS);

        this.setVisible(true);

    }

}
