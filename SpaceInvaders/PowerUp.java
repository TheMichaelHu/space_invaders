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
			this.isActive = true;
			this.wasActivated = true;
			return this.onHit((Player)a);
		} else if(!this.isActive && this.wasActivated) {
			return this.unpower((Player)a);
		} else {
			return a;
		}
	}

	Player onHit(Player p) {
		return p;
	}
	
	// Resets the player, removing the powerup
	Player unpower(Player p) {
		return new Player(p.x, p.y, Utils.PLAYER_SPD, p.lives);
	}
	
	// Returns itself if the timer has no run out, an empty powerup otherwise
	PowerUp nextPowerUp(int time) {
		if(this.isActive || !this.wasActivated) {
			return this;
		} else {
			return new EmptyPowerUp();
		}
	}
}