package Part_2.Inteface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.List;

public interface ExecutorAirport extends Remote {
    boolean add_new_airline(int id, String name) throws RemoteException;
    public boolean delete_airline_by_id(int id) throws RemoteException;
    public boolean add_trip(int id, String city_to, String city_from, double price, int id_airline) throws RemoteException;
    public boolean delete_trip_by_id(int id) throws RemoteException;
    public boolean edit_trip(int id, String city_to, String city_from, double price) throws RemoteException;
    public Trip show_trip(int id) throws RemoteException;
    public AirlineTrip get_all() throws RemoteException;
    public List<Airline> get_all_airlines() throws RemoteException;
    public List<Trip> get_trips_by_airline(String name) throws RemoteException;
}
