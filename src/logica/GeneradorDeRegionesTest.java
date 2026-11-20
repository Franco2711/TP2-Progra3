package logica;

public class GeneradorDeRegionesTest {

	@Test
    void debeLanzarExcepcionSiElGrafoEstaVacio() {

        Grafo grafo = new Grafo();

        GeneradorDeRegiones generador = new GeneradorDeRegiones();

        assertThrows(
                IllegalArgumentException.class,
                () -> generador.formarRegiones(grafo, 1));
    }

    @Test
    void debeLanzarExcepcionSiKEsMenorOIgualACero() {

        Grafo grafo = new Grafo();
        grafo.agregarVertice(0);

        GeneradorDeRegiones generador = new GeneradorDeRegiones();

        assertThrows(
                IllegalArgumentException.class,
                () -> generador.formarRegiones(grafo, 0));
    }

    @Test
    void debeLanzarExcepcionSiKSuperaCantidadDeVertices() {

        Grafo grafo = new Grafo();
        grafo.agregarVertice(0);
        grafo.agregarVertice(1);

        GeneradorDeRegiones generador = new GeneradorDeRegiones();

        assertThrows(
                IllegalArgumentException.class,
                () -> generador.formarRegiones(grafo, 3));
    }

    @Test
    void conKUnoDebeGenerarUnaSolaRegion() {

        Grafo grafo = crearGrafoSimple();

        GeneradorDeRegiones generador = new GeneradorDeRegiones();

        List<Set<Integer>> regiones =
                generador.formarRegiones(grafo, 1);

        assertEquals(1, regiones.size());
        assertEquals(4, regiones.get(0).size());
    }

    @Test
    void conKCantidadDeVerticesCadaVerticeDebeSerUnaRegion() {

        Grafo grafo = crearGrafoSimple();

        GeneradorDeRegiones generador = new GeneradorDeRegiones();

        List<Set<Integer>> regiones =
                generador.formarRegiones(grafo, 4);

        assertEquals(4, regiones.size());

        for(Set<Integer> region : regiones) {
            assertEquals(1, region.size());
        }
    }

    @Test
    void debeGenerarDosRegionesEliminandoLaAristaMasPesada() {

        Grafo grafo = new Grafo();

        for(int i = 0; i < 4; i++) {
            grafo.agregarVertice(i);
        }

        grafo.agregarArista(0, 1,
                new Arista(1, 1),
                new Arista(0, 1));

        grafo.agregarArista(1, 2,
                new Arista(2, 2),
                new Arista(1, 2));

        grafo.agregarArista(2, 3,
                new Arista(3, 10),
                new Arista(2, 10));

        GeneradorDeRegiones generador =
                new GeneradorDeRegiones();

        List<Set<Integer>> regiones =
                generador.formarRegiones(grafo, 2);

        assertEquals(2, regiones.size());

        assertTrue(
                regiones.contains(Set.of(0, 1, 2))
                || regiones.contains(Set.of(3)));

        assertTrue(
                regiones.contains(Set.of(3))
                || regiones.contains(Set.of(0, 1, 2)));
    }

    private Grafo crearGrafoSimple() {

        Grafo grafo = new Grafo();

        for(int i = 0; i < 4; i++) {
            grafo.agregarVertice(i);
        }

        grafo.agregarArista(0, 1,
                new Arista(1, 1),
                new Arista(0, 1));

        grafo.agregarArista(1, 2,
                new Arista(2, 1),
                new Arista(1, 1));

        grafo.agregarArista(2, 3,
                new Arista(3, 1),
                new Arista(2, 1));

        return grafo;
    }
}
	
}
