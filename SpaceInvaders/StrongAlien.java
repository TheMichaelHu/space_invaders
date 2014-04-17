import javalib.worldimages.*;

public class StrongAlien extends Alien {

	StrongAlien(int x, int y) {
		super(x,y);
		this.img = new FromFileImage(new Posn(x,y), Utils.STRONG_IMG);
	}

	void swapImg() {
		if(this.img1) {
			this.img = new FromFileImage(new Posn(x,y), Utils.STRONG_IMG2);
		} else {
			this.img = new FromFileImage(new Posn(x,y), Utils.STRONG_IMG);
		}
		this.img1 = !this.img1;
	}

	// updates this Actor when they are hit; returns null when Actor is destroyed
	Actor onHit() {
		return new Alien(this.x, this.y, this.dx, this.invasionProgress, this.img1, this.timer);
	}
}