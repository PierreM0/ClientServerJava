import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Tapez l'adresse du serveur");
        String servAddr = clavier.readLine().trim();
        System.out.println("Tapez le port du serveur");
        int servPort = Integer.parseInt(clavier.readLine().trim());
        InetAddress addr = InetAddress.getByName(servAddr);
        Socket s = new Socket(addr, servPort);

        System.out.println("socket crée " + s);

        BufferedReader fluxEntrant = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream fluxSortant = new PrintStream(s.getOutputStream());

        boolean quit = false;
        do {
            System.out.println("Tapez la requete: ");
            String requete = clavier.readLine().trim();
            fluxSortant.println(requete);
            if (requete.equalsIgnoreCase("q"))
                quit = true;
            else {
                String reponse = fluxEntrant.readLine();
                System.out.println(reponse);
            }
        } while (!quit);
    }
}