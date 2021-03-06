import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture extends JButton {
    final int SCALEDWIDTH = 500;
    final int SCAlEDHEIGHT = 500;
    final float LEVELDECREASER = 1.5f;
    public String path;
    private BufferedImage img;
    private int level;

    public Picture(String path) throws IOException {
        this.path = path;
        level = 50;

        File file= new File(path);
        img = ImageIO.read(file);
        setIcon(new ImageIcon(img));

        pixelate();

    }

    public void setImg(String path) throws IOException{
        img = ImageIO.read(new File(path));
        this.path = path;
        level = 100;
        pixelate();
    }

    public void pixelate() throws IOException{
       
        if(level<9)level=1;

        BufferedImage imge = ImageIO.read(new File(path));

        BufferedImage tmpimg = imge;
        int height = tmpimg.getHeight();
        int width = tmpimg.getWidth();

        for(int i = 0; i < width; i = i + level) {
            for (int j = 0; j < height; j = j + level) {
                int colour = tmpimg.getRGB(i,j);
                for(int x = 0; x < level; x++){
                    if(i+x < tmpimg.getWidth()){
                        for(int y = 0; y < level; y++){
                            if(j+y < tmpimg.getHeight()){

                                tmpimg.setRGB( i+x,j+y, colour);
                            }
                        }
                    }
                }
            }
        }
        //remove all
        removeAll();
        setIcon(new ImageIcon(tmpimg.getScaledInstance(SCALEDWIDTH, SCAlEDHEIGHT, java.awt.Image.SCALE_SMOOTH))); 
        level =(int) (level/LEVELDECREASER);
    }

    public static BufferedImage getImage(String imagePath) throws IOException{
        return ImageIO.read(new File(imagePath));

       
    }
   

}