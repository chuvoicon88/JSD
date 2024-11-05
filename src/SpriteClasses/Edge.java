package SpriteClasses;

/**
 * Edge class
 *
 */
public class Edge extends Block {
    public Edge(int x, int y) {
        super(x, y);
        loadImage("src/image/edge.png");
        getImageDimensions();
        setType(6);
        setHealth(1);
    }
}
