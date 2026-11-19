package logica;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AristaPrimTest {

    @Test
    public void compareToDeberiaRetornarNegativoCuandoElPesoEsMenor() {
        AristaPrim a1 = new AristaPrim(0, 1, 5);
        AristaPrim a2 = new AristaPrim(0, 2, 10);
        assertTrue(a1.compareTo(a2) < 0);
    }

    @Test
    public void compareToDeberiaRetornarPositivoCuandoElPesoEsMayor() {
        AristaPrim a1 = new AristaPrim(0, 1, 20);
        AristaPrim a2 = new AristaPrim(0, 2, 10);
        assertTrue(a1.compareTo(a2) > 0);
    }

    @Test
    public void compareToDeberiaRetornarCeroCuandoLosPesosSonIguales() {
        AristaPrim a1 = new AristaPrim(0, 1, 10);
        AristaPrim a2 = new AristaPrim(2, 3, 10);
        assertEquals(0, a1.compareTo(a2));
    }
}
