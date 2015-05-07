package packfichas;

import java.util.LinkedList;

import packestructuras.Posicion;
import packppal.Accion;
import packppal.Jugador;
import packppal.Tablero;

public class Saltador extends Ficha implements ISaltador
{

	public Saltador(Jugador pJugador, Posicion pPos)
	{
		super(pJugador, pPos);
	}

	@Override
	public void saltar(Posicion pPosObjetivo)
	{
		Ficha pNuevaFicha = new Saltador(getJugador(), pPosObjetivo);
		Tablero.getMiTablero().ponerFicha(pPosObjetivo, pNuevaFicha);
		Tablero.getMiTablero().convertirAdyacentes(pPosObjetivo, getJugador());
		Tablero.getMiTablero().borrarFicha(super.getPosicion());
	}

	@Override
	public LinkedList<Accion> calcularAcciones()
	{
		LinkedList<Accion> rdo = new LinkedList<Accion>();
		rdo.add(Accion.SALTAR);
		return rdo;
	}

	@Override
	public int calcularRango(Accion pAccion)
	{
		return alcanceSalto;
	}

	@Override
	public void realizarAccion(Accion pAccion, Posicion pObjetivo, Accion pRepSel)
	{
		switch (pAccion)
		{
			case SALTAR:
				saltar(pObjetivo);
				break;
		}			
		
		Tablero.getMiTablero().notificarObservers();
	}

	public LinkedList<Accion> getReplicPosibles()
	{
		return null;
	}

	@Override
	public String toString()
	{
		return "S" + getJugador().getNumJugador() + "";
	}

}
