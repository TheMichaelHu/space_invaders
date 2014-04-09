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

	// EFFECT: Moves this Missile
	void act() {
		this.move();
	}

	// EFFECT: Moves this Missile down by dy
	void move() {
		this.x += this.dx;
	}

	// updates this Actor when they are hit; returns null when Actor is destroyed
	Actor onHit() {
		return null;
	}
}