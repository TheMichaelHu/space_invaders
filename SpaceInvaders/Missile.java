import javalib.worldimages.*;

public class Missile extends Actor {

	Missile(int x, int y, int dy) {
		super();
		this.x = x;
		this.y = y;
		this.dy = dy;
		this.img = new FromFileImage(new Posn(x,y), Utils.MISSILE_IMG);
	}

	// EFFECT: Moves this Missile
	void act() {
		System.out.println("alien move");
		this.move();
	}

	// EFFECT: Moves this Missile down by dy
	void move() {
		this.y += this.dy;
		this.img.pinhole = new Posn(this.x, this.y);
	}

	// returns true if this Missile is touching the given Actor
	Actor hit(Actor a) {
		return a.gotHit(this);
	}
}