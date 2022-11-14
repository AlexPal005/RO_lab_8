package Part_1.Client;

public class Main_client {
    public static void main(String[] args) {
        Client client = new Client();
        client.start("localhost", 12345);
    }
}
