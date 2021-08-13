import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class Particle{
	double x, y;
	double width, height;
	double mass;
	double vx, vy;
	int charge;
	public double radius = 0;
	public double centerX = 0;
	public double centerY = 0;
	static Random rand = new Random();

	public Particle(double x, double y, double velX, double velY,double mass, int charge) {
		this.x = x;
		this.y=y;
		this.width=mass/10;
		this.height=mass/10;
		this.vx=velX;
		this.vy = velY;
		this.mass=mass;
		this.charge = charge;
		this.radius = this.width/2;
		this.centerX = this.x + (this.width/2);
		this.centerY = this.y + (this.height/2);
	}


	public static double velInit() {
		double val;
		if(rand.nextBoolean()) {
			val = rand.nextDouble()*0.5;
		} else {
			val = -rand.nextDouble()*0.5;
		}
		return val;
	}

	public void reinitializeVel(ArrayList<Particle> particles) {
		for(Particle p : particles) {
			p.vx = velInit();
			p.vy = velInit();
			
		}
		
	}
}