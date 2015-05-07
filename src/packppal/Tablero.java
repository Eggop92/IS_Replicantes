package packppal;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;

import net.sf.jga.algorithms.Summarize;
import packestructuras.ListaLigadaCircular;
import packestructuras.Posicion;
import packfichas.*;
import packfunctors.FunctorCasilla;

/**
 * Clase que contiene el tablero de la partida. Implementa el patron Singelton.
 * 
 * @author Egoitz
 * 
 */

public class Tablero extends Observable
{

	private static Tablero miTablero;
	private CasillaEstandar[][] tablero;
	private int maxFila;
	private int maxColum;

	/**
	 * constructora privada, propia del singelton
	 */
	private Tablero()
	{
	}

	/**
	 * metodo para obtener la instancia unica del tablero
	 * 
	 * @return
	 */
	public static Tablero getMiTablero()
	{
		if (miTablero == null)
		{
			miTablero = new Tablero();
		}
		return miTablero;
	}

	/**
	 * inicializa el tablero y coloca la primera ficha de cada jugador.
	 * 
	 * @param: int pX, int pY, Iterable<Jugador> pJugadores
	 * @return void
	 */
	public void inicializarTablero(int pX, int pY,
			ListaLigadaCircular<Jugador> pJugadores)
	{
		// Se inicializan las variables del tablero.
		tablero = new CasillaEstandar[pX][pY];
		maxFila = pY;
		maxColum = pX;

		int x = 0, y = 0;
		for (x = 0; x < maxColum; x++)
		{
			for (y = 0; y < maxFila; y++)
			{
				tablero[x][y] = new CasillaEstandar();
			}
		}

		// variables auxiliares
		Jugador primerJugador = pJugadores.next();
		Jugador actual;
		Ficha unaFicha;
		Posicion unaPosicion;
		int numItr = 0;

		// Colocar una ficha por cada jugador que hay, maximo 4 jugadores,
		// minimo 2
		do
		{
			actual = pJugadores.next();
			unaPosicion = siguienteEsquina(numItr);
			if (unaPosicion != null)
			{
				unaFicha = new SaltadorReplicante(actual, unaPosicion);
				ponerFicha(unaPosicion, unaFicha);
				numItr++;
			}
		} while (!primerJugador.equals(actual));

	}
	
	/**
	 * Devuelve el tamanyo del tablero en caso de ser cuadrado, sino devuelve 0
	 * @return
	 */
	public int getTamanyoSiCuadrado()
	{
		if(maxFila == maxColum)
		{
			return maxFila;
		}
		else
		{
			return 0;
		}
	}

	/**
	 * inicializa el tablero de una partida guardada previamente.
	 * 
	 * @param: String pTXT
	 * @return void
	 */
	public void inicializarTablero(String pTXT)
	{

		Scanner sc = new Scanner(pTXT);
		String segmento;
		sc.useDelimiter("&");
		segmento = sc.next();

		int tamanyoX = Integer.parseInt(segmento);
		segmento = sc.next();
		int tamanyoY = Integer.parseInt(segmento);
		tablero = new CasillaEstandar[tamanyoX][tamanyoY];
		maxFila = tamanyoY;
		maxColum = tamanyoX;

		sc.useDelimiter(";");

		segmento = sc.next();
		segmento = segmento.substring(1);

		for (int y = 0; y < tamanyoY; y++)
		{
			for (int x = 0; x < tamanyoX; x++)
			{
				switch (segmento.charAt(0))
				{
					case 'T':
						tablero[x][y] = new CasillaEstandar(
								new SaltadorReplicante(ListaJugadores
										.getMiListaJugadores().getJugador(
												Integer.parseInt(segmento
														.charAt(1) + "")),
										new Posicion(x, y)));
						break;
					case 'R':
						tablero[x][y] = new CasillaEstandar(new Replicante(
								ListaJugadores.getMiListaJugadores()
										.getJugador(
												Integer.parseInt(segmento
														.charAt(1) + "")),
								new Posicion(x, y)));
						break;
					case 'S':
						tablero[x][y] = new CasillaEstandar(new Saltador(
								ListaJugadores.getMiListaJugadores()
										.getJugador(
												Integer.parseInt(segmento
														.charAt(1) + "")),
								new Posicion(x, y)));
						break;
					default:
						tablero[x][y] = new CasillaEstandar();
						break;
				}

				try
				{
					segmento = sc.next();
				}
				catch (NoSuchElementException e)
				{

				}
			}// Fin for x
		}
	}

