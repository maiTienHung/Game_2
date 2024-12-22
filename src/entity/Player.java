package entity;

import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH ;
        screenX = gp.screenWidth / 2 - (gp.titleSize/2);
        screenY = gp.screenHeight / 2 - (gp.titleSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues(){

        worldX= gp.titleSize * 23;
        worldY= gp.titleSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {


            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collsionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpItem(objIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
             if (keyH.upPressed == true || keyH.downPressed == true ||
                     keyH.leftPressed == true || keyH.rightPressed == true) {

                 if (collsionOn == false) {
                     switch (direction) {
                         case "up":
                             worldY -= speed;
                             break;
                         case "down":
                             worldY += speed;
                             break;
                         case "left":
                             worldX -= speed;
                             break;
                         case "right":
                             worldX += speed;
                             break;
                     }
                 }
             }


            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    public void pickUpItem(int i){
        if (i!=999){
            String objName = gp.obj[i].name;

            switch (objName){
                case "KEY":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key! ");
                    break;
                case "DOOR":

                    if (hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        gp.playSE(3);
                        gp.ui.showMessage("You opened the door!");
                    }else {
                        gp.ui.showMessage("You need a key! ");
                    }

                    break;
                case "BOOTS":
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up! ");
                    break;
                case "CHEST":
                    gp.playSE(4);
                    gp.obj[i] = null;
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    break;
            }
        }

    }



    public void draw(Graphics2D g2){

       /* g2.setColor(Color.white);
        g2.fillRect(x, y, gp.titleSize, gp.titleSize);
        */


        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }

                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;

        }
        g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);

    }
}


