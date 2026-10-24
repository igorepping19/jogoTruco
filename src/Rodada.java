public class Rodada {

    // Armazena a carta vira, utilizada para determinar a manilha da rodada.
    public Carta vira;

    // Armazena o número que será considerado manilha nesta rodada.
    public String manilha;

    // Construtor: recebe a carta vira e define a manilha correspondente.
    public Rodada(Carta vira) {
        this.vira = vira; // Guarda a carta vira recebida como parâmetro.
        definirManilha(); // Calcula qual número será a manilha.
    }

    // Define a manilha com base no número da carta vira.
    private void definirManilha() {
        String numeroVira = vira.getNumero(); // Obtém o número da carta vira.

        // Verifica o número da vira e define o próximo número como manilha.
        switch (numeroVira) {
            case "4":
                manilha = "5";
                break;
            case "5":
                manilha = "6";
                break;
            case "6":
                manilha = "7";
                break;
            case "7":
                manilha = "10";
                break;
            case "10":
                manilha = "Q";
                break;
            case "Q":
                manilha = "J";
                break;
            case "J":
                manilha = "K";
                break;
            case "K":
                manilha = "A";
                break;
            case "A":
                manilha = "2";
                break;
            case "2":
                manilha = "3";
                break;
            case "3":
                manilha = "4";
                break;
            // Caso o número não corresponda a nenhum dos anteriores, define 5 como manilha.
            default:
                manilha = "5";
                break;
        }
    }

    // Calcula e retorna a força de uma carta de acordo com as regras implementadas.
    public int calcularForca(Carta carta) {
        String numero = carta.getNumero(); // Obtém o número da carta.
        String naipe = carta.getNaipe(); // Obtém o naipe da carta.

        // Verifica primeiro se a carta é uma manilha.
        if (numero.equals(manilha)) {
            // As manilhas têm forças diferentes de acordo com o naipe.
            switch (naipe) {
                case "Paus":
                    return 14; // Maior força entre as manilhas.
                case "Copas":
                    return 13;
                case "Espadas":
                    return 12;
                case "Ouros":
                    return 11; // Menor força entre as manilhas.
            }
        }

        // Se a carta não for manilha, define sua força pelo número.
        switch (numero) {
            case "3":
                return 10;
            case "2":
                return 9;
            case "A":
                return 8;
            case "K":
                return 7;
            case "J":
                return 6;
            case "Q":
                return 5;
            case "10":
                return 4;
            case "7":
                return 3;
            case "6":
                return 2;
            case "5":
                return 1;
            case "4":
                return 0;
            // Retorna -1 caso o número não corresponda a nenhuma opção.
            default:
                return -1;
        }
    }

    // Compara as cartas jogadas pelos quatro participantes e retorna o resultado da rodada.
    public int compararQuatroCartas(
            Carta cartaJogador1,
            Carta cartaPC1,
            Carta cartaJogador2,
            Carta cartaPC2) {

        // Calcula a força individual de cada uma das quatro cartas.
        int forcaJogador1 = calcularForca(cartaJogador1);
        int forcaPC1 = calcularForca(cartaPC1);
        int forcaJogador2 = calcularForca(cartaJogador2);
        int forcaPC2 = calcularForca(cartaPC2);

        // Encontra a maior força entre as quatro cartas.
        int maiorForca = Math.max(
                Math.max(forcaJogador1, forcaPC1),
                Math.max(forcaJogador2, forcaPC2));

        // Verifica se pelo menos um jogador da Dupla 1 possui a maior força.
        boolean dupla1TemMaior = forcaJogador1 == maiorForca
                || forcaJogador2 == maiorForca;

        // Verifica se pelo menos um jogador da Dupla 2 possui a maior força.
        boolean dupla2TemMaior = forcaPC1 == maiorForca
                || forcaPC2 == maiorForca;

        // Se as duas duplas possuem cartas com a maior força, considera empate.
        if (dupla1TemMaior && dupla2TemMaior) {
            return 0; // Retorna 0 para indicar empate.
        } else if (dupla1TemMaior) {
            return 1; // Retorna 1 para indicar vitória da Dupla 1.
        } else {
            return 2; // Retorna 2 para indicar vitória da Dupla 2.
        }
    }

}