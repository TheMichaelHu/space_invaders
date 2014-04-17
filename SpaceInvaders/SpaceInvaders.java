import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javalib.worldimages.*;
import javalib.colors.*;
import javalib.funworld.*;

public class SpaceInvaders extends World {
	Player p;
	ArrayList<ArrayList<Alien>> alienList;
	HashMap<String, Missile> misList;
	Saucer s;
	int score;
	PowerUp powerup;
	int time;
	ArrayList<Shield> shieldList;
	int level;

	SpaceInvaders() {
		super();
		this.p = new Player(Utils.WIDTH/2, Utils.HEIGHT-Utils.CUSHION);
		this.alienList = new ArrayList<ArrayList<Alien>>();
		this.misList = new HashMap<String, Missile>();
		this.s = new Saucer(-Utils.SAUCER_WIDTH, 2*Utils.CUSHION-Utils.SAUCER_HEIGHT);
		this.score = 0;
		this.powerup = new EmptyPowerUp();
		this.time = 0;
		this.shieldList = new ArrayList<Shield>();
		this.level = 1;

		// Adding Aliens to alienList
		for(int c = 0; c < Utils.COLUMNS; c++) {
			alienList.add(new ArrayList<Alien>());
			for(int r = 0; r < Utils.ROWS; r++) {
				if((c+r)%2 == 0) { // Predicate to determine placement of Strong Aliens
					alienList.get(c).add(new StrongAlien(Utils.ALIEN_WIDTH/2 + Utils.CUSHION*c, Utils.ALIEN_HEIGHT + Utils.CUSHION*(r + 2)));
				} else {
					alienList.get(c).add(new Alien(Utils.ALIEN_WIDTH/2 + Utils.CUSHION*c, Utils.ALIEN_HEIGHT + Utils.CUSHION*(r + 2)));
				}
			}
		}

		// Adding Shields to shieldList
		for(int i = 0; i < Utils.SHIELD_NUM; i++) {
			this.shieldList.add(new Shield((int)((i+1) * Utils.WIDTH/(Utils.SHIELD_NUM +1))));
		}
	}

