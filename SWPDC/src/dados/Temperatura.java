package dados;

import java.util.Timer;
import java.util.TimerTask;
import suporte.Comunicador;
import suporte.Verificador;

/**
 *
 * @author cachutti
 */
//Tarefa responsável pela medição das temperaturas nos pontos 1 e 2(portas ADC_C1 
// e ADC_C2 - Canais analógicos para aquisição de temperaturas.).
public class Temperatura {
    
    private BufferSimples m_bufTta; //Referência para o Gerenciador de Dados.
    private float[] m_buffer; //Últimas 12 amostras de cada ponto de leitura de temperatura.
    private int m_ixbuf; //Indexador da próxima posição livre do buffer (circular) de temperaturas.
    Timer timer;
    Comunicador comunicador;
    Verificador verificador;
    int amostraAtual = 1;
    //Ponto de entrada da tarefa.
    
    public void executar(int seconds){
        //int canal = 1; // os canais possíveis são 1 e 2
        m_buffer = new float[12];
        m_bufTta = new BufferSimples(this, (byte)10000);
        comunicador = Comunicador.instanciar();
        verificador = Verificador.instanciar();
        timer = new Timer();
        timer.scheduleAtFixedRate (new ObterAmostras(), 500 ,seconds*1000);
        
    }

    //Inicia canal ADC.
    //canal - Canal ADC usado para ler a temperatura (ponto para amostrar
    //digitalmente a tensão aplicada no canal).
    //private void iniciarCanal(int canal){
        
    //}
    
   //Lê a temperatura. 
   //canal - Canal ADC usado para ler a temperatura (ponto para amostrar
   //digitalmente a tensão aplicada no canal).
   //retorno - Valor digital correspondente a tensão lida do conversor AD no canal especificado.
   //private int lerTemperatura(int canal){
     //  return 1;
   //}
   
   //Obtém as últimas amostras de temperaturas, formatadas em 10 bits por amostra.
   //buf - Ponteiro para um vetor de tamanho fixo de 150 bytes, para comportas as 
   // últimas amostras 160 de temperaturas, em 10 bits por amostra.
    
    //-1 é colocado no buffer para mostrar que aquela amostra está fora do intervalo admitido
    class ObterAmostras extends TimerTask{
       
        @Override
        public void run (){
           
            comunicador.emitirComando("OBTER TEMPERATURA");
            System.out.println("Novas amostras de temperatura");
            comunicador.guardarNoHistorico("Obtenção das novas amostras de temperatura");
            System.out.println(amostraAtual);
            m_buffer = verificador.obterAmostras(amostraAtual);
            int todasAmostrasCorretas = 0;
            if (m_buffer != null) {
                for (int i = 0; i < 12; i++){
                    if (temperaturaPertenceAoIntevalo (m_buffer[i]) ) {
                        m_bufTta.inserir(m_buffer[i], (byte)10);
                    } else{
                          m_bufTta.inserir((float)-1.0, (byte)10);
                          todasAmostrasCorretas++;
                    } 
                }
                if (todasAmostrasCorretas == 0){
                    comunicador.guardarNoHistorico("Correto armazenamento da amostra " + amostraAtual + " no buffer.");
                } else {
                    System.out.println("Temperatura fora do intervalo detectada na amostra " + amostraAtual + ".");
                    comunicador.guardarNoHistorico("Foram detectados " + todasAmostrasCorretas + " dados errados durante o armazenamento da amostra " + amostraAtual );
                }
            }else{
                comunicador.guardarNoHistorico("Aguardando amostra...");
            }
            
            amostraAtual++;
        }

        private boolean temperaturaPertenceAoIntevalo(float temperatura) {
            if(temperatura >= 10.0 && temperatura <= 40.0) {
                return true;
            }
               return false;
        
        }
   }
}
