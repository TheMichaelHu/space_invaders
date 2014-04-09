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
		this.s = new Saucer(-20, 20); //magic numbers todo
		this.score = 0;
	}

	public World onTick() {
		return null;
		// todo
	}

	public WorldImage makeImage() {
		return null;
		// todo
	}

	public World onKeyEvent(String key) {
		return null;
		// todo
	}

	public WorldImage lastImage(String str) {
		return null;
		// todo
	}

	public WorldEnd worldEnds() {
		return lastWorld;
		// todo
	}
}