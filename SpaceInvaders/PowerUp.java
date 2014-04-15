import javalib.worldimages.OverlayImagesXY;
import javalib.worldimages.WorldImage;


abstract public class PowerUp extends Actor {

	int initTime;
	int time;
	boolean isActive;
	boolean wasActivated;

	PowerUp(int x, int time) {
		super();
		this.x = x;
		this.y = Utils.HEIGHT-Utils.CUSHION;
		this.initTime = time;
		this.time = time;
		this.isActive = false;
		this.wasActivated = false;
	}

	void act() {
		if(this.isActive) {
			if(this.time - this.initTime >= Utils.POWER_DURATION) {
				System.out.println("ITS OVER");
				this.isActive = false;
			}
			this.time++;
		}
	}
	
	WorldImage drawOn(WorldImage img) {
		if(this.wasActivated) {
			return img;
		}
		return new OverlayImagesXY(img, this.img, this. x, this.y);
	}

	Actor gotHit(Actor a) {
		int halfWidth = (Integer)(this.img.getWidth() / 2);
		int halfHeight = (Integer)(this.img.getHeight() / 2);

		if (a.x >= (this.x - halfWidth) && a.x <= (this.x + halfWidth) &&
				(a.y <= (this.y + halfHeight) && a.y >= (this.y - halfHeight)) &&
				!this.isActive && !this.wasActivated) {
			System.out.println("ACTIVATED");
			this.isActive = true;
			this.wasActivated = true;
			return this.onHit((Player)a);
		} else {
			return a;
		}
	}

	Player onHit(Player p) {
		return p;
	}

	PowerUp nextPowerUp(int time) {
		if(this.isActive || !this.wasActivated) {
			System.out.println("skjflasd");
			return this;
		} else {
			System.out.println("123123");
			return new EmptyPowerUp();
		}
	}
}