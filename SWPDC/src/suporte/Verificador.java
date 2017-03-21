package suporte;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cachutti
 */

public class Verificador {
    FileReader arquivoSysInfo = null, arquivoRegistroTemp = null;
    BufferedReader bufReaderSysInfo = null, bufReaderRegistroTemp = null;
    String caminhoAbsoluto = "~/Dropbox/Computer\\ Science/Java/workspace/Simulador/";
    Comunicador comunicador; 
    private static Verificador instancia = null;
           
    private Verificador (){
        try {
            arquivoSysInfo = new FileReader(caminhoAbsoluto + "sysInfo.txt");
            bufReaderSysInfo = new BufferedReader(arquivoSysInfo);
            
            arquivoRegistroTemp = new FileReader(caminhoAbsoluto + "registroTemp.txt");
            bufReaderRegistroTemp = new BufferedReader (arquivoRegistroTemp);
            
            comunicador = Comunicador.instanciar();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Verificador instanciar(){
        if (instancia == null) {
            instancia = new Verificador();
        }
      return instancia;
    }
    
    public boolean verificaStatusAlimentacaoLigado (){
        String linha;
        
        try {    
            linha = bufReaderSysInfo.readLine();
            
            if (!linha.equals("[status] ligado")) {
                System.out.println("Aguardando PDC ligar...");
                return false;    
            }
        } catch (IOException ex) {
            Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public String verificaEstado (){
            String linha;
        
            try {
            linha = bufReaderSysInfo.readLine();
        
            //while (linha !=null){ 
                if (!linha.contains("[modoOp]") || linha == null) {
                    //linha = bufReaderSysInfo.readLine();
                    comunicador.guardarNoHistorico("Problema com o o arquivo de sistema do PDC");
                    return null;
                } else {
                    return linha.substring(9);
                    //linhaAnterior = linha;
                    //linha = bufReaderSysInfo.readLine();
                }
            //}
            
        } catch (IOException ex) {
            Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // TODO: caso onde munAmostrasAtual tem 2 dígitos
    public float[] obterAmostras (int numAmostraAtual){
        float[] amostras = null;

        try {
            amostras = new float[13];
            String linha = bufReaderRegistroTemp.readLine();

            System.out.println(linha);

            if (linha == null || (linha.contains("[numAm]") && !EhAmostraAtual(numAmostraAtual, linha)) ) {
                 System.out.println("Arquivo de registro de amostras desatualizado!");
                 comunicador.guardarNoHistorico("Arquivo de amostras desatualizado");
                 return null;
            } 

            if (!linha.contains("[numAm]") && linha != null) {
                 System.out.println("Arquivo de registro de amostras errado!");
                 comunicador.guardarNoHistorico("Problema no arquivo de amostras");
                 return null;
            }

            if (linha.contains("[numAm]") && EhAmostraAtual(numAmostraAtual, linha) ) {
                 System.out.println("LinhaAnterior" + linha);
                 amostras = organizarAmostrar (linha);   
            }
        } catch (IOException ex) {
            Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Amostras obtidas com sucesso");
        comunicador.guardarNoHistorico("Amostras obtidas com sucesso");
        
        return amostras;
    }

    private float[] organizarAmostrar(String amostras) {
            float[] temperaturas;
            String valores;
            
            temperaturas = new float[12];
            valores = amostras.substring(18);
          
            for(int i = 0, j = 0; i < 12; i++, j +=6){
                temperaturas[i] = Float.parseFloat(valores.substring(j, j+4 ));
                System.out.println(temperaturas[i]);
            }
            
            return temperaturas;
        }

    private boolean EhAmostraAtual(int numAmostraAtual, String linha) {
        float numAmostra;
        
        numAmostra = Float.parseFloat(linha.substring(8, 9));
        
        if (numAmostra == numAmostraAtual){
            return true;
        } else{
            return false;
        }           
    }
    
    public int obterRelogio (){
        int relogio = 0;
        String linha;
        try {
            linha = bufReaderSysInfo.readLine();
            
              if (!linha.contains("[relogio]") || linha == null){
                    comunicador.guardarNoHistorico("Problema com o o arquivo de sistema do PDC - relógio não foi atualizado");
               } else {
                    relogio = Integer.parseInt(linha.substring(10));
              }
            
        } catch (IOException ex) {
            Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return relogio;
    }

}
