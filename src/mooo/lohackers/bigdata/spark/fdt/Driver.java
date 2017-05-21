package mooo.lohackers.bigdata.spark.fdt;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.*;

public class Driver {

	public static void main(String[] args) {
		
		JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("Turin Traffic Flow"));
		
		JavaRDD<String> inputRDD = sc.textFile(args[0]);
		
		JavaRDD<TrafficFlowEntry> entriesRDD = inputRDD
				.map(string -> {
					String[] fields = string.split("\"");
					return new TrafficFlowEntry(fields[1], 
							fields[3], fields[5], Long.valueOf(fields[7]), 
							fields[9].equals("positive") ? true : false, 
							Double.parseDouble(fields[11]), Double.valueOf(fields[13]), 
							Integer.valueOf(fields[15]), fields[19], Double.valueOf(fields[21]), Double.valueOf(fields[23]));
				});
		
		
		sc.close();

	}

}