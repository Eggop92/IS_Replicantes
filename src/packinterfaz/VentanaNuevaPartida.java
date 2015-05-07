package packinterfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packppal.ListaJugadores;
import packppal.Tablero;

public class VentanaNuevaPartida extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	private int numjug;
	private JComboBox comboBox;
	private JComboBox comboBox2; 

	/**
	 * Create the dialog.
	 */
	public VentanaNuevaPartida() 
	{
		setTitle("Nueva Partida");
		setBounds(100, 100, 267, 163);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblSeleccionaTamao = new JLabel("Selecciona tamano:");
				panel.add(lblSeleccionaTamao);
			}
			{
				comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30"}));
				panel.add(comboBox);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblSeleccionaJugadores = new JLabel("Selecciona jugadores:");
				panel.add(lblSeleccionaJugadores);
			}
			{
				comboBox2 = new JComboBox();
				comboBox2.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4"}));
				panel.add(comboBox2);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e)
					{
						try
						{
							int tamanyoX = Integer.parseInt((String) comboBox.getSelectedItem());
							int tamanyoY = tamanyoX;
							int numJug = Integer.parseInt((String) comboBox2.getSelectedItem());
							
							ListaJugadores.getMiListaJugadores().nuevaPartida(tamanyoX, tamanyoY, numJug);
							
							VentanaJuego intfazPpal = new VentanaJuego(tamanyoX, numJug);
							
							Tablero.getMiTablero().addObserver(intfazPpal);
							
							intfazPpal.setVisible(true);
							
							cerrarVentana();
						}
						catch(NumberFormatException e2)
						{
							System.out.println("Fallo al inicializar");
							e2.printStackTrace();
						}
						
						cerrarVentana();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) 
					{
						cerrarVentana();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void cerrarVentana()
	{
		this.dispose();
	}

}
