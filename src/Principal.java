

public class Principal {

    public static void main(String[] args) {
        
        System.out.println("--- Iniciando o Jogo do Aquário ---");

        
        try {
            
            Aquario meuJogo = new Aquario(1, 1, 1, 1, 1, 1, 1, 2);
            
            
            
        } catch (IllegalArgumentException e) {
            
            System.out.println("O jogo NÃO inicializou: " + e.getMessage());
        }
        
        System.out.println("--- Fim da execução ---");
    }
}