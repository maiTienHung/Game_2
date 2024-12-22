package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CHEST extends  SuperObject{
public OBJ_CHEST(){
    name = "CHEST";
    try {
        image = ImageIO.read(getClass().getResourceAsStream("/object/chest (OLD).png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
