package packppal;


/**
 * Esta clase representa un jugador en la partida
 * Esta clase es un TAD
 * 
 * La clase en si guarda un numero identificador del jugador (unico) y ofrece una serie de funcionalidades
 * para que el jugador lleve a cabo su turno
 * Tiene fuerte interaccion con la parte de interfaz
 * 
 * @author Alex
 */
public class Jugador 
{

	private static int cantidadJugadores = 1; //Cantidad de jugadores existentes. Sirve para adjudicar numeros unicos a cada jugador
	
	private int numJugador; //Numero identificativo para este jugador
	
	/**
	 * Constructora de jugador
	 * Crea un nuevo jugador con un numero identificativo unico
	 */
	public Jugador()
	{
		numJugador = cantidadJugadores;
		cantidadJugadores = cantidadJugadores + 1;
	}
	
	public int getNumJugador()
	{
		return this.numJugador;
	}


	public boolean equals(Jugador pOtroJug)
	{
		return this.numJugador == pOtroJug.getNumJugador();		
	}
	
}