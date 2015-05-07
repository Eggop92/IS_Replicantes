package packfichas;

import java.util.LinkedList;

import packestructuras.Posicion;
import packppal.Accion;
import packppal.Entidad;
import packppal.Jugador;

/**
 * Clase abstracta que representa las fichas que puede haber sobre el tablero.
 * 
 * @author Egoitz
 * 
 */
public abstract class Ficha extends Entidad
{

	private Jugador propietario;

	/**
	 * Constructora
	 * 
	 * @param pJugador
	 *            jugador al que pertenece la ficha.
	 * @param pPos
	 *            posicion en la que se encontrara la ficha.
	 */
	public Ficha(Jugador pJugador, Posicion pPos)
	{
		super(pPos);
		propietario = pJugador;
	}

	/**
	 * cambia el jugador de la ficha.
	 * 
	 * @param pJugador
	 *            jugador que adquiere la ficha.
	 */
	public void convertir(Jugador pJugador)
	{
		propietario = pJugador;
	}

	/**
	 * obtiene el Jugador al que pertenece la ficha Necesario para que los
	 * "hijo" tengan acceso al propietario.
	 * 
	 * @return Jugador
	 */
	public Jugador getJugador()
	{
		return propietario;
	}

	/**
	 * Dado un Jugador, dice si la ficha pertenece a este jugador o no.
	 * 
	 * @param pJugador
	 * @return
	 */
	public boolean esDeJugador(Jugador pJugador)
	{
		return propietario.equals(pJugador);
	}

	/**
	 * Mueve la ficha a la posObjetivo realizando la accion seleccionada
	 * @param pPosObjetivo 
	 * @param pAccion 
	 * @param pRepSel 
	 */
	public void moverFicha(Accion pAccion, Posicion pPosObjetivo, Accion pRepSel)
	{
		realizarAccion(pAccion, pPosObjetivo, pRepSel);
	}

	/*
	 * Metodos abstractos que deberan implementar cada tipo de ficha.
	 */
	public abstract LinkedList<Accion> calcularAcciones();

	public abstract int calcularRango(Accion pAccion);

	public abstract void realizarAccion(Accion pAccion, Posicion pObjetivo, Accion pRepSel);

	public abstract String toString();
	
	public abstract LinkedList<Accion> getReplicPosibles();
}
