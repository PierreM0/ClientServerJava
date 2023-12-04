import java.io.*;
import java.net.Socket;

public class Interlocuteur extends Thread {
    private int nbConnection;
    private BufferedReader fluxEntrant;
    private PrintStream fluxSortant;

    private Serveur serveur;
    public Interlocuteur(Socket socket, int nbConnection, Serveur serveur) throws IOException {
        fluxEntrant = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        fluxSortant = new PrintStream(socket.getOutputStream());

        this.serveur = serveur;
        this.nbConnection = nbConnection;
    }

    public int getNbConnection() {
        return nbConnection;
    }

    public void echo(String message) {
        fluxSortant.println(message);
    }

    @Override
    public void run() {
        boolean quit = false;
        while(!quit) {
            try {
                String line = fluxEntrant.readLine();
                if (line.equalsIgnoreCase("q"))
                    quit = true;
                else {
                    serveur.cast(line, nbConnection);
                    System.out.println("recu: " + line + " part " + nbConnection);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
