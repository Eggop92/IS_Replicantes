package packestructuras;

import java.util.Iterator;


public class IteratorListaLigadaCircular<T> implements Iterator<T>
{
	private Nodo<T> actual;
	private Nodo<T> primero;
	private boolean primeraPasada;
	
	public IteratorListaLigadaCircular(Nodo<T> pPrim)
	{
		actual = pPrim;
		primero = pPrim;
		primeraPasada = true;
	}

	@Override
	public boolean hasNext()
	{
		boolean rtdo;
		
		if(actual != null)
		{
			if(primeraPasada)
			{
				rtdo = true;
				primeraPasada = false;
			}
			else
			{
				rtdo = (actual != primero);
			}
		}
		else
		{
			rtdo = false;
		}
		
		return rtdo;
				
	}

	@Override
	public T next()
	{
		T rtdo = actual.getData();
		actual = actual.getNext();
		return rtdo;
	}

	@Override
	public void remove()
	{
		// TODO Auto-generated method stub		
	}

}
