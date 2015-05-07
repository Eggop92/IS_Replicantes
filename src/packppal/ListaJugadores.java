package packppal;

import java.util.Random;

import packestructuras.ListaLigadaCircular;
import packexcepciones.NumJugadoresIncorrectoException;

/**
 * Clase ListaJugadores
 * Esta clase es una MAE
 * 
 * Esta clase guarda a los jugadores que esten jugando la partida
 * La estructura creada para almacenar los datos es una lista ligada circular
 * Ofrece funcionalidades para gestionar los turnos de la partida
 * 
 * @author Alex
 *
 */
public class ListaJugadores 
{

	private static ListaJugadores miListaJugadores; //Atributo MAE. Patron Singleton
	private ListaLigadaCircular<Jugador> lista; //Contenedor
	
	/**
	 * Constructora de la clase
	 * Privada ya que sigue el patron singleton para crear esta clase que es una MAE
	 */
	private ListaJugadores()
	{
		lista = new ListaLigadaCircular<Jugador>();
	}
	
	/**
	 * Getter estatico para obtener la unica instancia de la clase ListaJugadores
	 * 
	 * @return La unica ListaJugadores
	 */
	public static ListaJugadores getMiListaJugadores()
	{
		if(miListaJugadores == null) //Comprueba si ya existe la instancia de ListaJugadores
		{
			miListaJugadores = new ListaJugadores(); //Si no exitse la crea
		}
		
		return miListaJugadores; //Devuelve la unica instancia de ListaJugadores
	}
	
	/**
	 * Crea los jugadores en esta clase y manda al tablero inicializarse
	 * 
	 *@param El numero de jugadores que llevaran a cabo la partida, ha de estar entre 2 y 4
	 */
	public void nuevaPartida(int pTamanyoX, int pTamanyoY, int pNumJugadores)
	{
		try
		{
			comprobarJugadores(pNumJugadores);
			
			crearJugadores(pNumJugadores);
			
			Tablero.getMiTablero().inicializarTablero(pTamanyoX, pTamanyoY, lista);			

		}
		//En caso de que no sea correcto tartaremos la excepcion recibida con un mensaje de error
		catch (NumJugadoresIncorrectoException e) 
		{
			System.out.println("Partida no iniciada. Comprueba los errores.");
		}		
	}

	/**
	 * Crea jugadores para una partida cargada. Ademas deja los punteros de la lista en el lugar adecuado para que continue el jugador
	 * al que le tocase el turno al guardar la partida
	 * 
	 * @param numJugadores Numero de jugadores que tomaban parte en la partida guardada
	 * @param jugActual Jugador al que le iba a tocar el turno
	 */
	public void nuevaPartida(int pNumJugadores, String pPlantillaTablero)
	{
		try
		{
			comprobarJugadores(pNumJugadores);
			
			crearJugadores(pNumJugadores);
			
			Tablero.getMiTablero().inicializarTablero(pPlantillaTablero);
			
			/*Siguientes lineas desactivan la interfaz por consola
			//Jugador jugadorGanador = jugarPartida();
			
			InterfazConsola.getMiIntfazConsola().anunciarGanador(jugadorGanador);
			*/
		}
		//En caso de que no sea correcto tartaremos la excepcion recibida con un mensaje de error
		catch (NumJugadoresIncorrectoException e) 
		{
			System.out.println("Partida no iniciada. Comprueba los errores.");
		}	
		
		/*Siguientes lineas desactivan la interfaz por consola
		Jugador.reiniciarContador();
		vaciarListaJugadores();
		*/
	}

	/**
	 * Crea tantos jugadores como el parametro de entrada indique y los almacena en la lista
	 * @param pNumJugadores Numero de jugadores a crear e introducir en la lista
	 */
	private void crearJugadores(int pNumJugadores) 
	{
		for(int i = 0; i < pNumJugadores; i++)
		{
			Jugador jugAux = new Jugador();
			
			lista.insert(jugAux);
		}
	}

	/**
	 * Comprueba que el numero de jugadores recibido como parametro esta entre 2 y 4
	 * @param pNumJugadores El numero de jugadores a comprobar si es correcto
	 * @throws NumJugadoresIncorrectoException Excepcion que indica que el numero de jugadores es incorrecto (menor a 2 o mayor a 4)
	 */
	private void comprobarJugadores(int pNumJugadores) throws NumJugadoresIncorrectoException
	{
		if((pNumJugadores < 2)||(pNumJugadores > 4))
		{
			throw (new NumJugadoresIncorrectoException(pNumJugadores));
		}		
	}
	
