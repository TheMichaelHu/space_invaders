import javalib.worldimages.*; 

public class Alien extends Actor {

	int value;

	Alien(int x, int y) {
		super();
		this.x = x;
		this.y = y; 
		this.dx = Utils.INIT_ALIEN_SPD;
		this.dy = Utils.DROP_SPD;
		this.img = new FromFileImage(new Posn(x,y), Utils.ALIEN_IMG);
		this.value = Utils.ALIEN_VAL;
	}

	// EFFECT: Changes the state of this Actor	
	void act() {
		// Todo
	}

	// produces a Missile in front of this Alien
	Missile fire() {
		return new Missile(this.x, this.y, Utils.MISSILE_SPD); //make sure the alien doesn't kill itself with its own missile....
	}

	// updates this Actor when they are hit; returns null when Actor is destroyed
	Actor onHit() {
		return null;
	}

	// EFFECT: Reverses this Alien's dx
	void swapDir() {
		this.dx *= -1;
	}
}