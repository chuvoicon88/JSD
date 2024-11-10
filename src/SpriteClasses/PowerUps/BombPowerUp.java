package SpriteClasses.PowerUps;

/**
 * BombPowerUp extends PowerUp
 */
public class BombPowerUp extends PowerUp {
    public BombPowerUp(int x, int y) {
        super(x, y);
        loadImage("src/image/powerup_grenade.png");
        getImageDimensions();
        setType(9);
        s = "src/image/powerup_grenade.png";
    }
}
