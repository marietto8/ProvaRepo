import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author dosse
 */
public class Prenotazione implements Comparable<Prenotazione>, Serializable{
    private String nome;
    private int nPersone;
    private LocalDateTime orario;
    private String note;
    public static final int SALA_INTERNA=0, SALA_ESTERNA=1;
    private int sala;
    
    private static final long model=1L;

    /**
     * Crea una nuova Prenotazione
     * 
     * @param nome la persona che vuole prenotare
     * @param nPersone il numero di persone
     * @param orario data e ora a cui ha prenotato (LocalDateTime)
     * @param sala SALA_INTERNA oppure SALA_ESTERNA. Se viene passato un valore non valido diventa SALA_ESTERNA
     * @param note eventuali note (stringa)
     */
    public Prenotazione(String nome, int nPersone, LocalDateTime orario, int sala, String note) {
        this.nome = nome;
        setnPersone(nPersone);
        this.orario = orario;
        setSala(sala);
        this.note=note;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getnPersone() {
        return nPersone;
    }

    public void setnPersone(int nPersone) {
        if(nPersone<1) nPersone=1;
        this.nPersone = nPersone;
    }

    public LocalDateTime getOrario() {
        return orario;
    }

    public void setOrario(LocalDateTime orario) {
        this.orario = orario;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSala() {
        return sala;
    }

    /**
     * Imposta la sala
     * @param sala SALA_INTERNA o SALA_ESTERNA (se il valore non è valido usa SALA_ESTERNA)
     */
    public void setSala(int sala) {
        if(sala!=SALA_INTERNA&&sala!=SALA_ESTERNA) sala=SALA_ESTERNA;
        this.sala = sala;
    }

    /**
     * Confronta una Prenotazione con un'altra, in particolare ne confronta data e ora, rendendo possibile ordinarle facilmente
     * @param t la Prenotazione con cui confrontarla
     * @return un valore negativo se questa prenotazione viene prima, un valore positivo se viene dopo, 0 se hanno la stessa data e ora
     */
    @Override
    public int compareTo(Prenotazione t) {
        return orario.compareTo(t.orario);
    }

    @Override
    public String toString() {
        return nome+" "+nPersone+" persone, "+orario;
    }
    
}
