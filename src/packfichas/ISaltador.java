package packfichas;

import packestructuras.Posicion;

/**
 * Interfaz ISaltador
 * 
 * Da un atributo especifico que indica la distancia de salto a toda clase que implemente esta interfaz
 * 
 * @author Alex
 *
 */
public interface ISaltador 
{
	int alcanceSalto = 2;
	
	public abstract void saltar(Posicion pPos);
}
