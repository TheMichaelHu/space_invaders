
public class MultiShotPlayer extends Player {

	MultiShotPlayer(int x, int y, int lives) {
		super(x, y, Utils.PLAYER_SPD, lives);
	}
	
	Missile fire() {
		return new MultiMissile(x, (this.y - (Utils.MISSILE_HEIGHT/2 + Utils.PLAYER_HEIGHT/2)), -Utils.MISSILE_SPD);
	}

}
