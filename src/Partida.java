import java.util.Scanner; // Importa a classe utilizada para ler as entradas do usuário pelo teclado.

public class Partida { // Declara a classe responsável por controlar a partida de Truco.

    // Declara os objetos que representam o baralho, os quatro jogadores e o placar.
    public Baralho baralho;
    public Jogador jogador1;
    public Jogador jogador2;
    public Jogador pc1;
    public Jogador pc2;
    public Placar placar;

    // Construtor: inicializa os objetos necessários para uma partida.
    public Partida() {
        baralho = new Baralho(); // Cria o baralho que será utilizado nas mãos.

        // Cria os quatro jogadores e define um identificador para cada um.
        jogador1 = new Jogador("Jogador 1", 1);
        pc1 = new Jogador("PC 1", 2);
        jogador2 = new Jogador("Jogador 2", 3);
        pc2 = new Jogador("PC 2", 4);

        placar = new Placar(); // Cria o placar para controlar os pontos das duplas.
    }

    // Inicia a partida e mantém as mãos acontecendo até uma dupla atingir 12 pontos.
    public void iniciar() {
        Scanner scanner = new Scanner(System.in); // Prepara a leitura das opções digitadas.

        // Continua jogando enquanto nenhuma dupla tiver alcançado 12 pontos.
        while (placar.pontosDupla1 < 12 && placar.pontosDupla2 < 12) {
            jogarMao(scanner); // Executa uma mão completa.
        }

        // Exibe o resultado final da partida.
        System.out.println("\n--- Fim da partida ---");
        placar.mostrarPlacar();

        // Verifica qual dupla atingiu a pontuação necessária para vencer.
        if (placar.pontosDupla1 >= 12) {
            System.out.println("Dupla 1 venceu a partida!");
        } else {
            System.out.println("Dupla 2 venceu a partida!");
        }

        scanner.close(); // Fecha o Scanner após o término da partida.
    }

