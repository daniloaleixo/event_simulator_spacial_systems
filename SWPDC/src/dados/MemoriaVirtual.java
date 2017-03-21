package dados;

/**
 *
 * @author cachutti
 */
//Representação de um fragmento (ou região) de memória virtual paginada - memória
//flash. Contém os endereços inicial e final da região. Uma região de memória 
//virtual é manipulada por um BufferVirtual.
 public class MemoriaVirtual {
     
     public EnderecoFisico endFinal; //O endereço final da região de memória virtual.
     public EnderecoFisico endInicial; //O endereço inicial da região de memória virtual.
    
}
