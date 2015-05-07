package packestructuras;


/**
 * Interfaz IListaLigadaCircular
 * 
 * Indica las cabeceras necesarias para implementar una lista ligada circular que nos brinde la 
 * funcionalidad necesaria en nuestro caso
 * 
 * @author Alex
 *
 * @param <T>
 */
public interface IListaLigadaCircular <T>
{

	/**
	 * Devuelve el objeto siguiente al actual en la lista y situa este nuevo como elemento actual
	 * 
	 * @return El siguiente objeto en la lista
	 */
	public T next();
	
	/**
	 * Anyade un objeto a la lista
	 * 
	 * @param arg Objeto a ser anyadido
	 */
	public void insert(T arg);
	
	/**
	 * Indica si la lista esta vacia
	 * @return Estado de la lista
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve la cantidad de elementos almacenada en la lista
	 * @return La cantidad de elementos almacenada en la lista
	 */
	public int size();
	
}
