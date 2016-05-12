package Services;

import Assets.AssetHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dylan on 25.12.14.
 */
public class FotoHandler {

    private Image foto, scaledFoto;
    private String picPath;
    private ImageIcon ic = null;

    public FotoHandler() {
        AssetHandler ah = new AssetHandler();
        ic = ah.getDefaultIcon();
        this.editIcon();
    }

    public FotoHandler(String path) {
        this.picPath = path;
        read(path);
    }

    public FotoHandler(byte[]x){
        ic =  new ImageIcon(x);
        this.editIcon();
    }

    public void read(String path){
        try {
            ic = new ImageIcon(ImageIO.read(new File(path)));
            this.editIcon();
        } catch (IOException ex) {
            Logger.getLogger(FotoHandler.class.getName()).log(Level.SEVERE, null, ex);
            ic = new ImageIcon();
        }
    }

    private void editIcon(){
        double ratio = (double) 50/ic.getIconHeight();

        foto = ic.getImage();
        scaledFoto = foto.getScaledInstance((int)(ratio * ic.getIconWidth()) , 50, Image.SCALE_SMOOTH);
        //scaledFoto = foto;
    }


    public Image getScaledFoto(){
        return scaledFoto;
    }

    public ImageIcon getImageIcon() { return ic;}

    public String path(){
        return picPath;
    }

    public InputStream stream (){
        ByteArrayInputStream inStream = null;
        try {


            BufferedImage bImage = new BufferedImage(scaledFoto.getWidth(null),scaledFoto.getHeight(null),BufferedImage.TYPE_INT_RGB);

            Graphics bg = bImage.getGraphics();
            bg.drawImage(scaledFoto,0,0,null);
            bg.dispose();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(bImage,"png",out);
            byte[] buf = out.toByteArray();
            // setup stream for blob
            inStream = new ByteArrayInputStream(buf);
        } catch (IOException ex) {
            Logger.getLogger(FotoHandler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return inStream;
    }
}
