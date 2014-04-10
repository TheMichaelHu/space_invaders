import java.util.ArrayList;
import java.util.HashMap;
import javalib.worldimages.*;
import javalib.funworld.*;

public class SpaceInvaders extends World {
	Player p;
	ArrayList<ArrayList<Alien>> alienList;
	HashMap<String, Missile> misList;
	Saucer s;
	int score;

	SpaceInvaders(Player p, ArrayList<ArrayList<Alien>> alienList) {
		super();
		this.p = p;
		this.alienList = alienList;
		this.misList = new HashMap<String, Missile>();
		this.s = new Saucer(-20, 20); //magic numbers TODO
		this.score = 0;
	}

	public World onTick() {
		return this;
		// TODO
	}

	public WorldImage makeImage() {
		return this.p.drawOn(this.s.drawOn(this.drawAliensOn(this.drawMissilesOn(Utils.SCENE))));
	}
	
	public WorldImage drawMissilesOn(WorldImage img) {
		return img;
		// TODO
	}
	
	public WorldImage drawAliensOn(WorldImage img) {
		return img;
		// TODO
	}

	public World onKeyEvent(String key) {
		return this;
		// TODO
	}

	public WorldImage lastImage(String str) {
		return this.makeImage();
		// TODO
	}

	public WorldEnd worldEnds() {
		return lastWorld;
		// TODO
	}
}