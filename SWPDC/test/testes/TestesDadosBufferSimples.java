/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dados.BufferSimples;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 *
 * @author cachutti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({})
public class TestesDadosBufferSimples extends TestCase{

    public void checkEstaVazio() {
        BufferSimples bufferTest;
        bufferTest = new BufferSimples(this, (byte)5);
        assertTrue( bufferTest.estaVazio());
        
    }
}