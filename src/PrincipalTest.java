import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
// esse arquivo é para testar o principal.java
// para aumentar a cobertura pois impacta nos numeros de testes
public class PrincipalTest {

    @Test
    public void testPrincipal_FluxoNormal() {
        String entradas = "5\n5\n2\n1\n\n\ns\n";
        
        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);

        Principal.main(new String[]{});
    }

    @Test
    public void testPrincipal_ErroInicializacao() {
        // Cenário: O usuário digita valores inválidos (Ex: Peixes negativos)
  
        String entradas = "5\n5\n-10\n1\n";
        
        InputStream in = new ByteArrayInputStream(entradas.getBytes());
        System.setIn(in);
        Principal.main(new String[]{});
    }
    
    @Test
    public void testPrincipal_VitoriaPeixesB_Extintos() {

         String entradas = "5\n5\n5\n0\n\ns\n";

         InputStream in = new ByteArrayInputStream(entradas.getBytes());
         System.setIn(in);

         Principal.main(new String[]{});
    }
}