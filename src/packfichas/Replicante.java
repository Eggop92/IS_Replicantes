package packfichas;

import java.util.LinkedList;

import packestructuras.Posicion;
import packppal.Accion;
import packppal.Jugador;
import packppal.Tablero;

public class Replicante extends Ficha implements IReplicante
{

	private LinkedList<Accion> replicPosib;
	
	public Replicante(Jugador pJugador, Posicion pPos)
	{
		super(pJugador, pPos);
		replicPosib = new LinkedList<Accion>();
		replicPosib.add(Accion.REPLICAR);
		replicPosib.add(Accion.SALTAR);
	}

	@Override
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

	@Override
	public LinkedList<Accion> calcularAcciones()
	{
		LinkedList<Accion> rdo = new LinkedList<Accion>();
		rdo.add(Accion.REPLICAR);
		return rdo;
	}

	@Override
	public int calcularRango(Accion pAccion)
	{
		return alcanceReplicacion;
	}

	@Override
	public void realizarAccion(Accion pAccion, Posicion pObjetivo, Accion pRepSel)
	{
		switch (pAccion)
		{
			case REPLICAR:
				replicar(pObjetivo, pRepSel);
				break;
		}
		
		Tablero.getMiTablero().notificarObservers();

	}

	@Override
	public LinkedList<Accion> getReplicPosibles()
	{
		return replicPosib;
	}

	@Override
	public String toString()
	{
		return "R" + getJugador().getNumJugador() + "";
	}

}
