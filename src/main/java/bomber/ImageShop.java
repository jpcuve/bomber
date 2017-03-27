package bomber;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jpc on 26-03-17.
 */
public class ImageShop {
    private static BufferedImage microSprites;
    private static Map<String, BufferedImage> cache = new HashMap<>();

    static {
        try (InputStream is = ClassLoader.getSystemResourceAsStream("microsprites_by_romson.gif")){
            microSprites = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // methode memoizée car getSubImage est une opération couteuse pour le processeur
    public static BufferedImage getMicroSprite(int x, int y){
        final String key = String.format("micro-sprite-%s-%s", x, y);
        return cache.computeIfAbsent(key, k -> microSprites.getSubimage(23 + x * 22, 23 + y * 22, 16, 16)); // attention java 8
    }
}
