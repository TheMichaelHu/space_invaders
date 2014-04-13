import javalib.worldimages.*; 

public class Alien extends Actor {

	int value;
	int invasionProgress;

	Alien(int x, int y) {
		super();
		this.x = x;
		this.y = y; 
		this.dx = Utils.INIT_ALIEN_SPD;
		this.dy = Utils.DROP_SPD;
		this.img = new FromFileImage(new Posn(x,y), Utils.ALIEN_IMG);
		this.value = Utils.ALIEN_VAL;
		this.invasionProgress = 0;
	}
	
	Alien(int x, int y, int dx, int progress) {
		super();
		this.x = x;
		this.y = y; 
		this.dx = dx;
		this.dy = Utils.DROP_SPD;
		this.img = new FromFileImage(new Posn(x,y), Utils.ALIEN_IMG);
		this.value = Utils.ALIEN_VAL;
		this.invasionProgress = progress;
	}

	// EFFECT: Changes the state of this Actor	
	void act() {
		this.move();
	}

	// EFFECT: Reverses this Alien's dx
	void swapDir() {
		this.dx *= -1;
	}

	// EFFECT: Moves Alien by dx
	void move() {
		this.x += this.dx;
		this.img.pinhole = new Posn(this.x, this.y);
	}
	
	// EFFECT: Moves Alien down by dy and swaps direction
	void moveDown() {
		System.out.println("droppin down" + this.invasionProgress);
		this.invasionProgress+= 1;
		this.y+= this.dy;
		this.img.pinhole = new Posn(this.x, this.y);
		this.swapDir();
		if (this.dx > 0) {
			this.dx = this.invasionProgress * Utils.ACCELERATION;
		} else {
			this.dx = -this.invasionProgress * Utils.ACCELERATION;
		}
	}
	
	void moveBy(int dist) {
		this.x+= dist;
		this.img.pinhole = new Posn(this.x, this.y);
	}
	
	// produces a Missile in front of this Alien
	Missile fire() {
		int halfHeight = (Integer)(this.img.getHeight() / 2);
		return new Missile(this.x, (this.y + halfHeight + 1), Utils.ALIEN_MISSILE_SPD);
	}

}