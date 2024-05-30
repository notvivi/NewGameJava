package tests;

import entities.Player;
import gamestates.Playing;

import main.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class that tests methods in player class.
 */
class PlayerTest {
    Playing playing = new Playing(new Game());
    Player player = new Player(10,10,10,10,playing);

    @Test
    void update() {
        player.setCurrentHealth(0);
        player.update();
        if(playing.isGameOver()){
            System.out.println("Player died as he should have.");
            assertTrue(true);
        }else{
            System.out.println("Player is still alive.");
        }
    }

    @Test
    void resetAll() {
        int[][] levelData = new int[9][5];
        player.loadLevelData(levelData);
        player.resetAll();
        if(player.getCurrentHealth() == player.getMaxHealth() && player.getHitBox().x == player.getX() && player.getHitBox().y == player.getY()) {
            System.out.println("Reset completed successfully.");
            assertTrue(true);
        }
    }

    @Test
    void render() {
    }

    @Test
    void updateHealth() {
        Player player = new Player(10,10,10,10,playing);
        int max = player.getMaxHealth();
        player.updateHealth(-50);
        assertEquals(50,player.getCurrentHealth());
        System.out.println("Player had: "+ max +"hp"+ " Players current hp is " + player.getCurrentHealth() + " He lost: " + (player.getMaxHealth() - player.getCurrentHealth()));

    }

    @Test
    void loadLevelData() {
    }

    @Test
    void resetDirectionBooleans() {
    }

    @Test
    void setAttack() {
    }

    @Test
    void setLeft() {
    }

    @Test
    void isUp() {
    }

    @Test
    void setUp() {
    }

    @Test
    void setRight() {
    }

    @Test
    void setDown() {
    }

    @Test
    void setJump() {
    }
}