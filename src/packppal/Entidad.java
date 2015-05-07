package packppal;

import packestructuras.Posicion;

/**
 * Esta clase represnta los posibles contenidos de una ficha, de ella colgaran las demas clases.
 * @author Egoitz
 *
 */

public abstract class Entidad
{

	private Posicion posicion;
	
	/**
	 * Se necesita introducir la posicion de la casilla a la que pertenece.
	 * @param pPos
	 */
	public Entidad(Posicion pPos){
		posicion = pPos;
	}
	
	/**
	 * Devuelve la posicion en la que se encuentra el elemento.
	 * @return Posicion.
	 */
	public Posicion getPosicion(){
		return posicion;
	}
}
