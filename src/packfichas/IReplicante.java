package packfichas;

import packestructuras.Posicion;
import packppal.Accion;

/**
 * Interfaz IReplicane
 * 
 * Da un atributo especifico que indica la distancia de replicacion a toda clase que implemente esta interfaz
 * 
 * @author Alex
 *
 */
public interface IReplicante 
{
	int alcanceReplicacion = 1;
	
	public abstract void replicar(Posicion pPos, Accion pRepSel);
}
