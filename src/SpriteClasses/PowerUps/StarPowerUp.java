package SpriteClasses.PowerUps;

/**
 * StarPowerUp extends PowerUp and sets the type as 8
 *
 */
public class StarPowerUp extends PowerUp {
    public StarPowerUp(int x, int y) {
        super(x, y);
        loadImage("image/powerup_star.png");
        getImageDimensions();
        setType(8);
        s = "image/powerup_star.png";
    }

}
