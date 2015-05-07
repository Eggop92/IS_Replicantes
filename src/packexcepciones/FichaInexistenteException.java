package packexcepciones;

public class FichaInexistenteException extends Exception 
{
	public FichaInexistenteException()
	{
		super();
		System.out.println("No existe una ficha en las coordenadas/posicion seleccionadas");
	}
}
