import java.util.ArrayList;
import javalib.worldimages.WorldImage;

public class MultiMissile extends Missile {
	ArrayList<Missile> misList = new ArrayList<Missile>();

	MultiMissile(int x, int y, int spd) {
		super(x, y, spd);

		double dTheta = 180/(Utils.MISSILE_NUM + 1);
		for(int i = 0; i < Utils.MISSILE_NUM; i++) {
			this.misList.add(new Missile(this.x, this.y, this.dy, dTheta + dTheta * i+1));
		}
	}

	// Draws this Actor onto the given WorldImage
	WorldImage drawOn(WorldImage img) {
		return img;
	}
	
	Actor hit(Actor a) {
		return a;
	}

	ArrayList<Missile> getMissiles() {
		return this.misList;
	}
}
