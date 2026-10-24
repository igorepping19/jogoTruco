public class Carta {

    // Armazena o número ou valor da carta, como "4", "10", "J", "Q", "K" ou "A".
    public String numero;

    // Armazena o naipe da carta, como "Paus", "Copas", "Espadas" ou "Ouros".
    public String naipe;

    // Construtor: cria uma carta recebendo seu número e seu naipe.
    public Carta(String numero, String naipe) {
        this.numero = numero;
        this.naipe = naipe;
    }

    // Retorna o número ou valor da carta.
    public String getNumero() {
        return numero;
    }

    // Retorna o naipe da carta.
    public String getNaipe() {
        return naipe;
    }

    // Sobrescreve o método toString() para definir como a carta será representada em texto.
    @Override
    public String toString() {
        // Combina o número e o naipe para formar uma descrição da carta.
        // Exemplo: "7 de Copas".
        return numero + " de " + naipe;
    }
}