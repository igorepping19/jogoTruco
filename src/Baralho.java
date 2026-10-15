import java.util.Random;

public class Baralho {
    
    public int proximaCarta = 0;

    public String[] naipes = {
        "Paus", "Copas", "Espadas", "Ouros"
    };

    public String[] numerosCartas = {
        "A", "2", "3", "4", "5", "6",
        "7", "10", "Q", "J", "K"
    };

    public Carta[] cartas = new Carta[44];

    public void criarBaralho() {
        int indice = 0;

        for (String naipe : naipes) {
            for (String numero : numerosCartas) {
                cartas[indice] = new Carta(numero, naipe);
                indice++;
            }
        }
    }

    public void embaralhar() {
        Random random = new Random();

        for (int i = cartas.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            Carta temporaria = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temporaria;
        }

    }

    public void distribuirCartas(Jogador jogador1, Jogador jogador2) {
        for (int i = 0; i < 3; i++) {
            jogador1.receberCarta(cartas[proximaCarta++]);
            jogador2.receberCarta(cartas[proximaCarta++]);
        }
    }

    public Carta revelarVira() {
        return cartas[proximaCarta++];
    }
}
