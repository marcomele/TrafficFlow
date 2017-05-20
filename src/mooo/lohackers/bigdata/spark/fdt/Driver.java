package mooo.lohackers.bigdata.spark.fdt;

import java.io.File;
import java.util.ArrayList;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.*;

import scala.Tuple2;

public class Driver {

	public static void main(String[] args) {
		
		JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("Turin Traffic Flow"));
		
		JavaRDD<String> inputRDD = sc.emptyRDD();
		
		for(String string : args) {
			if(new File(string) != null)
				inputRDD.union(sc.textFile(string));
		}
		
		JavaRDD<TrafficFlowEntry> entriesRDD = inputRDD
				.map(string -> {
					String[] fields = string.split("\"");
					return new TrafficFlowEntry(fields[1], 
							fields[3], fields[5], Long.valueOf(fields[7]), 
							fields[9].equals("positive") ? true : false, 
							Double.parseDouble(fields[11]), Double.valueOf(fields[13]), 
							Integer.valueOf(fields[15]), fields[19], Double.valueOf(fields[21]), Double.valueOf(fields[23]));
				});
		
		JavaPairRDD<String, Double> averageSpeedPerStreetRDD = entriesRDD
				.mapToPair(trafficFlowEntry -> new Tuple2<String,Double>(trafficFlowEntry.getRoadName(), trafficFlowEntry.getSpeed()))
				.groupByKey()
				.mapValues(iterableDoubleSpeed -> {
					ArrayList<Double> speeds = new ArrayList<>();
					iterableDoubleSpeed.forEach(speeds::add);
					return speeds.stream().mapToDouble(d -> d).average().orElseGet(() -> new Double(-1.0));
				});
		
		averageSpeedPerStreetRDD.saveAsTextFile("averageSpeedPerStreetOutput");
		
		sc.close();

	}

}