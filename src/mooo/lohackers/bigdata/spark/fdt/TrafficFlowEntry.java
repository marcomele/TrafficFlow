package mooo.lohackers.bigdata.spark.fdt;

import java.io.Serializable;

public class TrafficFlowEntry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -435808009787910196L;
	private String sensorId;
	private String roadId;
	private String roadName;
	private Long offset;
	private boolean direction;
	private Double latitude;
	private Double longitude;
	private Integer accuracy;
	private String date;
	private Double flow;
	private Double speed;
	public TrafficFlowEntry(String sensorId, String roadId, String roadName, Long offset, boolean direction,
			Double latitude, Double longitude, Integer accuracy, String date, Double flow, Double speed) {
		super();
		this.sensorId = sensorId;
		this.roadId = roadId;
		this.roadName = roadName;
		this.offset = offset;
		this.direction = direction;
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.date = date;
		this.flow = flow;
		this.speed = speed;
	}	@Override
	public String toString() {
		return new String(sensorId + ";"
				+ roadId + ";"
				+ roadName + ";"
				+ offset.toString() + ";"
				+ (direction ? "positive" : "negative") + ";"
				+ latitude.toString() + ";"
				+ longitude.toString() + ";"
				+ accuracy.toString() + ";"
				+ date + ";"
				+ flow.toString() + ";" 
				+ speed.toString()); 
	}
	public String getSensorId() {
		return sensorId;
	}
	public String getRoadId() {
		return roadId;
	}
	public String getRoadName() {
		return roadName;
	}
	public Long getOffset() {
		return offset;
	}
	public boolean isDirection() {
		return direction;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Integer getAccuracy() {
		return accuracy;
	}
	public String getDate() {
		return date;
	}
	public Double getFlow() {
		return flow;
	}
	public Double getSpeed() {
		return speed;
	}
}
