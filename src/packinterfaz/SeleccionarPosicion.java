package packinterfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packestructuras.Posicion;
import packppal.Accion;
import packppal.Tablero;

public class SeleccionarPosicion extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JComboBox coordenadas;
	private LinkedList<Posicion> posiciones;
	private Posicion posFicha;
	private Accion accSel;
	private Accion repSel;
	
	/**
	 * Create the dialog.
	 * @param pReplicacion 
	 * @param accSel 
	 * @param posFicha 
	 */
	public SeleccionarPosicion(LinkedList<Posicion> pPosiciones, Posicion pPosFicha, Accion pAccSel, Accion pReplicacion)
	{
		posiciones = pPosiciones;
		posFicha = pPosFicha;
		accSel = pAccSel;
		repSel = pReplicacion;
		initialize();
	}

	private void initialize()
	{
		setModal(true);
		setBounds(100, 100, 220, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			// panel.setBounds(new Rectangle(0, 0, 110, 0));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));

			JLabel lblCoordenadaX = new JLabel("X , Y");
			panel.add(lblCoordenadaX, BorderLayout.NORTH);

			coordenadas = new JComboBox();
			panel.add(coordenadas, BorderLayout.SOUTH);

			Iterator<Posicion> itr = posiciones.iterator();
			Posicion pAux;
			while (itr.hasNext())
			{
				pAux = itr.next();
				coordenadas.addItem(pAux.getX() + " , " + pAux.getY());
			}

		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblEligeLasCoordenadas = new JLabel(
						"Elige las coordenadas: ");
				panel.add(lblEligeLasCoordenadas);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter()
				{

					@Override
					public void mouseClicked(MouseEvent e)
					{
						int numPosObj = coordenadas.getSelectedIndex();
						Posicion posObj = null;

						Iterator<Posicion> itr = posiciones.iterator();
						for(int i = 0; i <= numPosObj; i++)
						{
							posObj = itr.next();
						}
						
						Tablero.getMiTablero().moverFicha(posFicha, accSel, posObj, repSel);
						
						cerrarVentana();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter()
				{

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
