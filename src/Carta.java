
public class Carta {

    public String numero;
    public String naipe;

    public Carta(String numero, String naipe) {
        this.numero = numero;
        this.naipe = naipe;
    }

    public String getNumero() {
        return numero;
    }

    public String getNaipe() {
        return naipe;
    }

    @Override
    public String toString() {
        return numero + " de " + naipe;
    }
}
