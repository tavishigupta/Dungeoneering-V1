import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String args[] ) throws Exception {
		ArrayList<Integer> canyonArray = new ArrayList<Integer>();
		ArrayList<Integer> minimumFlights = new ArrayList<Integer>();
		// used to check if incorrect input is entered
		boolean fail = false;
        
        // creates new file
		String filename = args[0];
		File file = new File(filename);
		BufferedReader reader = null;

		try {
			// if the array is null, throws exception
			if(args.length == 0){
				throw new Exception();
			}

            // reads the file from argument
			FileReader fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			String line = null;

            // parses the flights
			while((line = reader.readLine()) != null) {
				canyonArray.add(Integer.parseInt(line));
			}

			// if first value in the array is 0, throws exception
			if(canyonArray.get(0) == 0){
				throw new Exception();
			}
		} catch (Exception e) {
			fail = true;
			System.out.println("failure");
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				fail = true;
				System.out.println("failure");
			}
		}

		if(!fail){
			// calls helper function to get indices of minimum flights
			minimumFlights = flights(canyonArray, minimumFlights);

			// prints the indices
			for(int i = 0; i < minimumFlights.size(); i++)
				System.out.print(minimumFlights.get(i) + ", ");

			System.out.print("out");
		}
	}

	private static ArrayList<Integer> flights(ArrayList<Integer> canyonArray, 
			ArrayList<Integer> minimumFlights) {
		// initialize last and current reaches, and index of flight
		int last = 0;
		int curr = 0;
		int flightIndex = 0;
		int i;

		for (i = 0; i < canyonArray.size(); i++) {
			// check if index is beyond current reach
			if (i > curr) break;

			// updates the last reach and add index to minimumFlights array
			if (i > last) {
				last = curr;
				minimumFlights.add(flightIndex);
			}

			//
			int sum = i + canyonArray.get(i);

			if (sum > curr) 
				flightIndex = i;

			// update current reach
			curr = Math.max(curr, sum);
		}

		// if i is beyond last reach and flights exist, then add the last selected index
		if ((i > last) && (minimumFlights.size() > 0) && 
				(minimumFlights.get(minimumFlights.size() - 1) != flightIndex)) {
			minimumFlights.add(flightIndex);
		}

		return minimumFlights;
	}
}
