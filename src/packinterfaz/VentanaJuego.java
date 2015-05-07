package packinterfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import packinterfaz.listeners.CasillaPinchada;
import packppal.Jugador;
import packppal.ListaJugadores;
import packppal.Tablero;

public class VentanaJuego extends JFrame implements Observer {

	private int tamTablero;
	private Jugador jugadorActual;
	
	private JPanel contentPane;
	private JPanel norte;
	private JPanel sur;
	private JPanel este;
	private JPanel oeste;
	private JPanel centro;
	
	private JPanel unJugador;
	private JLabel nombreUno;
	private JLabel puntuacionUno;
	private JPanel dosJugador;
	private JLabel nombreDos;
	private JLabel puntuacionDos;
	private JPanel tresJugador;
	private JLabel puntuacionTres;
	private JLabel nombreTres;
	private JPanel cuatroJugador;
	private JLabel nombreCuatro;
	private JLabel puntuacionCuatro;
	
	private JButton[] botones;
	
	private CasillaPinchada casillaEvent;

	/**
	 * Create the frame.
	 */
	/*
	public VentanaJuego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//tamTablero= Tablero.getMiTablero().getNumColum();
		tamTablero=10;
		iniciarVentana();
	}*/
	
	public VentanaJuego(int pTamanyo, int pNumJug) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Replicantes");
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		tamTablero=pTamanyo;
		botones= new JButton[tamTablero*tamTablero];
		iniciarVentana();
	}
	
	public VentanaJuego() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Replicantes");
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		tamTablero=Tablero.getMiTablero().getTamanyoSiCuadrado();
		botones= new JButton[tamTablero*tamTablero];
		iniciarVentana();
	}

	private void iniciarVentana() 
	{	
		//norte
		//Con tiempo un menucillo, hasta entonces, nah
		JPanel norte = new JPanel();
		contentPane.add(norte, BorderLayout.NORTH);
				
		//OESTE <-- Izquierda
		oeste = new JPanel(new GridLayout(3, 1));
		oeste.setBounds(700, 0, 100, 600);
		
		unJugador= new JPanel(new GridLayout(2,1));
		nombreUno= new JLabel("Jugador Uno");
		nombreUno.setForeground(Color.red);
		puntuacionUno= new JLabel("0");
		puntuacionUno.setForeground(Color.red);
		unJugador.add(nombreUno);
		unJugador.add(puntuacionUno);
		oeste.add(unJugador);
		
		tresJugador= new JPanel(new GridLayout(2,1));
		nombreTres= new JLabel("Jugador Tres");
		nombreTres.setForeground(Color.green);
		puntuacionTres= new JLabel("0");
		puntuacionTres.setForeground(Color.green);
		tresJugador.add(nombreTres);
		tresJugador.add(puntuacionTres);
		oeste.add(tresJugador);
		contentPane.add(oeste, BorderLayout.WEST);
		
		//ESTE <-- Derecha
		este = new JPanel(new GridLayout(3, 1));
		este.setBounds(0, 0, 100, 600);
		
		dosJugador= new JPanel(new GridLayout(2,1));
		nombreDos= new JLabel("Jugador Dos");
		nombreDos.setForeground(Color.blue);
		puntuacionDos= new JLabel("0");
		puntuacionDos.setForeground(Color.blue);
		dosJugador.add(nombreDos);
		dosJugador.add(puntuacionDos);
		este.add(dosJugador);
		
		cuatroJugador= new JPanel(new GridLayout(2,1));
		nombreCuatro= new JLabel("Jugador Cuatro");
		nombreCuatro.setForeground(Color.MAGENTA);
		puntuacionCuatro= new JLabel("0");
		puntuacionCuatro.setForeground(Color.MAGENTA);
		cuatroJugador.add(nombreCuatro);
		cuatroJugador.add(puntuacionCuatro);
		este.add(cuatroJugador);
		contentPane.add(este, BorderLayout.EAST);
				
		//SUR
		//Nah, pero ponemos un JPanel para dejar un huequecito, si no mola, se quita
		sur = new JPanel();
		contentPane.add(sur, BorderLayout.SOUTH);
				
		//CENTRO
		//A lot of botones...
		centro = new JPanel();
		centro.setLayout(new GridLayout(tamTablero+1, tamTablero+1, 0, 0));
		casillaEvent = new CasillaPinchada(tamTablero, tamTablero, this);
		colocarBotones();
		refrescar();		
		contentPane.add(centro, BorderLayout.CENTER);
		
		//Decidimos quien es el primer jugador y anunciamos
		jugadorActual = ListaJugadores.getMiListaJugadores().determinarPrimerJugador();
		anunciarJugador(jugadorActual.getNumJugador());
	}	
	
	public CasillaPinchada getCasillaEvent()
	{
		return casillaEvent;
	}	
	
	/**
	 * Dado el numero de un jugador muestra un mensaje por pantalla que indica que le toca
	 * @param numJugador
	 */
	private void anunciarJugador(int pNumJugador)
	{
		String mensaje = "Le toca al jugador " + pNumJugador;
		JOptionPane.showMessageDialog(this, mensaje);
	}

	private void colocarBotones() 
	{
		//MODIFICADO PARA PODER PONER LOS INDICES DE LAS COLUMNAS Y LAS FILAS
		int numNombre=0;
		for(int filas=0; filas<=tamTablero ;filas++){//for que recorre las filas
			for(int columnas=0; columnas<=tamTablero ;columnas++){//for que recorre las columnas
				
				if(columnas!=0 && filas!=0)
				{
					//creamos boton
					JButton jB = new JButton();
					jB.setSize(25, 25);
					jB.setName(""+numNombre);
					botones[numNombre] = jB;
					numNombre++;
					
					//EVENTOS:					
					jB.addMouseListener(getCasillaEvent());
					
					centro.add(jB);
				}
				else 
				{
					if (columnas == 0 && filas == 0)
					{
						//Primera fila y columna
						JLabel vacio= new JLabel("");
						centro.add(vacio);
					}
					else
					{
						if(columnas == 0)
						{
							//Primera columna
							JLabel aux = new JLabel((filas-1)+"");
							aux.setSize(25, 25);
							aux.setHorizontalTextPosition(JLabel.CENTER);
							centro.add(aux);
						}
						else
						{
							//Primera fila
							JLabel aux = new JLabel((columnas-1)+"");
							centro.add(aux);
						}
						
					}
				}//Fin if col!=0 && fil!=0
							
			}//Fin bucle interno
		}//Fin bucle externo		
	}//Fin mtdo

	private void refrescar() {
		String tabl = Tablero.getMiTablero().toString();
		Scanner sc = new Scanner(tabl);
		String segmento;
		sc.useDelimiter("&");
		segmento = sc.next();
		segmento = sc.next();
		
		sc.useDelimiter(";");
		
		segmento=sc.next();
		segmento=segmento.substring(1);
		int jug, n1=0, n2=0, n3=0, n4=0;
		
		for(int cont = 0; cont < ((tamTablero*tamTablero)); cont++)
		{						
			if (!segmento.equals("NA"))
			{
				botones[cont].setText(segmento);
				jug = Integer.parseInt(segmento.charAt(1)+"");
				switch (jug){
					case 1: n1++; break;
					case 2: n2++; break;
					case 3: n3++; break;
					case 4: n4++; break;
				}
			}else {
				botones[cont].setText("");
			}
			try
			{
				segmento=sc.next();
			}
			catch(NoSuchElementException e)
			{
				
			}				
		}
		
		puntuacionUno.setText(n1+"");
		puntuacionDos.setText(n2+"");
		puntuacionTres.setText(n3+"");
		puntuacionCuatro.setText(n4+"");		
	}
	
	/**
	 * comprueba si se puede continuar por tablero y por jugador
	 * @return true si se puede continuar; false si no es posible
	 */
	public void siguienteJugador()
	{		
		if(Tablero.getMiTablero().posibleContinuarPorTablero() && ListaJugadores.getMiListaJugadores().posbileContinuarPorJugadores())
		{
			jugadorActual = ListaJugadores.getMiListaJugadores().siguienteJugador();
			anunciarJugador(jugadorActual.getNumJugador());
		}
		else
		{
			Jugador jugGanador = ListaJugadores.getMiListaJugadores().obtenerGanador();
			JOptionPane.showMessageDialog(this, "El jugador ganador es el jugador " + jugGanador.getNumJugador() + "\nEnhorabuena!");
			cerrarVentana();
		}
		
	}
	
	public Jugador getJugadorActual(){
		return jugadorActual;
	}

	private void cerrarVentana()
	{
		dispose();		
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		//GestorGuardado.getMiGestorGuardado().guardarPartida();
		refrescar();
		siguienteJugador();
	}	
}
