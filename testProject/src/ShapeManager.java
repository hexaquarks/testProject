import java.util.ArrayList;
import java.util.Iterator;

public class ShapeManager {
	public String shapeType; // circle , square, pentagon , hexagon 
	Point2D center;

	public ShapeManager(Point2D panelSize) {
		this.center= panelSize;
	}
	static ArrayList<Point2D> coordinates = new ArrayList<Point2D>();


	public ArrayList<Point2D> getCopy() {
		return (ArrayList<Point2D>) coordinates.clone();
	}

	public void circleCoords(ArrayList<Particle> particles) {
		int n = particles.size();
		double alpha = Math.toRadians(360.0/n);    // angle of each triangle in the polygon
		float angle = 0;
		double pW = particles.get(0).width;
		double pH = particles.get(0).height;
		float side = (float) (particles.get(0).width);
		float radius = (float) (side / (2*Math.sin(Math.PI / n)));
		if(!(2*radius >= center.x || 2*radius >= center.y)) {

			for(int i = 0 ; i < n ; i++) {
				this.coordinates.add(new Point2D((center.x + Math.sin(angle)*radius -pW/2),
						(center.y -Math.cos(angle)*radius) - pH/2));
				angle += alpha;

			}
		}

		//GamePanel.testing = true;

	}
	public ArrayList<Double> distanceCalculator(ArrayList<Particle> pList, ArrayList<Point2D> second) {
		ArrayList<Double> distances = new ArrayList<Double>();
		Iterator<Particle> iterator1 = pList.iterator();
		while(iterator1.hasNext()) {
			Particle iterated = iterator1.next();

			for(Point2D point : second) {
				double d = Math.sqrt(Math.pow(iterated.x -point.x, 2)
						+ Math.pow(iterated.y - point.y, 2));
				distances.add(d);
			}

		}
		return distances;
	}
	public void proximity(ArrayList<Particle> particles){
		ArrayList<Particle> particlesCopy = (ArrayList<Particle>) particles.clone();
		ArrayList<Point2D> coordinatesCopy = this.getCopy();
		ArrayList<Double> distances;

		Iterator<Particle> iterator1 = particlesCopy.iterator();
		while(iterator1.hasNext()) {
			Particle particle = iterator1.next(); 			
			Iterator<Point2D> iterator2 = coordinatesCopy.iterator();
			distances = distanceCalculator(particlesCopy, coordinatesCopy);

			int i = 0;
			while(iterator2.hasNext()) {

				Point2D coordinate = iterator2.next();
				double d = Math.sqrt(Math.pow(particle.x -coordinate.x, 2)
						+ Math.pow(particle.y - coordinate.y, 2));

				if(d == distances.get(i)) {
					coordinate.particle = particle;
					iterator1.remove();
					iterator2.remove();
					i++;
					break;
				}
			}

		}


	}

	public void setSpeed(ArrayList<Particle> particles) {

		for(Point2D point : coordinates) {
			if(point.particle.x - point.x <= 0) {
				point.particle.vx = (-point.particle.x + point.x)/(1000/16);
			} else if(point.particle.x - point.x > 0 ) {
				point.particle.vx = -(point.particle.x - point.x)/(1000/16);
			}

			if(point.particle.y - point.y <= 0) {
				point.particle.vy = (-point.particle.y + point.y)/(1000/16);
			} else if(point.particle.y - point.y > 0 ) {
				point.particle.vy = -(point.particle.y - point.y)/(1000/16);
			}
		}
	}


	public void checkArrival(ArrayList<Particle> particles) {

		for(Point2D p : coordinates) {
			if(p.particle.x >= p.x - p.particle.width/25 && p.particle.x <= p.x + p.particle.width/25) {
				p.particle.vx = 0;

			} 
			if(p.particle.y >= p.y - p.particle.height/25 && p.particle.y <= p.y + p.particle.height/25) {
				p.particle.vy = 0;
			} 
		}
	}


	public void reinitializeCoordinates() {
		this.coordinates = new ArrayList<Point2D>();
	}


}
