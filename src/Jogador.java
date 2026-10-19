
public class Jogador {
    public String nome;
    public Carta[] cartas = new Carta[3];
    public int quantidadeCartas = 0;
    public int posicao;

    public Jogador(String nome, int posicao) {
        this.nome = nome;
        this.posicao = posicao;
    }

    public void receber(Carta carta) {
        if (quantidadeCartas < cartas.length) {
            cartas[quantidadeCartas] = carta;
            quantidadeCartas++;
        }
    }

    public Carta jogar(int posicaoCarta) {
        if (posicaoCarta < 0 || posicaoCarta >= quantidadeCartas) {
            System.out.println("Posicao de carta invalida");
            return null;
        }
        Carta Jogada = cartas[posicaoCarta];

        for (int i = posicaoCarta; i < quantidadeCartas - 1; i++) {
            cartas[i] = cartas[i + 1];
        }
        cartas[quantidadeCartas - 1] = null;
        quantidadeCartas--;
        return Jogada;
    }

    public Jogador proximo(Jogador[] jogadores) {
        int Posicao = this.posicao - 1;

        if (Posicao < 0) {
            Posicao = jogadores.length - 1;
        }
        return jogadores[Posicao];
    }

    public void jogarNaRodada() {
        // Incompleto
    }

    public void limparMao() {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = null;
        }
        quantidadeCartas = 0;
    }
}
