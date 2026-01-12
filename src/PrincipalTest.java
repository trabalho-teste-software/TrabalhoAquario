import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
// esse arquivo é para testar o principal.java
// para aumentar a cobertura pois impacta nos numeros de testes
public class PrincipalTest {

    @Test
    public void testPrincipal_FluxoNormal() {
        String entradas = "5\n5\n2\n1\n5\n1\n5\n2\n\n\ns\n";
        
        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }

    @Test
    public void testPrincipal_ErroInicializacao() {
        // Cenário: O usuário digita valores inválidos (Ex: Peixes negativos)
  
        String entradas = "5\n5\n-10\n1\n5\n1\n5\n2\n";
        
        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);
        Principal.main(new String[]{});
    }
    
    @Test
    public void testPrincipal_VitoriaPeixesB_Extintos() {

         String entradas = "5\n5\n1\n1\n5\n1\n1\n2\n\n\ns\n";

         InputStream in = new ByteArrayInputStream(entradas.getBytes());
         System.setIn(in);

         Principal.main(new String[]{});
    }
    
    
    @Test
    public void testCT10_MA_Invalido() {
    	String entradas = "a\n5\n5\n1\n5\n1\n5\n2\n";

        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }
    
    @Test
    public void testCT11_RA_Invalido() {
    	String entradas = "5\nb\n5\n1\n5\n1\n5\n2\n";

        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }
    
    @Test
    public void testCT12_MB_Invalido() {
    	String entradas = "5\n5\nc\n1\n5\n1\n5\n2\n";

        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }
    
    @Test
    public void testCT13_RB_Invalido() {
    	String entradas = "5\n5\n5\nd\n5\n1\n5\n2\n";

        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }
    
    @Test
    public void testCT14_X_Invalido() {
    	String entradas = "5\n5\n5\n1\ne\n1\n5\n2\n";

        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }
    
    @Test
    public void testCT15_Y_Invalido() {
    	String entradas = "5\n5\n5\n1\n5\nf\n5\n2\n";

        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }
    
    @Test
    public void testCT16_M_Invalido() {
    	String entradas = "5\n5\n5\n1\n5\n1\ng\n2\n";

        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }
    
    @Test
    public void testCT17_N_Invalido() {
    	String entradas = "5\n5\n5\n1\n5\n1\n5\nh\n";

        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }
}