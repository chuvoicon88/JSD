package GameMain;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageUtility {
    // Instance vairables for the images
    private final Image lives, flagIcon, enemyIcon;
    private final Image arrow, tankBasic, tankFast, tankPower, tankArmor;
    private final Image background, tank, tree2;
    private static ImageUtility instance;

    /**
     * Get the instance of the ImageUtility
     *
     * @return instance
     */
    public static ImageUtility getInstance() {
        if (instance == null) {
            return new ImageUtility();
        }
        return instance;
    }

    private ImageUtility() {
        lives = loadImage("src/image/lives.png");
        flagIcon = loadImage("src/image/flag.png");
        enemyIcon = loadImage("src/image/enemy.png");
        arrow = loadImage("src/image/arrow.png");
        tankBasic = loadImage("src/image/tank_basic.png");
        tankFast = loadImage("src/image/tank_fast.png");
        tankPower = loadImage("src/image/tank_power.png");
        tankArmor = loadImage("src/image/tank_armor.png");
        background = loadImage("src/image/battle_city.png");
        tank = loadImage("src/image/playerTank_right.png");
        tree2 = loadImage("src/image/trees2.png");
    }

    /**
     * Load images given the image address
     *
     * @param imageAddress image address to the demand image
     * @return image
     */
    public Image loadImage(String imageAddress) {
        ImageIcon icon = new ImageIcon(imageAddress);
        return icon.getImage();
    }

    // Getter for different images
    /**
     * Get lives image
     *
     * @return lives
     */
    public Image getLives() {
        return lives;
    }

    /**
     * Get flag icon image
     *
     * @return flagIcon
     */
    public Image getFlagIcon() {
        return flagIcon;
    }

    /**
     * Get enemy icon image
     *
     * @return enemyIcon
     */
    public Image getEnemyIcon() {
        return enemyIcon;
    }

    /**
     * Get arrow icon image
     *
     * @return arrow
     */
    public Image getArrow() {
        return arrow;
    }

    /**
     * Get basic tank image
     *
     * @return tankBasic
     */
    public Image getTankBasic() {
        return tankBasic;
    }

    /**
     * Get fast tank image
     *
     * @return Image tankFast
     */
    public Image getTankFast() {
        return tankFast;
    }

    /**
     * Get power tank image
     *
     * @return tankPower
     */
    public Image getTankPower() {
        return tankPower;
    }

    /**
     * Get armor tank image
     *
     * @return tankArmor
     */
    public Image getTankArmor() {
        return tankArmor;
    }

    /**
     * Get background image for the menu
     *
     * @return background
     */
    public Image getBackground() {
        return background;
    }

    /**
     * Get tree image for the menu
     *
     * @return tree2
     */
    public Image getTree2() {
        return tree2;
    }

    /**
     * Get tank image for the menu
     *
     * @return tank Image
     */
    public Image getTank() {
        return tank;
    }

}
