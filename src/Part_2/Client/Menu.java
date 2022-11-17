package Part_2.Client;


import Part_2.Inteface.Airline;
import Part_2.Inteface.AirlineTrip;
import Part_2.Inteface.ExecutorAirport;
import Part_2.Inteface.Trip;

import java.io.*;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menu{
    private ExecutorAirport executor_airport;
    private Scanner in;
    public Menu(ExecutorAirport executor_airport){
        this.executor_airport = executor_airport;
        in = new Scanner(System.in);
    }
    private void show_menu(){
        System.out.println("----------Меню----------");
        System.out.println("1. Додати нову авіакомпанію");
        System.out.println("2. Видалити авіакомпанію");
        System.out.println("3. Додати рейс");
        System.out.println("4. Видалити рейс");
        System.out.println("5. Редагувати рейс");
        System.out.println("6. Знайти рейс за його id");
        System.out.println("7. Отримати всі авіакомпанії з їх рейсами");
        System.out.println("8. Отримати всі авіакомпанії");
        System.out.println("9. Отримати рейси заданої авіакомпанії");
    }
    public void create_menu() throws RemoteException {
        while(true) {
            show_menu();
            int switch_val;
            switch_val = in.nextInt();
            in.nextLine();
            switch (switch_val) {
                case (1):
                    add_airline();
                    break;
                case (2):
                    delete_airline();
                    break;
                case (3):
                    add_trip();
                    break;
                case (4):
                    delete_trip();
                    break;
                case (5):
                    edit_trip();
                    break;
                case (6):
                    search_trip_by_id();
                    break;
                case (7):
                    get_all_airl_trips();
                    break;
                case (8):
                    get_all_airlines();
                    break;
                case (9):
                    get_trips_by_name_airline();
                    break;
                default:
                    System.out.println("Помилка! Уведіть номер пункту меню!");
                    break;
            }
        }
    }
    private void add_airline() throws RemoteException {
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        if(id <= 0 ){
            System.out.println("id має бути більшим за 0");
        }
        System.out.println("Уведіть назву: ");
        String name = in.nextLine();
        if(!executor_airport.add_new_airline(id, name)){
            System.out.println("Помилка! Авіакомпанія не додана!");
        }
        else{
            System.out.println("Додано!");
        }
    }
    private void delete_airline()throws RemoteException{
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        if(!executor_airport.delete_airline_by_id(id)){
            System.out.println("Помилка! Авіакомпанія не видалена!");
        }
        else{
            System.out.println("Видалено!");
        }
    }
    private void add_trip() throws RemoteException{
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.println("Уведіть місто відправлення: ");
        String city_from = in.nextLine();
        System.out.println("Уведіть місто прибуття: ");
        String city_to = in.nextLine();
        System.out.println("Уведіть ціну квитка: ");
        double price = in.nextDouble();
        in.nextLine();
        System.out.println("Уведіть id авіакомпанії:");
        int id_airline = in.nextInt();
        in.nextLine();
        if(executor_airport.add_trip(id,city_to,city_from,price,id_airline)){
            System.out.println("Додано!");
        }
        else{
            System.out.println("Помилка!");
        }

    }
    private void delete_trip() throws RemoteException{
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        if(!executor_airport.delete_trip_by_id(id)){
            System.out.println("Помилка! Авіакомпанія не видалена!");
        }
        else{
            System.out.println("Видалено!");
        }
    }
    private void edit_trip() throws RemoteException{
        System.out.println("Уведіть id рейсу, який потрібно редагувати: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.println("Уведіть місто відправлення: ");
        String city_from = in.nextLine();
        System.out.println("Уведіть місто прибуття: ");
        String city_to = in.nextLine();
        System.out.println("Уведіть ціну квитка: ");
        double price = in.nextDouble();
        in.nextLine();
        if(executor_airport.edit_trip(id,city_to,city_from,price)){
            System.out.println("Успішно редаговано!");
        }
        else{
            System.out.println("Помилка!");
        }
    }
    private void search_trip_by_id() throws RemoteException{
        System.out.println("Уведіть id рейсу, який потрібно показати: ");
        int id = in.nextInt();
        in.nextLine();
        executor_airport.show_trip(id);
        try{
           Trip trip = executor_airport.show_trip(id);
            // print result
            int id_res = trip.getCode();
            String city_to =  trip.getCity_to();
            String city_from =  trip.getCity_from();
            double price = trip.getPrice();
            int id_airline = trip.getNumber_airline();
            System.out.println("Id:" + id_res);
            System.out.println("Місто прибуття: " + city_to);
            System.out.println("Місто відправлення: " + city_from);
            System.out.println("Ціна квитка: " + price);
            System.out.println("Id авіакомпанії : " + id_airline);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void get_all_airl_trips() throws RemoteException{
        AirlineTrip airlineTrip = executor_airport.get_all();
        List<Airline> airlines = airlineTrip.getAirlines();
        List<Trip> trips = airlineTrip.getTrips();

        for (int i = 0; i < airlines.size(); i++) {
            System.out.println(airlines.get(i));
            for (int j = 0; j < trips.size(); j++) {
                if(airlines.get(i).getCode() == trips.get(j).getNumber_airline()){
                    System.out.println(trips.get(j));
                }
            }
        }
    }
    private void get_all_airlines() throws RemoteException{
        List<Airline> airlines = executor_airport.get_all_airlines();
        for (int i = 0; i < airlines.size(); i++) {
            System.out.println(airlines.get(i));
        }
    }
    private void get_trips_by_name_airline() throws RemoteException{
        System.out.println("Уведіть назву авіакомпанії: ");
        String name = in.nextLine();
        List<Trip> trips= executor_airport.get_trips_by_airline(name);
        System.out.println(name);
        for (int j = 0; j < trips.size(); j++) {
            System.out.println(trips.get(j));
        }

    }
}