    // Executa uma mão, desde a distribuição das cartas até a atribuição dos pontos.
    public void jogarMao(Scanner scanner) {
        baralho.criarBaralho(); // Cria as cartas do baralho.
        baralho.embaralhar(); // Embaralha as cartas antes da distribuição.

        // Remove as cartas restantes das mãos dos quatro jogadores.
        jogador1.limparMao();
        jogador2.limparMao();
        pc1.limparMao();
        pc2.limparMao();

        // Distribui as cartas entre os quatro participantes.
        baralho.distribuirCartas(jogador1, jogador2, pc1, pc2);

        // Revela a carta vira e cria o objeto responsável pelas regras das rodadas.
        Carta vira = baralho.revelarVira();
        Rodada rodada = new Rodada(vira);

        // Mostra a carta vira e a manilha definida para esta mão.
        System.out.println("\n--- Nova mão ---");
        System.out.println("Vira: " + vira.getNumero() + " de " + vira.getNaipe());
        System.out.println("Manilha: " + rodada.manilha);

        // Organiza os jogadores em um vetor, na ordem em que estão posicionados na mesa.
        Jogador[] jogadores = { jogador1, pc1, jogador2, pc2 };

        // Armazena os nomes correspondentes aos índices do vetor de jogadores.
        String[] nomes = {
                "Jogador 1", "PC 1", "Jogador 2", "PC 2"
        };

        int quemComeca = 0; // Define o índice do jogador que começa a primeira rodada.
        int valorMao = 1; // Define o valor inicial da mão, em pontos.

        // Armazena o resultado de cada uma das três rodadas.
        // Os valores utilizados são 1 para a Dupla 1, 2 para a Dupla 2 e 0 para empate.
        int[] resultados = new int[3];

        int vitoriasDupla1 = 0; // Conta quantas rodadas a Dupla 1 venceu.
        int vitoriasDupla2 = 0; // Conta quantas rodadas a Dupla 2 venceu.
        int rodadasJogadas = 0; // Conta quantas rodadas foram efetivamente concluídas.

        // Uma mão pode ter até três rodadas.
        for (int i = 0; i < 3; i++) {

            System.out.println("\n--- Rodada " + (i + 1) + " ---");

            // Permite aumentar o valor da mão antes de jogar.
            while (true) {
                System.out.println("\nValor atual da mão: " + valorMao);

                // Só oferece aumento se o valor atual for menor que 12 pontos.
                if (valorMao < 12) {
                    System.out.println("1 - Pedir aumento da aposta");
                }

                System.out.println("2 - Jogar rodada");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt(); // Lê a opção escolhida pelo usuário.

                // Se a opção for jogar, encerra o menu de aumento.
                if (opcao == 2) {
                    break;
                }

                // Rejeita opções diferentes de 1 ou 2 e impede novos aumentos quando a mão vale 12.
                if (opcao != 1 || valorMao == 12) {
                    System.out.println("Opção inválida!");
                    continue; // Retorna ao início do menu.
                }

                int valorAnterior = valorMao; // Guarda o valor anterior para usar caso o aumento seja recusado.

                // Atualiza o valor da mão conforme a sequência de aumentos implementada.
                if (valorMao == 1) {
                    valorMao = 3;
                } else if (valorMao == 3) {
                    valorMao = 6;
                } else if (valorMao == 6) {
                    valorMao = 9;
                } else if (valorMao == 9) {
                    valorMao = 12;
                }

                System.out.println("\nVocê pediu aumento para " + valorMao + " pontos.");

                // O computador aceita a aposta com 70% de chance.
                if (Math.random() < 0.70) {
                    System.out.println("O computador aceitou!");

                } else {
                    // Se o aumento for recusado, a dupla do jogador recebe o valor anterior da mão.
                    System.out.println("O computador recusou a aposta!");
                    System.out.println("Sua dupla recebe " + valorAnterior + " ponto(s).");

                    placar.adicionarPontos(1, valorAnterior); // Atualiza o placar da Dupla 1.
                    return; // Encerra a mão sem continuar as rodadas.
                }
            }

            // Guarda cada carta na posição fixa do respectivo participante.
            Carta[] cartasJogadas = new Carta[4];

            // A ordem de jogo começa em quemComeca e segue pela mesa.
            for (int j = 0; j < 4; j++) {
                int indiceJogador = (quemComeca + j) % 4; // Calcula quem joga nesta vez, voltando ao índice 0 após o 3.
                Jogador jogadorAtual = jogadores[indiceJogador]; // Obtém o objeto do jogador da vez.

                System.out.println("\nVez de " + nomes[indiceJogador]);

                // Os índices 0 e 2 correspondem aos jogadores humanos.
                if (indiceJogador == 0 || indiceJogador == 2) {
                    // Jogador humano: mostra as cartas disponíveis.
                    System.out.println("Suas cartas:");

                    // Percorre as cartas que ainda estão na mão do jogador.
                    for (int k = 0; k < jogadorAtual.quantidadeCartas; k++) {
                        Carta carta = jogadorAtual.cartas[k]; // Obtém a carta da posição atual.

                        // Mostra o índice, o número e o naipe da carta.
                        System.out.println(
                                k + " - " + carta.getNumero()
                                        + " de " + carta.getNaipe());
                    }

                    int escolha; // Armazena o índice da carta escolhido pelo jogador.

                    // Continua solicitando uma escolha até que o índice seja válido.
                    while (true) {
                        System.out.print("Escolha o índice da carta: ");
                        escolha = scanner.nextInt(); // Lê o índice informado pelo usuário.

                        // Verifica se o índice está dentro das posições disponíveis.
                        if (escolha >= 0
                                && escolha < jogadorAtual.quantidadeCartas) {
                            break; // Sai do laço quando a escolha é válida.
                        }

                        System.out.println("Índice inválido! Tente novamente.");
                    }

                    // Faz o jogador jogar a carta escolhida e guarda a carta jogada na posição dele.
                    cartasJogadas[indiceJogador] = jogadorAtual.jogar(escolha);

                } else {
                    // Computador: joga a primeira carta disponível.
                    cartasJogadas[indiceJogador] = jogadorAtual.jogar(0);
                }

                // Recupera a carta que acabou de ser jogada.
                Carta cartaJogada = cartasJogadas[indiceJogador];

                // Informa o nome do participante e a carta que ele jogou.
                System.out.println(
                        nomes[indiceJogador] + " jogou "
                                + cartaJogada.getNumero() + " de "
                                + cartaJogada.getNaipe());
            }

            // Compara as quatro cartas, independentemente da ordem em que foram jogadas.
            int resultado = rodada.compararQuatroCartas(
                    cartasJogadas[0],
                    cartasJogadas[1],
                    cartasJogadas[2],
                    cartasJogadas[3]);

            resultados[i] = resultado; // Registra o resultado na posição correspondente à rodada.
            rodadasJogadas++; // Incrementa o total de rodadas concluídas.

            // Atualiza o contador da dupla vencedora e mostra o resultado.
            if (resultado == 1) {
                vitoriasDupla1++;
                System.out.println("A rodada foi vencida pela Dupla 1!");

            } else if (resultado == 2) {
                vitoriasDupla2++;
                System.out.println("A rodada foi vencida pela Dupla 2!");

            } else {
                System.out.println("A rodada terminou empatada!");
            }

            // Descobre a maior força entre as quatro cartas.
            int maiorForca = -1; // Começa com um valor inferior às forças esperadas das cartas.

            // Percorre todas as cartas jogadas para encontrar a maior força.
            for (Carta carta : cartasJogadas) {
                int forca = rodada.calcularForca(carta); // Calcula a força da carta atual.

                // Atualiza a maior força sempre que encontrar uma carta mais forte.
                if (forca > maiorForca) {
                    maiorForca = forca;
                }
            }

            // Indica se cada dupla possui pelo menos uma carta com a maior força.
            boolean dupla1TemMaior = false;
            boolean dupla2TemMaior = false;

            // Verifica quais duplas possuem cartas com a maior força encontrada.
            for (int j = 0; j < 4; j++) {
                if (rodada.calcularForca(cartasJogadas[j]) == maiorForca) {
                    // Os índices 0 e 2 pertencem à Dupla 1; os índices 1 e 3 pertencem à Dupla 2.
                    if (j == 0 || j == 2) {
                        dupla1TemMaior = true;
                    } else {
                        dupla2TemMaior = true;
                    }
                }
            }

            // Se somente uma dupla tiver a maior carta,
            // o jogador dessa dupla que jogou a maior carta começa.
            if (dupla1TemMaior && !dupla2TemMaior) {
                // Procura, na ordem em que as cartas foram jogadas, a maior carta da Dupla 1.
                for (int j = 0; j < 4; j++) {
                    int indiceJogador = (quemComeca + j) % 4; // Obtém o índice do jogador correspondente à ordem de jogo.

                    // Quando encontra a maior carta, define quem começará a próxima rodada.
                    if (rodada.calcularForca(cartasJogadas[indiceJogador]) == maiorForca) {
                        quemComeca = indiceJogador;
                        break; // Interrompe a busca após encontrar a primeira carta com essa força.
                    }
                }

            } else if (dupla2TemMaior && !dupla1TemMaior) {
                // Procura, na ordem em que as cartas foram jogadas, a maior carta da Dupla 2.
                for (int j = 0; j < 4; j++) {
                    int indiceJogador = (quemComeca + j) % 4; // Obtém o índice do jogador correspondente à ordem de jogo.

                    // Quando encontra a maior carta, define quem começará a próxima rodada.
                    if (rodada.calcularForca(cartasJogadas[indiceJogador]) == maiorForca) {
                        quemComeca = indiceJogador;
                        break; // Interrompe a busca após encontrar a primeira carta com essa força.
                    }
                }

            } else {
                // As maiores cartas empataram entre duplas diferentes.
                // Mantém o mesmo jogador que iniciou esta rodada.
                System.out.println(
                        "As maiores cartas empataram entre as duplas. "
                                + nomes[quemComeca] + " começa a próxima rodada.");
            }

            // Se uma dupla venceu duas rodadas, a mão já está decidida.
            if (vitoriasDupla1 == 2 || vitoriasDupla2 == 2) {
                break; // Encerra o laço das rodadas.
            }

            // Mantém as regras anteriores para empates entre rodadas.
            if (i == 1) {
                // Se a primeira rodada empatou e a segunda teve vencedor, a mão é decidida pela segunda.
                if (resultados[0] == 0 && resultados[1] != 0) {
                    // Primeira rodada empatada: a segunda decide.
                    break;
                }

                // Se a primeira rodada teve vencedor e a segunda empatou, a mão é decidida pela primeira.
                if (resultados[0] != 0 && resultados[1] == 0) {
                    // Primeira rodada teve vencedor e a segunda empatou.
                    break;
                }
            }
        }

        // Decide quem venceu a mão usando os resultados das rodadas.
        int vencedor = 0; // O valor 0 indica que ainda não foi definido um vencedor.

        // Duas vitórias da Dupla 1 garantem a vitória da mão.
        if (vitoriasDupla1 >= 2) {
            vencedor = 1;

        // Duas vitórias da Dupla 2 garantem a vitória da mão.
        } else if (vitoriasDupla2 >= 2) {
            vencedor = 2;

        // Trata o caso em que a primeira rodada terminou empatada.
        } else if (resultados[0] == 0) {
            // A primeira rodada empatou.
            if (resultados[1] != 0) {
                vencedor = resultados[1]; // A segunda rodada teve um vencedor.
            } else if (rodadasJogadas >= 3) {
                vencedor = resultados[2]; // Se as duas primeiras empataram, considera a terceira.
            }

        // Se a segunda rodada empatou, vale o resultado da primeira.
        } else if (resultados[1] == 0) {
            // A segunda rodada empatou: vale o vencedor da primeira.
            vencedor = resultados[0];

        // Se a mesma dupla venceu as duas primeiras rodadas, ela vence a mão.
        } else if (resultados[0] == resultados[1]) {
            vencedor = resultados[0];

        // Se as duas primeiras rodadas tiveram vencedores diferentes, verifica a terceira.
        } else if (rodadasJogadas >= 3) {
            // As duas primeiras rodadas tiveram vencedores diferentes.
            if (resultados[2] != 0) {
                vencedor = resultados[2]; // A terceira rodada teve um vencedor.
            } else {
                // Se a terceira empatar, vence quem ganhou a primeira.
                vencedor = resultados[0];
            }
        }

        // Se um vencedor foi definido, atribui os pontos da mão à dupla correspondente.
        if (vencedor != 0) {
            System.out.println("\nA Dupla " + vencedor + " venceu a mão!");
            System.out.println("Pontos recebidos: " + valorMao);

            placar.adicionarPontos(vencedor, valorMao); // Atualiza a pontuação da dupla vencedora.

        } else {
            // Se nenhuma dupla foi definida como vencedora, a mão termina sem pontuação.
            System.out.println("\nA mão terminou empatada. Nenhuma dupla pontuou.");
        }
    }

}