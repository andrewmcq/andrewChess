package Test;

import JavaChess.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    Main main;

    /**
     * makes sure main instantiates
     */
    @Test
    public void testMain() {
        main = new Main();
        assertTrue(main instanceof Main);
        Main.main(new String[]{});
    }
}
