package packppal;

import java.util.LinkedList;

import packestructuras.Posicion;
import packfichas.Ficha;

/**
 * Clase que representa las casillas del tablero
 * 
 * @author Egoitz
 * 
 */

public class CasillaEstandar
{

	private Entidad contenido;

	/**
	 * constructora vacia
	 */
	public CasillaEstandar()
	{
		contenido = null;
	}

	/**
	 * constructora sobrecargada, para rellenar el atributo desde la creacion de
	 * la casilla
	 * 
	 * @param pContenido
	 *            elemento a introducir en la casilla
	 */
	public CasillaEstandar(Entidad pContenido)
	{
		contenido = pContenido;
	}

	/**
	 * Compara si el contenido de la casilla es una ficha.
	 * 
	 * @return true si el contenido es una ficha, false en caso contrario.
	 */
	public boolean contieneFicha()
	{
		boolean rdo;
		rdo = (contenido instanceof Ficha);
		return rdo;
	}

	/**
	 * Introduce el elemento pEntidad en la casilla.
	 * 
	 * @param pEntidad
	 *            es el elemento a introducir.
	 */
	public void rellenar(Entidad pEntidad)
	{
		contenido = pEntidad;
	}

	/**
	 * Mueve la ficha de esta casilla a la posObjetivo realizando la accion seleccionada
	 * @param pAccion
	 * @param pPosObjetivo
	 * @param pRepSel 
	 */
	public void moverFicha(Accion pAccion, Posicion pPosObjetivo, Accion pRepSel)
	{
		((Ficha)contenido).moverFicha(pAccion, pPosObjetivo, pRepSel);
	}
	 

	/**
	 * Devuelve la lista de acciones posible de la ficha de esta casilla Si no
	 * hay ficha devuelve null
	 * 
	 * @return
	 */
	public LinkedList<Accion> calcularAccionesDe()
	{
		LinkedList<Accion> rtdo = null;

		if (contieneFicha())
		{
			rtdo = ((Ficha) contenido).calcularAcciones();
		}

		return rtdo;
	}

	/**
	 * Si la casilla contiene una ficha, convierte la ficha al jugador pNuevoJ.
	 * 
	 * @param pNuevoJ
	 *            jugador al que se le añade la ficha.
	 */
	public void convertirFicha(Jugador pNuevoJ)
	{
		if (contieneFicha())
		{
			((Ficha) contenido).convertir(pNuevoJ);
		}
	}

	/**
	 * Elimina el contenido de la casilla
	 */
	public void borrarFicha()
	{
		if (contieneFicha())
		{
			contenido = null;
		}

	}

	/**
	 * Decide si la ficha que contiene es de el jugador introducido o no. En
	 * caso de no tener una ficha se devolvera un false
	 * 
	 * @param pJugador
	 * @return true si la ficha pertenece a pJugador, false en caso contrario o
	 *         no haya ficha
	 */
	public boolean esDeJugador(Jugador pJugador)
	{
		boolean rdo;
		if (contieneFicha())
		{
			rdo = ((Ficha) contenido).esDeJugador(pJugador);
		}
		else
		{
			rdo = false;
		}
		return rdo;
	}

	
	/**
	 * devuelve el rango que la ficha contenida puede alcanzar segun la accion seleccionada en pAccSel.
	 * 
	 * @param pAccSel
	 * @return
	 */
	public int calcularRango(Accion pAccSel)
	{
		return ((Ficha)this.contenido).calcularRango(pAccSel);
	}

	
	/**
	 * Devuelve la lista de tipos de  replicaciones que puede hacer esa ficha.
	 * 
	 * @return
	 */
	public LinkedList<Accion> getReplicPosiblFicha()
	{
		return ((Ficha)this.contenido).getReplicPosibles();
	}

	@Override
	public String toString()
	{
		String rdo;

		if (contieneFicha())
		{
			rdo = ((Ficha) contenido).toString();
		}
		else
		{
			rdo = "NA";
		}

		return rdo;
	}

}
