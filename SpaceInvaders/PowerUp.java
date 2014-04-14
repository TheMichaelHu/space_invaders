import javalib.worldimages.*;

abstract public class PowerUp extends Actor {
	
	PowerUp(int x) {
		super();
		this.x = x;
		this.y = Utils.HEIGHT-Utils.CUSHION;
	}
	
	Actor gotHit(Actor a) {
		int halfWidth = (Integer)(this.img.getWidth() / 2);
		int halfHeight = (Integer)(this.img.getHeight() / 2);

		if (a.x >= (this.x - halfWidth) && a.x <= (this.x + halfWidth) &&
				(a.y <= (this.y + halfHeight) && a.y >= (this.y - halfHeight))) {
			return this.onHit((Player)a);
		} else {
			return a;
		}
	}

	Player onHit(Player p) {
		return p;
	}
}