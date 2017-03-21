package dados;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import suporte.Iniciador;
/**
 *
 * @author cachutti
 */

//Tarefa responsável pela geração e formatação de dados de housekeeping.
public class Housekeeper {
    
    //Memória de trabalho para composição do pacote de housekeeping.
    private PacoteHK m_pacHke = new PacoteHK();
    String caminhoAbsolutoRelatorio = "/Users/cachutti/Desktop/IC/teste/relatorio.txt";
    
    //Ponto de entrada da tarefa.
    
    public void executar() throws IOException{
            
        prepararPacoteHK();
        formatarRelatorio();
        }
    

    //Executa a preparação do pacote de housekeeping, acessando as origens de 
    //dados para um housekeeping completo, gravando os dados no pacote de 
    //housekeeping interno (m_pacHke).
    
    
    private void prepararPacoteHK(){
        
        m_pacHke.amoTta [0] = 0;
        m_pacHke.amoTta [1] = 1;
        m_pacHke.amoTta [2] = 2;
        m_pacHke.amoTta [3] = 3;
        m_pacHke.amoTta [4] = 4;
        m_pacHke.amoTta [5] = 5;
        m_pacHke.amoTta [6] = 6;
        m_pacHke.amoTta [7] = 7;
        m_pacHke.amoTta [8] = 8;
        m_pacHke.amoTta [9] = 9;
        m_pacHke.amoTta [10] = 10;
        m_pacHke.amoTta [11] = 11;
 //Últimas 12 amostras de temperaturas
        m_pacHke.modOpe = "Nominal"; //Modo de operação corrente do SWPDC.
        m_pacHke.tpoAmoTta = 0; //Tempo de amostragem de temperaturas.
        m_pacHke.qteErrSim = 0; //Quantidade de erros simples ocorridos desde o último zeramento.
        m_pacHke.relEvt = TipoRelatoEventoEnum.treBufDdoCco10; //Últimos relatos de eventos ocorridos.
        // m_pacHke.ptrCga = 0; //Ponteiro de carga de programas.
       // m_pacHke.qteErrCG = 0; //Quantidade de erros de cão-de-guarda ocorridos desde o último zeramento.
       // m_pacHke.qteErrDup = 0; //Quantidade de erros duplos ocorridos desde o último zeramento.
       //m_pacHke.staAlmEHX1 = 0; //Status da alimentação do conjunto EPP-HX1.
       //m_pacHke.staAlmEHX2 = 0; //Status da alimentação do conjunto EPP-HX2.
       // m_pacHke.tamanho = 0; //Tamanho dos dados de housekeeping.
       // m_pacHke.tpoHke = 0; //Tempo de housekeeping em segundos.
    }
    
    private void formatarRelatorio () throws IOException{
        try{  
           
            File arquivo = new File( caminhoAbsolutoRelatorio );
            arquivo.createNewFile();
            FileWriter arquivoW = new FileWriter (arquivo); 
            PrintWriter printW = new PrintWriter (arquivoW);  
            printW.println("Relatorio PacoteHK");
            printW.println();
            printW.println("[data] " + getData() + "      [hora] " + getTime());
            printW.println("[modoOpSWPDC] " + m_pacHke.modOpe);
            printW.println();
            printW.println("Amostras de temperaturas");
            printW.println(m_pacHke.amoTta[0]);
            printW.println(m_pacHke.amoTta[1]);
            printW.println(m_pacHke.amoTta[2]);
            printW.println(m_pacHke.amoTta[3]);
            printW.println(m_pacHke.amoTta[4]);
            printW.println(m_pacHke.amoTta[5]);
            printW.println(m_pacHke.amoTta[6]);
            printW.println(m_pacHke.amoTta[7]);
            printW.println(m_pacHke.amoTta[8]);
            printW.println(m_pacHke.amoTta[9]);
            printW.println(m_pacHke.amoTta[10]);
            printW.println(m_pacHke.amoTta[11]);
            printW.println(m_pacHke.qteErrSim + " Erros");
            printW.println("Tempo de amostragem: " + m_pacHke.tpoAmoTta );
            printW.println();
            printW.println("Historico eventos");
            printW.println(m_pacHke.relEvt);
            printW.flush();
            printW.close();
        } catch (IOException e) {
            Logger.getLogger(Iniciador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private String getData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
