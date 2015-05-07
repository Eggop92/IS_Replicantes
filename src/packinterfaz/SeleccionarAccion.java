package packinterfaz;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import packestructuras.Posicion;
import packppal.Accion;
import packppal.Tablero;

import java.util.Iterator;
import java.util.LinkedList;

public class SeleccionarAccion extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private Posicion posFicha;
	
	/**
	 * Create the dialog.
	 */
	public SeleccionarAccion(LinkedList<Accion> acciones, Posicion pPosFicha) 
	{
		posFicha = pPosFicha;
		initialize(acciones);
	}
	private void initialize(LinkedList<Accion> acciones) {
		setModal(true);
		setBounds(100, 100, 210, 138);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblSeleccionaLaOpcion = new JLabel("Selecciona la opcion deseada:");
			contentPanel.add(lblSeleccionaLaOpcion, BorderLayout.NORTH);
		}
		{
			comboBox = new JComboBox();
			Iterator<Accion> itr = acciones.iterator();
			while (itr.hasNext()){
				comboBox.addItem(itr.next());
			}
			contentPanel.add(comboBox, BorderLayout.SOUTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						Accion accSel = ((Accion) comboBox.getSelectedItem());
						
						LinkedList<Posicion> listaCasPos = Tablero.getMiTablero().calcularPosiblesMovimientos(posFicha, accSel);	
						
						if(accSel.equals(Accion.REPLICAR))
						{
							SeleccionarReplicacion SR = new SeleccionarReplicacion(listaCasPos, posFicha, accSel, Tablero.getMiTablero().getReplicPosiblesCasilla(posFicha));
							SR.setVisible(true);
						}
						else
						{
							SeleccionarPosicion SP = new SeleccionarPosicion(listaCasPos, posFicha, accSel, null);
							SP.setVisible(true);
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
					public void mouseClicked(MouseEvent e) {
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
