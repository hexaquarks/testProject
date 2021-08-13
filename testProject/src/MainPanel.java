import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel{

	static Random rand = new Random();
	static ArrayList<Particle> particleList = new ArrayList<Particle>();
	static int timeTick = 1000/60; //fps normal

	/* 
	 * Timer that updates the screen every at 60 FPS. 
	 */
	Timer fpsTimer = new Timer(timeTick, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				for(Particle p : particleList) {
					p.x += p.vx;
					p.y += p.vy;
				}
			repaint();
		}
	});

	/* 
	 * Timer that updates the physics every 1 millisecond.
	 */
	Timer physicsTimer = new Timer(1 , new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ae) {

				for(Particle p1 : particleList) {		
					for(Particle p2 : particleList) {
						if(p1 == p2) continue;

						// p1.edgeCollision(p2);
						// applyForces(p1, p2);
						// applyCollision(p1, p2);
					}
					p1.x += p1.vx;
					p1.y += p1.vy;
				}	
		}
	});

	/* 
	 * Function that initializes some particles on the canvas at the beggining.
	 */
	public void initializeParticles(int numberOfParticles, int mass, int charge) {

		for(int i = 0 ; i < numberOfParticles ; i++) {
			int xPos;
			int yPos;
			do {
				xPos = rand.nextInt(100)+25;
				yPos = rand.nextInt(100)+25;
				Particle p = new Particle(xPos, yPos, 0, 0,mass, charge); //mass charge at end
				particleList.add(p);
			}while(!particleAlreadyExists(xPos, yPos));
			
		}
	}

	/* 
	 * Draw the particles on the canvas.
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for(Particle particle : particleList) {
			g2d.setColor(Color.WHITE);
			Shape circle = new Arc2D.Double(particle.x, particle.y, particle.width, particle.height,
					0 , 360, Arc2D.CHORD);
			g2d.fill(circle);
		}
	}

	/* 
	 * Checks if a particle already exists at the generated position.
	 */
	public boolean particleAlreadyExists(double x, double y) {
		
		boolean particleExistsAlready = false;
		for(Particle particle : particleList) {
			if(x >= particle.x-particle.width && x <= particle.x+ 2*particle.width &&
					y >= particle.y-particle.height && y <= particle.y + 2*particle.height) {
				particleExistsAlready=true;
				break;
			}
		}
		return particleExistsAlready;
		
	}

	public void addParticle(int mass, int charge) {
		initializeParticles(1, mass, charge);
	}

}