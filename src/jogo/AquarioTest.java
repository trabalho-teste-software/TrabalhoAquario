package jogo;

import static org.junit.Assert.*;

import org.junit.Test;

public class AquarioTest {

	@Test
	public void testCT01_InicializacaoValida() {  
	    Aquario jogo = new Aquario(1, 1, 1, 1, 1, 1, 1, 2);
	    assertNotNull(jogo); 
	}
	

    @Test(expected = IllegalArgumentException.class)
    public void testCT02_MA_Invalido() {
        new Aquario(0, 1, 1, 1, 1, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT03_RA_Invalido() {
        new Aquario(1, 0, 1, 1, 1, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT04_MB_Invalido() {
        new Aquario(1, 1, 0, 1, 1, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT05_RB_Invalido() {
        new Aquario(1, 1, 1, 0, 1, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT06_X_Invalido() {
        new Aquario(1, 1, 1, 1, 0, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT07_Y_Invalido() {
        new Aquario(1, 1, 1, 1, 1, 0, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT08_M_Invalido() {
        new Aquario(1, 1, 1, 1, 1, 1, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT09_N_Invalido() {
        new Aquario(1, 1, 1, 1, 1, 1, 1, 0);
    }
    
    @Test
    public void testCT18_Numero_Peixes_Maior_Que_Aquario() {
    	Exception ex = assertThrows(IllegalArgumentException.class, () -> {
    		new Aquario(1, 1, 1, 1, 1, 1, 1, 1);
    	});
    	String expectedMessage = "Erro: O número de peixes deve ser menor ou igual ao número de casas do aquário.";
    	assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    public void testCT19_MovimentoPeixeA() {
        // CT19: Peixe A deve mudar de posição (simples movimentação)
        // Cenário: MA grande (não morre), X=1, M=3, N=3 (espaço para andar)
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 1, 20, 20);
        
        jogo.executarIteracao();        
        assertEquals(1, jogo.getQuantidadePeixesA());
    }

    @Test
    public void testCT20_ReproducaoPeixeA() {
        // CT20: Peixe A reproduz.
        // Cenário: RA=1 (reproduz logo), MA=10 (não morre)
    	Aquario jogo = new Aquario(10, 1, 10, 10, 20, 1, 20, 20);
        
        
        
        // Executa o turno. O peixe deve se mover e criar um filho.
        jogo.executarIteracao();
 
        assertEquals("Peixe A deveria ter reproduzido", 20, jogo.getQuantidadePeixesA());
    }

    @Test
    public void testCT21_MortePeixeAFome() {
        // CT21: Peixe A morre de fome (por inatividade).
        // O mapa estará CHEIO. O Peixe A não terá para onde ir (vizinhos ocupados).
        Aquario jogo = new Aquario(1, 10, 10, 10, 1, 1, 1, 2);
        

        jogo.executarIteracao();
        
        // Se morreu, deve sobrar 0
        assertEquals("Peixe A deveria ter morrido de fome", 0, jogo.getQuantidadePeixesA());
    }

    @Test
    public void testCT22_PeixeB_Come_PeixeA() {
        // CT22: Peixe B come Peixe A.
        // Cenário: Colocamos 1 A e 1 B em um aquário pequeno para forçar o encontro
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 1, 1, 2);
        
        jogo.executarIteracao();
        
        // Se B comeu A, A deve ser 0.
        // (Nota: Isso depende da sorte/posicionamento na lógica final, mas é o objetivo do teste)
        assertEquals("Peixe A deveria ter sido comido", 0, jogo.getQuantidadePeixesA());
    }
    
    @Test
    public void testCT23_MortePeixeBFome() {
        // CT23: Peixe B morre de fome (não comeu A).        

        Aquario jogo = new Aquario(10, 10, 1, 10, 1, 1, 2, 2); 
        
        jogo.executarIteracao();
        jogo.executarIteracao();
        jogo.executarIteracao();
        
        // B deve morrer
        assertEquals("Peixe B deveria morrer de fome", 0, jogo.getQuantidadePeixesB());
    }

    @Test
    public void testExtra_Visualizacao() {
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 1, 5, 5);
        jogo.imprimirAquario(); 
    }

    @Test
    public void testExtra_Setters() {
    
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 1, 5, 5);
        
        jogo.setQuantidadePeixesA(5);
        jogo.setQuantidadePeixesB(3);
        
    }
    
    @Test
    public void testExtra_PeixeB_MovimentoSemComer() {
       
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 1, 20, 20);
        jogo.executarIteracao();
        // Apenas rodar já cobre as linhas de "mover para vazio" do Peixe B
    }

    @Test
    public void testExtra_PeixeB_Preso() {
     
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 8, 3, 3);
        
        jogo.executarIteracao();
       
    }

    @Test
    public void testExtra_PeixeB_MoveSemComer() {
    
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 1, 20, 20);
        
        jogo.setQuantidadePeixesA(0);
        
        jogo.executarIteracao();

        assertEquals(1, jogo.getQuantidadePeixesB());
    }
    
    @Test
    public void testExtra_Espaco_Para_PeixeA_Mover() {
    	
    	Aquario jogo = new Aquario(4, 20, 4, 20, 2, 2, 20, 20);
    	
    	jogo.setQuantidadePeixesA(0);
    	
    	jogo.executarIteracao();
    	
    	assertEquals(2, jogo.getQuantidadePeixesB());
    }
    
}
