package main;


import object.OBJ_KEY;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.text.DecimalFormat;


public class UI {
    GamePanel gp;
    Font font, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        font = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_KEY key = new OBJ_KEY();
        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true) {

            g2.setFont(font);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
             x = gp.screenWidth/2 - textLength/2;
             y = gp.screenHeight/2 - (gp.titleSize*3);
             g2.drawString(text, x, y);

            text = "You time is: " + decimalFormat.format(playTime)+ "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.titleSize);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text = "Congratulations!!!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.titleSize*3);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {


            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.titleSize / 2, gp.titleSize / 2, gp.titleSize, gp.titleSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 50);

            //TIME
            playTime += (double)1/60;
            g2.drawString("Time: " +decimalFormat.format(playTime), gp.titleSize *11, 50);

            //MESSAGE
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.titleSize / 2, gp.titleSize * 2);

                messageCounter++;
                if (messageCounter > 100) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}