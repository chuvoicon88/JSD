package SpriteClasses.PowerUps;

/**
 * ShieldPowerUp extends PowerUp and sets the type at 12
 *
 */
public class ShieldPowerUp extends PowerUp {
    public ShieldPowerUp(int x, int y) {
        super(x, y);
        loadImage("image/powerup_helmet.png");
        getImageDimensions();
        setType(12);
        s = "image/powerup_helmet.png";
    }

}
