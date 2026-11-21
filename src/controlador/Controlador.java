package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import modelo.Arista;
import modelo.GeneradorDeRegiones;
import modelo.Grafo;
import modelo.Mapa;
import vista.InterfazRegiones;

public class Controlador {
	private Grafo grafo;
	private Mapa mapa;
	private GeneradorDeRegiones generadorDeRegiones;
	private InterfazRegiones interfaz;
	
	public void setInterfaz (InterfazRegiones interfaz) {
		this.interfaz = interfaz;
	}
	public void iniciarInterfaz() {
		interfaz.mostrar();
		
	}
	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	public void setGeneradorDeRegiones(GeneradorDeRegiones generador) {
		this.generadorDeRegiones = generador;
	}
	public void agregarProvincia(String nombre, double latitud, double longitud) {
		if(mapa.existeProvincia(nombre)) {
			interfaz.MensajeError("La provincia ya existe.");
			return;
		}try {
			mapa.agregar(nombre, latitud, longitud);
			grafo.agregarVertice(mapa.obtenerIndice(nombre));
			interfaz.actualizarListaProvincias(mapa.obtenerIndice(nombre), nombre);
			interfaz.actualizarComboBox(nombre);
			interfaz.MensajeExito("¡Porvincia agregada exitosamente!");
		} catch (IllegalArgumentException e) {
			interfaz.MensajeError(e.getMessage()+ " Error al agregar la provincia");
		}
	}
	public void agregarSimilaridad(String provincia1, String provincia2, int similaridad) {
		if (provincia1.equals(provincia2)) {
			interfaz.MensajeError("Las provincias seleccionadas no pueden ser iguales.");
			return;
		}
		try {
			int verticeOrigen= mapa.obtenerIndice(provincia1);
			int verticeDestino= mapa.obtenerIndice(provincia2);
			if (grafo.existeArista(verticeOrigen, verticeDestino)){
				interfaz.MensajeError("La similaridad entre estas 2 provincias ya existe.");
				return;
			}
			Arista origen = new Arista(verticeDestino, similaridad);
			Arista destino = new Arista(verticeOrigen, similaridad);
			grafo.agregarArista(verticeOrigen, verticeDestino, origen, destino);
			interfaz.actualizarListaSimilaridad(provincia1, provincia2, similaridad);
		}
		catch(IllegalArgumentException e){
			interfaz.MensajeError(e.getMessage()+ " Error al agregar la similaridad");
		}
	}
	public void generarRegiones(int k) {
		try {
			List<Set<Integer>> regiones = generadorDeRegiones.formarRegiones(this.grafo, k);
			List<List<String>> StringRegiones = convertirIndicesANombres(regiones);
			interfaz.actualizarRegiones(StringRegiones);
		}
		catch (IllegalArgumentException e) {
			interfaz.MensajeError(e.getMessage()+ ". No fue posible generar la division en regiones.");
		}
	}
	public List<List<String>> convertirIndicesANombres(List<Set<Integer>> indicesDeRegiones){
		List<List<String>> listaRegiones = new ArrayList<>();
		if (indicesDeRegiones==null)
			return listaRegiones;
		for (Set<Integer> region : indicesDeRegiones) {
			List<String> nombresEnRegion= new ArrayList<>();
			for (Integer indice : region) {
			String nombreProvincia = mapa.obtenerNombre(indice);
			nombresEnRegion.add(nombreProvincia);
			}
		listaRegiones.add(nombresEnRegion);		
		}
		return listaRegiones;
	}
}
