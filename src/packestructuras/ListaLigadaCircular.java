package packestructuras;


/**
 * Clase ListaLigadaCircular<T>
 * Esta clase es un TAD
 * 
 * Esta clase como su nombre indica es una lista ligada circular. Acepta todo tipo de objetos, ya que es
 * generica
 * 
 * No mantiene ningun tipo de orden sobre sus objetos, ni tiene ninguna forma predecible o uniforme de anyadir
 * objetos a la lista
 * 
 * @author Alex
 *
 * @param <T>
 */
public class ListaLigadaCircular<T> implements IListaLigadaCircular<T> 
{
	private int size;
	private Nodo<T> actual; //Nodo actual
	private Nodo<T> primero; //Primero nodo de la lista
	
	/**
	 * Crea una lista ligada circular sin nodos
	 */
	public ListaLigadaCircular()
	{
		primero = null;
		actual = null;
		size = 0;
	}
	
	/**
	 * Crea una lista ligada circular con un unico nodo que contiene los datos recibidos como parametro
	 * @param pObj Datos a incluir en la lista
	 */
	public ListaLigadaCircular(T pObj)
	{
		Nodo<T> node = new Nodo(pObj);
		primero = node;
		actual = node;
		actual.setNext(primero);
		size = 1;
	}
	
	/**
	 * Devuelve los datos del nodo a actual
	 * Actual se posicionara despues sobre el nodo siguiente del cual hemos extraido los datos
	 * 
	 * Si la lista esta vacia devuelve null
	 * 
	 * @return Los datos del nodo siguiente al actual
	 * @Override
	 */	
	public T next() 
	{
		T data = null;
		
		if(!this.isEmpty())
		{
			data = actual.getData(); //extraigo los datos del nodo
			actual = actual.getNext(); //actual apunta ahora al siguiente nodo
		}

		return data;
	}

	/**
	 * Inserta un nodo en la lista que contiene los datos pasados como parametro
	 * El nuevo nodo sera el nodo actual
	 *  
	 * @Override
	 */	
	public void insert(T pObj) 
	{
		Nodo<T> node = new Nodo(pObj);
		
		if(this.isEmpty()) //Si la lista esta vacia
		{
			primero = node; //El nuevo nodo sera el primero
			actual = node; //Ademas del actual
			actual.setNext(primero); //Aqui se crea el enlace con si mismo para que sea circular
		}
		else //Si la lista NO esta vacia
		{
			node.setNext(actual.getNext());//Al nuevo nodo le metemos el siguiente a actual
			actual.setNext(node); //Al actual le metemos el nuevo nodo.
			this.next();
		}
		
		size = size +1;
	}
	
	/**
	 * Indica si la lista esta vacia o no
	 * @return Estado de la lista
	 */
	public boolean isEmpty()
	{
		return primero == null;
	}
	
	/**
	 * Devuelve el numero de elementos de la lista
	 * @return El numero de elementos de la lista
	 */
	public int size()
	{
		return this.size;
	}

	public ListaLigadaCircular clonar()
	{
		ListaLigadaCircular<T> copia = new ListaLigadaCircular<T>();
		
		for(int i = 0; i < this.size(); i++)
		{
			copia.insert(this.next());
		}
		
		return copia;
	}
	
	public IteratorListaLigadaCircular<T> iterator()
	{
		return new IteratorListaLigadaCircular<T>(primero);
	}
}
