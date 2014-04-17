import javalib.worldimages.*; 

public class Alien extends Actor {

	int value;
	int invasionProgress;
	int timer;
	boolean img1;

	Alien(int x, int y) {
		super();
		this.x = x;
		this.y = y; 
		this.dx = Utils.INIT_ALIEN_SPD;
		this.dy = Utils.DROP_SPD;
		this.img = new FromFileImage(new Posn(x,y), Utils.ALIEN_IMG);
		this.value = Utils.ALIEN_VAL;
		this.invasionProgress = 0;
		this.timer = 0;
		this.img1 = true;
	}
	
	Alien(int x, int y, int dx, int progress, boolean img1, int timer) {
		super();
		this.x = x;
		this.y = y; 
		this.dx = dx;
		this.dy = Utils.DROP_SPD;
		this.img = new FromFileImage(new Posn(x,y), Utils.ALIEN_IMG);
		this.value = Utils.ALIEN_VAL;
		this.invasionProgress = progress;
		this.img1 = img1;
		this.timer = timer;
	}

	// EFFECT: Changes the state of this Actor	
	void act() {
		if(this.timer >= 10) {
			this.swapImg();
			this.timer = 0;
		}
		this.move();
		timer++;
	}
	
	void swapImg() {
		if(this.img1) {
			this.img = new FromFileImage(new Posn(x,y), Utils.ALIEN_IMG2);
		} else {
			this.img = new FromFileImage(new Posn(x,y), Utils.ALIEN_IMG);
		}
		this.img1 = !this.img1;
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
		//System.out.println("droppin down" + this.invasionProgress);
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