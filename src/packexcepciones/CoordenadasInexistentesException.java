package packexcepciones;

public class CoordenadasInexistentesException extends Exception 
{
	public CoordenadasInexistentesException()
	{
		super();
		System.out.println("Las coordenadas/posicion elegidas no existen");
	}
}
