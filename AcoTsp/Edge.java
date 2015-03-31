import java.util.*;

public class Edge {

		public double distance;
		public double pheramone;

		public Edge() {
			distance = 0.0;
			pheramone = 0.0;
		}

		public Edge(double d, double p) {
			distance = d;
			pheramone = p;
		}

		public double getPheramone() {
			return pheramone;
		}

		public double getDistance() {
			return distance;
		}

		public void setPheramone(double p) {
			pheramone = p;
		}

		public void setDistance(double d) {
			distance = d;
		}
	}

