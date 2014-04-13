import javalib.worldimages.*;

public class Saucer extends Actor {
	int value;
	boolean canMove;

	Saucer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.dx = Utils.SAUCER_SPD;
		this.img = new FromFileImage(new Posn(x,y), Utils.SAUCER_IMG);
		this.value = Utils.SAUCER_VAL;
		this.canMove = false;
	}
	
	void reset() {
		this.canMove = false;
		this.x = -Utils.SAUCER_WIDTH;
		this.img.pinhole = new Posn(this.x, this.y);
	}

	// EFFECT: Moves this Missile
	void act() {
		if(this.canMove) {
			this.move();
			if(this.x > Utils.WIDTH+Utils.SAUCER_WIDTH) {
				this.reset();
			}
		}
	}

	// EFFECT: Moves this Missile down by dy
	void move() {
		this.x += this.dx;
		this.img.pinhole = new Posn(this.x, this.y);
	}

	// updates this Actor when they are hit; returns null when Actor is destroyed
	Actor onHit() {
		this.reset();
		return null;
	}
}