package SpriteClasses;

import GameMain.*;
import SpriteClasses.PowerUps.*;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

import static GameMain.BoardUtility.powerUps;

/**
 * Tank extends Sprite The Tank represents the player in the game. The tank has
 * an array of bullets and is capable of moving and firing bullets depending on
 * key events (arrow keys for movement and space bar for firing bullets) in four
 * different directions. The tank also can be upgraded in several ways
 * increasing the firing speed movement speed and the ability to break steel
 * blocks
 *
 */
public class Tank extends Sprite {
    private final int BOARD_WIDTH = Map.BOARD_WIDTH;
    private final int BOARD_HEIGHT = Map.BOARD_HEIGHT;
    private int dx;
    private int dy;
    private ArrayList<Bullet> bullets;
    public int direction;
    private long lastFired = 0;
    private int health = 3;
    public boolean isPlayer2;
    public int starLevel = 0;
    public boolean shield = false;
    private boolean hasEmergencyPowerUp = false;

    public int getHealth() {
        return health;
    }

    public void upStarLevel() {
        starLevel += 1;
    }

    public void downHealth() {
        if (shield == false) {
            this.health -= 1;
        }
    }

    public Tank(int x, int y, boolean isPlayer2) {
        super(x, y);
        loadImage(!isPlayer2 ? "src/image/playerTank_up.png" : "src/image/playerTank_up2.png");
        getImageDimensions();
        bullets = new ArrayList<>();
        direction = 0;
        this.isPlayer2 = isPlayer2;
    }

