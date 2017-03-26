package bomber;

import java.awt.image.BufferedImage;

/**
 * Created by jpc on 26-03-17.
 */
public enum Tile {
    EMPTY('.'){
        @Override
        public BufferedImage getImage(long frame) {
            return ImageShop.getMicroSprite(9, 5);
        }
    },
    PLAYER('P'){
        @Override
        public BufferedImage getImage(long frame) {
            return (frame / 25) % 2 == 0 ? ImageShop.getMicroSprite(0, 1) : ImageShop.getMicroSprite(2, 1);
        }
    },
    POWER_UP('U'){
        @Override
        public BufferedImage getImage(long frame) {
            return (frame / 10) % 2 == 0 ? ImageShop.getMicroSprite(3, 3) : ImageShop.getMicroSprite(4, 3);
        }
    },
    WALL('W'){
        @Override
        public BufferedImage getImage(long frame) {
            return ImageShop.getMicroSprite(0, 7);
        }
    };

    Tile(char letter) {
        this.letter = letter;
    }

    private char letter;
    public abstract BufferedImage getImage(long frame);

    public static Tile forLetter(char c) {
        for (Tile tile: Tile.values()){
            if (tile.letter == c){
                return tile;
            }
        }
        return null;
    }
}
