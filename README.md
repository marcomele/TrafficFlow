# TrafficFlow
Java/Spark and python analysis of traffic flow data in Turin, IT

branch master:

* spark/java application : read the xml file and generate csv
* python : generic analysis

branch AVGSpeedPerStreet:
* spark/java application : read the xml file and generate a (street, avgSpeed) pairRDD and save as text file

## Dataset features

Feature | Description
------------ | -------------
Lcd1 | Location code of the starting TMC point of the current edge.
Road_LCD | Location code of the TMC road to which this TMC point belongs
Road_name | Road name to which this TMC point belongs
Offset | Distance from the beginning of the edge along the travel direction [m]
Direction | Travel direction (positive or negative)
Latitude | Latitude of this TMC point
Longitude | Longitude of this TMC point
Accuracy | Measure accuracy (percentage)
Period | Aggregation period [minutes]
Date | Measurement date
Flow | Vehicle flow [vehicles/h]
Speed | Average speed [km/h]

[Source : http://www.5t.torino.it/simone/ns/traffic_data.xsd ]

