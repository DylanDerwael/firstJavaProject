package Assets;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by dylan on 12.01.15.
 */
public class AssetHandler {
    public ImageIcon getDefaultIcon(){

        BufferedImage buff = null;
        try {
            buff = ImageIO.read(getClass().getResourceAsStream("default.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return new ImageIcon(buff);

    }
}
