package logica;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AGMPrim {

	private Grafo grafo;
	
	public AGMPrim(Grafo grafo) {
        this.grafo = grafo;
    }
	
	public Grafo crearAGM(int origen) {
        if (!grafo.existeVertice(origen)) {
            throw new IllegalArgumentException("El vértice origen no existe");
        }
        Grafo agm = new Grafo();
        for(Integer v : grafo.obtenerVertices()) {
        	agm.agregarVertice(v);
        }
        Set<Integer> visitados = new HashSet<>();
        PriorityQueue<AristaPrim> cola = new PriorityQueue<>();
        visitados.add(origen);
        agregarAristas(origen, cola, visitados);
        while(!cola.isEmpty()) {
            AristaPrim actual = cola.poll();
            if(visitados.contains(actual.getDestino())) {
                continue;
            }
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

    private void agregarAristas(int vertice, PriorityQueue<AristaPrim> cola, Set<Integer> visitados) {
        for(Arista a : grafo.getTodasLasAristas(vertice)) {
            if(!visitados.contains(a.getDestino())) {
                cola.add(new AristaPrim(vertice, a.getDestino(), a.getPeso()));
            }
        }
    }
    
}
