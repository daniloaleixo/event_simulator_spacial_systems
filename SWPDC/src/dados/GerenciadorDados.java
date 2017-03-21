package dados;
import java.util.GregorianCalendar;
import suporte.Comunicador;

/**
 *
 * @author cachutti
 */

//Coordena a manipulação de dados dos buffers: científico, diagnóstico,
//housekeeping, carga de dados, descarga de dados e relato de eventos.
//singleton
public class GerenciadorDados implements IGerenciadorDados {

      //private BufferVirtual m_bufCco; //Buffer virtual para dados científicos.
      //private BufferVirtual m_bufCga; //Buffer virtual para carga de dados/programas.
      //private BufferVirtual m_bufDge; //Buffer virtual para dados de diagnóstico.
      //private BufferVirtual m_bufDmp; // Buffer virtual para descarga de dados - dump.
        private BufferSimples m_bufCco; //Buffer simples para armazenamento de temperaturas.
        private BufferSimples m_bufEvt; //Buffer simples (memória não paginada) para relato de eventos.
      //private BufferVirtual m_bufHke; //Buffer virtual para dados de housekeeping.
      //private BufferVirtual m_bufTstHX1; //Buffer virtual para ados de teste da EPP HX1.
      //private BufferVirtual m_bufTstHX2; //Buffer virtual para dados de teste da EPP HX2.
      //private MemoriaVirtual m_memCco; //Região de memória virtual para dados científicos.
      //private MemoriaVirtual m_memCga; //Região de memória virtual para carga de dados/programa.
      //private MemoriaVirtual m_memDge; //Região de memória virtual para dados de diagnóstico.
      //private MemoriaVirtual m_memDmp; //Região de memória virtual para descarga de dados - dump.
      //private MemoriaVirtual m_memHke; //Região de memória virtual para dados de housekeeping.
      //private MemoriaVirtual m_memRelEvt; //Área de memória para os relatos de eventos.
      //private MemoriaVirtual m_memTstHX1; //Região de memória virtual para dados de teste da EPP HX1.
      //private MemoriaVirtual m_memTstHX2; //Região de memória virtual para dados de teste da EPP HX2.
      private static GerenciadorDados instancia = null;
    
     private GerenciadorDados (){
         
     }
    
     public static GerenciadorDados instanciar(){
        if (instancia == null) {
            instancia = new GerenciadorDados();
        }
      return instancia;
    }

    @Override
    public void altBufDmp(double endInicial, double endFinal) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void altEnderecoInicialDmp(double endInicial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void altSelecaoMemDmp(int mem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void iniciar() {
        byte capacidade= (byte)1024;
        m_bufEvt = new BufferSimples( this , capacidade);
      
    }

    @Override
    public void iniciarCga(double endereco) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void iniciarDgeHx2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void iniciarDmp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void iniciarTst() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double obtEnderecoInicialDmp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int obtSelecaoMemDmp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double obterCSR(int subtipo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int obterUltimoIdHx() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double adicionar(TipoBufferEnum destino, int dado, byte tamanho) {
        byte tamanho_evento_adicionado; 
        switch (destino) {
            case tbCientifico:
                break;
            case tbDiagnose:
                break;
            case tbTeste:
                break;
            case tbHousekeeping: case tbTemperatura:
                break;
            case tbRelatoEventos:
               tamanho_evento_adicionado = m_bufEvt.inserir(dado, tamanho);
               return tamanho_evento_adicionado;
            default:
                break;
        }
         
        return 0;
    }

    @Override
    public void relatarEvento(TipoRelatoEventoEnum tipo, int[] info) {
        RelatoEvento relatoEvento = new RelatoEvento();
        relatoEvento.idTipo = tipo;
        relatoEvento.info = info;
        
        
    }

public int getHora() {  
  
// cria um StringBuilder  
StringBuilder sb = new StringBuilder();  
  
// cria um GregorianCalendar que vai conter a hora atual  
GregorianCalendar d = new GregorianCalendar();  
  
// anexa do StringBuilder os dados da hora  
sb.append( d.get( GregorianCalendar.HOUR_OF_DAY ) );  
sb.append( d.get( GregorianCalendar.MINUTE ) );  
sb.append( d.get( GregorianCalendar.SECOND ) );  

String hora = sb.toString();
int horaInt = Integer.parseInt(hora);

 return horaInt;
    }

@Override
    public double remover(TipoBufferEnum local, int dado, byte tamanho) {
       byte tamanho_evento_removido; 
        switch (local) {
            case tbCientifico:
                break;
            case tbDiagnose:
                break;
            case tbTeste:
                break;
            case tbHousekeeping: case tbTemperatura:
                break;
            case tbRelatoEventos:
               tamanho_evento_removido = m_bufEvt.inserir(dado, tamanho);
               return tamanho_evento_removido;
            default:
                break;
        }
         
        return 0;
    }

    
}
