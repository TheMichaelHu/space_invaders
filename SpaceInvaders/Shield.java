import javalib.worldimages.*;

public class Shield extends Actor {
	
	
	Shield(int x) {
		super();
		this.x = x;
		this.y =  Utils.HEIGHT-(3 * Utils.CUSHION);
		this. dx = 0;
		this.dy = 0;
		this.img = new FromFileImage(new Posn (x, y), Utils.SHIELD_IMG);
	}
	
	Actor onHit() {
		return null;
	}
	
	// Returns true if the given (x, y) is within this Shield
	boolean isWithin(int x, int y) {
		int widthDif = Utils.PLAYER_WIDTH/2 + Utils.SHIELD_WIDTH/2;
		int heightDif = Utils.PLAYER_HEIGHT/2 + Utils.SHIELD_HEIGHT/2;
		return Math.abs(x - this.x) < widthDif && Math.abs(y - this.y) <= heightDif;
	}
	
}