	/**
	 * Comienza y lleva a cabo una partida
	 * Parte de la premisa de que ya estan los jugadores creados y el tablero debidamente inicializado
	 * @return El jugador ganador
	 */
	/* NO SE VA A LLAMAR A ESTE MTDO CON LA INTFAZ GRAFICA
	private Jugador jugarPartida() 
	{
		jugadorActual = determinarPrimerJugador(); //Variable para almacenar el jugador al que le toca
		Jugador jugadorGanador; //Variable para almacenar el jugador ganador
		
		//Variable de control que decide si se puden hacer mas movimientos o la partida ha llegado al estancamiento
		boolean movimientoPosible = true; 
		
		//Imprimir el tablero al comienzo de la partida
		InterfazConsola.getMiIntfazConsola().dibujarTablero();
				
		while(movimientoPosible) //Mientras la partida no este estancada
		{
			System.out.println("\nLe toca al jugador " + jugadorActual.getNumJugador());
			System.out.println();
			
			jugadorActual.jugarTurno(); //Indicar al jugador al que le toque que es su turno
			
			//Imprimir el tablero despues de cada turno
			InterfazConsola.getMiIntfazConsola().dibujarTablero();
			
			movimientoPosible = Tablero.getMiTablero().posibleContinuarPorTablero(); //Tras el turno del jugador comprobar que es posible seguir
			movimientoPosible = movimientoPosible && posbileContinuarPorJugadores();
			
			if(movimientoPosible) //Si es posible seguir
			{
				jugadorActual = lista.next(); //Seleccionar el siguiente jugador
				
				GestorGuardado.getMiGestorGuardado().guardarPartida(); //Tras haber acabado un turno, si la partida continua se guarda el estado
			}
		}
		
		jugadorGanador = obtenerGanador();
		
		return jugadorGanador;
	}*/

	public boolean posbileContinuarPorJugadores()
	{
		int jugadoresConFichas = 0;
		
		ListaLigadaCircular<Jugador> listaTemp = lista.clonar();
		
		for(int i = 0; i < listaTemp.size(); i++)
		{
			if(Tablero.getMiTablero().contarFichas(listaTemp.next()) != 0)
			{
				jugadoresConFichas = jugadoresConFichas + 1;
			}
		}
				
		return jugadoresConFichas != 1;
	}

	/**
	 * Determina de forma aleatoria el jugador a comenzar la partida
	 * @return Un jugador al azar de los que hay en la lista
	 */
	public Jugador determinarPrimerJugador() 
	{
		Random randomGenerator = new Random(lista.size()+1);
		
		int jugadorDeComienzo = randomGenerator.nextInt();
		
		for(int i = 0; i < jugadorDeComienzo; i++)
		{
			lista.next();
		}
		
		return lista.next();
	}
	
	/**
	 * Decide quien es el jugador que mas fichas tiene sobre el tablero, es decir el ganador
	 * @return El jugador ganador
	 */
	public Jugador obtenerGanador() 
	{
		Jugador jugadorGanador = null; //Variable para almacenar al ganador
		Jugador jugAux = null; //Variable auxiliar para guardar datos extraidos en un bucle
		int puntosAlcanzados = 0; //Puntos alcanzados por el jugador que estamos estudiando en el momento que sea
		int puntosMaximos = 0; //Puntos maximos alcanzados
		
		for(int i = 0; i < lista.size(); i++) //Mientras no haya recorrido todos los jugadores
		{
			jugAux = lista.next(); //Tomo un jugador de la lista
			puntosAlcanzados = Tablero.getMiTablero().contarFichas(jugAux); //Calculo sus puntos
			
			if(puntosAlcanzados > puntosMaximos) //Si sus puntos superan el maximo
			{
				puntosMaximos = puntosAlcanzados; //Su puntuacion se convierte en la maxima
				jugadorGanador = jugAux; //Y por ahora se corona como ganador
			}
			else
			{
				if(puntosAlcanzados == puntosMaximos)
				{
					//EMPATE
				}
			}
			
		}		
		
		return jugadorGanador; //Se devuelve el jugador ganador
	}
	
	/**
	 * Busca en la lista de jugadores un jugador que tenga el mismo numero que es pasado como parametro.
	 * Si existe devuelve el jugador, sino devuelve null;
	 * @param pNumJug Numero del jugador que queremos hallar
	 * @return Jugador con el numero indicado. Null si no existe
	 */
	public Jugador getJugador(int pNumJug)
	{
		Jugador rtdo = null;
		ListaLigadaCircular<Jugador> listaAux = lista.clonar();
		boolean encontrado = false;
		int i = 0;
		
		while(i < lista.size() && !encontrado)
		{
			rtdo = listaAux.next();
			
			if(rtdo.getNumJugador() == pNumJug)
			{
				encontrado = true;
			}
			
			i++;
		}		
		
		if(!encontrado)
		{
			rtdo = null;
		}
		
		return rtdo;
	}

	//	private void vaciarListaJugadores()
	//	{
	//		lista = new ListaLigadaCircular<Jugador>();
	//		jugadorActual = null;
	//	}
		
		public Jugador siguienteJugador()
		{
			return lista.next();
		}

	@Override
	public String toString()
	{
		String rtdo = lista.size() + "%";
		return rtdo;		
	}
	
	
	
//	private void vaciarListaJugadores()
//	{
//		lista = new ListaLigadaCircular<Jugador>();
//		jugadorActual = null;
//	}

}
