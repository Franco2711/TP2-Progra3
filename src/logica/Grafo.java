package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Grafo {
	
	private Map<Integer, Set<Arista>> vecindad;
	
	public Grafo() {
		vecindad = new HashMap<>();
	}
	
	public void agregarVertice(int vertice) {
		verificarExisteVertice(vertice);
		vecindad.putIfAbsent(vertice, new HashSet<>());
	}
	
	public void eliminarVertice(int vertice) {
		verificarVertice(vertice);
		Set<Integer> vecinos = obtenerVecinos(vertice);
		for(Integer vecino : vecinos) {
			eliminarArista(vertice, vecino);
		}
		vecindad.remove(vertice);
	}
	
	public void agregarArista(int origen, int destino, Arista aristaOrigen, Arista aristaDestino) {
		verificarVertice(origen);
		verificarVertice(destino);
		verificarVerticesDistintos(origen, destino);
		if(verificarExisteArista(origen, destino)){
			throw new IllegalArgumentException("La arista que intentas agregar ya existe");
		}
		vecindad.get(origen).add(aristaOrigen);
		vecindad.get(destino).add(aristaDestino);
	}
	
	public boolean existeArista(int origen, int destino) {
		verificarVertice(origen);
		verificarVertice(destino);
		verificarVerticesDistintos(origen, destino);
		return verificarExisteArista(origen, destino);
	}
	
	public void eliminarArista(int origen, int destino) {
		verificarVerticesDistintos(origen, destino);
		verificarNoExisteArista(origen, destino);
		Arista a = getArista(origen, destino);
		vecindad.get(origen).remove(a);
		a = getArista(destino, origen);
		vecindad.get(destino).remove(a);
	}
		
	public boolean existeVertice(int vertice) {
		return (vecindad.containsKey(vertice));
	}
	
	public Set<Integer> obtenerVertices (){
		return new HashSet<>(vecindad.keySet());
	}
	
	public Set<Integer> obtenerVecinos(int vertice){
		verificarVertice(vertice);
		Set<Integer> vecinos = new HashSet<>();
		Iterator<Arista> it = vecindad.get(vertice).iterator();
		while(it.hasNext()) {
			Arista arista = it.next();
			vecinos.add(arista.getDestino());
		}return vecinos;
	}
	
	public void reiniciarGrafo() {
		vecindad.clear();
	}
	
	private void verificarExisteVertice(int vertice) {
		if(vertice < 0) {
			throw new IllegalArgumentException("El vertice no puede ser menor a cero");
		}if(vecindad.containsKey(vertice)) {
			throw new IllegalArgumentException("El vertice ya existe");
		}
	}
	private void verificarVertice(int vertice) {
		if(vertice < 0) {
			throw new IllegalArgumentException("El vertice no puede ser menor a cero");
		}if(!vecindad.containsKey(vertice)) {
			throw new IllegalArgumentException("El vertice no existe");
		}
	}
	
	private void verificarVerticesDistintos(int origen, int destino) {
		if(origen == destino) {
			throw new IllegalArgumentException("El origen y el destino no deben ser iguales");
		}
	}
	
	private boolean verificarExisteArista(int origen, int destino) {
		Arista arista;
		Iterator<Arista> it = vecindad.get(origen).iterator();
		while(it.hasNext()) {
			arista = it.next();
			if(arista.getDestino() == destino) {
				return true;
			}
		}return false;
	}
	
	private void verificarNoExisteArista(int origen, int destino) {
		Iterator<Arista> it = vecindad.get(origen).iterator();
		while(it.hasNext()) {
			Arista arista = it.next();
			if(arista.getDestino() == destino) {
				return;
			}
		}
		throw new IllegalArgumentException("La arista que intentas eliminar no existe");
	}
	
	private Arista getArista(int origen, int destino) {
		Iterator<Arista> it = vecindad.get(origen).iterator();
		while(it.hasNext()) {
			Arista a = it.next();
			if(a.getDestino() == destino) {
				return a;
			}
		}
		return null;
	}
	
	public Set<Arista> getTodasLasAristas(int vertice){
		verificarVertice(vertice);
		return new HashSet<>(vecindad.get(vertice));
	}
	
	
}
