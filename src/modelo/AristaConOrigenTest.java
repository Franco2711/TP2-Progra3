package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AristaConOrigenTest {

    @Test
    public void compareToDeberiaRetornarNegativoCuandoElPesoEsMenor() {
    	AristaConOrigen a1 = new AristaConOrigen(0, 1, 5);
    	AristaConOrigen a2 = new AristaConOrigen(0, 2, 10);
        assertTrue(a1.compareTo(a2) < 0);
    }

    @Test
    public void compareToDeberiaRetornarPositivoCuandoElPesoEsMayor() {
    	AristaConOrigen a1 = new AristaConOrigen(0, 1, 20);
    	AristaConOrigen a2 = new AristaConOrigen(0, 2, 10);
        assertTrue(a1.compareTo(a2) > 0);
    }

    @Test
    public void compareToDeberiaRetornarCeroCuandoLosPesosSonIguales() {
    	AristaConOrigen a1 = new AristaConOrigen(0, 1, 10);
    	AristaConOrigen a2 = new AristaConOrigen(2, 3, 10);
        assertEquals(0, a1.compareTo(a2));
    }
}
