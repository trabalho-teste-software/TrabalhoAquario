package jogo;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class AquarioMutationTest {

    @Test
    public void testMatarMutante_MovimentacaoFisica() {
        // Mata mutantes que alteram p.x = novoX ou manipulação de matriz
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 1, 10, 10);
        Peixe p = jogo.getPeixes().get(0);
        int xOriginal = p.x;
        int yOriginal = p.y;
        
        jogo.executarIteracao();
        
        // Se o peixe moveu, a posição antiga na matriz DEVE estar zerada
        // Isso mata mutantes que removem a linha "matriz[p.x][p.y] = 0"
        if (p.x != xOriginal || p.y != yOriginal) {
            assertEquals("A posição antiga deve ser limpa na matriz", 0, jogo.getMatriz()[xOriginal][yOriginal]);
            assertTrue("A nova posição deve estar marcada na matriz", jogo.getMatriz()[p.x][p.y] > 0);
        }
    }

    @Test
    public void testMatarMutante_ContadorReproducaoA() {
        // Mata mutantes que alteram o incremento de reprodução (p.movimentosParaReproduzir++)
        // Configuramos RA=1. No turno 1 ele anda (contador vira 1). No turno 2 ele deve parir.
        Aquario jogo = new Aquario(10, 1, 10, 10, 1, 1, 20, 20); 
        
        jogo.executarIteracao(); // Turno 1: Move e incrementa
        int qtdAposTurno1 = jogo.getQuantidadePeixesA();
        
        jogo.executarIteracao(); // Turno 2: Deve detectar contador >= RA e criar filho
        
        assertTrue("Mutante detectado: Peixe A deveria ter reproduzido no turno 2", 
                   jogo.getQuantidadePeixesA() > qtdAposTurno1);
    }

    @Test
    public void testMatarMutante_FomeExtremaB() {
        // Mata mutantes que alteram a condição de morte (>= MB para > MB)
        // Se MB=1, o peixe deve morrer IMEDIATAMENTE após 1 movimento sem comer.
        Aquario jogo = new Aquario(10, 10, 1, 10, 1, 1, 20, 20);
        // Removemos os Peixes A para garantir que B não coma
        jogo.getPeixes().removeIf(p -> p.tipo == 0);
        jogo.setQuantidadePeixesA(0);
        
        jogo.executarIteracao();
        
        assertEquals("Mutante detectado: Peixe B deveria ter morrido com MB=1", 0, jogo.getQuantidadePeixesB());
    }

    @Test
    public void testMatarMutante_LimitesDeBorda() {
        // Força o teste de boundaries (p.x > 0, p.x < M-1)
        // Criamos um aquário 1x2. O peixe em (0,0) só pode ir para (0,1).
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 1, 1, 2);
        
        // Verifica se não há crash de ArrayIndexOutOfBounds
        // Isso mata mutantes que trocam "> 0" por ">= 0"
        try {
            for(int i=0; i<5; i++) jogo.executarIteracao();
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("Mutante de limite de borda detectado: o código tentou acessar índice inválido");
        }
    }
    
    @Test
    public void testMatarMutante_ResetTurno() {
        // Mata mutantes que removem "p.moveuNesteTurno = false"
        Aquario jogo = new Aquario(10, 10, 10, 10, 1, 1, 5, 5);
        jogo.executarIteracao();
        
        for (Peixe p : jogo.getPeixes()) {
            // Após a iteração, o código deve estar pronto para a próxima
            assertFalse("Mutante detectado: o flag moveuNesteTurno não foi resetado", p.moveuNesteTurno);
        }
    }
}