package packestructuras;

/**
 * Clase Nodo
 * Esta clase es un TAD
 * 
 * Implementa los nodos para ListaLigadaCircular
 * 
 * @author Alex
 *
 */
public class Nodo<T>
{

	private T data; //datos a contener por el nodo
	private Nodo<T> next; //puntero al siguiente nodo
	
	/**
	 * Constructora de Nodo
	 * 
	 * @param pData Datos a ser contenidos por este nodo
	 */
	public Nodo(T pData)
	{
		this.data = pData;
		this.next = null;
	}
	
	/**
	 * Fija el nodo siguiente a this
	 * 
	 * @param pNext el que sera el siguiente nodo a this
	 */
	public void setNext(Nodo<T> pNext)
	{
		this.next = pNext;
	}
	
	/**
	 * Devuelve el nodo siguiente a this
	 * 
	 * @return El nodo siguiente a this
	 */
	public Nodo<T> getNext()
	{
		return this.next;
	}
	
	/**
	 * Devuelve los datos contenidos en este nodo
	 * 
	 * @return Los datos contenidos en este nodo
	 */
	public T getData()
	{
		return this.data;
	}
	
}
