import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		//System.out.println("ontick");
		// Updating Aliens
		for(ArrayList<Alien> list:this.alienList) {
			for(Alien a: list) {
				a.act();
			}
		}

		ArrayList<Alien> lastCol = this.alienList.get(this.alienList.size() - 1);
		ArrayList<Alien> firstCol = this.alienList.get(0);

		//System.out.println(firstCol.get(0).dx);
		// Moving Aliens down
		if ((lastCol.get(0).x > Utils.WIDTH - Utils.ALIEN_WIDTH/2) ||
				(firstCol.get(0).x < Utils.ALIEN_WIDTH/2)) { 
			for(ArrayList<Alien> list:this.alienList) {
				for(Alien a: list) {
			//		System.out.println("moving down");
					a.moveDown();
					if (a.dx > 0) {
						a.moveBy(Utils.ALIEN_WIDTH/2 - firstCol.get(0).x);
					}
					else {
						a.moveBy(Utils.WIDTH - (lastCol.get(0).x + Utils.ALIEN_WIDTH/2));
					}
				}
			}
		}

		// Moving Missiles
		for (Map.Entry<String, Missile> entry : this.misList.entrySet()) {
			//System.out.println("move missile");
			entry.getValue().move();
		}
		
		// Removes Missile if off-screen
		if ((this.misList.get("player") != null) && (this.misList.get("player").y < -Utils.MISSILE_HEIGHT/2)) {
			this.misList.remove("player");
		}
		
		return this;
	}

	public WorldImage makeImage() {
		return this.p.drawOn(this.s.drawOn(this.drawAliensOn(this.drawMissilesOn(Utils.SCENE))));
	}

	public WorldImage drawMissilesOn(WorldImage img) {
		//System.out.println("draw missiles");
		for (Map.Entry<String, Missile> entry : this.misList.entrySet()) {
			//System.out.println("draw entry");
				img = entry.getValue().drawOn(img);
			}
		return img;
	}

	public WorldImage drawAliensOn(WorldImage img) {
		for(ArrayList<Alien> list:this.alienList) {
			for(Alien a: list) {
				img = a.drawOn(img);
			}
		}
		return img;
	}

	public World onKeyEvent(String key) {
		//System.out.println("onkey");
		if (key.equals("left")) {
			//System.out.println("left");
			this.p.moveLeft();
		} else if (key.equals("right")) {
			//System.out.println("right");
			this.p.moveRight();
		} else if ((key.equals(" ")) && (this.misList.get("player") == null)) {
			System.out.println("spacebar");
			this.misList.put("player",this.p.fire());
		}
		System.out.println(this.misList.get("player") == null);
		return this;
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