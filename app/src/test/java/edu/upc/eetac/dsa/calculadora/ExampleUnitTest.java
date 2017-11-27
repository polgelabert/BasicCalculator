package edu.upc.eetac.dsa.calculadora;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        String[] op = new String[1];
        op[0] = "1: 5 + 3 = 8";
        //new PantallaPrincipal().mostrarHistorial();
        new LlistaOperacions().Listar2(op);
        assertEquals(4, 2 + 2);
    }
}