package Part_2.Server;

import java.rmi.registry.*;

public class Main_server {
    public static void main(String[] args) throws Exception {
        ExecutorAirportImpl executor = new ExecutorAirportImpl();
        Registry registry = LocateRegistry.createRegistry(123);
        registry.rebind("Calc", executor);
    }
}
