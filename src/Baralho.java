import java.util.Random;

public class Baralho {

    // Indica a posição da próxima carta que será utilizada no baralho.
    public int proximaCarta = 0;

    // Lista com os quatro naipes utilizados nas cartas.
    public String[] naipes = {
            "Paus", "Copas", "Espadas", "Ouros"
    };

    // Lista com os números e valores das cartas utilizadas no jogo.
    public String[] numerosCartas = {
            "A", "2", "3", "4", "5", "6",
            "7", "10", "Q", "J", "K"
    };

    // Array que armazena as 44 cartas do baralho.
    public Carta[] cartas = new Carta[44];

    // Cria todas as cartas combinando cada naipe com cada número.
    public void criarBaralho() {

        // Controla a posição em que cada nova carta será armazenada.
        int indice = 0;

        // Reinicia a posição da próxima carta para o início do baralho.
        proximaCarta = 0;

        // Percorre todos os naipes disponíveis.
        for (String naipe : naipes) {

            // Para cada naipe, percorre todos os números e valores disponíveis.
            for (String numero : numerosCartas) {

                // Cria uma carta com o número e o naipe atuais.
                cartas[indice] = new Carta(numero, naipe);

                // Avança para a próxima posição livre do array.
                indice++;
            }
        }
    }

    // Embaralha as cartas, alterando aleatoriamente suas posições no array.
    public void embaralhar() {

        // Cria um objeto responsável por gerar números aleatórios.
        Random random = new Random();

        // Percorre o array de trás para frente, até a segunda posição.
        for (int i = cartas.length - 1; i > 0; i--) {

            // Escolhe aleatoriamente uma posição entre 0 e i, inclusive.
            int j = random.nextInt(i + 1);

            // Guarda temporariamente a carta da posição atual.
            Carta temporaria = cartas[i];

            // Coloca na posição atual a carta da posição aleatória.
            cartas[i] = cartas[j];

            // Coloca a carta guardada na posição aleatória.
            cartas[j] = temporaria;
        }

    }

    // Distribui três cartas para cada um dos quatro jogadores.
    public void distribuirCartas(Jogador jogador1, Jogador jogador2, Jogador pc1, Jogador pc2) {

        // Repete o processo três vezes para distribuir três cartas a cada jogador.
        for (int i = 0; i < 3; i++) {

            // Entrega uma carta ao primeiro jogador e avança a posição do baralho.
            jogador1.receber(cartas[proximaCarta++]);

            // Entrega uma carta ao segundo jogador e avança a posição do baralho.
            jogador2.receber(cartas[proximaCarta++]);

            // Entrega uma carta ao primeiro jogador controlado pelo computador.
            pc1.receber(cartas[proximaCarta++]);

            // Entrega uma carta ao segundo jogador controlado pelo computador.
            pc2.receber(cartas[proximaCarta++]);
        }
    }

    // Revela a próxima carta do baralho e avança para a posição seguinte.
    // Essa carta pode ser utilizada como a vira para determinar as manilhas.
    public Carta revelarVira() {
        return cartas[proximaCarta++];
    }

}