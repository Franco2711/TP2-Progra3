package modelo;

 public class AristaConOrigen implements Comparable<AristaConOrigen> {

	private int origen;
    private int destino;
    private int peso;

    public AristaConOrigen(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
    
    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public int compareTo(AristaConOrigen otra) {
        return Integer.compare(this.peso, otra.peso);
    }

}
