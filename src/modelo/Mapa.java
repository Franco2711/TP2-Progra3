package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa {

	    private final List<Provincia> provincias = new ArrayList<>();
	    private final Map<String, Integer> indicePorNombre = new HashMap<>();
	    
	    public boolean existeProvincia(String nombre) {
	    	if (nombre == null || nombre.trim().isEmpty())
	    		return false;
	    	return indicePorNombre.containsKey(normalizar(nombre));
	    }
	    public int agregar(String nombre, double latitud, double longitud) {
	        if (nombre == null || nombre.trim().isEmpty()) {
	            throw new IllegalArgumentException("El nombre de la provincia no puede estar vacío.");
	        }
	        String clave = normalizar(nombre);
	        if (indicePorNombre.containsKey(clave)) {
	            throw new IllegalArgumentException("La provincia ya está registrada: " + nombre.trim());
	        }

	        int nuevoIndice = provincias.size();
	        provincias.add(new Provincia(nombre, latitud, longitud));
	        indicePorNombre.put(clave, nuevoIndice);
	        return nuevoIndice;
	    }

	    public int obtenerIndice(String nombre) {
	        if (nombre == null) {
	            throw new IllegalArgumentException("El nombre no puede ser nulo.");
	        }
	        Integer indice = indicePorNombre.get(normalizar(nombre));
	        if (indice == null) {
	            throw new IllegalArgumentException("Provincia no encontrada: " + nombre);
	        }
	        return indice;
	    }
	    
	    public Provincia obtenerProvincia(int indice) {
	        return provincias.get(indice);
	    }

	    public List<Provincia> obtenerTodas() {
	        return Collections.unmodifiableList(provincias);
	    }

	    private String normalizar(String nombre) {
	        return nombre.trim().toLowerCase();
	    }
	    public String obtenerNombre(Integer indice) {
	    	return provincias.get(indice).getNombre();
	    }

}
