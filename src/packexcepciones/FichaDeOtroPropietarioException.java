package packexcepciones;

public class FichaDeOtroPropietarioException extends Exception 
{
	public FichaDeOtroPropietarioException()
	{
		super();
		System.out.println("La ficha de las coordenadas/posicion elegidas pertenece a otro juagdor diferente (y no a ti)");
	}
}
