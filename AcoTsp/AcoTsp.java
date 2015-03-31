import java.util.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Vector;

public class AcoTsp{

	//Ints
	private static int numAnts;
	private static int numIterations;
	private static int numCities;

	//Doubles
	private static double alpha;
	private static double beta;
	private static double phi;
	private static double epsilon;

	private static Vector<City> cityVector; 
	private static Edge[][] edgeArray;
	private static Ant[] antArray;

	public static void antTest() {
		initAnts();
		for(int i = 0; i < numAnts; i++) {
			System.out.println("Ant " + (i+1) + " has visited "); // + antArray[i].getVisitedCities().elementAt(0));
		}
	}

	//Creates and initializes ants to first city
	private static void initAnts() {
		int city = 0;
		antArray = new Ant[numAnts];

		while(city < numCities) {
			antArray[city] = new Ant(1, 10);
			city++;
		}
	}

	//Calculates the distances between each pair of cities and puts it into the distance[][] array
	private static void calculateDistances(Vector cArray) {

		edgeArray = new Edge[numCities][numCities];
		for(int cityA = 0; cityA < numCities; cityA++) {
			for(int cityB = cityA + 1; cityB < numCities; cityB++) {
				edgeArray[cityA][cityB] = new Edge();
				edgeArray[cityA][cityB].setDistance(Math.sqrt((cityVector.elementAt(cityB).xCoor - cityVector.elementAt(cityA).xCoor) * (cityVector.elementAt(cityB).xCoor - cityVector.elementAt(cityA).xCoor) + (cityVector.elementAt(cityB).yCoor - cityVector.elementAt(cityA).yCoor) * (cityVector.elementAt(cityB).yCoor - cityVector.elementAt(cityA).yCoor)));
			}
		}
	}	

	//Read in the TSP file and initialize cities
	public static void readTSP(String filePath) throws IOException{

		try{
			//Create Reader
			FileReader reader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(reader);

			//Create Variable
			String currentLine;
			String cityCount;
			StringBuilder stringBuilder = new StringBuilder();

			//Read in the header
			while(true){

				currentLine = bufferedReader.readLine();

				//Get the Number of Cities
				if(currentLine.contains("DIMENSION")){
					for(int i = 0;i < currentLine.length(); i++)
					{
						//Create string of number of cities
						if(Character.isDigit(currentLine.charAt(i))){
							stringBuilder.append(currentLine.charAt(i));
						}

					}
				}
				//End loop
				if(currentLine.contains("NODE_COORD_SECTION")){
					break;
				}
			}

			//Set numCities
			cityCount = stringBuilder.toString();
			numCities = Integer.parseInt(cityCount);
			System.out.println("numCities: " + numCities);

			cityVector = new Vector<City>(numCities);

			//Loop to obtain coordinates of cities
			int cityNumber;
			double xCord, yCord;
			while(true){	
				currentLine = bufferedReader.readLine();

				//Break loop at the end of the file
				if(currentLine.contains("EOF")){
					break;
				}

				//Get the cities coordinates
				String[] splited = currentLine.split("\\s+");	
				cityNumber = Integer.parseInt(splited[1]);
				xCord = Double.parseDouble(splited[2]);
				yCord = Double.parseDouble(splited[3]);

				City newCity = new City(xCord, yCord);			
				cityVector.addElement(newCity);
			}	
		}
		catch(IOException e){
		}
	}

	//Main method
	public static void main(String[] args) throws IOException{

		String filePath = " ";

		if(args.length < 1){
			System.out.println("Please enter arguments");
			return;
		}

		filePath = args[0];

		numAnts = Integer.parseInt(args[1]);
		numIterations = Integer.parseInt(args[2]);

		alpha = Double.parseDouble(args[3]);
		beta = Double.parseDouble(args[4]);
		phi = Double.parseDouble(args[5]);
		epsilon = Double.parseDouble(args[6]);

		System.out.println("Num Ants: " + numAnts + " Epsilon: " + epsilon);

		readTSP(filePath);
		calculateDistances(cityVector);	

		antTest();

		//System.out.println(cityVector.elementAt(1).xCoor + " " + cityVector.elementAt(1).yCoor);

		/*for(int i = 0; i < numCities - 1; i++) {
			System.out.println("Distance from city " + (i+1) + " to city " + (i+2) + " is : " + edgeArray[i][i+1].getDistance());
		}*/

	}

}

