package packinterfaz.listeners;

import java.awt.event.MouseAdapter;
import java.util.LinkedList;

import javax.swing.JButton;

import packestructuras.Posicion;
import packinterfaz.SeleccionarAccion;
import packinterfaz.VentanaJuego;
import packppal.Accion;
import packppal.Tablero;


public class CasillaPinchada extends MouseAdapter
{
	
	private int tamX;
	private int tamY;
	private VentanaJuego ventanaPpal;
	
	public CasillaPinchada(int pTamX, int pTamY, VentanaJuego pVent)
	{
		tamX = pTamX;
		tamY = pTamY;
		ventanaPpal = pVent;
	}
	
	public void mouseClicked(java.awt.event.MouseEvent e) 
	{
		JButton botonPulsado = (JButton) e.getComponent(); 
		//e.getComponent() seria el elemento que a lanzado el evento (en este caso un botn del tablero)
		String numS = botonPulsado.getName();
		int num= Integer.parseInt(numS);
		int x = num % tamX;
		int y = num / tamY;
		Posicion pos = new Posicion(x, y);
		
		//Comprobamos si la ficha elegida pertenece al jugador actual
		if(Tablero.getMiTablero().fichaEsDeJugador(pos, ventanaPpal.getJugadorActual()))
		{
			LinkedList<Accion> listaAcc = Tablero.getMiTablero().calcularAccionesDe(pos);
			
			if(listaAcc != null)//Si hay acciones disponibles preguntar al usu. que hacer
			{
				SeleccionarAccion SA = new SeleccionarAccion(listaAcc, pos);
				SA.setVisible(true);
			}//Si no hay acciones disponibles pasamos de el
		}			
	}
}
