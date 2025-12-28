

public class Principal {

    public static void main(String[] args) {
        
        System.out.println("--- Iniciando o Jogo do Aquário ---");

        
        try {
            
            Aquario meuJogo = new Aquario(1, 1, 1, 1, 5, 1, 5, 5);
        	//Aquario meuJogo = new Aquario(10, 10, 10, 10, 4, 1, 3, 3);
            
         // 1. Tira a foto do estado INICIAL
            System.out.println("\n1. Estado Inicial:");
            meuJogo.imprimirAquario();
            
            // 2. Roda a lógica (O peixe deve andar)
            System.out.println("... O tempo passa (Executando iteração) ...");
            meuJogo.executarIteracao();
            
            // 3. Tira a foto do estado FINAL
            System.out.println("\n2. Estado Após Movimento:");
            meuJogo.imprimirAquario();
            
            
        } catch (IllegalArgumentException e) {
            
            System.out.println("O jogo NÃO inicializou: " + e.getMessage());
        }
        
        System.out.println("--- Fim da execução ---");
    }
}