package SpriteClasses;

/**
 * Steel is a Block with type 2 and health 1
 *
 */
public class Steel extends Block {

    public Steel(int x, int y) {
        super(x, y);
        loadImage("src/image/wall_steel.png");
        getImageDimensions();
        setHealth(1);
        setType(2);
    }

}
