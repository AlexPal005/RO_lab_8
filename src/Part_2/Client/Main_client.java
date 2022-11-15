package Part_2.Client;

import Part_2.Intarface.ExecutorAirport;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main_client {
    public static void main(String[] args) throws NotBoundException, RemoteException, MalformedURLException {
        String url = "rmi://localhost:123/Calc";
        ExecutorAirport executorAirport = (ExecutorAirport) Naming.lookup(url);
        Menu menu = new Menu(executorAirport);
        menu.create_menu();
    }
}
