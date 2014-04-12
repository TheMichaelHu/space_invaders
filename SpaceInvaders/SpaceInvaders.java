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

	SpaceInvaders() {
		super();
		this.p = new Player(Utils.WIDTH/2, Utils.HEIGHT-Utils.CUSHION);;
		this.alienList = new ArrayList<ArrayList<Alien>>();
		this.misList = new HashMap<String, Missile>();
		this.s = new Saucer(-Utils.SAUCER_WIDTH, Utils.CUSHION-Utils.SAUCER_HEIGHT);
		this.score = 0;

		// Adding Aliens to alienList
		for(int c = 0; c < Utils.COLUMNS; c++) {
			alienList.add(new ArrayList<Alien>());
			for(int r = 0; r < Utils.ROWS; r++) {
				if((c+r)%2 == 0) { // Predicate to determine placement of Strong Aliens
					alienList.get(c).add(new StrongAlien(Utils.ALIEN_WIDTH/2 + Utils.CUSHION*c, Utils.ALIEN_HEIGHT + Utils.CUSHION*(r + 1)));
				} else {
					alienList.get(c).add(new Alien(Utils.ALIEN_WIDTH/2 + Utils.CUSHION*c, Utils.ALIEN_HEIGHT + Utils.CUSHION*(r + 1)));
				}
			}
		}
	}

	public World onTick() {
		return this;
		// TODO
	}

	public WorldImage makeImage() {
		return this.p.drawOn(this.s.drawOn(this.drawAliensOn(this.drawMissilesOn(Utils.SCENE))));
	}

	public WorldImage drawMissilesOn(WorldImage img) {
		for(ArrayList<Alien> list:this.alienList) {
			for(Alien a: list) {
				img = a.drawOn(img);
			}
		}
		return img;
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