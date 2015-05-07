package packexcepciones;

public class NumJugadoresIncorrectoException extends Exception
{
	
	public NumJugadoresIncorrectoException(int pNumJugadoresIntroducido)
	{
		super();
		System.out.println("El numero de jugadores introducido es incorrecto");
		System.out.println("El numero de jugadores debe variar entre 2 y 4 (ambos inclusive)");
		System.out.println("Pero el numero de jugadores introducido ha sido:" + pNumJugadoresIntroducido);
	}

}
