package Part_1.Server;

public class Main_server {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        final int port = 12345;
        server.start(port);
    }}
