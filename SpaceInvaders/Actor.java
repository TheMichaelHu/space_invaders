import javalib.colors.*;
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
		return (WorldImage)(new FrameImage(new Posn(Utils.WIDTH/2, Utils.HEIGHT/2), Utils.WIDTH, Utils.HEIGHT, new Black()));
	}

	// EFFECT: Changes the position of this Actor based on dx and dy
	void move() {}

	// Returns true if this Actor is hit by given Missile
	boolean gotHit(Missile m) {
		int halfWidth = (Integer)(this.img.getWidth() / 2);
		int halfHeight = (Integer)(this.img.getHeight() / 2);

		return (m.x >= (this.x - halfWidth) && m.x <= (this.x + halfWidth) &&
				(m.y <= (this.y + halfHeight) && m.y >= (this.y - halfHeight))); 
	}

	// updates this Actor when they are hit; returns null when Actor is destroyed
	Actor onHit() {
		return this;
	}  
} 