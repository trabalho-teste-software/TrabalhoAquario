
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
        if (X+Y > M*N) {
        	throw new IllegalArgumentException("Erro: O número de peixes deve ser menor ou igual ao número de casas do aquário.");
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
        while (tentativas < 5) {
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
            
//            if (!p.moveuNesteTurno) {
            	
            	boolean morreu = false;
            	
                if (p.tipo == 0) {
                	
                    morreu = moverPeixeA(p);
                } else {
                    // AGORA O PEIXE B ENTRA EM AÇÃO!
                    morreu = moverPeixeB(p);
                }
                
                if (morreu) {
                	i--;
                } else {
                	
                	p.moveuNesteTurno = true;
                }
            }
            
//        }
    }

    private boolean moverPeixeA(Peixe p) {
      
        ArrayList<int[]> vizinhosLivres = new ArrayList<>();
        
        // Verifica as 4 direções (Cima, Baixo, Esq, Dir) se estão dentro do mapa E vazias (0)
        if (p.x > 0 && matriz[p.x - 1][p.y] == 0) vizinhosLivres.add(new int[]{p.x - 1, p.y});
        if (p.x < M - 1 && matriz[p.x + 1][p.y] == 0) vizinhosLivres.add(new int[]{p.x + 1, p.y});
        if (p.y > 0 && matriz[p.x][p.y - 1] == 0) vizinhosLivres.add(new int[]{p.x, p.y - 1});
        if (p.y < N - 1 && matriz[p.x][p.y + 1] == 0) vizinhosLivres.add(new int[]{p.x, p.y + 1});

        // --- 2. Decisão: Mover ou Ficar? ---
        if (vizinhosLivres.isEmpty()) {
            // REGRA MORTE: Se não se movimentar, conta como "fome"
            p.movimentosSemComer++; // Usamos essa variável para contar turnos preso
            
            // Se atingiu o limite MA, morre!
            if (p.movimentosSemComer >= MA) {
                System.out.println("✝ Peixe A morreu de fome (preso) na posição (" + p.x + "," + p.y + ")");
                matriz[p.x][p.y] = 0; // Remove corpo do aquário
                peixes.remove(p);     // Remove da lista
                return true;          // Avisa que morreu
            }
            return false; // Está preso, mas vivo
            
        } else {
            // Se tem vizinho livre, ele VAI se mexer. Reseta o contador de morte.
            p.movimentosSemComer = 0; 
            
            // Sorteia para onde vai
            int[] destino = vizinhosLivres.get(random.nextInt(vizinhosLivres.size()));
            int novoX = destino[0];
            int novoY = destino[1];

            // --- 3. REGRA REPRODUÇÃO  ---
            // Verifica se já andou o suficiente (RA) para ter filho
            if (p.movimentosParaReproduzir >= RA) {
                // REPRODUZ!
                // Pai: Fica na posição antiga (não muda p.x nem p.y)
                // Filho: Nasce na posição nova (destino)
                
                // Cria o filho
                Peixe filho = new Peixe(0, novoX, novoY);
                filho.moveuNesteTurno = true; // Filho não age no turno que nasce
                peixes.add(filho);
                
                // Atualiza Matriz com o filho
                matriz[novoX][novoY] = 1; 
                
                // Reseta contador do pai
                p.movimentosParaReproduzir = 0;
                
            } else {
                // MOVIMENTO NORMAL (Sem filho)
                // Atualiza Matriz: Sai da casa velha
                matriz[p.x][p.y] = 0;
                
                // Peixe anda
                p.x = novoX;
                p.y = novoY;
                
                // Atualiza Matriz: Entra na casa nova
                matriz[p.x][p.y] = 1;
                
                // Só aumenta a vontade de reproduzir se andar
                p.movimentosParaReproduzir++; 
            }
            
            return false; // Não morreu
        }
    }


    
    
    
    private boolean moverPeixeB(Peixe p) {
        
        ArrayList<int[]> vizinhosComida = new ArrayList<>();
        ArrayList<int[]> vizinhosVazios = new ArrayList<>();
        
        // Vamos olhar em volta (Cima, Baixo, Esq, Dir)
        // Definindo os offsets para não repetir código
        int[][] direcoes = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        
        for (int[] dir : direcoes) {
            int nx = p.x + dir[0];
            int ny = p.y + dir[1];
            
            // Verifica se está dentro do aquário
            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                if (matriz[nx][ny] == 1) {
                    vizinhosComida.add(new int[]{nx, ny}); // Achou Peixe A!
                } else if (matriz[nx][ny] == 0) {
                    vizinhosVazios.add(new int[]{nx, ny}); // Achou Vazio
                }
            }
        }

        boolean comeu = false;

        // --- 2. Ação: COMER (Prioridade) ---
        if (!vizinhosComida.isEmpty()) {
            // Escolhe uma presa aleatória
            int[] destino = vizinhosComida.get(random.nextInt(vizinhosComida.size()));
            int novoX = destino[0];
            int novoY = destino[1];
            
            // 1. Remove o Peixe A que estava lá
            System.out.println("⚔ Peixe B (" + p.x + "," + p.y + ") comeu Peixe A em (" + novoX + "," + novoY + ")");

            removerPeixeNaPosicao(novoX, novoY);
            
            // 2. Move o Peixe B
            matriz[p.x][p.y] = 0; // Sai da antiga
            p.x = novoX;
            p.y = novoY;
            matriz[p.x][p.y] = 2; // Ocupa a nova como B
            
            comeu = true;
            p.movimentosSemComer = 0; // Encheu a barriga
            p.movimentosParaReproduzir++; // Conta como "Peixe Comido" para reprodução
        
        } else if (!vizinhosVazios.isEmpty()) {
            // --- 3. Ação: MOVER (Sem comer) ---
            int[] destino = vizinhosVazios.get(random.nextInt(vizinhosVazios.size()));
            
            matriz[p.x][p.y] = 0;
            p.x = destino[0];
            p.y = destino[1];
            matriz[p.x][p.y] = 2;
            
            p.movimentosSemComer++; // Aumenta a fome
        } else {
            // --- 4. Ação: FICAR PARADO ---
            p.movimentosSemComer++; // Aumenta a fome mesmo parado
        }

        // --- 5. Verificação de MORTE (Fome) ---
        if (p.movimentosSemComer >= MB) {
            System.out.println("✝ Peixe B morreu de fome na posição (" + p.x + "," + p.y + ")");
            
            matriz[p.x][p.y] = 0;
            peixes.remove(p);
            return true; // Morreu
        }

        // --- 6. Verificação de REPRODUÇÃO ---
        // Regra: Ter comido RB peixes E ter espaço livre em volta (vizinhosVazios)

        if (comeu && p.movimentosParaReproduzir >= RB) {
            

            ArrayList<int[]> vagasParaFilho = new ArrayList<>();
            for (int[] dir : direcoes) {
                int nx = p.x + dir[0];
                int ny = p.y + dir[1];
                if (nx >= 0 && nx < M && ny >= 0 && ny < N && matriz[nx][ny] == 0) {
                    vagasParaFilho.add(new int[]{nx, ny});
                }
            }

            if (!vagasParaFilho.isEmpty()) {
                // Nasce o peixe!
                int[] localFilho = vagasParaFilho.get(random.nextInt(vagasParaFilho.size()));
                Peixe filho = new Peixe(1, localFilho[0], localFilho[1]);
                filho.moveuNesteTurno = true; // Recém nascido não age
                peixes.add(filho);
                matriz[localFilho[0]][localFilho[1]] = 2;
                
                p.movimentosParaReproduzir = 0; // Reseta contador
            }
        }

        return false; // Não morreu
    }
    
    
    
    
    private void removerPeixeNaPosicao(int x, int y) {
        for (int i = 0; i < peixes.size(); i++) {
            Peixe alvo = peixes.get(i);
            // Se achou um peixe na posição X,Y que não seja o próprio predador (só pra garantir)
            if (alvo.x == x && alvo.y == y) {
                peixes.remove(i);
                return; // Encontrou e removeu, pode parar
            }
        }
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
                } else {
                    System.out.print("B  "); 
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
    
}