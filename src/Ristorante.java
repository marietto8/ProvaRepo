
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 *
 * @author dosse
 */
public class Ristorante implements Serializable {

    private final TreeSet<Prenotazione> prenotazioni;
    private static final long model = 1L;

    /**
     * Crea un nuovo Ristorante, inizialmente senza prenotazioni
     */
    public Ristorante() {
        prenotazioni = new TreeSet<>();
    }

    /**
     * Aggiunge una Prenotazione (se non è già inserita)
     *
     * @param p la prenotazione da aggiungere
     */
    public void aggiungiPrenotazione(Prenotazione p) {
        if (!prenotazioni.contains(p)) {
            prenotazioni.add(p);
        }
    }

    /**
     * Rimuove una Prenotazione (se è presente)
     *
     * @param p la prenotazione da rimuovere
     */
    public void rimuoviPrenotazione(Prenotazione p) {
        if (prenotazioni.contains(p)) {
            prenotazioni.remove(p);
        }
    }

    /**
     * Restituisce l'elenco di prenotazione per i prossimi giorni, ordinate per
     * data crescente
     *
     * @param giorni il numero di giorni futuri da controllare oppure -1 se si
     * vuole avere tutte le prenotazioni future
     * @return array di Prenotazione
     */
    public Prenotazione[] getPrenotazioni(int giorni) {
        if (giorni < 1) {
            return prenotazioni.tailSet(new Prenotazione("", 1, LocalDateTime.now(), 0, "")).toArray(new Prenotazione[0]);
        } else {
            LocalDateTime from = LocalDateTime.now(), to = from.plusDays(giorni);
            return prenotazioni.subSet(new Prenotazione("", 1, from, 0, ""), new Prenotazione("", 1, to, 0, "")).toArray(new Prenotazione[0]);
        }
    }

    private boolean salva(File f) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
        } catch (Exception e) {
            return false;
        }

        try {
            oos.writeObject(this);
            oos.flush();
            oos.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Errore di salvatagio");
            try {
                oos.close();
            } catch (Exception ex1) {
            }
            return false;
        }
    }
/*
    public static Ristorante carica(File f){
        ObjectInputStream ois=null;
        try {
            ois= new ObjectInputStream(new FileInputStream(f));
        } catch (Exception e) {
            return new Ristorante();
        }
        
        try {
            Ristorante r=(Ristorante) ois.readObject();
            ois.close();
            return r;
        } catch (IOException ex) {
            
        }
    }
*/
}