	/**
	 * Selecciona donde colocar las primeras fichas del tablero.
	 * 
	 * @param numItr
	 * @return
	 */
	private Posicion siguienteEsquina(int numItr)
	{
		Posicion rdo;
		switch (numItr)
		{
			case 0:
				rdo = new Posicion(0, 0);
				break;
			case 1:
				rdo = new Posicion(maxColum - 1, maxFila - 1);
				break;
			case 2:
				rdo = new Posicion(maxColum - 1, 0);
				break;
			case 3:
				rdo = new Posicion(0, maxFila - 1);
				break;
			default:
				rdo = null;
				break;
		}
		return rdo;
	}

	/**
	 * devuelve la Casilla en la posicion indicada.
	 * 
	 * @param pPos
	 * @return
	 */
	/*
	 * NO UTILIZADO POR INTFAZ GRAFICA
	private CasillaEstandar getCasilla(Posicion pPos)
	{
		CasillaEstandar rdo;
		rdo = tablero[pPos.getX()][pPos.getY()];
		return rdo;
	}*/

	/**
	 * metodo que decide si se puede continuar la partida o no
	 * 
	 * @return true en caso afirmativo, false en caso contrario.
	 */
	public boolean posibleContinuarPorTablero()
	{

		int x = 0, y;
		boolean sePuede = false;
		Posicion pPos;

		// Recorremos columnas
		while (x < maxColum && !sePuede)
		{
			y = 0;
			// Recorremos filas
			while (y < maxFila && !sePuede)
			{
				// Comprobamos que la casilla no tiene una ficha
				if (!tablero[x][y].contieneFicha())
				{
					pPos = new Posicion(x, y);
					// Comprobamos que la casilla tiene alguna ficha al rededor
					// que pueda ocuparla.
					if (hayFichaAlRededor(pPos))
					{
						sePuede = true;
					}
				}

				y++;
			}
			x++;
		}
		return sePuede;

	}

	/**
	 * Cuenta las fichas que tiene el jugador introducido y las devuelve.
	 * 
	 * @param pJugador
	 * @return numero de fichas del jugador.
	 */
	public int contarFichas(Jugador pJugador)
	{
		int cont = 0;

		for (int i = 0; i < maxColum; i++)
		{
			cont = cont
					+ (int) Summarize.count(tablero[i], new FunctorCasilla(
							pJugador));
		}

		return cont;
	}

	/**
	 * Mira alrededor de la posicion indicada si hay alguna ficha.
	 * 
	 * @param pPos
	 * @return true si hay una o mas fichas alrededor, false en caso contrario.
	 */
	private boolean hayFichaAlRededor(Posicion pPos)
	{

		int xInicio, xFin, yInicio, yFin;
		if (pPos.getX() - 1 < 0)
		{
			xInicio = 0;
		}
		else
		{
			xInicio = pPos.getX() - 1;
		}

		if (pPos.getX() + 1 > maxColum - 1)
		{
			xFin = maxColum - 1;
		}
		else
		{
			xFin = pPos.getX() + 1;
		}

		if (pPos.getY() - 1 < 0)
		{
			yInicio = 0;
		}
		else
		{
			yInicio = pPos.getY() - 1;
		}

		if (pPos.getY() + 1 > maxFila - 1)
		{
			yFin = maxFila - 1;
		}
		else
		{
			yFin = pPos.getY() + 1;
		}

		boolean rdo = false;
		int x, y;
		x = xInicio;
		while (x <= xFin && !rdo)
		{
			y = yInicio;
			while (y <= yFin && !rdo)
			{
				// Comprueba si la casilla no esta ya ocupada.
				if (tablero[x][y].contieneFicha())
				{
					rdo = true;
				}

				y++;
			}
			x++;
		}
		return rdo;
	}

