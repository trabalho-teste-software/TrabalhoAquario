
import java.util.ArrayList;
import java.util.Random;



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
    private ArrayList<Peixe> peixes; 
    private Random random = new Random();
    
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
        this.peixes = new ArrayList<>();
        posicionarPeixesIniciais();
        
        System.out.println("O jogo inicializa com sucesso!");
    }
    
    // Métodos 
   
    private void posicionarPeixesIniciais() {

        for (int i = 0; i < this.X; i++) {
            criarPeixeEmPosicaoAleatoria(0);
        }
        for (int i = 0; i < this.Y; i++) {
            criarPeixeEmPosicaoAleatoria(1);
        }
    }

    private void criarPeixeEmPosicaoAleatoria(int tipo) {
        int tentativas = 0;
        while (tentativas < 100) {
            int rX = random.nextInt(M);
            int rY = random.nextInt(N);
            
            if (matriz[rX][rY] == 0) { // Se vazio
                matriz[rX][rY] = (tipo == 0) ? 1 : 2; // Marca na matriz
                
                Peixe novoPeixe = new Peixe(tipo, rX, rY);
                peixes.add(novoPeixe);
                return;
            }
            tentativas++;
        }
    }

    public void executarIteracao() {
        //  Reseta movimentos
        for (Peixe p : peixes) {
            p.moveuNesteTurno = false;
        }

        // Move os peixes
        for (int i = 0; i < peixes.size(); i++) {
            Peixe p = peixes.get(i);
            
            if (!p.moveuNesteTurno) {
                if (p.tipo == 0) {
                    moverPeixeA(p);
                }
                // ( moverPeixeB  faltaAinda)
                
                p.moveuNesteTurno = true;
            }
        }
    }

    private void moverPeixeA(Peixe p) {
 
        ArrayList<int[]> vizinhosLivres = new ArrayList<>();
        
        if (p.x > 0 && matriz[p.x - 1][p.y] == 0) vizinhosLivres.add(new int[]{p.x - 1, p.y});
        if (p.x < M - 1 && matriz[p.x + 1][p.y] == 0) vizinhosLivres.add(new int[]{p.x + 1, p.y});
        if (p.y > 0 && matriz[p.x][p.y - 1] == 0) vizinhosLivres.add(new int[]{p.x, p.y - 1});
        if (p.y < N - 1 && matriz[p.x][p.y + 1] == 0) vizinhosLivres.add(new int[]{p.x, p.y + 1});

        // Se tiver lugar para ir, sorteia e vai
        if (!vizinhosLivres.isEmpty()) {
            int[] destino = vizinhosLivres.get(random.nextInt(vizinhosLivres.size()));
            
            // Atualiza Matriz: Sai da posição antiga
            matriz[p.x][p.y] = 0;
            
            // Atualiza Peixe: Muda coordenada
            p.x = destino[0];
            p.y = destino[1];
            
            // Atualiza Matriz: Entra na nova posição
            matriz[p.x][p.y] = 1; 
        }
        
        // Atualiza status vital
        p.movimentosSemComer++;
        p.movimentosParaReproduzir++;
    }


    public int getQuantidadePeixesA() {
        int conta = 0;
        for (Peixe p : peixes) {
            if (p.tipo == 0) conta++;
        }
        return conta;
    }

    public int getQuantidadePeixesB() {
        int conta = 0;
        for (Peixe p : peixes) {
            if (p.tipo == 1) conta++;
        }
        return conta;
    }
    
    public void setQuantidadePeixesA(int x) { this.X = x; }
    public void setQuantidadePeixesB(int y) { this.Y = y; }
    
    

    public void imprimirAquario() {
        System.out.println("--- Visualização do Aquário ---");
        
        for (int i = 0; i < M; i++) { 
            for (int j = 0; j < N; j++) { 
                
                int valor = matriz[i][j];
                
                if (valor == 0) {
                    System.out.print(".  "); 
                } else if (valor == 1) {
                    System.out.print("A  "); 
                } else if (valor == 2) {
                    System.out.print("B  "); 
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
    
}