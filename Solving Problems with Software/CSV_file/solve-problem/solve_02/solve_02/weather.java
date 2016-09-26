/**
 * Write a description of weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class weather {
    // 1
    public CSVRecord coldestHourInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord largestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
		    
		    //If TemperatureF is -9999, drop this filed
		    if(currentRow.get("TemperatureF").equals("-9999")) {
		        continue;
		    }
		    
			//If largestSoFar is nothing
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
				//Check if currentRow’s temperature < largestSoFar’s
				if (currentTemp < largestTemp) {
					//If so update largestSoFar to currentRow
					largestSoFar = currentRow;
				}
			}
		}
		//The largestSoFar is the answer
		return largestSoFar;
	}

	public void testColdestHourInFile() {
		FileResource fr = new FileResource();
		CSVRecord largest = coldestHourInFile(fr.getCSVParser());
		System.out.println("coldest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("DateUTC"));
	}
	
	//2
	public String fileWithColdestTemperature() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		String fileName = null;
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
				//Check if currentRow’s temperature < largestSoFar’s
				if (currentTemp < largestTemp) {
					//If so update largestSoFar to currentRow
					largestSoFar = currentRow;
					fileName = f.getName();
				}
			}
		}
		//The largestSoFar is the answer
		return fileName;
	}
	
	public void testFileWithColdestTemperature(){
		String coldestFileName = fileWithColdestTemperature();
		FileResource fr = new FileResource("nc_weather/2013/" + coldestFileName);
		CSVRecord largest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest day was in file " + coldestFileName);
		System.out.println("coldest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("TimeEST"));
	}
	
	// 3
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord largestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
		    
		    //If TemperatureF is "N/A", drop this filed
		    if(currentRow.get("Humidity").equals("N/A")) {
		        continue;
		    }
		    
			//If largestSoFar is nothing
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
				double largestTemp = Double.parseDouble(largestSoFar.get("Humidity"));
				//Check if currentRow’s temperature < largestSoFar’s
				if (currentTemp < largestTemp) {
					//If so update largestSoFar to currentRow
					largestSoFar = currentRow;
				}
			}
		}
		//The largestSoFar is the answer
		return largestSoFar;
	}
	
	public void testLowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVRecord largest = lowestHumidityInFile(fr.getCSVParser());
		System.out.println("Lowest Humidity was " + largest.get("Humidity") +
				   " at " + largest.get("DateUTC"));
	}
	
	// 4 
	public CSVRecord lowestHumidityInManyFiles() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
				double largestTemp = Double.parseDouble(largestSoFar.get("Humidity"));
				//Check if currentRow’s temperature < largestSoFar’s
				if (currentTemp < largestTemp) {
					//If so update largestSoFar to currentRow
					largestSoFar = currentRow;
				}
			}
		}
		//The largestSoFar is the answer
		return largestSoFar;
	}
	
	public void testLowestHumidityInManyFiles() {
		CSVRecord lowestHumidity = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") +
				   " at " + lowestHumidity.get("DateUTC"));
	}
	
	// 5
	 public double averageTemperatureInFile(CSVParser parser) {
		
	    double sum = 0.0;
	    int count = 0;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
		    
		    //get TemperatureF
		    String temperatureF = currentRow.get("TemperatureF");
		    
		    //If TemperatureF is -9999, drop this filed
		    if(temperatureF.equals("-9999")) {
		        continue;
		    }
		    
		    double currentTemp = Double.parseDouble(temperatureF);
		    sum += currentTemp;
		    count ++;
		}
		
		return sum/count;
	}
		
	

	public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		double AverageTemperature = averageTemperatureInFile(fr.getCSVParser());
		System.out.println("Average temperature in file is " + AverageTemperature);
	}
	
	// 6
	 public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		
	    double sum = 0.0;
	    int count = 0;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
		    
		    //get TemperatureF and Humidity
		    String temperatureF = currentRow.get("TemperatureF");
		    String humidity = currentRow.get("Humidity");
		    
		    //If TemperatureF is -9999, drop this filed
		    if(temperatureF.equals("-9999")) {
		        continue;
		    }
		    
		    //If Humidity is "N/A", drop this filed
		    if(humidity.equals("N/A")) {
		        continue;
		    }
		    
		    int currentHum = Integer.parseInt(humidity);
		    double currentTemp = Double.parseDouble(temperatureF);
		    
		    if (currentHum >= value) {
		        sum += currentTemp;
		        count ++;
		    }
		}
		
		if (count != 0){
		  	return sum/count;
		}else{
		    return 0;
		}

	}
		
	

	public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		double AverageTemperature = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
		if (AverageTemperature !=0 ){
		    System.out.println("Average Temp when high Humidity is " + AverageTemperature);
		}else{
		    System.out.println("No temperatures with that humidity");
		}

	}
}

