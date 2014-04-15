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
	
}