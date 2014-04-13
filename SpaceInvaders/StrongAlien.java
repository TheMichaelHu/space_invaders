import javalib.worldimages.*;

public class StrongAlien extends Alien {
  
  StrongAlien(int x, int y) {
  	super(x,y);
    this.img = new FromFileImage(new Posn(x,y), Utils.STRONG_IMG);
  }
  
  // updates this Actor when they are hit; returns null when Actor is destroyed
  Actor onHit() {
  	return new Alien(this.x, this.y, this.dx, this.invasionProgress);
  }
}