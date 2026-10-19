public class Rodada {

    public Carta vira;
    public String manilha;

    public Rodada(Carta vira) {
        this.vira = vira;
        definirManilha();
    }

    private void definirManilha() {
        String numeroVira = vira.getNumero();

        switch (numeroVira) {
            case "4": manilha = "5"; break;
            case "5": manilha = "6"; break;
            case "6": manilha = "7"; break;
            case "7": manilha = "Q"; break;
            case "Q": manilha = "J"; break;
            case "J": manilha = "K"; break;
            case "K": manilha = "A"; break;
            case "A": manilha = "2"; break;
            case "2": manilha = "3"; break;
            case "3": manilha = "4"; break;
            default: manilha = "5"; break;
        }
    }

    public int calcularForca(Carta carta) {
        String numero = carta.getNumero();
        String naipe = carta.getNaipe();

        if (numero.equals(manilha)) {
            switch (naipe) {
                case "Paus": return 14;
                case "Copas": return 13;
                case "Espadas": return 12;
                case "Ouros": return 11;
            }
        }

        switch (numero) {
            case "3": return 10;
            case "2": return 9;
            case "A": return 8;
            case "K": return 7;
            case "J": return 6;
            case "Q": return 5;
            case "10": return 4;
            case "7": return 3;
            case "6": return 2;
            case "5": return 1;
            case "4": return 0;
            default: return -1;
        }
    }

    
    public int compararCartas(Carta carta1, Carta carta2) {
        int forca1 = calcularForca(carta1);
        int forca2 = calcularForca(carta2);

        if (forca1 > forca2) {
            return 1; // Jogador 1 venceu
        } else if (forca2 > forca1) {
            return 2; // Jogador 2 venceu
        } else {
            return 0; // Empate
        }
    }

}
