public class Aquario {

   
    private int MA; // Movimentos para A morrer de fome
    private int RA; // Movimentos para A reproduzir
    private int MB; // Movimentos para B morrer de fome
    private int RB; // Peixes A comidos para B reproduzir
    
   
    private int X; // Qtd inicial Peixes A
    private int Y; // Qtd inicial Peixes B
    private int M; // Altura da matriz
    private int N; // Largura da matriz
    

    private int[][] matriz; 

    
    public Aquario(int MA, int RA, int MB, int RB, int X, int Y, int M, int N) {
        
        
        if (MA <= 0) {
            throw new IllegalArgumentException("Erro: MA deve ser maior que 0.");
        }
        if (RA <= 0) {
            throw new IllegalArgumentException("Erro: RA deve ser maior que 0.");
        }
        if (MB <= 0) {
            throw new IllegalArgumentException("Erro: MB deve ser maior que 0.");
        }
        if (RB <= 0) {
            throw new IllegalArgumentException("Erro: RB deve ser maior que 0.");
        }
        if (X <= 0) {
            throw new IllegalArgumentException("Erro: X deve ser maior que 0.");
        }
        if (Y <= 0) {
            throw new IllegalArgumentException("Erro: Y deve ser maior que 0.");
        }
        if (M <= 0) {
            throw new IllegalArgumentException("Erro: M deve ser maior que 0.");
        }
        if (N <= 0) {
            throw new IllegalArgumentException("Erro: N deve ser maior que 0.");
        }

      
        this.MA = MA;
        this.RA = RA;
        this.MB = MB;
        this.RB = RB;
        this.X = X;
        this.Y = Y;
        this.M = M;
        this.N = N;
        
        
        this.matriz = new int[M][N];
        
        System.out.println("O jogo inicializa com sucesso!");
    }
    
    // Métodos 
}