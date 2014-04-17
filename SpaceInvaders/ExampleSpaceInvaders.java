import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javalib.worldimages.*;
import javalib.colors.*;
import javalib.funworld.*;
import tester.*;

public class ExampleSpaceInvaders {

	boolean runAnimation = new SpaceInvaders().bigBang(Utils.WIDTH, Utils.HEIGHT, .03);

	// Test data to be put into testSpaceInvaders
	Player testPlayer = new Player(250, 250, Utils.PLAYER_SPD, 3);
	Alien testAlien1 = new Alien(250, 250);
	Alien testAlien2 = new Alien(250 + Utils.INIT_ALIEN_SPD, 250);
	Alien testAlien3 = new Alien(250, 250);
	StrongAlien testStrongAlien1 = new StrongAlien(250, 250);
	Missile testMissile1 = new Missile(300, 250, Utils.MISSILE_SPD);
	Missile testMissile2 = new Missile(250, 250, Utils.MISSILE_SPD);
	Missile testMissile3 = new Missile(20, 20, Utils.MISSILE_SPD);
	Saucer testSaucer1 = new Saucer(20, 20);
	Saucer testSaucer2 = new Saucer(20, 20);
	Saucer testSaucer3 = new Saucer(Utils.WIDTH+Utils.SAUCER_WIDTH+1, 20);
	Shield testShield = new Shield(250);
	FastPowerUp testPowerUp1 = new FastPowerUp(250, 250, 10); // not active
	FastPowerUp testPowerUp2 = new FastPowerUp(250, 250, 10); // active
	
	
	void setSaucer() {
		this.testSaucer2.canMove = true;
	}
	
	void setPowerUp() {
		this.testPowerUp2.isActive = true;
	}


	// Reset Method
	void reset() {
		testPlayer = new Player(250, 250, Utils.PLAYER_SPD, 3);
		testAlien1 = new Alien(250,250);
		testAlien2 = new Alien(250 + Utils.INIT_ALIEN_SPD, 250);
		testAlien3 = new Alien(250, 250);
		testStrongAlien1 = new StrongAlien(250, 250);
		testMissile1 = new Missile(300, 250, Utils.MISSILE_SPD);
		testMissile2 = new Missile(250, 250, Utils.MISSILE_SPD);
		testMissile3 = new Missile(20, 20, Utils.MISSILE_SPD);
		testSaucer1 = new Saucer(20, 20);
		testSaucer2 = new Saucer(20,20);
		testSaucer3 = new Saucer(Utils.WIDTH+Utils.SAUCER_WIDTH+1, 20);
		testShield = new Shield(250);
		testPowerUp1 = new FastPowerUp(250, 250, 10);
		testPowerUp2 = new FastPowerUp(250, 250, 10);
		this.setSaucer();
		this.setPowerUp();
		
	}

	/* ==================================== TEST ALIEN METHODS ==================================== */ 
	// Test act() in Alien 
	void testAlienAct(Tester t) {
		this.testAlien1.act();
		t.checkExpect(this.testAlien1.x, 250+Utils.INIT_ALIEN_SPD);
		
		this.reset();
	}

	// Test move(), moveDown(), moveBy(int), swapDir() in Alien
	void testAlienMove(Tester t) {
		this.testAlien1.move(); 
		t.checkExpect(this.testAlien1.x, 250+Utils.INIT_ALIEN_SPD, "move Alien test" + "\n");

		this.testAlien2.moveDown();
		t.checkExpect(this.testAlien2.y, 265, "Alien moveDown test"+ "\n");
		t.checkExpect(this.testAlien2.dx, -Utils.ACCELERATION);

		this.testAlien3.moveBy(100);
		t.checkExpect(this.testAlien3.x, 350);	

		this.testAlien1.swapDir();
		t.checkExpect(this.testAlien1.dx, -Utils.INIT_ALIEN_SPD);

		this.reset();
	}

	// Test fire() in Alien
	void testAlienFire(Tester t) {
		t.checkExpect(this.testAlien1.fire().x, this.testAlien1.x);
		t.checkExpect(this.testAlien1.fire().y, ((this.testAlien1.y + Utils.ALIEN_HEIGHT/2) + 1));
		t.checkExpect(this.testAlien1.fire().dy, Utils.ALIEN_MISSILE_SPD);

		this.reset();
	}

	// Test gotHit(Actor), onHit() in Alien
	void testAlienHit(Tester t) {
		t.checkExpect(this.testAlien1.gotHit(this.testPlayer), null);
		t.checkExpect(this.testAlien1.gotHit(this.testMissile1), this.testAlien1);
		t.checkExpect(this.testAlien1.onHit(), null);

		this.reset();
	}

