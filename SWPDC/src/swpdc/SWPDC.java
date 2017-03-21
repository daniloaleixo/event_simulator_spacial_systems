package swpdc; 

import dados.GerenciadorDados;
import dados.Housekeeper;
import dados.Temperatura;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import suporte.Iniciador;
import suporte.Relogio;

/**
 *
 * @author cachutti
 */
public class SWPDC {

     private static GerenciadorDados gerenciadorDados;
     private static int estadoPDC;
     Iniciador iniciador;
     Housekeeper hk;
     static Relogio relogio; 
     static Temperatura temperatura;
    public static void main(String[] args) throws IOException {
        /* A Inicialização foi dividida em 2 fases.A primeira é a POST, que 
         * realiza a verificação de hardware -> excluido e a segunda é a que ocorre depois
         * e é o relacionada mais oa software */
        Iniciador iniciador = new Iniciador();
        Housekeeper hk = new Housekeeper();
        
        iniciador.iniciar();
        if (iniciador.m_relPOST == 1){
           estadoPDC = iniciador.obterEstadoPDC();
        }
        
        if (estadoPDC == 1){
            iniciador.ativarModuloDados();
        }
        //hk.executar();\
        
        temperatura = new Temperatura();
        temperatura.executar(10);
       
        relogio = new Relogio();
        relogio.executar(60);
        
        //iniciarTarefas();
    }
    
    /**
     * Iniciação das tarefas de regime permanente (classes ativas).
     */
    public static void iniciarTarefas(){
       
        gerenciadorDados = GerenciadorDados.instanciar();
        gerenciadorDados.iniciar();
        
        //while (true){
            
            
        
        
        
        
        //}
    }
}
