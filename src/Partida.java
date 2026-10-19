import java.util.Scanner;

public class Partida {
    public Baralho baralho;
    public Jogador jogador1;
    public Jogador jogador2;
    public Placar placar;

    public Partida() {
        baralho = new Baralho();
        jogador1 = new Jogador("Jogador 1", 1);
        jogador2 = new Jogador("Computador", 2);
        placar = new Placar();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (placar.pontosDupla1 < 12 && placar.pontosDupla2 < 12) {
            jogarMao(scanner);
        }

        System.out.println("\n--- Fim da partida ---");
        placar.mostrarPlacar();

        if (placar.pontosDupla1 >= 12) {
            System.out.println("Você venceu a partida!");
        } else {
            System.out.println("O computador venceu a partida!");
        }

        scanner.close();
    }

    public void jogarMao(Scanner scanner) {
        baralho.criarBaralho();
        baralho.embaralhar();

        jogador1.limparMao();
        jogador2.limparMao();

        baralho.distribuirCartas(jogador1, jogador2);

        Carta vira = baralho.revelarVira();
        System.out.println("\nVira: " + vira);

        Rodada rodada = new Rodada(vira);

        int vitoriasJogador = 0;
        int vitoriasComputador = 0;

        for (int i = 1; i <= 3; i++) {
            System.out.println("\n--- Rodada " + i + " ---");
            System.out.println("Suas cartas:");

            for (int j = 0; j < jogador1.quantidadeCartas; j++) {
                System.out.println((j + 1) + " - " + jogador1.cartas[j]);
            }

            System.out.print("Escolha uma carta: ");
            int escolha = scanner.nextInt();

            while (escolha < 1 || escolha > jogador1.quantidadeCartas) {
                System.out.print("Escolha inválida. Digite novamente: ");
                escolha = scanner.nextInt();
            }

            Carta cartaJogador = jogador1.jogar(escolha - 1);
            Carta cartaComputador = jogador2.jogar(0);

            System.out.println("Você jogou: " + cartaJogador);
            System.out.println("Computador jogou: " + cartaComputador);

            int resultado = rodada.compararCartas(cartaJogador, cartaComputador);

            if (resultado == 1) {
                vitoriasJogador++;
                System.out.println("Você venceu a rodada!");
            } else if (resultado == 2) {
                vitoriasComputador++;
                System.out.println("O computador venceu a rodada!");
            } else {
                System.out.println("A rodada empatou!");
            }

            if (vitoriasJogador == 2 || vitoriasComputador == 2) {
                break;
            }
        }

        System.out.println("\n--- Resultado da mão ---");

        if (vitoriasJogador > vitoriasComputador) {
            System.out.println("Você venceu a mão!");
            placar.marcarMao(1);
        } else if (vitoriasComputador > vitoriasJogador) {
            System.out.println("O computador venceu a mão!");
            placar.marcarMao(2);
        } else {
            System.out.println("A mão terminou empatada!");
        }
    }

}
