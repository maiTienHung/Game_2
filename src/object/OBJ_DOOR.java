package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DOOR extends SuperObject {
    public OBJ_DOOR(){
        name = "DOOR";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
