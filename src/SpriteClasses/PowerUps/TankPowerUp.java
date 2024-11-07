package SpriteClasses.PowerUps;

/**
 * TankPowerUp extends the PowerUp and sets the type as 7
 *
 */
public class TankPowerUp extends PowerUp {
    public TankPowerUp(int x, int y) {
        super(x, y);
        loadImage("src/image/powerup_tank.png");
        getImageDimensions();
        setType(7);
        s = "src/image/powerup_tank.png";
    }

}
