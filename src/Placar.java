
public class Placar {
    public int pontosDupla1 = 0;
    public int pontosDupla2 = 0;

    public void marcarTruco(int dupla) {
        adicionarPontos(dupla, 3);
    }

    public void marcarSeis(int dupla) {
        adicionarPontos(dupla, 6);
    }

    public void marcarNove(int dupla) {
        adicionarPontos(dupla, 9);
    }

    public void marcarDoze(int dupla) {
        adicionarPontos(dupla, 12);
    }

    public void adicionarPontos(int dupla, int pontos) {
        if (dupla == 1) {
            pontosDupla1 += pontos;
        } else if (dupla == 2) {
            pontosDupla2 += pontos;
        } else {
            System.out.println("Dupla invalida");
            return;
        }
        if (pontosDupla1 >= 12) {
            pontosDupla1 = 12;
            mostrarPlacar();
            System.out.println("Dupla 1 venceu a partida!");
            zerarPlacar();
        } else if (pontosDupla2 >= 12) {
            pontosDupla2 = 12;
            mostrarPlacar();
            System.out.println("Dupla 2 venceu a partida!");
            zerarPlacar();
        } else {
            mostrarPlacar();
        }
    }

    public void mostrarPlacar() {
        System.out.println("Placar: " + pontosDupla1 + " x " + pontosDupla2);
    }

    public void zerarPlacar() {
        pontosDupla1 = 0;
        pontosDupla2 = 0;

        System.out.println("Placar reiniciado: 0 x 0");
    }

    public void marcarMao(int jogador) {
        adicionarPontos(jogador, 1);
    }
}