	// Test onHit() in StrongAlien
	void testStrongAlienHit(Tester t) {
		Alien a = (Alien)this.testStrongAlien1.onHit();

		t.checkExpect(a.x, 250);
		t.checkExpect(a.y, 250);
		t.checkExpect(a.dx, Utils.INIT_ALIEN_SPD);
		t.checkExpect(a.invasionProgress, 0);

		this.reset();
	}


	/* ==================================== TEST PLAYER METHODS ==================================== */ 

	// Test moveLeft(), moveRight(), moveY() in Player class
	void testPlayerMove(Tester t) {
		this.testPlayer.moveLeft();
		t.checkExpect(this.testPlayer.x, 250-Utils.PLAYER_SPD);

		this.testPlayer.moveRight();
		t.checkExpect(this.testPlayer.x, 250);

		this.testPlayer.moveUp();
		t.checkExpect(this.testPlayer.y, 240);
		
		this.testPlayer.moveDown();
		t.checkExpect(this.testPlayer.y, 250);

		this.reset();
	}

	// Test fire() in Player class
	void testPlayerFire(Tester t) {
		t.checkExpect(this.testPlayer.fire().x, this.testPlayer.x);
		t.checkExpect(this.testPlayer.fire().y, 250 - (Utils.PLAYER_HEIGHT/2 + 1));
		t.checkExpect(this.testPlayer.fire().dy, -Utils.MISSILE_SPD);

		this.reset();
	}

	// Test gotHit(Actor) and onHit() in Player class
	void testPlayerHit(Tester t) {
		//Player p = (Player)this.testPlayer.gotHit(this.testMissile2);
		//t.checkExpect(p.getLives(), 2);
		t.checkExpect(this.testPlayer.gotHit(this.testMissile1), this.testPlayer);

		this.reset();
	}

	/* ==================================== TEST SAUCER METHODS ==================================== */ 
	
	// Test reset() in Saucer
	void testSaucerReset(Tester t) {
		this.testSaucer1.reset();
		t.checkExpect(this.testSaucer1.canMove, false);
		t.checkExpect(this.testSaucer1.x, -Utils.SAUCER_WIDTH);
		t.checkExpect(this.testSaucer1.img.pinhole, new Posn(-Utils.SAUCER_WIDTH, this.testSaucer1.y));

		this.reset();
	}
	
	// Test move() in Saucer
	void testSaucerMove(Tester t) {
		this.testSaucer1.move();
		t.checkExpect(this.testSaucer1.x, 20+Utils.SAUCER_SPD);

		this.reset();
	}
	
	// Test act() in Saucer
	void testSaucerAct(Tester t) {
		this.testSaucer2.act();
		t.checkExpect(this.testSaucer2.x, 20+Utils.SAUCER_SPD);
		
		this.testSaucer1.act();
		t.checkExpect(this.testSaucer1.x, 20);
		this.reset();
	}
	
	// Test onHit() in Saucer
	void testSaucerHit(Tester t) {
		this.testSaucer2.onHit();
		t.checkExpect(this.testSaucer2.canMove, false);
		t.checkExpect(this.testSaucer2.x, -Utils.SAUCER_WIDTH);
		t.checkExpect(this.testSaucer2.onHit(), null);
		
		this.reset();
	}

	/* ==================================== TEST SHIELD METHODS ==================================== */
	
	// Test onHit() in Shield
	void testShieldHit(Tester t) {
		t.checkExpect(this.testShield.onHit(), null);
		
		this.reset();
	}
	
	/* ==================================== TEST POWERUP METHODS ==================================== */
	
	// Test act() in PowerUp
	void testPowerUpAct(Tester t) {
		this.testPowerUp1.act();
		t.checkExpect(this.testPowerUp1.time, 10);
		
		this.testPowerUp2.act();
		t.checkExpect(this.testPowerUp2.time, 11);
		this.testPowerUp1.time = 300;
		this.testPowerUp1.act();
		t.checkExpect(this.testPowerUp1.isActive, false);
		
		this.reset();
	}
	
	// Test gotHit(Actor) and onHit(Player) in PowerUp
	/*
	void testPowerUpHit(Tester t) {
		Player p = (Player)this.testPowerUp1.gotHit(this.testPlayer);
		t.checkExpect(p.dx, 30);
		
	}*/
	
	// Test nextPowerUp(int) in PowerUp
	void testNextPowerUp(Tester t) {
		t.checkExpect(this.testPowerUp2.nextPowerUp(100), this.testPowerUp2);
		
		this.testPowerUp1.wasActivated = true;
		//t.checkExpect(this.testPowerUp1.nextPowerUp(100), new EmptyPowerUp());
		
		this.reset();
	}
	
	
	
}