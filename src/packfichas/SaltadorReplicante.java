package packfichas;

import java.util.LinkedList;

import packestructuras.Posicion;
import packppal.Accion;
import packppal.Jugador;
import packppal.Tablero;

/**
 * Ficha que implementa ISaltadodor y IReplicante, con lo que tiene ambas
 * funciones
 * 
 * @author Egoitz
 * 
 */

public class SaltadorReplicante extends Ficha implements IReplicante, ISaltador
{
	private LinkedList<Accion> replicPosib;
	
	public SaltadorReplicante(Jugador pPropietario, Posicion pPosicion)
	{
		super(pPropietario, pPosicion);
		replicPosib = new LinkedList<Accion>();
		replicPosib.add(Accion.REPLICAR);
		replicPosib.add(Accion.SALTAR);
	}
	
	public LinkedList<Accion> getReplicPosibles()
	{
		return replicPosib;
	}

	/**
	 * Este metodo precisa que acciones puede realizar esta ficha.
	 */
	public LinkedList<Accion> calcularAcciones()
	{
		LinkedList<Accion> rdo = new LinkedList<Accion>();

		// Esta clase puede realizar estas caracteristicas, que se añaden a la
		// lista.
		rdo.add(Accion.SALTAR);
		rdo.add(Accion.REPLICAR);

		return rdo;
	}

	/**
	 * Con la accion a realizar, calcula hasta donde puede llegar el rango de
	 * accion de la ficha
	 * 
	 * @param Accion
	 *            pAccion accion que se va a realizar.
	 * @return int rango de accion.
	 */
	public int calcularRango(Accion pAccion)
	{
		int rdo;

		switch (pAccion)
		{
			case SALTAR:
				rdo = alcanceSalto;
				break;
			case REPLICAR:
				rdo = alcanceReplicacion;
				break;
			default:
				rdo = -1; // <-- Asegurarse de que es tratado correctamente para
						  // que no pase
		}

		return rdo;
	}

	/**
	 * Segun la accion a realizar, se decide que metodo se ejecutara
	 * 
	 * @param Accion
	 *            pAccion accion que se va a realizar.
	 * @param Posicion
	 *            pObjetivo lugar donde se realizara la accion
	 */
	public void realizarAccion(Accion pAccion, Posicion pObjetivo, Accion pRepSel)
	{
		switch (pAccion)
		{
			case SALTAR:
				saltar(pObjetivo);
				break;
			case REPLICAR:
				replicar(pObjetivo, pRepSel);
				break;
		}
		
		Tablero.getMiTablero().notificarObservers();
						
	}

	/**
	 * Deberia ser private. Puesto public porque nos obliga la interfaz
	 * ISaltador metodo que mueve la ficha de la casilla actual a la
	 * introducida. Despues convierte a los de alrededor.
	 * 
	 * @param pObjetivo
	 *            posicion objetivo.
	 */
	public void saltar(Posicion pPosObjetivo)
	{

		Ficha pNuevaFicha = new SaltadorReplicante(getJugador(), pPosObjetivo);
		Tablero.getMiTablero().ponerFicha(pPosObjetivo, pNuevaFicha);
		Tablero.getMiTablero().convertirAdyacentes(pPosObjetivo, getJugador());
		Tablero.getMiTablero().borrarFicha(super.getPosicion());
	}

	/**
	 * Deberia ser private. Puesto public porque nos obliga la interfaz
	 * IReplicante metodo para crear una nueva ficha a una casilla adyacente
	 * Despues convierte a los de alrededor de la nueva ficha.
	 * @param pRepSel 
	 * 
	 * @param pObjetivo
	 */
	public void replicar(Posicion pPosObjetivo, Accion pRepSel)
	{

		Ficha pNuevaFicha = null;

		switch (pRepSel)
		{
			case SALTAR:
				pNuevaFicha = new Saltador(getJugador(), pPosObjetivo);
				break;
			case REPLICAR:
				pNuevaFicha = new Replicante(getJugador(), pPosObjetivo);
				break;
		}

		Tablero.getMiTablero().ponerFicha(pPosObjetivo, pNuevaFicha);
		Tablero.getMiTablero().convertirAdyacentes(pPosObjetivo, getJugador());
	}

	public String toString()
	{
		return "T" + getJugador().getNumJugador() + "";
	}

}
