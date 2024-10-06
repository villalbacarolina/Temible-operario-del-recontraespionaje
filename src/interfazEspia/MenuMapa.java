package interfazEspia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Desktop;

public class MenuMapa {

	private JFrame frameMapa;
	private JMapViewer mapa;
	private String argentina, china, rusia, usa; //pasar a set
	private Coordinate argentinaCoordenadas, chinaCoordenadas, rusiaCoordenadas, usaCoordenadas;
	private JTextField txtNumeroProbabilidad;
	private JTextField txtProbabilidadDeIntercepcion;

	
	public MenuMapa(String ubicacion) {
		inicializarCoordenadas();
		initialize(ubicacion);
	}

	
	private void initialize(String ubicacion) {
		
		crearFrameMapa();
		crearMapa(ubicacion);
		
		frameMapa.getContentPane().add(mapa);
		
		JButton archivoButton = new JButton("Archivo");
		archivoButton.setBackground(new Color(0, 0, 0));
		archivoButton.setForeground(new Color(255, 255, 255));
		archivoButton.setBounds(57, 513, 89, 23);
		archivoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
			    	selectorDeArchivos();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		mapa.add(archivoButton);
		
		JButton enviarMensajeButton = new JButton("Enviar Mensaje");
		enviarMensajeButton.setBackground(new Color(0, 0, 0));
		enviarMensajeButton.setForeground(new Color(255, 255, 255));
		enviarMensajeButton.setBounds(216, 513, 154, 23);
		enviarMensajeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					System.out.println("Hola");  //en esta linea estaria la creacion del AGM y mostrarlo por el mapa.
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
		});
		mapa.add(enviarMensajeButton);
		
		visualizarProbabilidadIntercepcion();
		
		JLabel fondoLabel = new JLabel("");
		fondoLabel.setIcon(new ImageIcon(MenuMapa.class.getResource("/Imagenes/imagenFondo.png")));
		fondoLabel.setBounds(0, 491, 584, 70);
		mapa.add(fondoLabel);
		
	}


	private void visualizarProbabilidadIntercepcion() {
		txtProbabilidadDeIntercepcion = new JTextField();
		txtProbabilidadDeIntercepcion.setForeground(new Color(255, 255, 255));
		txtProbabilidadDeIntercepcion.setBackground(new Color(0, 0, 0));
		txtProbabilidadDeIntercepcion.setText("Probabilidad de Intercepcion");
		txtProbabilidadDeIntercepcion.setHorizontalAlignment(SwingConstants.CENTER);
		txtProbabilidadDeIntercepcion.setEditable(false);
		txtProbabilidadDeIntercepcion.setBounds(411, 503, 163, 20);
		mapa.add(txtProbabilidadDeIntercepcion);
		txtProbabilidadDeIntercepcion.setColumns(10);
		
		txtNumeroProbabilidad = new JTextField();
		txtNumeroProbabilidad.setForeground(new Color(255, 255, 255));
		txtNumeroProbabilidad.setBackground(new Color(0, 0, 0));
		txtNumeroProbabilidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumeroProbabilidad.setEditable(false);
		txtNumeroProbabilidad.setBounds(444, 528, 86, 20);
		mapa.add(txtNumeroProbabilidad);
		txtNumeroProbabilidad.setColumns(10);
	}


	private void crearFrameMapa() {
		frameMapa = new JFrame();
		frameMapa.setBounds(425, 100, 600, 600);
		frameMapa.setResizable(false);
		frameMapa.setVisible(true);
		frameMapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMapa.setTitle("Mapa");
	}


	private void crearMapa(String ubicacion) {
		mapa = new JMapViewer();
		mapa.setZoomControlsVisible(false);
		
		if(argentina.equals(ubicacion)) {
			mapa.setDisplayPosition(argentinaCoordenadas, 12);
		}
		if(rusia.equals(ubicacion)) {
			mapa.setDisplayPosition(rusiaCoordenadas, 12);
		}
		if(china.equals(ubicacion)) {
			mapa.setDisplayPosition(chinaCoordenadas, 12);
		}
		if(usa.equals(ubicacion)) {
			mapa.setDisplayPosition(usaCoordenadas, 15);
		}
	}


	private void inicializarCoordenadas() {
		argentina = "argentina";
		argentinaCoordenadas = new Coordinate(-34.521, -58.719);
		
		china = "china";
		chinaCoordenadas = new Coordinate(39.541, 116.247);
		
		rusia = "rusia";
		rusiaCoordenadas = new Coordinate(55.452, 37.370);
		
		usa = "usa";
		usaCoordenadas = new Coordinate(40.750, -73.985);
	}
	
	
	private boolean selectorDeArchivos() {
		try {
			JFileChooser selector = new JFileChooser();
			selector.setCurrentDirectory(new File("Informacion de espias"));
			int result = selector.showOpenDialog(null);
			
			if(result == JFileChooser.APPROVE_OPTION) {
				File archivoSeleccionado = new File(selector.getSelectedFile().getAbsolutePath());

				if(!Desktop.isDesktopSupported()) {
					System.out.println("Archivo no es soportado");
					return false;					
				}
				else{
					Desktop escritorio = Desktop.getDesktop(); //estas dos lineas te abren el archivo en la compu
					escritorio.open(archivoSeleccionado);		//hay q cambiar para que guarde el contenido del archivo en el grafo.
					return true;
				}
			} 
			else if(result == JFileChooser.CANCEL_OPTION){
				System.out.println("Cancelar");
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
