package GameMain;

import SpriteClasses.Animation;
import SpriteClasses.Block;
import SpriteClasses.Bullet;
import SpriteClasses.ExplodingTank;
import SpriteClasses.PowerUps.*;
import SpriteClasses.Tank;
import SpriteClasses.TankAI;
import SpriteClasses.TankShield;
import SpriteClasses.TankSpawn;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BoardUtility {

    private static ArrayList<TankAI> enemy = new ArrayList<>();
    private static ArrayList<Block> blocks = new ArrayList<>();
    private static ArrayList<Animation> animations = new ArrayList<>();
    public static ArrayList<PowerUp> powerUps = new ArrayList<>();
    private static Tank tankP1;
    private static Tank tankP2;
//    private static boolean isMultiplayer = false;


    /**
     * Constructor for the BoardUtility class
     *
     * @param enemy an array list that stores enemy tanks
     * @param blocks an array list that stores blocks on the board
     * @param animations an array list that stores different animations
     * @param powerUps an array list that stores different power-ups
     * @param tank the Tank class that represents the player
     */
    public static void loadBoardUtility(ArrayList<TankAI> enemy,
                                        ArrayList<Block> blocks,
                                        ArrayList<Animation> animations,
                                        ArrayList<PowerUp> powerUps,
                                        Tank tank,
                                        boolean isPlayer2) {
        if (!isPlayer2) {
            BoardUtility.tankP1 = tank;
        } else {
            BoardUtility.tankP2 = tank;
//            isMultiplayer = true;
        }
        BoardUtility.enemy = enemy;
        BoardUtility.blocks = blocks;
        BoardUtility.animations = animations;
        BoardUtility.powerUps = powerUps;
    }

    /**
     * Update power ups on the board.
     */
    public static void updatePowerUps() {
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp p = powerUps.get(i);
            p.updateAnimation();
            BlockType type = BlockType.getTypeFromInt(p.getType());

            // Check if power-up intersects with tankP1 or tankP2
            Rectangle r1 = tankP1.getBounds();
            Rectangle r2 = null;
            if (tankP2 != null) {
                r2 = tankP2.getBounds();
            }
            Rectangle powerUpBounds = p.getBounds();

            if (System.currentTimeMillis() - p.getLoadTime() > 10000) {
                powerUps.remove(i);
                i--; // Adjust index after removal
                continue;
            }

            if (r1.intersects(powerUpBounds)) {
                handlePowerUpPickup(tankP1, type);
                powerUps.remove(i);
                i--; // Adjust index after removal
                continue;
            } else if (r2 != null && r2.intersects(powerUpBounds)) {
                handlePowerUpPickup(tankP2, type);
                powerUps.remove(i);
                i--; // Adjust index after removal
                continue;
            }
        }
    }

    /**
     * Handles the actions when a tank picks up a power-up.
     *
     * @param tank The tank picking up the power-up.
     * @param type The type of the power-up being picked up.
     */
    private static void handlePowerUpPickup(Tank tank, BlockType type) {
        SoundUtility.powerupPick();

        switch (type) {
            case TANK:
                tank.upHealth();
                break;
            case SHIELD:
                tank.shield = true;
                animations.add(new TankShield(tank, 1));
                break;
            case STAR:
                tank.upStarLevel();
                break;
            case CLOCK:
                for (TankAI ai : enemy) {
                    ai.frozen = true;
                    ai.frozenStartTime = System.currentTimeMillis();
                }
                break;
            case BOMB:
                for (TankAI ai : enemy) {
                    ai.vis = false;
                    CollisionUtility.incrementNum(ai);
                    animations.add(new ExplodingTank(ai.x, ai.y));
                }
                Board.decrementEnemies(enemy.size());
                break;
            default:
                break;
        }
    }

    /**
     * Spawn random PowerUp when the given tank AI carries powerUp and is
     * destroyed.
     */
    public static void spawnPowerUp() {
        if (CollisionUtility.powerUpX != 0 || CollisionUtility.powerUpY != 0) {
        Random random = new Random();
        int randomPow = random.nextInt(5);
            switch (randomPow) {
                case 0:
                    powerUps.add(new BombPowerUp(CollisionUtility.powerUpX,
                                                 CollisionUtility.powerUpY));
                    break;
                case 1:
                    powerUps.add(new ClockPowerUp(CollisionUtility.powerUpX,
                                                  CollisionUtility.powerUpY));
                    break;
                case 2:
                    powerUps.add(new ShieldPowerUp(CollisionUtility.powerUpX,
                                                   CollisionUtility.powerUpY));
                    break;
                case 3:
                    powerUps.add(new StarPowerUp(CollisionUtility.powerUpX,
                                                 CollisionUtility.powerUpY));
                    break;
                case 4:
                    powerUps.add(new TankPowerUp(CollisionUtility.powerUpX,
                                                 CollisionUtility.powerUpY));
                    break;
                default:
                    break;
            }
            CollisionUtility.powerUpX = 0;
            CollisionUtility.powerUpY = 0;
        }
    }

    /**
     * Spawn Tank AI on the board.
     *
     * @param difficulty a string that represents the difficulty of the tank AI
     * @param powerUp a boolean that represents if the tank AI carries powerUp
     */
    public static void spawnTankAI(String difficulty, boolean powerUp) {
        Random random = new Random();
        int randomPos = random.nextInt(3);
        int randomType = random.nextInt(20);
        String type;
        if (randomType < 2) {
            type = "armor";
        } else if (randomType >= 2 && randomType < 7) {
            type = "power";
        } else if (randomType >= 8 && randomType < 13) {
            type = "fast";
        } else {
            type = "basic";
        }
        if (randomPos == 0) {
            animations.add(new TankSpawn(2 * 16, 1 * 16));
            TankAI AI = new TankAI(2 * 16, 1 * 16, difficulty, type, powerUp);
            enemy.add(AI);
        } else if (randomPos == 1) {
            animations.add(new TankSpawn(14 * 16, 1 * 16));
            TankAI AI = new TankAI(14 * 16, 1 * 16, difficulty, type, powerUp);
            enemy.add(AI);
        } else {
            animations.add(new TankSpawn(26 * 16, 1 * 16));
            TankAI AI = new TankAI(26 * 16, 1 * 16, difficulty, type, powerUp);
            enemy.add(AI);
        }
    }

    /**
     * Update bullets and tank AI on the board.
     */
    public static void updateBulletsTankAI() {
        for (TankAI tankAI : enemy) {
            ArrayList<Bullet> bullets = tankAI.getBullets();
            for (int i = 0; i < bullets.size(); i++) {
                Bullet b = bullets.get(i);
                if (b.isVisible()) {
                    b.move();
                } else if (b.isVisible() == false) {
                    bullets.remove(i);
                }
            }
        }
    }

    /**
     * Update bullets and tank on the Board.
     */
    public static void updateBulletsTank() {
        for (Tank tank : Arrays.asList(tankP1, tankP2)) {
            if (tank != null) {
                ArrayList<Bullet> bullets = tank.getBullets();
                for (int i = 0; i < bullets.size(); i++) {
                    Bullet b = bullets.get(i);
                    if (b.isVisible()) {
                        b.move();
                    } else {
                        bullets.remove(i);
                    }
                }
            }
        }
    }

    /**
     * Update blocks on the Board.
     */
    public static void updateBlocks() {
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            BlockType type = BlockType.getTypeFromInt(b.getType());
            if (type.equals(BlockType.RIVER)) {
                b.updateAnimation();
            } else if (type.equals(BlockType.BASE)) {
                b.updateAnimation();
            }
            if (b.isVisible() == false) {
                blocks.remove(i);
            }
        }
    }

    /**
     * Update animations on the Board.
     */
    public static void updateAnimations() {
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).vis == false) {
                animations.remove(i);
            } else {
                animations.get(i).updateAnimation();
            }
        }
    }

    /**
     * Update tank on the Board.
     */
    public static void updateTank() {
        if (tankP1.getHealth() < 0) {
            tankP1.setVisible(false);
        }
        if (tankP1.isVisible()) {
            tankP1.move();
        }
        if (tankP2 != null) {
            if (tankP2.getHealth() < 0) {
                tankP2.setVisible(false);
            }
            if (tankP2.isVisible()) {
                tankP2.move();
            }
        }
    }

    /**
     * Check for collisions on the board.
     */
    public static void checkCollisions() {
        ArrayList<Bullet> bullets = new ArrayList<>();

        // Collect bullets from both player tanks
        bullets.addAll(tankP1.getBullets());
        if (tankP2 != null) {
            bullets.addAll(tankP2.getBullets());
        }
        // Add bullets from enemy tanks
        for (TankAI tankAI : enemy) {
            bullets.addAll(tankAI.getBullets());
        }

        // Perform collision checks
        CollisionUtility.checkCollisionBulletsBlocks(bullets, blocks);

        // Check if bullets from both tanks hit enemy tanks
        CollisionUtility.checkCollisionBulletsTankAI(bullets, enemy);

        // Check if enemy bullets hit each player tank
        CollisionUtility.checkCollisionBulletsTank(bullets, tankP1);
        if (tankP2 != null) {
            CollisionUtility.checkCollisionBulletsTank(bullets, tankP2);
        }
        // Check for collisions between player tanks and enemy tanks
        CollisionUtility.checkCollisionTankTankAI(enemy, tankP1);
        if (tankP2 != null) {
            CollisionUtility.checkCollisionTankTankAI(enemy, tankP2);
        }
    }

}
