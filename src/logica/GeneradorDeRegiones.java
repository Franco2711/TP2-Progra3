package logica;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GeneradorDeRegiones {

    public List<Set<Integer>> formarRegiones(Grafo mapa, int k) {
    	Set<Integer> vertices = mapa.obtenerVertices();
        int cantidadVertices = vertices.size();
        if(cantidadVertices == 0) {
        	throw new IllegalArgumentException("El grafo no contiene provincias");
        }
        if (k <= 0 || k > cantidadVertices) {
            throw new IllegalArgumentException("Debe ingresar un valor entre 1 y la cantidad de provincias");
        }
        // Construyo el AGM
        Grafo agm = new AGMPrim(mapa).crearAGM(vertices.iterator().next());
        // Obtengo todas las aristas del AGM
        List<AristaConOrigen> aristas = obtenerAristasUnicas(agm);
        // Ordeno por peso creciente
        aristas.sort(Comparator.comparingInt(AristaConOrigen::getPeso));
        // Elimino las k-1 aristas más pesadas
        int cantidadAEliminar = k-1;
        for (int i = 0; i < cantidadAEliminar; i++) {
        	AristaConOrigen a = aristas.get(aristas.size() - 1 - i);
            agm.eliminarArista(a.getOrigen(), a.getDestino());
        }
        // Obtengo las componentes conexas
        return obtenerComponentesConexas(agm);
    }

    private List<AristaConOrigen> obtenerAristasUnicas(Grafo grafo) {
        List<AristaConOrigen> resultado = new ArrayList<>();
        for (Integer origen : grafo.obtenerVertices()) {
            for (Arista a : grafo.getTodasLasAristas(origen)) {
                int destino = a.getDestino();
                // Evita duplicados en grafo no dirigido
                if (origen < destino) {
                    resultado.add(new AristaConOrigen(origen, destino, a.getPeso()));
                }
            }
        }

        return resultado;
    }

    private List<Set<Integer>> obtenerComponentesConexas(Grafo grafo) {
        List<Set<Integer>> regiones = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        for (Integer vertice : grafo.obtenerVertices()) {
            if (!visitados.contains(vertice)) {
                Set<Integer> componente = bfs(grafo, vertice);
                visitados.addAll(componente);
                regiones.add(componente);
            }
        }
        return regiones;
    }

    private Set<Integer> bfs(Grafo grafo, int origen) {
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new ArrayDeque<>();
        cola.add(origen);
        visitados.add(origen);
        while (!cola.isEmpty()) {
            int actual = cola.poll();
            for (Integer vecino : grafo.obtenerVecinos(actual)) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
        return visitados;
    }
}