package dados;

import java.nio.ByteBuffer;
import java.util.NoSuchElementException;
/**
 *
 * @author cachutti
 */
//buffer circular
public class BufferSimples implements IBufferSimples, IBuffer {

    
    private Object m_area; //"Ponteiro" para a área de dados alocada para este buffer.
    private int m_ixrd; //Indexador de leitura do buffer. head
    private int m_ixwr; //Indexador de escrita do buffer. tail
    private byte m_tamanho; //Tamanho, em bytes, da área de dados alocada
    ByteBuffer buffer;
    
    public BufferSimples (Object area, byte tamanho){
       m_tamanho = tamanho;
        m_area = area;
        buffer = ByteBuffer.allocateDirect(tamanho);
        m_ixwr = buffer.limit();
        m_ixrd = buffer.position();
       alocar (area, tamanho );
    }
    
    
    //Registra alocação
    @Override
    public final void alocar(Object area, byte tamanho) {
      //e no caso de não ter rolado a alocação correta
        System.out.println("Alocação no Buffer de tamanho" + tamanho + "registrada");

       
    }

    @Override
    public boolean estaVazio() {
        if (m_ixwr == 0) {
            return true;
        }
   
        return false;
    }
   

    @Override
    public byte inserir(float dado, byte tamanho){            
      
        byte capacidadeInicial;
        byte capacidadeFinal;
        byte bytesInseridos;
      
        if (buffer.capacity() < tamanho){
            System.out.println("Erro: Estouro do buffer");
        }
          
        capacidadeInicial = (byte) buffer.remaining();
        buffer = buffer.putFloat(tamanho, dado);   
        capacidadeFinal = (byte) buffer.remaining();
      
        bytesInseridos = (byte)(capacidadeFinal - capacidadeInicial);
      
        m_ixwr = buffer.limit();
        m_ixrd = buffer.position();
       
      return bytesInseridos;
    }

    @Override
    public byte remover(float dado, byte tamanho) {  
           
        if (m_ixwr == 0) {  
                throw new NoSuchElementException();  
           }
        
        byte capacidadeInicial;
        byte capacidadeFinal;
        byte bytesRemovidos;
        float dadoRemovido;
         
        capacidadeInicial = (byte) buffer.remaining();
        dadoRemovido = buffer.getFloat();
        capacidadeFinal = (byte) buffer.remaining();
        bytesRemovidos = (byte)(capacidadeFinal - capacidadeInicial);
      
        m_ixwr = buffer.limit();
        m_ixrd = buffer.position();
       
      return bytesRemovidos;
    }
           
           
           
    @Override
    public byte tamanho() {
        
        byte capacidade = (byte) buffer.capacity();
        byte livre = (byte) buffer.remaining();
        
        m_tamanho = (byte)(capacidade - livre);
        
        
        return m_tamanho;   
    }

    private int limit( ByteBuffer buffer) {
       
       return buffer.limit();
    }

   
   
        
       
}
