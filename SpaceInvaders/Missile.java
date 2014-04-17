import java.util.ArrayList;

import javalib.worldimages.*;

public class Missile extends Actor {
	
	double theta;

	Missile(int x, int y, int spd) {
		super();
		this.x = x;
		this.y = y;
		this.dx = spd;
		this.dy = spd;
		this.theta = 90;
		this.img = new FromFileImage(new Posn(x,y), Utils.MISSILE_IMG);
	}
	
	Missile(int x, int y, int spd, double theta) {
		super();
		this.x = x;
		this.y = y;
		this.dx = spd;
		this.dy = spd;
		this.theta = theta;
		this.img = new FromFileImage(new Posn(x,y), Utils.MISSILE_IMG);
	}

	// EFFECT: Moves this Missile
	void act() {
		this.move();
	}

	// EFFECT: Moves this Missile down by dy
	void move() {
		this.y += Math.sin(this.theta * Math.PI/180) * this.dy;
		this.x += Math.cos(this.theta * Math.PI/180) * this.dx;
		this.img.pinhole = new Posn(this.x, this.y);
	}

	// returns true if this Missile is touching the given Actor
	Actor hit(Actor a) {
		return a.gotHit(this);
	}
	
	ArrayList<Missile> getMissiles() {
		return new ArrayList<Missile>();
	}
}