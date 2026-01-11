import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("=== BEM-VINDO AO JOGO DO AQUÁRIO ===");
        
        // 1. Configuração Inicial (Perguntas ao usuário)
        System.out.println("Digite a altura do Aquário (M): ");
        int m = teclado.nextInt();
        
        System.out.println("Digite a largura do Aquário (N): ");
        int n = teclado.nextInt();
        
        System.out.println("Quantos Peixes A (Presas)?: ");
        int x = teclado.nextInt();
        
        System.out.println("Quantos Peixes B (Predadores)?: ");
        int y = teclado.nextInt();
        
        // SUAS CONFIGURAÇÕES PERSONALIZADAS
        int ma = 5; // Fome A
        int ra = 1; // Reprodução A
        int mb = 5; // Fome B
        int rb = 2; // Reprodução B
        
        try {
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
        }
        
        System.out.println("Execução Encerrada.");
        teclado.close();
    }
}