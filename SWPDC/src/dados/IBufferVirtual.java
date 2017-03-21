package dados;

/**
 *
 * @author cachutti
 */
public interface IBufferVirtual {
    
// Inicia o buffer virtual com um conjunto de páginas identificadas pela região
//de memória indicada por MemoriaVirtual*, memoria - Região (segmento de página,
// página, setores) de memória virtual que será alocada para o buffer, tamBloco 
// - Tamanho do bloco a ser gravado, apagavel - 1 (TRUE), se o buffer tem 
// autonomia para apagar a si mesmo a cada overlap; ou 0 (FALSE) caso contrário. o retorno - Nenhum.

public void iniciar( MemoriaVirtual memoria, double tamBloco, int apagavel);

}
