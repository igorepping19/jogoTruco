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
        int quemComeca = 1;
        int[] resultados = new int[3];
        int valorMao = 1;

        for (int i = 1; i <= 3; i++) {
            System.out.println("\n--- Rodada " + i + " ---");

            Carta cartaJogador;
            Carta cartaComputador;
            int resultado;
            int escolha;
            int acao = 0;

            while (acao != 2) {
                if (valorMao == 1) {
                    System.out.println("\n1 - Pedir truco");
                } else if (valorMao == 3) {
                    System.out.println("\n1 - Pedir seis");
                } else if (valorMao == 6) {
                    System.out.println("\n1 - Pedir nove");
                } else if (valorMao == 9) {
                    System.out.println("\n1 - Pedir doze");
                }

                System.out.println("2 - Jogar carta");
                System.out.print("Escolha uma opção: ");
                acao = scanner.nextInt();

                if (acao == 1 && valorMao < 12) {
                    int valorAnterior = valorMao;

                    if (valorMao == 1) {
                        System.out.println("Você pediu truco!");
                        valorMao = 3;
                    } else if (valorMao == 3) {
                        System.out.println("Você pediu seis!");
                        valorMao = 6;
                    } else if (valorMao == 6) {
                        System.out.println("Você pediu nove!");
                        valorMao = 9;
                    } else if (valorMao == 9) {
                        System.out.println("Você pediu doze!");
                        valorMao = 12;
                    }

                    if (Math.random() < 0.7) {
                        System.out.println("O computador aceitou!");
                    } else {
                        System.out.println("O computador correu!");
                        placar.adicionarPontos(1, valorAnterior);
                        return;
                    }
                } else if (acao != 2) {
                    System.out.println(
                        "Opção inválida. Escolha uma opção do menu."
                    );
                }
            }

            if (quemComeca == 2) {
                cartaComputador = jogador2.jogar(0);
                System.out.println("\nComputador jogou: " + cartaComputador);
            } else {
                cartaComputador = null;
            }

            System.out.println("\nSuas cartas:");

            for (int j = 0; j < jogador1.quantidadeCartas; j++) {
                System.out.println((j + 1) + " - " + jogador1.cartas[j]);
            }

            System.out.print("Escolha uma carta: ");
            escolha = scanner.nextInt();

            while (escolha < 1 || escolha > jogador1.quantidadeCartas) {
                System.out.print("Escolha inválida. Digite novamente: ");
                escolha = scanner.nextInt();
            }

            cartaJogador = jogador1.jogar(escolha - 1);

            if (quemComeca == 1) {
                cartaComputador = jogador2.jogar(0);
                System.out.println("\nVocê jogou: " + cartaJogador);
                System.out.println("Computador jogou: " + cartaComputador);
            } else {
                System.out.println("\nVocê jogou: " + cartaJogador);
            }

            System.out.println();

            resultado = rodada.compararCartas(cartaJogador, cartaComputador);
            resultados[i - 1] = resultado;
            if (resultado == 1) {
                vitoriasJogador++;
                quemComeca = 1;
                System.out.println("Você venceu a rodada!");
            } else if (resultado == 2) {
                vitoriasComputador++;
                quemComeca = 2;
                System.out.println("O computador venceu a rodada!");
            } else {
                System.out.println("A rodada empatou!");
            }
            if (vitoriasJogador == 2 || vitoriasComputador == 2
                    || (i == 2 && resultados[0] == 0 && resultados[1] != 0)
                    || (i == 2 && resultados[0] != 0 && resultados[1] == 0)) {
                break;
            }
        }

        System.out.println("\n--- Resultado da mão ---");

        int vencedor = 0;

        if (vitoriasJogador >= 2) {
            vencedor = 1;
        } else if (vitoriasComputador >= 2) {
            vencedor = 2;
        } else if (resultados[0] == 0) {
            vencedor = resultados[1] != 0
                    ? resultados[1]
                    : resultados[2];
        } else if (resultados[1] == 0 || resultados[2] == 0) {
            vencedor = resultados[0];
        } else {
            vencedor = resultados[2];
        }

        if (vencedor == 1) {
            System.out.println("Você venceu a mão!");
            placar.adicionarPontos(1, valorMao);
        } else if (vencedor == 2) {
            System.out.println("O computador venceu a mão!");
            placar.adicionarPontos(2, valorMao);
        } else {
            System.out.println("A mão terminou empatada!");
        }
    }

}
