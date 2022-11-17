package Part_2.Server;

import Part_2.Inteface.Airline;
import Part_2.Inteface.AirlineTrip;
import Part_2.Inteface.ExecutorAirport;
import Part_2.Inteface.Trip;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExecutorAirportImpl extends UnicastRemoteObject implements ExecutorAirport {
    private Connection connection1;
    private Connection connection2;
    private Statement statement;
    private Statement statement2;
    public ExecutorAirportImpl() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/airport", "root", "rudenko1234567");
        statement = connection1.createStatement();
        connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/airport", "root", "rudenko1234567");
        statement2 = connection2.createStatement();
    }
    @Override
    public boolean add_new_airline(int id, String name) {
        try{
            String sql_command = "INSERT airlines VALUE(" + id + "," + " \" " + name  + "\"" + ");";
            statement.executeUpdate(sql_command);
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    @Override
    public boolean delete_airline_by_id(int id){
        try{
            String sql_command = "DELETE FROM airlines WHERE id_airline =" + id + ";";
            int count = statement.executeUpdate(sql_command);
            if(count > 0){
                return true;
            }
            return false;
        }catch(SQLException e){
            return false;
        }
    }
    @Override
    public boolean add_trip(int id, String city_to, String city_from, double price, int id_airline){
        try{
            String sql_command = "INSERT INTO trips VALUE (" + id +","+
                    "\"" + city_to + "\"" +","+
                    "\"" + city_from + "\"" + "," + price + "," + id_airline +");";
            statement.executeUpdate(sql_command);
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    @Override
    public boolean delete_trip_by_id(int id){
        try{
            String sql_command = "DELETE FROM trips WHERE id_trip =" + id + ";";
            int count = statement.executeUpdate(sql_command);
            if(count > 0){
                return true;
            }
            return false;
        }catch(SQLException e){
            return false;
        }
    }
    @Override
    public boolean edit_trip(int id, String city_to, String city_from, double price){
        try{
            String sql_command = "UPDATE trips SET city_to =" + "\""+ city_to + "\"" + "," +
                    "city_from =" + "\""+ city_from + "\"" + "," +
                    "price =" + price + " WHERE id_trip =" + id + ";";
            statement.executeUpdate(sql_command);
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    @Override
    public Trip show_trip(int id) {
        try{
            String sql_command = "SELECT*FROM trips WHERE id_trip =" + id + ";";
            ResultSet res = statement.executeQuery(sql_command);
            Trip trip = null;
            while(res.next()){
                int id_res = res.getInt("id_trip");
                String city_to =  res.getString("city_to");
                String city_from =  res.getString("city_from");
                double price = res.getDouble("price");
                int id_airline = res.getInt("id_airl");
                trip = new Trip(id_res, city_from,city_to,price,id_airline);
            }
            return trip;
        }catch(Exception e) {
            throw new RuntimeException();
        }
    }
    @Override
    public AirlineTrip get_all(){
        try{
            List<Airline> airlines = new ArrayList<>();
            List<Trip> trips = new ArrayList<>();
            String sql_command_airlines = "SELECT * FROM airlines;";
            String sql_command_trips = "SELECT * FROM trips;";
            ResultSet res_airlines = statement.executeQuery(sql_command_airlines);
            ResultSet res_trips = statement2.executeQuery(sql_command_trips);
            Airline airline = null;
            while(res_airlines.next()){
                int id_airline = res_airlines.getInt("id_airline");
                String name = res_airlines.getString("name");
                airline = new Airline(id_airline, name);
                airlines.add(airline);

            }
            Trip trip = null;
            while(res_trips.next()){
                trip = new Trip(res_trips.getInt("id_trip"), res_trips.getString("city_from"),
                    res_trips.getString("city_to"), res_trips.getDouble("price"),
                    res_trips.getInt("id_airl"));
                trips.add(trip);
            }
            return new AirlineTrip(airlines,trips);

        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
    @Override
    public List<Airline> get_all_airlines(){
        List<Airline> airlines = new ArrayList<>();
        String sql_command_airlines = "SELECT * FROM airlines;";
        try {
            ResultSet res_airlines = statement.executeQuery(sql_command_airlines);
            Airline airline = null;
            while(res_airlines.next()){
                int id_airline = res_airlines.getInt("id_airline");
                String name = res_airlines.getString("name");
                airline = new Airline(id_airline, name);
                airlines.add(airline);

            }
            return airlines;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Trip> get_trips_by_airline(String name){
        try{
            List<Trip> trips = new ArrayList<>();
            String sql_command = "SELECT * FROM trips INNER JOIN airlines ON airlines.name =" +"\"" + name + "\"" + "\n" +
                    "WHERE airlines.id_airline = trips.id_airl;";
            ResultSet res_trips = statement.executeQuery(sql_command);
            Trip trip = null;
            while(res_trips.next()){
                trip = new Trip(res_trips.getInt("id_trip"), res_trips.getString("city_from"),
                        res_trips.getString("city_to"), res_trips.getDouble("price"),
                        res_trips.getInt("id_airl"));
                trips.add(trip);
            }
            return trips;
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
}
