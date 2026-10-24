public class Jogador {

    // Nome que identifica o jogador.
    public String nome;

    // Array que armazena as cartas do jogador. Cada jogador pode ter até 3 cartas.
    public Carta[] cartas = new Carta[3];

    // Quantidade de cartas que o jogador possui atualmente.
    public int quantidadeCartas = 0;

    // Posição do jogador na ordem de jogadores da partida.
    public int posicao;

    // Construtor: define o nome e a posição do jogador ao criar o objeto.
    public Jogador(String nome, int posicao) {
        this.nome = nome;
        this.posicao = posicao;
    }

    // Adiciona uma carta à mão do jogador, caso ainda exista espaço disponível.
    public void receber(Carta carta) {
        if (quantidadeCartas < cartas.length) {
            // Armazena a carta na próxima posição livre do array.
            cartas[quantidadeCartas] = carta;

            // Atualiza a quantidade de cartas que o jogador possui.
            quantidadeCartas++;
        }
    }

    // Remove e devolve a carta escolhida pelo jogador.
    public Carta jogar(int posicaoCarta) {

        // Verifica se a posição escolhida corresponde a uma carta existente.
        if (posicaoCarta < 0 || posicaoCarta >= quantidadeCartas) {
            System.out.println("Posicao de carta invalida");
            return null;
        }

        // Guarda a carta escolhida antes de removê-la do array.
        Carta cartaJogada = cartas[posicaoCarta];

        // Desloca as cartas seguintes uma posição para a esquerda,
        // preenchendo o espaço deixado pela carta jogada.
        for (int i = posicaoCarta; i < quantidadeCartas - 1; i++) {
            cartas[i] = cartas[i + 1];
        }

        // Limpa a última posição ocupada, evitando manter uma referência repetida.
        cartas[quantidadeCartas - 1] = null;

        // Diminui a quantidade de cartas na mão do jogador.
        quantidadeCartas--;

        // Devolve a carta que foi jogada.
        return cartaJogada;
    }

    // Retorna o próximo jogador com base na posição atual e no array de jogadores.
    public Jogador proximo(Jogador[] jogadores) {

        // Calcula a próxima posição usando o resto da divisão pelo número de jogadores.
        // Isso permite voltar ao início do array quando chegar ao final.
        int proximaPosicao = this.posicao % jogadores.length;

        // Retorna o jogador que está na posição calculada.
        return jogadores[proximaPosicao];
    }

    // Remove todas as cartas da mão do jogador.
    public void limparMao() {

        // Percorre todas as posições do array e limpa cada uma delas.
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = null;
        }

        // Reinicia a quantidade de cartas para zero.
        quantidadeCartas = 0;
    }
}