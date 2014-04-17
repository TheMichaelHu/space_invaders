import java.util.Random;


public class EmptyPowerUp extends PowerUp{
	Random r;

	EmptyPowerUp() {
		super(0, 0);
		r = new Random();
		this.x = r.nextInt(Utils.WIDTH);
	}
	
	void act() {}
	
	PowerUp nextPowerUp(int time) {
		int rand = r.nextInt(2);
		if(rand == 0) {
			return new MultiShotPowerUp(this.x, Utils.PLAYER_HEIGHT, time);
		}
		return new FastPowerUp(this.x, Utils.PLAYER_HEIGHT, time);
	}
}
