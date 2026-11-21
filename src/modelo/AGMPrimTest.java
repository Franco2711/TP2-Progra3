package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class AGMPrimTest {

	private int calcularCostoTotal(Grafo grafo) {
	    int total = 0;
	    for (Integer v : grafo.obtenerVertices()) {
	        for (Arista a : grafo.getTodasLasAristas(v)) {
	            total += a.getPeso();
	        }
	    }
	    return total / 2;
	}
	
	@Test
	public void primSobreUnVerticeDebeRetornarUnArbolVacio() {
	    Grafo g = new Grafo();
	    g.agregarVertice(0);
	    AGMPrim prim = new AGMPrim(g);
	    Grafo agm = prim.crearAGM(0);
	    assertEquals(1, agm.obtenerVertices().size());
	}

	@Test
	public void primDebeConservarLaUnicaAristaDisponible() {
	    Grafo g = new Grafo();
	    g.agregarVertice(0);
	    g.agregarVertice(1);
	    g.agregarArista(0, 1, new Arista(1, 5), new Arista(0, 5));
	    AGMPrim prim = new AGMPrim(g);
	    Grafo agm = prim.crearAGM(0);
	    assertTrue(agm.existeArista(0, 1));
	    assertEquals(5, calcularCostoTotal(agm));
	}
	
	@Test
	public void primDebeElegirLasDosAristasMasBaratasDelTriangulo() {
	    Grafo g = new Grafo();
	    for(int i = 0; i < 3; i++) {
	        g.agregarVertice(i);
	    }
	    g.agregarArista(0, 1, new Arista(1,4), new Arista(0,4));
	    g.agregarArista(0, 2, new Arista(2,2), new Arista(0,2));
	    g.agregarArista(1, 2, new Arista(2,3), new Arista(1,3));
	    AGMPrim prim = new AGMPrim(g);
	    Grafo agm = prim.crearAGM(0);
	    assertEquals(5, calcularCostoTotal(agm));
	}
	
	@Test
	public void elAGMDebeTenerCantidadDeAristasIgualAVMenosUno() {
	    Grafo g = new Grafo();
	    for(int i = 0; i < 4; i++) {
	        g.agregarVertice(i);
	    }
	    g.agregarArista(0, 1, new Arista(1,1), new Arista(0,1));
	    g.agregarArista(1, 2, new Arista(2,1), new Arista(1,1));
	    g.agregarArista(2, 3, new Arista(3,1), new Arista(2,1));
	    AGMPrim prim = new AGMPrim(g);
	    Grafo agm = prim.crearAGM(0);
	    int cantidadAristas = 0;
	    for (Integer v : agm.obtenerVertices()) {
	        cantidadAristas += agm.getTodasLasAristas(v).size();
	    }
	    cantidadAristas /= 2;
	    assertEquals(agm.obtenerVertices().size() - 1, cantidadAristas);
	}
	
	@Test
	public void deberiaLanzarExcepcionSiElGrafoNoEsConexo() {
	    Grafo g = new Grafo();
	    g.agregarVertice(0);
	    g.agregarVertice(1);
	    g.agregarVertice(2);
	    g.agregarArista(0, 1, new Arista(1, 10), new Arista(0, 10));
	    AGMPrim prim = new AGMPrim(g);
	    assertThrows(IllegalStateException.class, () -> prim.crearAGM(0));
	}
	
	@Test
	public void debeElegirLaAristaMasBarataDisponible() {
	    Grafo g = new Grafo();
	    g.agregarVertice(0);
	    g.agregarVertice(1);
	    g.agregarVertice(2);
	    g.agregarArista(0, 1, new Arista(1,100), new Arista(0,100));
	    g.agregarArista(0, 2, new Arista(2,1), new Arista(0,1));
	    g.agregarArista(1, 2, new Arista(2,50), new Arista(1,50));
	    AGMPrim prim = new AGMPrim(g);
	    Grafo agm = prim.crearAGM(0);
	    assertEquals(51, calcularCostoTotal(agm));
	}
	
}
