package SpriteClasses;

import GameMain.Map;

/**
 * Bullet is a Sprite. Direction refers to which way the bullet is facing North
 * East South and West refer to 0/1/2/3 respectively Bullets can move with a pre
 * defined speed. Additionally, bullets can be upgraded to destroy steel bricks.
 * Bullets can be spawned either by player tank or enemy tank, this boolean is
 * helpful to stop tanks from killing themselves on the bullets that they spawn
 * Furthermore for future
 *
 */
public class Bullet extends Sprite {
    private final int BULLET_SPEED = 3;
    private final int BOARD_WIDTH = Map.BOARD_WIDTH;
    private final int BOARD_HEIGHT = Map.BOARD_HEIGHT;
    private int direction;
    private boolean upgrade = false;
    public boolean isEnemy;

    public Bullet(int x, int y, int direction, boolean Enemy) {
        super(x, y);
        this.direction = direction;
        if (direction == 0) {
            loadImage("src/image/bullet_up.png");
        }
        if (direction == 1) {
            loadImage("src/image/bullet_right.png");
        }
        if (direction == 2) {
            loadImage("src/image/bullet_down.png");
        }
        if (direction == 3) {
            loadImage("src/image/bullet_left.png");
        }
        isEnemy = Enemy;
        getImageDimensions();
    }

    public void move() {
        if (direction == 0) {
            y -= BULLET_SPEED;
        } else if (direction == 1) {
            x += BULLET_SPEED;
        } else if (direction == 2) {
            y += BULLET_SPEED;
        } else if (direction == 3) {
            x -= BULLET_SPEED;
        }
        if (x > BOARD_WIDTH + 100 + width) {
            vis = false;
        }
        if (x < -width - 100) {
            vis = false;
        }
        if (y > BOARD_HEIGHT + height + 100) {
            vis = false;
        }
        if (y < -height - 100) {
            vis = false;
        }
    }

    public void upgrade() {
        this.upgrade = true;
    }

    public boolean getUpgrade() {
        return this.upgrade;
    }

}
