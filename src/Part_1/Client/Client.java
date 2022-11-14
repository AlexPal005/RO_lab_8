package Part_1.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket sock;
    private PrintWriter out;
    private BufferedReader in;
    public void start(String ip, int port){
        try {
            sock = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);
            read_send_data();
            String response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void read_send_data(){
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        while(true) {
            menu.show_menu();
            System.out.println("Уведіть номер меню: ");
            int number_menu = scanner.nextInt();

            switch (number_menu) {
                case (1):
                    String q = number_menu + "#" + menu.add_airline();
                    out.println(q);
                    is_done();
                    break;
                case (2):
                    out.println(number_menu + "#" + menu.delete_airline());
                    is_done();
                    break;
                case (3):
                    out.println(number_menu + "#" + menu.add_trip());
                    is_done();
                    break;
                case (4):
                    out.println(number_menu + "#" + menu.delete_trip());
                    is_done();
                    break;
                case (5):
                    out.println(number_menu + "#" + menu.edit_trip());
                    is_done();
                    break;
                case (6):
                    out.println(number_menu + "#" + menu.search_trip_by_id());
                    show_set_trips();
                    break;
                case (7):
                    out.println(number_menu);
                    show_set_all();
                    break;
                case (8):
                    out.println(number_menu);
                    show_set_all_airlines();
                    break;
                case (9):
                    out.println(number_menu + "#" + menu.get_trips_by_name_airline());
                    show_set_trips();
                    break;
                default:
                    System.out.println("Уведіть номер меню!");
                    break;
            }

        }

    }

    private void is_done(){
        try{
            String str = in.readLine();
            if(Integer.parseInt(str) == 1){
                System.out.println("Виконано!");
            }
            else{
                System.out.println("Помилка!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void show_set_trips(){
        try{
            String str = in.readLine();
            if(str.equals("2") || str.equals("") ){
                System.out.println("Нічого не знайдено!");
            }
            else{
                String[] res = str.split("#");
                for (int i = 0; i < res.length; i++) {
                    System.out.print(res[i] + " ");
                    if(i % 2 != 0){
                        System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void show_set_all(){
        try{
            String str = in.readLine();
            if(str.equals("")){
                System.out.println("Нічого не знайдено!");
            }
            else{
                String[] res = str.split("#");
                int prev_id_airline = 0;
                int id = 1;
                for (int i = 0; i < res.length; i++) {
                    if(res[i].equals("Id: ")){
                        id = Integer.parseInt(res[i+1]);
                        if(id != prev_id_airline){
                            System.out.println(res[i] + " " + res[i + 1] + " ");
                            System.out.println(res[i+2] + " " + res[i + 3] + " ");
                            i = i + 3;
                        }
                        else{
                            i = i+3;
                        }
                    }
                    else {
                        System.out.print("\t" + res[i] + " ");
                    }
                    prev_id_airline = id;
                    if(i % 2 != 0){
                        System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void show_set_all_airlines(){
        try{
            String str = in.readLine();
            if(str.equals("")){
                System.out.println("Нічого не знайдено!");
            }
            else{
                String[] res = str.split("#");
                for (int i = 0; i < res.length; i++) {
                    System.out.print(res[i] + " ");
                    if(i % 2 != 0){
                        System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
