package controlador;

import modelo.GeneradorDeRegiones;
import modelo.Grafo;
import modelo.Mapa;
import vista.InterfazRegiones;

public class IniciarClases {
	
	public IniciarClases() {
	
		Controlador controlador= new Controlador();
		Grafo grafo= new Grafo();
		Mapa mapa = new Mapa();
		GeneradorDeRegiones generador= new GeneradorDeRegiones();
		InterfazRegiones regiones = new InterfazRegiones();
		
		regiones.setControlador(controlador);
		controlador.setGrafo(grafo);
		controlador.setMapa(mapa);
		controlador.setGeneradorDeRegiones(generador);
		controlador.setInterfaz(regiones);
		controlador.iniciarInterfaz();
	}
}
