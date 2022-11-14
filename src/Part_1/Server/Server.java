package Part_1.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab_7.*;

public class Server {
    private ServerSocket server = null;
    private Socket sock = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Airport airport;
    public void start(final int port){
        try {
            server = new ServerSocket(port);

            while(true){
                sock = server.accept();

                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                out = new PrintWriter(sock.getOutputStream(), true);
                while (processQuery()) ;
            }
        } catch (IOException e) {
            System.out.println("Помилка!");
        }
    }
    public Server(){
        try{
            airport = new Airport();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private boolean processQuery(){
        try{
            String query = in.readLine();

            if (query == null) return false;

            String[] fields = query.split("#");
            if(fields.length == 0) {
                return false;
            }
            else{
                int number_menu = Integer.parseInt(fields[0]);
                switch (number_menu){
                    case(1):
                       first(fields);
                       break;
                    case(2):
                        second(fields);
                        break;
                    case(3):
                        third(fields);
                        break;
                    case(4):
                        fourth(fields);
                        break;
                    case(5):
                        fifth(fields);
                        break;
                    case(6):
                        sixth(fields);
                        break;
                    case(7):
                        seventh();
                        break;
                    case(8):
                        eighth();
                        break;
                    case(9):
                        nineth(fields);
                        break;
                    default:
                        out.println(0);
                        break;
                }

            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    private void first(String[] fields){
        if(airport.add_new_airline(Integer.parseInt(fields[1]),fields[2])){
            out.println("1");
        }
        else{
            out.println("0");
        }
    }
    private void second(String[] fields){
        if(airport.delete_airline_by_id(Integer.parseInt(fields[1]))){
            out.println("1");
        }
        else{
            out.println("0");
        }
    }
    private void third(String[] fields){
        if(airport.add_trip(Integer.parseInt(fields[1]), fields[2],fields[3],
                Double.parseDouble(fields[4]),Integer.parseInt(fields[5]))){
            out.println("1");
        }
        else{
            out.println("0");
        }
    }
    private void fourth(String[] fields){
        if(airport.delete_trip_by_id(Integer.parseInt(fields[1]))){
            out.println("1");
        }
        else{
            out.println("0");
        }
    }
    private void fifth(String[] fields){
        if(airport.edit_trip(Integer.parseInt(fields[1]),fields[2],fields[3], Double.parseDouble(fields[4]))){
            out.println("1");
        }
        else{
            out.println("0");
        }
    }
    private void sixth(String[] field){
        ResultSet result_set = airport.show_trip(Integer.parseInt(field[1]));
        try{
            if(result_set.next()){
                int id_res = result_set.getInt("id_trip");
                String city_to =  result_set.getString("city_to");
                String city_from =  result_set.getString("city_from");
                double price = result_set.getDouble("price");
                int id_airl = result_set.getInt("id_airl");
                String result =  "Id:"  + "#" + id_res + "#" +
                        "Місто прибуття: " + "#" +  city_to + "#" +
                        "Місто відправлення: "  + "#" + city_from + "#" +
                        "Ціна квитка: " + "#"  + price + "#" +
                        "Id авіакомпанії : " + "#"  + id_airl;
                out.println(result);
            }
            else{
                out.println("2");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void seventh(){
        ResultSet result_set = airport.get_all();
        try{
            String result = "";
            while(result_set.next()){
                int id_airline = result_set.getInt("id_airline");
                String name = result_set.getString("name");

                int id_res = result_set.getInt("id_trip");
                String city_to =  result_set.getString("city_to");
                String city_from =  result_set.getString("city_from");
                double price = result_set.getDouble("price");
                int id_trip = result_set.getInt("id_airl");

                result +=
                        "Id: " + "#" + id_airline + "#" +
                        "Name: " + "#" + name + "#" +
                        "Id trip:"  + "#" + id_res + "#" +
                        "Місто прибуття: " + "#" +  city_to + "#" +
                        "Місто відправлення: "  + "#" + city_from + "#" +
                        "Ціна квитка: " + "#"  + price + "#" +
                        "Id авіакомпанії : " + "#"  + id_trip + "#";
            }
            out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void eighth(){
        ResultSet result_set = airport.get_all_airlines();
        try{
            String result = "";
            while(result_set.next()){
                int id_airline = result_set.getInt("id_airline");
                String name = result_set.getString("name");

                result +=
                        "Id: " + "#" + id_airline + "#" +
                                "Name: " + "#" + name + "#";
            }
            out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void nineth(String[] fields){
        ResultSet result_set = airport.get_trips_by_airline(fields[1]);
        try{
            String result = "";
            while(result_set.next()){
                int id_res = result_set.getInt("id_trip");
                String city_to =  result_set.getString("city_to");
                String city_from =  result_set.getString("city_from");
                double price = result_set.getDouble("price");
                int id_airl = result_set.getInt("id_airl");
                result +=  "Id:"  + "#" + id_res + "#" +
                        "Місто прибуття: " + "#" +  city_to + "#" +
                        "Місто відправлення: "  + "#" + city_from + "#" +
                        "Ціна квитка: " + "#"  + price + "#" +
                        "Id авіакомпанії : " + "#"  + id_airl + "#";
            }
            out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void stop() {
        try{
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
