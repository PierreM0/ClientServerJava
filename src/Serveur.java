import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Serveur {
    private ArrayList<Interlocuteur> interlocuteurs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Serveur s = new Serveur();
        s.run();
    }

    public void cast(String message, int nbConnection) {
        interlocuteurs.forEach(interlocuteur -> {
            if (interlocuteur.getNbConnection() != nbConnection)
                interlocuteur.echo(message);
        });
    }

    public void run() throws IOException {
        ServerSocket s = new ServerSocket(6969);
        System.out.println("Server started ...");

        int nbConnection = 0;
        while (true) {
            Socket socket = s.accept();
            System.out.println("Client numéro: " + nbConnection + " connecté");
            nbConnection += 1;

            Interlocuteur interlocuteur = new Interlocuteur(socket, nbConnection, this);
            interlocuteurs.add(interlocuteur);
            interlocuteur.start();
        }
    }
}
