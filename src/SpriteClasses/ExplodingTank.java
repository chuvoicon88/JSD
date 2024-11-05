package SpriteClasses;

/**
 * Class that handles the exploding animations of the tanks Exploding tank is
 * extended from Explosion which is extended from Animation This class is
 * responsible for loading images of an exploding tank
 *
 */
public class ExplodingTank extends Explosion {

    private long initialTime = System.currentTimeMillis();

    /**
     * Constructor for the ExlodingTank class
     *
     * @param x coordinate of the tank
     * @param y coordinate of the tank
     */
    public ExplodingTank(int x, int y) {
        super(x - 8, y - 8);
        loadImage("src/image/big_explosion_1.png");
        getImageDimensions();
    }

    @Override
    public void updateAnimation() {
        if ((System.currentTimeMillis() - initialTime) > 125) {
            loadImage("src/image/big_explosion_2.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - initialTime > 500)) {
            loadImage("src/image/big_explosion_3.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - initialTime > 700)) {
            loadImage("src/image/big_explosion_4.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - initialTime > 900)) {
            loadImage("src/image/big_explosion_5.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - initialTime > 1100)) {
            super.vis = false;
        }
    }

}
