package modelo;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AGMPrim {

	private Grafo grafo;
	private int costoTotal;
	
	public AGMPrim(Grafo grafo) {
        this.grafo = grafo;
    }
	
	public Grafo crearAGM(int origen) {
		costoTotal = 0;
        if (!grafo.existeVertice(origen)) {
            throw new IllegalArgumentException("El vértice origen no existe");
        }
        Grafo agm = new Grafo();
        for(Integer v : grafo.obtenerVertices()) {
        	agm.agregarVertice(v);
        }
        Set<Integer> visitados = new HashSet<>();
        PriorityQueue<AristaConOrigen> cola = new PriorityQueue<>();
        visitados.add(origen);
        agregarAristas(origen, cola, visitados);
        while(!cola.isEmpty()) {
        	AristaConOrigen actual = cola.poll();
            if(visitados.contains(actual.getDestino())) {
                continue;
            }
            costoTotal += actual.getPeso();
            agm.agregarArista(actual.getOrigen(), actual.getDestino(), new Arista(actual.getDestino(), actual.getPeso()),
            		new Arista( actual.getOrigen(), actual.getPeso()));
            visitados.add(actual.getDestino());
            agregarAristas(actual.getDestino(), cola, visitados);
        }

        if(visitados.size() != grafo.obtenerVertices().size()) {
            throw new IllegalStateException("El grafo no es conexo");
        }
        return agm;
    }

    private void agregarAristas(int vertice, PriorityQueue<AristaConOrigen> cola, Set<Integer> visitados) {
        for(Arista a : grafo.getTodasLasAristas(vertice)) {
            if(!visitados.contains(a.getDestino())) {
                cola.add(new AristaConOrigen(vertice, a.getDestino(), a.getPeso()));
            }
        }
    }
    
    public int getCostoTotal() {
    	return costoTotal;
    }
    
}
