import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;

public class MultiShotPowerUp extends PowerUp {

	MultiShotPowerUp(int x, int y, int time) {
		super(x, time);
		this.img = new FromFileImage(new Posn(x, this.y), Utils.MULTISHOT_IMG);
	}
	
	// Returns a new faster Player
	Player onHit(Player p) {
		return new MultiShotPlayer(p.x, p.y, p.lives);
	}
}
