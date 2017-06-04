package api;
import java.io.Serializable;

/**
 * Rappresenta un' eccezione che si puo' verificare quando un giocatore vuole
 * aggiungersi a una partita cancellata dalla lista oppure quando la partita e'
 * gia' cominciata e gia' sono presenti due giocatori.
 * It represents an exception that can be thrown when a player wants to join a game removed from the list,
 * or when a player wants to join a game that has 4 players already.
 */
public class GameJoinException extends Exception implements Serializable {

	private static final long serialVersionUID = -5982382329250873917L;

	/**
	 * Constructor of the class
	 */
	public GameJoinException() {
		super();
	}

	/**
	 * Costruttore con messaggio di errore rilevato.
	 * Constructor with the error message
	 * @param message is the error message
	 */
	public GameJoinException(String message) {
		super(message);
	}

}