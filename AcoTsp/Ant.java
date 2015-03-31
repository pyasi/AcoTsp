import java.util.*;

public class Ant {

		public Vector<Integer> visitedCities;
		public Vector<Integer> availableCities;

		public Ant(int startCity, int numCities) {
			for(int i = 1; i < numCities + 1; i++) {			
				if(i == startCity) {
					visitedCities.addElement(i);
				}
				else {
					availableCities.addElement(i);
				}
			}
		}
		
		public Vector<Integer> getAvailableCities() {
			return availableCities;
		}

		public Vector<Integer> getVisitedCities() {
			return visitedCities;
		}

	}