    public void move() {

        Rectangle theTank = new Rectangle(x + dx, y + dy, width, height);

        if (CollisionUtility.checkCollisionTankBlocks(theTank) == false) {
            x += dx;
            y += dy;
        }

        if (x > BOARD_WIDTH - width) {
            x = BOARD_WIDTH - width;
        }

        if (y > BOARD_HEIGHT - height) {
            y = BOARD_HEIGHT - height;
        }
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public ArrayList<Bullet> getBullets() {

        return bullets;
    }

    public void fire() {
        Bullet aBullet;
        if (direction == 0) {
            aBullet = new Bullet(x + width / 3, y, 0, false);
        } else if (direction == 1) {
            aBullet = new Bullet(x + width - 3, y + height / 3, 1, false);
        } else if (direction == 2) {
            aBullet = new Bullet(x + width / 3, (y + height) - 3, 2, false);
        } else {
            aBullet = new Bullet(x, y + height / 3, 3, false);
        }
        if (starLevel == 3) {
            aBullet.upgrade();
        }
        bullets.add(aBullet);
        SoundUtility.fireSound();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void spawnRandomPowerUp() {
        if (hasEmergencyPowerUp) {
            System.out.println("Emergency Power-up already been used in this stage!");
            return;
        } else {
            Random random = new Random();

            // Randomly choose a subclass of PowerUp
            PowerUp powerUp = null;
            int randomChoice = random.nextInt(4);

            // Random offset for where the power-up spawns
            int offsetX = random.nextInt(40) - 20;
            int offsetY = random.nextInt(40) - 20;
            int spawnX = this.x + offsetX;
            int spawnY = this.y + offsetY;

            // Instantiate a random subclass of PowerUp
            switch (randomChoice) {
                case 0:
                    powerUp = new TankPowerUp(spawnX, spawnY);
                    break;
                case 1:
                    powerUp = new ShieldPowerUp(spawnX, spawnY);
                    break;
                case 2:
                    powerUp = new StarPowerUp(spawnX, spawnY);
                    break;
                case 3:
                    powerUp = new BombPowerUp(spawnX, spawnY);
                    break;
                default:
                    break;
            }

            if (powerUp != null) {
                // Add the power-up to the game's list of power-ups
                powerUps.add(powerUp);
            }
            hasEmergencyPowerUp = true;
        }
    }

    /**
     * Resets the power-up usage for a new stage.
     * This should be called when a new stage begins.
     */
    public void resetPowerUpUsageForNewStage() {
        hasEmergencyPowerUp = false;
    }

    public void keyPressed(KeyEvent e) {
        int time;
        int key = e.getKeyCode();
        if (starLevel == 0) {
            time = 700;
        } else {
            time = 250;
        }
        if (!isPlayer2) {
            if (key == KeyEvent.VK_SPACE && (System.currentTimeMillis() - lastFired) > time) {
                fire();
                lastFired = System.currentTimeMillis();
            } else if (key == KeyEvent.VK_A) {
                dx = -1;
                dy = 0;
                if (starLevel > 1) {
                    dx = -2;
                }
                ImageIcon ii = new ImageIcon("src/image/playerTank_left.png");
                image = ii.getImage();
                direction = 3;
            } else if (key == KeyEvent.VK_D) {
                dx = 1;
                dy = 0;
                if (starLevel > 1) {
                    dx = 2;
                }
                ImageIcon ii = new ImageIcon("src/image/playerTank_right.png");
                image = ii.getImage();
                direction = 1;
            } else if (key == KeyEvent.VK_W) {
                ImageIcon ii = new ImageIcon("src/image/playerTank_up.png");
                image = ii.getImage();
                dy = -1;
                dx = 0;
                if (starLevel > 1) {
                    dy = -2;
                }
                direction = 0;
            } else if (key == KeyEvent.VK_S) {
                ImageIcon ii = new ImageIcon("src/image/playerTank_down.png");
                image = ii.getImage();
                dy = 1;
                dx = 0;
                if (starLevel > 1) {
                    dy = 2;
                }
                direction = 2;
            } else if (key == KeyEvent.VK_F) {
                spawnRandomPowerUp();
            }
            /** Player 2 */
        } else {
            if (key == KeyEvent.VK_BACK_SPACE && (System.currentTimeMillis() - lastFired) > time) {
                fire();
                lastFired = System.currentTimeMillis();
            } else if (key == KeyEvent.VK_LEFT) {
                dx = -1;
                dy = 0;
                if (starLevel > 1) {
                    dx = -2;
                }
                ImageIcon ii = new ImageIcon("src/image/playerTank_left2.png");
                image = ii.getImage();
                direction = 3;
            } else if (key == KeyEvent.VK_RIGHT) {
                dx = 1;
                dy = 0;
                if (starLevel > 1) {
                    dx = 2;
                }
                ImageIcon ii = new ImageIcon("src/image/playerTank_right2.png");
                image = ii.getImage();
                direction = 1;
            } else if (key == KeyEvent.VK_UP) {
                ImageIcon ii = new ImageIcon("src/image/playerTank_up2.png");
                image = ii.getImage();
                dy = -1;
                dx = 0;
                if (starLevel > 1) {
                    dy = -2;
                }
                direction = 0;
            } else if (key == KeyEvent.VK_DOWN) {
                ImageIcon ii = new ImageIcon("src/image/playerTank_down2.png");
                image = ii.getImage();
                dy = 1;
                dx = 0;
                if (starLevel > 1) {
                    dy = 2;
                }
                direction = 2;
            } else if (key == KeyEvent.VK_P) {
                spawnRandomPowerUp();
            }
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if (!isPlayer2) {
            if (key == KeyEvent.VK_A) {
                dx = 0;
            }

            if (key == KeyEvent.VK_D) {
                dx = 0;
            }

            if (key == KeyEvent.VK_W) {
                dy = 0;
            }

            if (key == KeyEvent.VK_S) {
                dy = 0;
            }
        } else {
            if (key == KeyEvent.VK_LEFT) {
                dx = 0;
            }

            if (key == KeyEvent.VK_RIGHT) {
                dx = 0;
            }

            if (key == KeyEvent.VK_UP) {
                dy = 0;
            }

            if (key == KeyEvent.VK_DOWN) {
                dy = 0;
            }
        }
    }

    public void upHealth() {
        this.health += 1;
    }

    public int getStarLevel() {
        return starLevel;
    }
}
