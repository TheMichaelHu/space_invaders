import java.util.Random;


public class EmptyPowerUp extends PowerUp{
	Random r;

	EmptyPowerUp() {
		super(0, 0);
		r = new Random();
		this.x = r.nextInt(Utils.WIDTH);
		this.isActive = true;
	}
	
	void act() {}
	
	PowerUp nextPowerUp(int time) {
		return new FastPowerUp(this.x, Utils.PLAYER_HEIGHT, time);
	}
	
	Player gotHit(Actor a) {
		Player p = (Player)a;
		return new Player(p.x, p.y, Utils.PLAYER_SPD, p.lives);
	}
}
