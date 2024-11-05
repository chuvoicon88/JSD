package SpriteClasses;

/**
 * Brick is a block with type 1 and health 1.
 *
 */
public class Brick extends Block {
    public Brick(int x, int y) {
        super(x, y);
        loadImage("src/image/wall_brick.png");
        getImageDimensions();
        setType(1);
        setHealth(1);
    }

}
