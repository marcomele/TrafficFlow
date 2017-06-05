package mooo.lohackers.bigdata.spark.fdt;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
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
		
				List<String> parsedLine = entriesRDD.map(TrafficFlowEntry::toString).collect();

				try {
					URI uri = URI.create("parsedData.csv");

					FileSystem file = FileSystem.get(uri, new Configuration());
					FSDataOutputStream outputFile = file.create(new Path(uri));

					BufferedWriter bOutFile = new BufferedWriter(new OutputStreamWriter(outputFile, "UTF-8"));
					
					try {
						bOutFile.write(TrafficFlowEntry.getCSVFirstRow());
						bOutFile.newLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					parsedLine.stream()
						.forEach(string -> { 
							try { 
								bOutFile.write(string);
								bOutFile.newLine(); 
							} catch (IOException e) { 
								e.printStackTrace(); 
							} 
						});

					bOutFile.close();
					outputFile.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
		
		sc.close();

	}

}