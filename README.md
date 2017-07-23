# TrafficFlow
Java/Spark analysis of traffic flow data in Turin, IT

branch master: spark/java application
  read the xml file and generate csv
  
branch AVGSpeedPerStreet: spark/java application
  read the xml file and generate a (street, avgSpeed) pairRDD and save as text file

## Dataset features

Lcd1 => Location code of the starting TCP point of the current edge.

Road_LCD => Location code of the TMC road to which this TMC point belongs

Road_name => Road name to which this TMC point belongs

Offset => Distance from the beginning of the edge along the travel 

direction [m]

Direction => Travel direction (positive or negative)

Latitude => Latitude of this TCP point

Longitude => Longitude of this TCP point

Accuracy => Measure accuracy (percentage)

Period => Aggregation period [minutes]

Date => Measurement date

Flow => Vehicle flow [vehicles/h]

Speed => Average speed [km/h]

