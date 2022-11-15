package Part_2.Server;

import Part_2.Intarface.ExecutorAirport;

import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class ExecutorAirportImpl extends UnicastRemoteObject implements ExecutorAirport {
    private Connection connection;
    private Statement statement;
    public ExecutorAirportImpl() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airport", "root", "rudenko1234567");
        statement = connection.createStatement();
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
    public ResultSet show_trip(int id) {
        try{
            String sql_command = "SELECT*FROM trips WHERE id_trip =" + id + ";";
            ResultSet res = statement.executeQuery(sql_command);
            return res;
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
    @Override
    public ResultSet get_all(){
        try{
            String sql_command = "SELECT * FROM airlines LEFT JOIN trips\n" +
                    "ON trips.id_airl = airlines.id_airline;";
            ResultSet res = statement.executeQuery(sql_command);
            return res;
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
    @Override
    public ResultSet get_all_airlines(){
        try{
            String sql_command = "SELECT * FROM airlines;";
            ResultSet res = statement.executeQuery(sql_command);
            return res;
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
    @Override
    public ResultSet get_trips_by_airline(String name){
        try{
            String sql_command = "SELECT * FROM trips INNER JOIN airlines ON airlines.name =" +"\"" + name + "\"" + "\n" +
                    "WHERE airlines.id_airline = trips.id_airl;";
            ResultSet res = statement.executeQuery(sql_command);
            return res;
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
}
