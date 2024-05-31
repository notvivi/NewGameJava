package tests;

import utilz.LoadSave;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class that tests method in load save class.
 */
class LoadSaveTest {
    /**
     * Method that tests if getting pikachus from level is okey.
     */
    @org.junit.jupiter.api.Test
    void getPikachus() {
        int number = LoadSave.getPikachus().size();
        assertEquals(5,number);
    }

}