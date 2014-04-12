import javalib.colors.Black;
import javalib.worldimages.*;

public class Utils {
	
	// Screen
	final static int WIDTH = 1000;
	final static int HEIGHT = 800;
	final static WorldImage SCENE = new RectangleImage(new Posn(WIDTH/2, HEIGHT/2), WIDTH, HEIGHT, new Black());
	
	// Actor Constants
	final static String ACTOR_IMG = "actor.png";
  	
  	// Player Constants
  	final static String PLAYER_IMG = "player.png";
  	final static int PLAYER_WIDTH = 63;
  	final static int PLAYER_HEIGHT = 39;
  	final static int PLAYER_SPD = 10;
  	final static int LIVES = 3;
  	
  	// Alien Constants
  	final static String ALIEN_IMG = "alien.png";
  	final static String STRONG_IMG = "strong.png";
  	final static int ROWS = 5;
  	final static int COLUMNS = 11;
  	final static int CUSHION = 65;
  	final static int ALIEN_WIDTH = 53;
  	final static int ALIEN_HEIGHT = 38;
  	final static int INIT_ALIEN_SPD = 5;
  	final static int DROP_SPD = 20;
  	final static int ALIEN_VAL = 10;
  	
  	// Saucer Constants
	final static String SAUCER_IMG = "saucer.png";
  	final static int SAUCER_WIDTH = 64;
  	final static int SAUCER_HEIGHT = 29;
  	final static int SAUCER_SPD = 10;
  	final static int SAUCER_VAL = 250;
  	
  	// Missile Constants
  	final static String MISSILE_IMG = "missile.png";
  	final static int MISSILE_WIDTH = 7;
  	final static int MISSILE_HEIGHT = 17;
	final static int MISSILE_SPD = 5;
  	
}