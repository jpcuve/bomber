package bomber;

import java.awt.image.BufferedImage;

/**
 * Created by jpc on 26-03-17.
 */
public enum Tile {
    EMPTY('.', false){
        @Override
        public BufferedImage getImage(long frame) {
            return ImageShop.getMicroSprite(9, 5);
        }
    },
    PLAYER('P', false){
        @Override
        public BufferedImage getImage(long frame) {
            return (frame / 25) % 2 == 0 ? ImageShop.getMicroSprite(0, 1) : ImageShop.getMicroSprite(2, 1);
        }
    },
    POWER_UP('U', false){
        @Override
        public BufferedImage getImage(long frame) {
            return (frame / 10) % 2 == 0 ? ImageShop.getMicroSprite(3, 3) : ImageShop.getMicroSprite(4, 3);
        }
    },
    WALL('W', true){
        @Override
        public BufferedImage getImage(long frame) {
            return ImageShop.getMicroSprite(0, 7);
        }
    };

    Tile(char letter, boolean obstacle) {
        this.letter = letter;
        this.obstacle = obstacle;
    }

    private char letter;
    private boolean obstacle;
    public abstract BufferedImage getImage(long frame);

    public boolean isObstacle(){
        return obstacle;
    }

    public static Tile forLetter(char c) {
        for (Tile tile: Tile.values()){
            if (tile.letter == c){
                return tile;
            }
        }
        return null;
    }
}
