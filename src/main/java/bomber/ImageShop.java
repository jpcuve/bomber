package bomber;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jpc on 26-03-17.
 */
public class ImageShop {
    private static BufferedImage microSprites;

    static {
        try (InputStream is = ClassLoader.getSystemResourceAsStream("microsprites_by_romson.gif")){
            microSprites = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getMicroSprite(int x, int y){
        return microSprites.getSubimage(23 + x * 22, 23 + y * 22, 16, 16);

    }
}
