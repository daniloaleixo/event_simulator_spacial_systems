package suporte;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cachutti
 */

public class Comunicador {
   
    String caminhoAbsoluto = "~/Dropbox/Computer\\ Science/Java/workspace/Simulador/";
    FileWriter arquivoHistW = null;
    FileWriter arquivoInstW = null;
    BufferedWriter bufHistW = null;
    BufferedWriter bufInstW = null;
    private static Comunicador instancia;
    // tipoArquivo = 1 -> historico
    // tipoArquivo = 2 -> instrução, sysInfo, registroTemp
    // os dois últimos estarão lá sempre (em teoria) só precisamos ver se os dados que estão lá já são os atualizados.
    private Comunicador (){
        try {
            File historico = criaArquivo("historicoSWPDC.txt");
            File instrucoes = criaArquivo("instrucoes.txt" );
            arquivoHistW = new FileWriter (historico, true);
            bufHistW = new BufferedWriter (arquivoHistW);

            arquivoInstW = new FileWriter (instrucoes, true);
            bufInstW = new BufferedWriter (arquivoInstW);
        } catch (IOException ex) {
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
        
    public static Comunicador instanciar(){
        if (instancia == null) {
            instancia = new Comunicador();
        }
      return instancia;
    }
    
    private File criaArquivo (String nomeArquivo){
       
        File arquivo = new File(  caminhoAbsoluto + nomeArquivo );
        if (arquivo.exists()){
           arquivo.delete();
        }
        try {
            arquivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arquivo;
    }

    
    //TODO: Usar comandosEnum
    public void emitirComando (String comando){
        
         try {
            bufInstW.append(comando);
            bufInstW.newLine();
            bufInstW.flush();
            guardarNoHistorico(getDataTime() + " emitiu comando: " + comando);
             } catch (IOException ex) {
             Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
       
     public void fimDaEmissaoDeComandos (){
       
         try {
            bufInstW.close();
        } catch (IOException ex) {
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
   public void guardarNoHistorico ( String relatoEvento ){
         try {
            bufHistW.append(getDataTime() + " " + relatoEvento);
            bufHistW.newLine();
            bufHistW.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void fecharHistorico (){
        try {
            bufHistW.close();
        } catch (IOException ex) {
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   private String getDataTime() {
        DateFormat formatoDataTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS"); 
        Date date = new Date();
        return formatoDataTime.format(date);
    }
         
}
            
         