	/**
	 * Calcula las casillas que esten disponibles a una distancia de la posicion
	 * 
	 * @param pDistancia
	 * @param pPos
	 * @return lista de posiciones viables
	 */
	public LinkedList<Posicion> casillasLibresADistancia(int pDistancia,
			Posicion pPos)
	{

		// Variables que inican el comienzo y el final de los for.
		// Sirve para el control de las esquinas y los lados.
		int xInicio, xFin, yInicio, yFin;
		if (pPos.getX() - pDistancia < 0)
		{
			xInicio = 0;
		}
		else
		{
			xInicio = pPos.getX() - pDistancia;
		}

		if (pPos.getX() + pDistancia >= maxColum)
		{
			xFin = maxColum - 1;
		}
		else
		{
			xFin = pPos.getX() + pDistancia;
		}

		if (pPos.getY() - pDistancia < 0)
		{
			yInicio = 0;
		}
		else
		{
			yInicio = pPos.getY() - pDistancia;
		}

		if (pPos.getY() + pDistancia >= maxFila)
		{
			yFin = maxFila - 1;
		}
		else
		{
			yFin = pPos.getY() + pDistancia;
		}

		// Recorrido en busca de posiciones adecuadas.
		LinkedList<Posicion> rdo = new LinkedList<Posicion>();
		int x, y;
		for (x = xInicio; x <= xFin; x++)
		{
			for (y = yInicio; y <= yFin; y++)
			{
				// Comprueba que la casilla esta a una distancia adecuada para
				// el movimiento.
				if ((x - pPos.getX() == pDistancia)
						|| (pPos.getX() - x == pDistancia)
						|| (y - pPos.getY() == pDistancia)
						|| (pPos.getY() - y == pDistancia))
				{
					// Comprueba si la casilla no esta ya ocupada.
					if (!tablero[x][y].contieneFicha())
					{
						rdo.add(new Posicion(x, y));
					}
				}
			}
		}
		return rdo;
	}

	/**
	 * coloca la ficha en la casilla de la posicion indicada
	 * 
	 * @param pPos
	 * @param pFicha
	 */
	public void ponerFicha(Posicion pPos, Ficha pFicha)
	{
		tablero[pPos.getX()][pPos.getY()].rellenar(pFicha);
	}

	/**
	 * vuelve del jugador introducido todas las fichas adyacentes a la posicion
	 * 
	 * @param pPos
	 * @param pNuevoDuenyo
	 */
	public void convertirAdyacentes(Posicion pPos, Jugador pNuevoDuenyo)
	{

		int xInicio, xFin, yInicio, yFin;
		if (pPos.getX() - 1 < 0)
		{
			xInicio = 0;
		}
		else
		{
			xInicio = pPos.getX() - 1;
		}

		if (pPos.getX() + 1 >= maxColum)
		{
			xFin = maxColum - 1;
		}
		else
		{
			xFin = pPos.getX() + 1;
		}

		if (pPos.getY() - 1 < 0)
		{
			yInicio = 0;
		}
		else
		{
			yInicio = pPos.getY() - 1;
		}

		if (pPos.getY() + 1 >= maxFila)
		{
			yFin = maxFila - 1;
		}
		else
		{
			yFin = pPos.getY() + 1;
		}

		int x, y;
		for (x = xInicio; x <= xFin; x++)
		{
			for (y = yInicio; y <= yFin; y++)
			{
				// Si hay ficha la convierte al jugador.
				if (tablero[x][y].contieneFicha())
				{
					tablero[x][y].convertirFicha(pNuevoDuenyo);
				}
			}
		}
	}

