
public class Peixe {
    int tipo; // 0 = Tipo A (Presa), 1 = Tipo B (Predador)
    int x, y; // Posição na matriz
    int movimentosSemComer; // Para morte (MA ou MB)
    int movimentosParaReproduzir; // Para reprodução (RA ou RB)
    boolean moveuNesteTurno; // Para não mover o mesmo peixe duas vezes na mesma iteração

    public Peixe(int tipo, int x, int y) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
        this.movimentosSemComer = 0;
        this.movimentosParaReproduzir = 0;
        this.moveuNesteTurno = false;
    }
}