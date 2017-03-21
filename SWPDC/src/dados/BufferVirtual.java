package dados;

/**
 *
 * @author cachutti
 */

//Buffer de memória. Usado para manipular dados (bytes) em memória. 
//Um Buffer não sabe nada a respeito do conteúdo da memória que ele manipula.
//A estratégia de manipulação dados é do tipo buffer circular (ring-buffer).

public class BufferVirtual implements IBufferVirtual, IBuffer{
    
  private int apagavel; //1, se o buffer tem autonomia para apagar a si mesmo a cada overlap.
  private int flgPagErase; //1, se ocorreu erase na página corrente.
  private MemoriaVirtual m_buf; //Referência para uma região de memória virtual a ser manipulada pelo buffer.
  private EnderecoFisico m_ixrd; //Endereço virtual de referência para o indexador de escrita do buffer a partir do qual dados podem ser escritos.
  private EnderecoFisico m_ixwr; //Endereço virtual de referência para o indexador de escrita do buffer a partir do qual dados podem ser escritos.
  private double tamBloco; //Tamanho do Bloco a ser gravado.
    
    //Dado um endereço de memória externa (XDATA) da página corrente e um byte, 
    //grava o byte na memória flash (endereço dado).
    //Atenção: Antes de chamar este método, a página de dados já deve ter sido selecionada.
    //ender - Endereço virtual da memória flash a ser gravado.
    //dado - Byte a ser gravado (programado) na memória flash.
    //retorno - Retorna 0 (zero) se houve erro na operação de escrita do byte na
    //memória flash; ou 1 (um) se a operação foi bem sucedida.

    private int gravarFlash( /*EnderecoVirtual*/ int ender, int dado){
        return 1;
    }

    @Override
    public void iniciar(MemoriaVirtual memoria, double tamBloco, int apagavel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean estaVazio() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte inserir(float dado, byte tamanho) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte remover(float dado, byte tamanho) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte tamanho() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
