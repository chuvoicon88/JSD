package SpriteClasses;

public class Base extends Block {
    public boolean gameOver = false;

    public Base(int x, int y) {
        super(x, y);
        loadImage("src/image/base.png");
        getImageDimensions();
        setHealth(1);
        setType(3);

    }

    public void updateAnimation() {
        if (gameOver == true) {
            loadImage("src/image/base_destroyed.png");
            getImageDimensions();

        }
    }

}