	// Calls act() on all actors, checks for collisions, and randomly unleashes the Saucer and alien missiles
	public World onTick() {
		ArrayList<String> keysToRemove = new ArrayList<String>();
		Random chance = new Random();

		// Updating Aliens
		for(int c = 0; c < alienList.size(); c++) {
			for(int r = 0; r < alienList.get(c).size(); r++) {
				Alien a = this.alienList.get(c).get(r);
				a.act();

				if(r == alienList.get(c).size() - 1 && chance.nextInt(1000) <= Utils.FIRE_CHANCE && misList.get("alien" + c) == null) {
					misList.put("alien" + c, a.fire());
				}
			}
		}

		ArrayList<Alien> lastCol = this.alienList.get(this.alienList.size() - 1);
		ArrayList<Alien> firstCol = this.alienList.get(0);

		// Moving Aliens down
		if ((lastCol.get(0).x > Utils.WIDTH - Utils.ALIEN_WIDTH/2) ||
				(firstCol.get(0).x < Utils.ALIEN_WIDTH/2)) { 
			for(ArrayList<Alien> list:this.alienList) {
				for(Alien a: list) {
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

		// Saucer logic + movement
		if(chance.nextInt(1000) <= Utils.SAUCER_CHANCE && !this.s.canMove) {
			this.s.canMove = true;
		}
		this.s.act();

		// Moving Missiles
		for (Map.Entry<String, Missile> entry : this.misList.entrySet()) {
			entry.getValue().act();

			if (entry.getValue().y < -Utils.MISSILE_HEIGHT/2 ||
					entry.getValue().y > Utils.HEIGHT + Utils.MISSILE_HEIGHT/2 ||
					entry.getValue().x < -Utils.MISSILE_WIDTH/2 ||
					entry.getValue().x > Utils.WIDTH + Utils.MISSILE_WIDTH/2) {
				keysToRemove.add(entry.getKey());
			}

			// Alien collision detection
			for(int c = 0; c < alienList.size(); c++) {
				int prevKeysToRemove = keysToRemove.size();
				for(int r = 0; r < alienList.get(c).size(); r++) {
					Alien a = this.alienList.get(c).get(r);
					Alien newAlien = (Alien) entry.getValue().hit(a);
					if(newAlien == null) {
						this.score += a.value;
						this.alienList.get(c).remove(r);
						if(this.alienList.get(c).size() == 0) {
							this.alienList.remove(c);
							break;
						}
						keysToRemove.add(entry.getKey());
						break;
					} else if(!newAlien.equals(a)) {
						this.score += a.value;
						this.alienList.get(c).set(r, newAlien);
						keysToRemove.add(entry.getKey());
						break;
					}
				}
				if(keysToRemove.size() != prevKeysToRemove) {
					break; // Once an Alien has been hit there no point in checking other columns
				}
			}

			// Saucer collision detection
			if(entry.getValue().hit(this.s) == null) {
				this.score += this.s.value;
				keysToRemove.add(entry.getKey());
			}

			// Player collision detection
			if(entry.getValue().hit(this.p) == null) {
				keysToRemove.add(entry.getKey());
			}

			// Shield collision detection
			for (Shield s : this.shieldList) {
				if (entry.getValue().hit(s) == null) {
					keysToRemove.add(entry.getKey());
				}
			}
		}

		// Removes off-screen and exploded Missiles
		for(int i = 0; i < keysToRemove.size(); i++) {
			this.misList.remove(keysToRemove.get(i));
		}

		// PowerUp creation
		this.powerup.act();
		if(chance.nextInt(1000) <= Utils.POWER_CHANCE) {
			//once we make all powerups, randomly choose from them
			this.powerup = this.powerup.nextPowerUp(this.time);
		}

		// PowerUp collision
		this.p = (Player)this.powerup.gotHit(this.p);

		// Level Advancement Logic
		if(this.alienList.isEmpty() && this.level < 5) {
			// Adding Aliens to alienList
			for(int c = 0; c < Utils.COLUMNS; c++) {
				alienList.add(new ArrayList<Alien>());
				for(int r = 0; r < Utils.ROWS; r++) {
					if((c+r)%2 == 0) { // Predicate to determine placement of Strong Aliens
						alienList.get(c).add(new StrongAlien(Utils.ALIEN_WIDTH/2 + Utils.CUSHION*c, Utils.ALIEN_HEIGHT + Utils.CUSHION*(r + 2)));
					} else {
						alienList.get(c).add(new Alien(Utils.ALIEN_WIDTH/2 + Utils.CUSHION*c, Utils.ALIEN_HEIGHT + Utils.CUSHION*(r + 2)));
					}
				}
			}
			this.level++;
		}

		// Incrementing time
		this.time++;
		return this;

	}

	// Produces an image representation of this World
	public WorldImage makeImage() {
		return this.drawDataOn(this.p.drawOn(this.drawShieldsOn(this.drawPowerUpOn(this.s.drawOn(this.drawAliensOn(this.drawMissilesOn(Utils.SCENE)))))));
	}

	// Draws the PowerUp onto the given scene
	public WorldImage drawPowerUpOn(WorldImage img) {
		if (this.powerup == null) {
			return img;
		} else {
			return this.powerup.drawOn(img);
		}
	}

	// Draws all Missiles onto the given scene
	public WorldImage drawMissilesOn(WorldImage img) {
		for (Map.Entry<String, Missile> entry : this.misList.entrySet()) {
			img = entry.getValue().drawOn(img);
		}
		return img;
	}

	// Draws all Aliens onto the given scene
	public WorldImage drawAliensOn(WorldImage img) {
		for(ArrayList<Alien> list:this.alienList) {
			for(Alien a: list) {
				img = a.drawOn(img);
			}
		}
		return img;
	}

	// Draws the Shields onto the given scene
	public WorldImage drawShieldsOn(WorldImage img) {
		for(Shield s: this.shieldList) {
			img = s.drawOn(img);
		}
		return img;
	}

	// Draws game data onto the given scene
	public WorldImage drawDataOn(WorldImage img) {
		return new OverlayImages(
				new OverlayImages(
						new OverlayImages(img, new TextImage(new Posn(Utils.SCORE_X, Utils.SCORE_Y), 
								"Score: " + this.score, 25, new White())),
								new TextImage(new Posn(Utils.WIDTH - Utils.SCORE_X, Utils.SCORE_Y),
										"Lives: " + this.p.lives, 25, new White())), 
										new TextImage(new Posn(Utils.WIDTH/2, Utils.SCORE_Y),
												"Level: " + this.level, 25, new White()));
	}

	// Handles key events
	public World onKeyEvent(String key) {
		if (key.equals("left") && this.p.x > Utils.PLAYER_WIDTH/2 && // move left
				!inShields(this.p.x - this.p.dx, this.p.y)) {
			this.p.moveLeft();
		} else if (key.equals("right") && this.p.x < Utils.WIDTH-Utils.PLAYER_WIDTH/2 && // move right
				!inShields(this.p.x + this.p.dx, this.p.y)) {
			this.p.moveRight();
		} else if (key.equals("up") && this.p.y > Utils.PLAYER_HEIGHT/2 && // move up
				!inShields(this.p.x, this.p.y - this.p.dy)) {
			this.p.moveUp();
		} else if (key.equals("down") && this.p.y < Utils.HEIGHT-Utils.PLAYER_HEIGHT/2 && // move down
				!inShields(this.p.x, this.p.y + this.p.dy)) {
			this.p.moveDown();
		} else if ((key.equals(" ")) && (this.misList.get("player") == null)) { // fire
			Missile mis = this.p.fire();
			this.misList.put("player", mis);
			ArrayList<Missile> moreMis = mis.getMissiles();
			for(int i = 0; i < moreMis.size(); i++) {
				this.misList.put("aux" + i, moreMis.get(i));
			}
		}
		return this;
	}

	// returns true if the given point (x,y) is within one of the shields
	boolean inShields(int x, int y) {
		for(Shield s : this.shieldList) {
			if(s.isWithin(x, y)) {
				return true;
			}
		}
		return false;
	}

	// produces the lose screen
	public WorldImage loseScreen() {
		return new OverlayImages(
				new OverlayImages(
						new RectangleImage(new Posn(Utils.WIDTH/2, Utils.HEIGHT/2), Utils.WIDTH, Utils.HEIGHT, new Black()),
						new TextImage(new Posn(Utils.WIDTH/2, Utils.HEIGHT/3), "You suck.", 100, new Red())),
						new TextImage(new Posn(Utils.WIDTH/2, 2*Utils.HEIGHT/3), "Your sucky score: " + this.score, 50, new Red()));
	}

	// produces the win screen
	public WorldImage winScreen() {
		return new OverlayImages(
				new OverlayImages(
						new RectangleImage(new Posn(Utils.WIDTH/2, Utils.HEIGHT/2), Utils.WIDTH, Utils.HEIGHT, new Black()),
						new TextImage(new Posn(Utils.WIDTH/2, Utils.HEIGHT/3), "You have saved Earth!", 80, new Green())),
						new TextImage(new Posn(Utils.WIDTH/2, 2*Utils.HEIGHT/3), "Your amazing score: " + this.score, 50, new Green()));
	}

	// Logic for when the game is over
	public WorldEnd worldEnds() {
		if(this.p.lives <= 0 || // Player loses all lives
				this.earthInvaded()) { // Aliens invade earth
			return new WorldEnd(true, this.loseScreen());
		} else if(this.level >= 5 && this.alienList.isEmpty()) { // All alien waves vanquished
			return new WorldEnd(true, this.winScreen());
		} else {
			return new WorldEnd(false, this.makeImage());
		}
	}

	// returns true if the aliens have invaded earth
	boolean earthInvaded() {
		for(int c = 0; c < alienList.size(); c++) {
			for(int r = 0; r < alienList.get(c).size(); r++) {
				if(r == this.alienList.get(c).size()-1 &&
						this.alienList.get(c).get(r).y > Utils.HEIGHT-Utils.CUSHION) {
					return true;
				}
			}
		}
		return false;
	}
}