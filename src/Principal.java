import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        try {
        
	        System.out.println("===              BEM-VINDO AO JOGO DO AQUÁRIO                        ===");
	        System.out.println("===                  Variáveis do jogo                               ===");
	        System.out.println("=== X:  Quantidade inicial de peixes A                               ===");
	        System.out.println("=== Y:  Quantidade inicial de peixes B                               ===");
	        System.out.println("=== M:  Altura do aquário                                            ===");
	        System.out.println("=== N:  Largura do aquário                                           ===");
	        System.out.println("=== MA: Número de movimentos para peixes A morrer de fome            ===");
	        System.out.println("=== RA: Número de movimentos para peixes A reproduzir                ===");
	        System.out.println("=== MB: Número de movimentos para peixes B morrer de fome            ===");
	        System.out.println("=== RR: Número de movimentos para peixes B reproduzir                ===");
	        
	        // 1. Configuração Inicial (Perguntas ao usuário)
	        System.out.println("Insira o valor de M: ");
	        int m = teclado.nextInt();
	        
	        System.out.println("Insira o valor de N: ");
	        int n = teclado.nextInt();
	        
	        System.out.println("Insira o valor de X: ");
	        int x = teclado.nextInt();
	        
	        System.out.println("Insira o valor de Y: ");
	        int y = teclado.nextInt();
	        
	        System.out.println("Insira o valor de MA: ");
	        int ma = teclado.nextInt();
	        
	        System.out.println("Insira o valor de RA: ");
	        int ra = teclado.nextInt();
	        
	        System.out.println("Insira o valor de MB: ");
	        int mb = teclado.nextInt();
	        
	        System.out.println("Insira o valor de RB: ");
	        int rb = teclado.nextInt();
	        
//	        // SUAS CONFIGURAÇÕES PERSONALIZADAS
//	        int ma = 5; // Fome A
//	        int ra = 1; // Reprodução A
//	        int mb = 5; // Fome B
//	        int rb = 2; // Reprodução B
   
            // Cria o jogo
            Aquario jogo = new Aquario(ma, ra, mb, rb, x, y, m, n);
            
            // Loop do Jogo
            String opcao = "";
            int turno = 0;
            
            // O loop continua enquanto o usuário não digitar "S"
            while (!opcao.equalsIgnoreCase("s")) {
                System.out.println("\n--- Turno " + turno + " ---");
                System.out.println("Peixes A: " + jogo.getQuantidadePeixesA());
                System.out.println("Peixes B: " + jogo.getQuantidadePeixesB());
                
                jogo.imprimirAquario();
                
                // --- NOVA REGRA DE PARADA ---
                // Verifica se os predadores foram extintos
                if (jogo.getQuantidadePeixesB() == 0) {
                    System.out.println("\n>>> FIM DE JOGO: Os peixes do tipo B foram extintos! <<<");
                    System.out.println("Pontuação Final (Turnos): " + turno);
                    break; // Sai do while imediatamente
                }
                
                System.out.println("[ENTER] Próximo Turno | [S] Sair");
                teclado.nextLine(); // Limpa buffer
                opcao = teclado.nextLine();
                
                if (!opcao.equalsIgnoreCase("s")) {
                    jogo.executarIteracao();
                    turno++;
                }
            }
            
        } catch (IllegalArgumentException e) {
        	System.out.println("Erro ao iniciar jogo: " + e.getMessage());
	    } catch (InputMismatchException e) {
	    	System.out.println("Erro ao iniciar jogo: O tipo do valor inserido é diferente do tipo esperado (inteiro).");
	    }
        
        System.out.println("Execução Encerrada.");
        teclado.close();
    }
}