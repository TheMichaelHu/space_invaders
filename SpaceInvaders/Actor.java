import javalib.worldimages.*;

abstract public class Actor {
	int x; 
	int y;
	int dx;
	int dy;
	WorldImage img;

	Actor() {
		this.x = 0;
		this.y = 0;
		this.dx = 0;
		this.dy = 0;
		this.img = new FromFileImage(new Posn(x,y), Utils.ACTOR_IMG);;
	}

	// EFFECT: Changes the state of this Actor	
	void act(){}

	// Draws this Actor onto the given WorldImage
	WorldImage drawOn(WorldImage img) {
		return new OverlayImagesXY(img, this.img, this. x, this.y);
	}

	// EFFECT: Changes the position of this Actor based on dx and dy
	void move() {}

	// returns onHit() if the Actor was hit, returns itself otherwise
	Actor gotHit(Actor a) {
		int halfWidth = (Integer)(this.img.getWidth() / 2);
		int halfHeight = (Integer)(this.img.getHeight() / 2);

		if (a.x >= (this.x - halfWidth) && a.x <= (this.x + halfWidth) &&
				(a.y <= (this.y + halfHeight) && a.y >= (this.y - halfHeight))) {
			return this.onHit();
		} else {
			return this;
		}
	}

	// updates this Actor when they are hit; returns null when Actor is destroyed
	Actor onHit() {
		return null;
	}  
} 