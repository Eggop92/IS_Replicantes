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

public class SeleccionarReplicacion extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	
	private LinkedList<Posicion> listaCasPos;
	private Posicion posFicha;
	private Accion accSel;
	

	public SeleccionarReplicacion(LinkedList<Posicion> pListaCasPos, Posicion pPosFicha, Accion pAccSel, LinkedList<Accion> pReplicPsibles)
	{
		listaCasPos = pListaCasPos;
		posFicha = pPosFicha;
		accSel = pAccSel;
		initialize(pReplicPsibles);
	}
	
	
	private void initialize(LinkedList<Accion> replicaciones) {
		setModal(true);
		setBounds(100, 100, 210, 138);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblSeleccionaLaOpcion = new JLabel("Que tipo de ficha deseas crear?:");
			contentPanel.add(lblSeleccionaLaOpcion, BorderLayout.NORTH);
		}
		{
			comboBox = new JComboBox();
			Iterator<Accion> itr = replicaciones.iterator();
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
						Accion repSel = (Accion) comboBox.getSelectedItem();
						
						SeleccionarPosicion SP = new SeleccionarPosicion(listaCasPos, posFicha, accSel, repSel);
						SP.setVisible(true);
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
