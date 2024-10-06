package interfazEspia;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;


public class MenuUbicacion {

	private JFrame frameUbicationSelection;
	private JTextField txtSeleccioneUnaUbicacion;
	private MenuMapa mapa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUbicacion window = new MenuUbicacion();
					window.frameUbicationSelection.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuUbicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		crearFrameUbicacion();
		
		JButton argentinaButton = new JButton("");
		argentinaButton.setBounds(40, 90, 100, 63);
		argentinaButton.setIcon(new ImageIcon(MenuUbicacion.class.getResource("/Imagenes/argentina.png")));		
		argentinaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mapa = new MenuMapa("argentina");
			    	frameUbicationSelection.setVisible(false);
			    	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frameUbicationSelection.getContentPane().add(argentinaButton);
		
		JButton chinaButton = new JButton("");
		chinaButton.setBounds(186, 90, 100, 63);
		chinaButton.setIcon(new ImageIcon(MenuUbicacion.class.getResource("/Imagenes/china.png")));
		chinaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mapa = new MenuMapa("china");
			    	frameUbicationSelection.setVisible(false);
			    	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frameUbicationSelection.getContentPane().add(chinaButton);
		
		JButton estadosUnidosButton = new JButton("");		
		estadosUnidosButton.setBounds(40, 193, 100, 63);
		estadosUnidosButton.setIcon(new ImageIcon(MenuUbicacion.class.getResource("/Imagenes/usa.png")));
		estadosUnidosButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mapa = new MenuMapa("usa");
			    	frameUbicationSelection.setVisible(false);
			    	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frameUbicationSelection.getContentPane().add(estadosUnidosButton);
		
		JButton rusiaButton = new JButton("");
		rusiaButton.setBounds(186, 193, 100, 63);
		rusiaButton.setIcon(new ImageIcon(MenuUbicacion.class.getResource("/Imagenes/rusia.png")));
		rusiaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mapa = new MenuMapa("rusia");
			    	frameUbicationSelection.setVisible(false);
			    	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frameUbicationSelection.getContentPane().add(rusiaButton);
		
		crearTextoSeleccionUbicacion();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuUbicacion.class.getResource("/Imagenes/imagenFondo.png")));
		lblNewLabel.setBounds(0, 0, 334, 311);
		frameUbicationSelection.getContentPane().add(lblNewLabel);
	}

	
	private void crearTextoSeleccionUbicacion() {
		txtSeleccioneUnaUbicacion = new JTextField();
		txtSeleccioneUnaUbicacion.setForeground(new Color(255, 255, 255));
		txtSeleccioneUnaUbicacion.setBackground(new Color(0, 0, 0));
		txtSeleccioneUnaUbicacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtSeleccioneUnaUbicacion.setText("Seleccione una ubicacion");
		txtSeleccioneUnaUbicacion.setEditable(false);
		txtSeleccioneUnaUbicacion.setBounds(88, 37, 160, 20);
		frameUbicationSelection.getContentPane().add(txtSeleccioneUnaUbicacion);
		txtSeleccioneUnaUbicacion.setColumns(10);
	}

	
	private void crearFrameUbicacion() {
		frameUbicationSelection = new JFrame();
		frameUbicationSelection.setBounds(550, 200, 350, 350);
		frameUbicationSelection.setResizable(false);
		frameUbicationSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameUbicationSelection.setTitle("Ubicacion de la red");
		frameUbicationSelection.getContentPane().setLayout(null);
	}
}
