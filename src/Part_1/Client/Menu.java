package Part_1.Client;

import java.util.Scanner;

public class Menu {
    private Scanner in;
    public Menu(){
        in = new Scanner(System.in);
    }
    public void show_menu(){
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
    public String add_airline(){
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        if(id <= 0 ){
            System.out.println("Помилка! id має бути більшим за 0!");
            return "";
        }
        else{
            System.out.println("Уведіть назву: ");
            String name = in.nextLine();
            return id + "#" + name;
        }

    }
    public String delete_airline(){
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        if(id <= 0 ){
            System.out.println("Помилка! id має бути більшим за 0!");
            return "";
        }
        else{
            return Integer.toString(id);
        }
    }
    public String add_trip(){
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

        return id + "#" + city_from + "#" + city_to + "#" + price + "#" + id_airline;

    }
    public String delete_trip(){
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        return Integer.toString(id);
    }
    public String edit_trip(){
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
        return id + "#" + city_to + "#" + city_from + "#" + price;
    }
    public String search_trip_by_id(){
        System.out.println("Уведіть id рейсу, який потрібно показати: ");
        int id = in.nextInt();
        in.nextLine();
        return Integer.toString(id);
    }
    public String get_trips_by_name_airline(){
        System.out.println("Уведіть назву авіакомпанії: ");
        String name = in.nextLine();
        return name;
    }
}
