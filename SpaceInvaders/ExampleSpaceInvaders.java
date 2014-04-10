import java.util.ArrayList;

public class ExampleSpaceInvaders {

	Player p1 = new Player(500, 400);
	ArrayList<ArrayList<Alien>> al1 = new ArrayList<ArrayList<Alien>>();
	
	/** run the animation */
	SpaceInvaders w1 = new SpaceInvaders(p1, al1);
	
	// run the first game
	boolean runAnimation = this.w1.bigBang(Utils.WIDTH, Utils.HEIGHT, .3);

}
