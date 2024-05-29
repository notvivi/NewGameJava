package gamestates;

/**
 * Enum that checks in which state of game we are.
 */
public enum GameState {
    PLAYING, MENU, QUIT, START, STORY;

    public static GameState state = START;
}
