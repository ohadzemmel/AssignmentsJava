import biuoop.GUI;

public class MainAssiment3Test {
    public static void main(String[] args) {
        final double WIDTH = 600.0;
        final double HEIGHT = 400.0;
        final int NUM_OF_BLOCKS =8;

        GUI gui = new GUI("TestA3", (int)WIDTH, (int)HEIGHT);
        GameEnvironment gameEnvironment = new GameEnvironment(gui);
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            Block block = new Block(new Rectangle(
                    new Point(i * WIDTH/NUM_OF_BLOCKS, 0),
                    WIDTH / NUM_OF_BLOCKS,
                    HEIGHT / NUM_OF_BLOCKS
            ));
            gameEnvironment.addCollidable(block);
        }

        gameEnvironment.startGame();
    }
}

