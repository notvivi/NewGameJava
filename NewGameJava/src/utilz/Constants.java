package utilz;

import main.Game;

public class Constants {

    public static class EnemyConstants{
        public static final int PIKACHU = 0;
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int PIKACHU_WIDTH_DEFAULT = 72;
        public static final int PIKACHU_HEIGHT_DEFAULT = 32;

        public static final int PIKACHU_WIDTH = (int)(PIKACHU_WIDTH_DEFAULT * Game.SCALE);
        public static final int PIKACHU_HEIGHT = (int)(PIKACHU_HEIGHT_DEFAULT * Game.SCALE);

        public static final int PIKACHU_OFFSET_X = (int)(26 * Game.SCALE);
        public static final int PIKACHU_OFFSET_Y = (int)(9 * Game.SCALE);

        public static int getSpriteAmount(int enemyType, int enemyState){
            switch (enemyType){
                case PIKACHU:
                    switch (enemyState){
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                    }

            }
         return 0;
        }

        public static int getMaxHealth(int enemyType){
            switch(enemyType){
                case PIKACHU:
                    return 10;
                default:
                    return 1;
            }
        }

        public static int getEnemyDamage(int enemyType){
            switch(enemyType){
                case PIKACHU:
                    return 15;
                default:
                    return 0;
            }
        }

    }
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class Environment {
        public static final int BIG_CLOUDS_WIDTH_DEFAULT = 448;
        public static final int BIG_CLOUDS_HEIGHT_DEFAULT = 101;
        public static final int BIG_CLOUDS_WIDTH = (int) (BIG_CLOUDS_WIDTH_DEFAULT* Game.SCALE);
        public static final int BIG_CLOUDS_HEIGHT = (int) (BIG_CLOUDS_HEIGHT_DEFAULT * Game.SCALE);

        public static final int SMALL_CLOUDS_WIDTH_DEFAULT = 74;
        public static final int SMALL_CLOUDS_HEIGHT_DEFAULT = 24;
        public static final int SMALL_CLOUDS_WIDTH = (int) (SMALL_CLOUDS_WIDTH_DEFAULT * Game.SCALE);
        public static final int SMALL_CLOUDS_HEIGHT = (int) (SMALL_CLOUDS_HEIGHT_DEFAULT * Game.SCALE);




    }
    public static class Ui{
        public static class Buttons{
            public static final int BUTTON_WIDTH_DEFAULT = 140;
            public static final int BUTTON_HEIGHT_DEFAULT = 56;
            public static final int BUTTON_WIDTH = (int) (BUTTON_WIDTH_DEFAULT * Game.SCALE);
            public static final int BUTTON_HEIGHT = (int) (BUTTON_HEIGHT_DEFAULT * Game.SCALE);

            public static final int CIRCLE_BUTTON_WIDTH_DEFAULT = 69;
            public static final int CIRCLE_BUTTON_HEIGHT_DEFAULT = 69;
            public static final int CIRCLE_BUTTON_WIDTH = (int) (CIRCLE_BUTTON_WIDTH_DEFAULT * Game.SCALE);
            public static final int CIRCLE_BUTTON_HEIGHT = (int) (CIRCLE_BUTTON_HEIGHT_DEFAULT * Game.SCALE);;
            
        }
    }

    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int ATTACK = 4;
        public static final int HIT = 5;
        public static final int DEAD = 6;

        public static int getSpriteAmount(int player_action) {
            switch (player_action) {
                case DEAD:
                    return 8;
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case JUMP:
                case ATTACK:
                    return 3;
                case FALLING:
                default:
                    return 1;
            }
        }

    }

}
