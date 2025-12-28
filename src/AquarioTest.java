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
        
        //  MA=0 (Inválido), o resto válido.
        new Aquario(0, 1, 1, 1, 1, 1, 1, 2);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT03_RA_Invalido() {
        // RA=0 (Inválido), resto=1
        new Aquario(1, 0, 1, 1, 1, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT04_MB_Invalido() {
        // MB=0 (Inválido)
        new Aquario(1, 1, 0, 1, 1, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT05_RB_Invalido() {
        // RB=0 (Inválido)
        new Aquario(1, 1, 1, 0, 1, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT06_X_Invalido() {
        // X=0 (Inválido - Sem peixes A)
        new Aquario(1, 1, 1, 1, 0, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT07_Y_Invalido() {
        // Y=0 (Inválido - Sem peixes B)
        new Aquario(1, 1, 1, 1, 1, 0, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT08_M_Invalido() {
        // M=0 (Inválido - Altura 0)
        new Aquario(1, 1, 1, 1, 1, 1, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCT09_N_Invalido() {
        // N=0 (Inválido - Largura 0)
        new Aquario(1, 1, 1, 1, 1, 1, 1, 0);
    }

}