	/**
	 * elimina la ficha de la casilla seleccionada
	 * 
	 * @param pPos
	 */
	public void borrarFicha(Posicion pPos)
	{
		tablero[pPos.getX()][pPos.getY()].borrarFicha();
	}

	/**
	 * Mueve la fiha seleccionada con la accion seleccionada a la posicionObjetivo seleccionada
	 * @param pRepSel 
	 * 
	 * @param pPos
	 */
	
	public void moverFicha(Posicion pPosFicha, Accion pAccion, Posicion pPosObjetivo, Accion pRepSel)
	{
		tablero[pPosFicha.getX()][pPosFicha.getY()].moverFicha(pAccion, pPosObjetivo, pRepSel); 
	}
	 

	/**
	 * Devuelve las acciones posibles que puede realizar la ficha de la posicion
	 * pasada como param
	 * 
	 * Si no hay ficha o algo sale mal devuelve null
	 * 
	 * @param pPos
	 *            Posicion del tablero
	 * @return Lista de acciones posibles
	 */
	public LinkedList<Accion> calcularAccionesDe(Posicion pPos)
	{
		return tablero[pPos.getX()][pPos.getY()].calcularAccionesDe();
	}

	/**
	 * Decide si el parametro esta dentro de los limites correctos.
	 * 
	 * @param pPos
	 *            posicion a decidir.
	 * @return true si la posicion es correcta, false en caso contrario.
	 */
	public boolean existenCoordenadas(Posicion pPos)
	{
		boolean rdo;
		if (pPos.getX() >= maxColum || pPos.getY() >= maxFila
				|| pPos.getX() < 0 || pPos.getY() < 0)
		{
			rdo = false;
		}
		else
		{
			rdo = true;
		}

		return rdo;
	}

	/**
	 * Decide si la casilla del parametro esta ocupada por una ficha.
	 * 
	 * @param pPos
	 * @return true si la casilla contiene una ficha, false en caso contrario.
	 */
	public boolean existeFichaEnPosicion(Posicion pPos)
	{
		return tablero[pPos.getX()][pPos.getY()].contieneFicha();
	}

	/**
	 * Decide si la casilla del parametro esta ocupada por una ficha del jugador
	 * introducido.
	 * 
	 * @param pPos
	 * @param pJugador
	 * @return true si la ficha pertenece a el jugador, false en caso contrario.
	 */
	public boolean fichaEsDeJugador(Posicion pPos, Jugador pJugador)
	{
		return tablero[pPos.getX()][pPos.getY()].esDeJugador(pJugador);
	}

	public void notificarObservers()
	{
		setChanged();
		notifyObservers();
	}

	/**
	 * Calcula las posiciones a la que la ficha en la posición pPosFicha puede acceder con la acción pAccSel.
	 * 
	 * @param pPosFicha
	 * @param pAccSel
	 * @return
	 */
	public LinkedList<Posicion> calcularPosiblesMovimientos(Posicion pPosFicha, Accion pAccSel)
	{		
		return casillasLibresADistancia(tablero[pPosFicha.getX()][pPosFicha.getY()].calcularRango(pAccSel), pPosFicha);
	}

	
	/**
	 * Devuelve los tipos de replicación que puede realizar esa ficha en pPos.
	 * 
	 * @param pPos
	 * @return
	 */
	public LinkedList<Accion> getReplicPosiblesCasilla(Posicion pPos)
	{
		return tablero[pPos.getX()][pPos.getY()].getReplicPosiblFicha();
	}

	@Override
	public String toString()
	{
		String rdo;
		int x, y;

		rdo = maxColum + "";
		rdo += "&";
		rdo += maxFila + "";
		rdo += "&";

		for (y = 0; y < maxFila; y++)
		{
			for (x = 0; x < maxColum; x++)
			{
				rdo += tablero[x][y].toString() + ";";
			}
		}

		return rdo;

	}

}
