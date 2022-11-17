package Part_2.Inteface;

import java.io.Serializable;
import java.util.List;

public class AirlineTrip implements Serializable {
    private List<Airline> airlines;
    private List<Trip> trips;
    public AirlineTrip(List<Airline> airlines,List<Trip> trips){
        this.airlines = airlines;
        this.trips = trips;
    }

    public List<Airline> getAirlines() {
        return airlines;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
