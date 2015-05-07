package packinterfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packppal.GestorGuardado;

public class InicioJugar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioJugar frame = new InicioJugar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public InicioJugar() {
		setTitle("Replicantes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 121);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnIniciarPartidaNueva = new JButton("Iniciar Partida Nueva");
		btnIniciarPartidaNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaNuevaPartida vn = new VentanaNuevaPartida();
				vn.setVisible(true);
				cerrarVentana();
			}
		});
		panel.add(btnIniciarPartidaNueva);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCargarPartida = new JButton("Cargar Partida");
		btnCargarPartida.setEnabled(false);
		btnCargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestorGuardado.getMiGestorGuardado().cargarPartida();
				VentanaJuego vn = new VentanaJuego();
				vn.setVisible(true);
				cerrarVentana();
			}
		});
		panel_1.add(btnCargarPartida);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblAutoresAlexanderMariel = new JLabel("Autores: Alexander Mariel, Egoitz Puerta");
		panel_2.add(lblAutoresAlexanderMariel);
	}

	public void cerrarVentana()
	{
		this.dispose();
	}
}
