package SpriteClasses;

/**
 * Tree is a block with type 5 and health 1
 *
 */
public class Tree extends Block {
    public Tree(int x, int y) {
        super(x, y);
        loadImage("src/image/trees.png");
        getImageDimensions();
        setType(5);
        setHealth(1);
    }

}
