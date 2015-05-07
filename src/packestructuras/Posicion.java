package packestructuras;

/**
 * Clase para representar una posicion en el tablero.
 * @author Egoitz
 *
 */

public class Posicion {

	private int x;
	private int y;
	
	
	/**
	 * Introducir las dos posiciones en el tablero. x-->Columnas y-->Filas
	 * @param pX
	 * @param pY
	 */
	public Posicion (int pX, int pY){
		x=pX;
		y=pY;
	}

	
	/**
	 * devuelve la columna
	 * @return x
	 */
	public int getX() {
		return x;
	}

	
	/**
	 * devuelve la fila
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Compara dos posiciones en base a sus coordenadas
	 */
	@Override
	public boolean equals(Object pPos)
	{
		if(pPos instanceof Posicion)
		{
			return ((this.getX() == ((Posicion)pPos).getX()) && (this.getY() == ((Posicion)pPos).getY()));
		}
		else
		{
			return false;
		}
	}
	
	
}
