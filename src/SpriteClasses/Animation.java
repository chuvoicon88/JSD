package SpriteClasses;

/**
 * Animation is a child class of Sprite, it adds the functionality of
 * updateAnimation() which is a method used to change the loaded image. The
 * initialTime is used to update the image
 *
 */
public class Animation extends Sprite {
    long initialTime = System.currentTimeMillis();

    public Animation(int x, int y) {
        super(x, y);
    }

    public void updateAnimation() {

    }

}
