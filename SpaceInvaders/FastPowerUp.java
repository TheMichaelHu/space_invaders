import javalib.worldimages.*;

public class FastPowerUp extends PowerUp {

	FastPowerUp(int x, int y, int time) {
		super(x, time);
		this.img = new FromFileImage(new Posn(x, this.y), Utils.FAST_IMG);
	}
	
	// Returns a new faster Player
	Player onHit(Player p) {
		return new Player(p.x, p.y, (p.dx * 3), p.lives);
	}
}