package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BOOTS extends SuperObject{
    public OBJ_BOOTS(){
        name = "BOOTS";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
