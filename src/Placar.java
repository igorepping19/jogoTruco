public class Placar {

    // Armazena a pontuação atual de cada dupla, começando em zero.
    public int pontosDupla1 = 0;
    public int pontosDupla2 = 0;

    // Registra três pontos para a dupla que pediu e venceu o truco.
    public void marcarTruco(int dupla) {
        adicionarPontos(dupla, 3); // Reutiliza o método que adiciona pontos ao placar.
    }

    // Registra seis pontos para a dupla indicada.
    public void marcarSeis(int dupla) {
        adicionarPontos(dupla, 6);
    }

    // Registra nove pontos para a dupla indicada.
    public void marcarNove(int dupla) {
        adicionarPontos(dupla, 9);
    }

    // Registra doze pontos para a dupla indicada.
    public void marcarDoze(int dupla) {
        adicionarPontos(dupla, 12);
    }

    // Adiciona a quantidade de pontos informada à dupla correspondente.
    public void adicionarPontos(int dupla, int pontos) {

        // Verifica qual dupla receberá os pontos.
        if (dupla == 1) {
            pontosDupla1 += pontos; // Soma os pontos à pontuação da Dupla 1.
        } else if (dupla == 2) {
            pontosDupla2 += pontos; // Soma os pontos à pontuação da Dupla 2.
        } else {
            // Exibe uma mensagem caso o número da dupla seja inválido.
            System.out.println("Dupla invalida");
            return; // Encerra o método sem continuar a execução.
        }

        // Impede que a pontuação da Dupla 1 ultrapasse 12 pontos.
        if (pontosDupla1 >= 12) {
            pontosDupla1 = 12;
        // Impede que a pontuação da Dupla 2 ultrapasse 12 pontos.
        } else if (pontosDupla2 >= 12) {
            pontosDupla2 = 12;
        }

        mostrarPlacar(); // Exibe o placar atualizado após a pontuação.

        // Verifica se a Dupla 1 atingiu os 12 pontos necessários para vencer.
        if (pontosDupla1 >= 12) {
            System.out.println("Dupla 1 venceu a partida!");
        // Caso contrário, verifica se a Dupla 2 atingiu 12 pontos.
        } else if (pontosDupla2 >= 12) {
            System.out.println("Dupla 2 venceu a partida!");
        }
    }

    // Exibe a pontuação atual das duas duplas.
    public void mostrarPlacar() {
        System.out.println("Placar: " + pontosDupla1 + " x " + pontosDupla2);
    }

    // Reinicia a pontuação das duas duplas para zero.
    public void zerarPlacar() {
        pontosDupla1 = 0;
        pontosDupla2 = 0;

        // Informa que o placar foi reiniciado.
        System.out.println("Placar reiniciado: 0 x 0");
    }

    // Registra um ponto para a dupla indicada.
    public void marcarMao(int jogador) {
        adicionarPontos(jogador, 1); // Adiciona um ponto usando o método geral.
    }
}