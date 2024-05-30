package tests;

import utilz.LoadSave;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class that tests method in load save class.
 */
class LoadSaveTest {

    @org.junit.jupiter.api.Test
    void getSpriteAtlas() {
    }

    @org.junit.jupiter.api.Test
    void getPikachus() {
        int number = LoadSave.getPikachus().size();
        assertEquals(5,number);
    }

    @org.junit.jupiter.api.Test
    void getDataAboutLevel() {
    }
}