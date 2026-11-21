package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import controlador.Controlador;
import controlador.IniciarClases;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class InterfazRegiones extends JFrame{

	private Controlador controlador;
	private JFrame frmDisenoDeRegiones;
	private JTextField tf_nombre;
	private JTextField tf_latitud;
	private JTextField tf_longitud;
	private DefaultListModel<String> listadoProvincias = new DefaultListModel<>();
	private DefaultListModel<String> listadoSimilaridades = new DefaultListModel<>();
	private JComboBox<String>cbProvincia1;
	private JComboBox<String>cbProvincia2;
	private JSpinner spinnerPeso;
	private JSpinner spinnerRegiones;
	private SpinnerNumberModel valoresSpinnerPeso = new SpinnerNumberModel(1,1,null,1);
	private SpinnerNumberModel valoresSpinnerRegiones = new SpinnerNumberModel(1,1,null,1);
	private JTextArea taInformacion;
	private JMapViewer vistaMapa;
	private Coordinate inicio;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new IniciarClases();

	}


	public InterfazRegiones() {
		initialize();
	}

	private void initialize() {

		frmDisenoDeRegiones = new JFrame();
		frmDisenoDeRegiones.setTitle("Diseño de regiones");
		frmDisenoDeRegiones.setBounds(100, 100, 1366, 766);
		frmDisenoDeRegiones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDisenoDeRegiones.getContentPane().setLayout(null);
		frmDisenoDeRegiones.setVisible(true);
		frmDisenoDeRegiones.setResizable(false);
		
		JPanel panel_mapa = new JPanel();
		panel_mapa.setBorder(new LineBorder(new Color(0, 0, 160), 5, true));
		panel_mapa.setBounds(0, 0, 500, 768);
		panel_mapa.setLayout(new BorderLayout());
		frmDisenoDeRegiones.getContentPane().add(panel_mapa);
		
		vistaMapa = new JMapViewer();
		new DefaultMapController(vistaMapa);
		inicio = new Coordinate(-38.4161, -63.6167);
		int zoom = 4;
		vistaMapa.setDisplayPosition(inicio, zoom);
		panel_mapa.add(vistaMapa, BorderLayout.CENTER);
		
		JPanel panelCargaDatos = new JPanel();
		panelCargaDatos.setBorder(new LineBorder(new Color(0, 0, 160), 5, true));
		panelCargaDatos.setBounds(510, 0, 832, 493);
		panelCargaDatos.setLayout(null);
		frmDisenoDeRegiones.getContentPane().add(panelCargaDatos);
		
		JLabel lblAgregarProvincia = new JLabel("Agregar provincia:");
		lblAgregarProvincia.setFont(new Font("Serif", Font.PLAIN, 14));
		lblAgregarProvincia.setBounds(84, 5, 150, 30);
		panelCargaDatos.add(lblAgregarProvincia);
		
		JLabel lblNombreProvincia = new JLabel("Nombre:");
		lblNombreProvincia.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNombreProvincia.setBounds(50, 40, 100, 30);
		panelCargaDatos.add(lblNombreProvincia);
		
		tf_nombre = new JTextField();
		tf_nombre.setBounds(172, 43, 100, 30);
		panelCargaDatos.add(tf_nombre);
		tf_nombre.setColumns(10);
		
		JLabel lblLatitud = new JLabel("Latitud:");
		lblLatitud.setFont(new Font("Serif", Font.PLAIN, 14));
		lblLatitud.setBounds(50, 80, 100, 30);
		panelCargaDatos.add(lblLatitud);
		
		tf_latitud = new JTextField();
		tf_latitud.setColumns(10);
		tf_latitud.setBounds(172, 83, 100, 30);
		panelCargaDatos.add(tf_latitud);
		
		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setFont(new Font("Serif", Font.PLAIN, 14));
		lblLongitud.setBounds(50, 120, 100, 30);
		panelCargaDatos.add(lblLongitud);
		
		tf_longitud = new JTextField();
		tf_longitud.setColumns(10);
		tf_longitud.setBounds(172, 123, 100, 30);
		panelCargaDatos.add(tf_longitud);
		
		JButton btnAnadirProvincia = new JButton("Añadir");
		btnAnadirProvincia.setFont(new Font("Serif", Font.PLAIN, 14));
		btnAnadirProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.agregarProvincia(tf_nombre.getText(), Double.parseDouble(tf_latitud.getText()), Double.parseDouble(tf_longitud.getText()));
			}
		});
		btnAnadirProvincia.setBounds(50, 160, 100, 30);
		panelCargaDatos.add(btnAnadirProvincia);
		
		JLabel lblProvincia_1 = new JLabel("Provincia 1:");
		lblProvincia_1.setFont(new Font("Serif", Font.PLAIN, 14));
		lblProvincia_1.setBounds(50, 248, 100, 30);
		panelCargaDatos.add(lblProvincia_1);
		
		JLabel lblProvincia_2 = new JLabel("Provincia 2:");
		lblProvincia_2.setFont(new Font("Serif", Font.PLAIN, 14));
		lblProvincia_2.setBounds(50, 288, 100, 30);
		panelCargaDatos.add(lblProvincia_2);
		
		cbProvincia1 = new JComboBox();
		cbProvincia1.setFont(new Font("Serif", Font.PLAIN, 14));
		cbProvincia1.setBounds(162, 250, 100, 30);
		panelCargaDatos.add(cbProvincia1);
		
		cbProvincia2 = new JComboBox();
		cbProvincia2.setFont(new Font("Serif", Font.PLAIN, 14));
		cbProvincia2.setBounds(162, 288, 100, 30);
		panelCargaDatos.add(cbProvincia2);
		
		JButton btnAnadirArista = new JButton("Añadir");
		btnAnadirArista.setFont(new Font("Serif", Font.PLAIN, 14));
		btnAnadirArista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.agregarSimilaridad((String) cbProvincia1.getSelectedItem(),(String) cbProvincia2.getSelectedItem(), (int) spinnerPeso.getValue());
			}
		});
		btnAnadirArista.setBounds(50, 364, 100, 30);
		panelCargaDatos.add(btnAnadirArista);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Serif", Font.PLAIN, 14));
		lblPeso.setBounds(82, 332, 85, 22);
		panelCargaDatos.add(lblPeso);
		
		spinnerPeso = new JSpinner(valoresSpinnerPeso);
		spinnerPeso.setFont(new Font("Serif", Font.PLAIN, 14));
		spinnerPeso.setBounds(174, 328, 60, 30);
		panelCargaDatos.add(spinnerPeso);
		
		JLabel lblCantidadRegiones = new JLabel("Cantidad de regiones:");
		lblCantidadRegiones.setFont(new Font("Serif", Font.PLAIN, 14));
		lblCantidadRegiones.setBounds(50, 430, 140, 30);
		panelCargaDatos.add(lblCantidadRegiones);
		
		spinnerRegiones = new JSpinner(valoresSpinnerRegiones);
		spinnerRegiones.setFont(new Font("Serif", Font.PLAIN, 14));
		spinnerRegiones.setBounds(188, 431, 60, 30);
		panelCargaDatos.add(spinnerRegiones);
		
		JButton btnGenerarRegiones = new JButton("Generar regiones");
		btnGenerarRegiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.generarRegiones((int)spinnerRegiones.getValue());
			}
		});
		btnGenerarRegiones.setFont(new Font("Serif", Font.PLAIN, 14));
		btnGenerarRegiones.setBounds(614, 430, 150, 30);
		panelCargaDatos.add(btnGenerarRegiones);
		
		JList list_Provincias = new JList();
		list_Provincias.setFont(new Font("Serif", Font.PLAIN, 14));
		list_Provincias.setBounds(323, 10, 499, 180);
		list_Provincias.setModel(listadoProvincias);
		panelCargaDatos.add(list_Provincias);
		
		JList listSimilaridades = new JList();
		listSimilaridades.setFont(new Font("Serif", Font.PLAIN, 14));
		listSimilaridades.setBounds(323, 224, 499, 180);
		listSimilaridades.setModel(listadoSimilaridades);
		panelCargaDatos.add(listSimilaridades);
		
		JLabel lblAgregarSimilaridades = new JLabel("Agregar similaridades:");
		lblAgregarSimilaridades.setFont(new Font("Serif", Font.PLAIN, 14));
		lblAgregarSimilaridades.setBounds(84, 200, 150, 30);
		panelCargaDatos.add(lblAgregarSimilaridades);
		
		taInformacion = new JTextArea();
		taInformacion.setFont(new Font("Serif", Font.PLAIN, 14));
		taInformacion.setBounds(510, 513, 832, 216);
		frmDisenoDeRegiones.getContentPane().add(taInformacion);

	}
	public void mostrar() {
		frmDisenoDeRegiones.setVisible(true);
		frmDisenoDeRegiones.repaint();
	}
	public void actualizarListaProvincias(int posicion, String provincia) {
		listadoProvincias.addElement("P"+String.valueOf(posicion) + ": " + provincia );;
	}
	public void actualizarListaSimilaridad(String provincia1, String provincia2, int similaridad) {
		listadoSimilaridades.addElement("[ "+ provincia1+ ", " + provincia2 + " ]: " + String.valueOf(similaridad));
	}
	public void actualizarComboBox(String provincia) {
		cbProvincia1.addItem(provincia.trim());
		cbProvincia2.addItem(provincia.trim());
	}	
	public void actualizarRegiones(List<List<String>> stringRegiones) {
		StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < stringRegiones.size(); i++) {
	        sb.append("Región ").append(i + 1).append(": ")
	          .append(String.join(", ", stringRegiones.get(i)))
	          .append("\n");
	    }
	    // Ejemplo si tenés un JTextArea llamado textAreaRegiones:
	    taInformacion.setText(sb.toString());
	    taInformacion.revalidate();
	    taInformacion.repaint();
	
}
	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}
	public void MensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.INFORMATION_MESSAGE );
	}
	public void MensajeExito(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Exito!", JOptionPane.INFORMATION_MESSAGE );
	}
	public void MensajeSimilaridadAgregada(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Similaridad agregada", JOptionPane.INFORMATION_MESSAGE );
	}
}
