package SpriteClasses;

/**
 * Explosion extends Animation. This is the explosion used for bullets and
 * various types of blocks.
 *
 */
public class Explosion extends Animation {

    public Explosion(int x, int y) {
        super(x - 8, y - 8);
        loadImage("src/image/bullet_explosion_1.png");
        getImageDimensions();
    }

    @Override
    public void updateAnimation() {
        if ((System.currentTimeMillis() - initialTime) > 125) {
            loadImage("src/image/bullet_explosion_2.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - initialTime > 250)) {
            loadImage("src/image/bullet_explosion_3.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - initialTime > 300)) {
            super.vis = false;
        }

    }

}
