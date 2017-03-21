/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package suporte;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author cachutti
 */
public class Relogio {
    Timer timer;
    Comunicador comunicador;
    Verificador verificador;
    
    
    public void executar(int seconds){
        //int canal = 1; // os canais possíveis são 1 e 2
        comunicador = Comunicador.instanciar();
        verificador = Verificador.instanciar();
        timer = new Timer();
        timer.scheduleAtFixedRate (new ObterRelogioPDC(), 500 ,seconds*1000);
        
    }
    
    
    
    class ObterRelogioPDC extends TimerTask{
       
        @Override
        public void run (){
            int relogioSWPDC, relogioPDC, diferenca;
            
            comunicador.emitirComando("OBTER RELOGIO");
            System.out.println("Verificando relógio ");
            
            relogioPDC = verificador.obterRelogio();
            relogioSWPDC = Integer.parseInt(getTime());
           
            diferenca = relogioPDC - relogioSWPDC;
            
            if ( diferenca > 30000 || diferenca < -30000) {
                comunicador.emitirComando("COMPENSAR DRIFT RELOGIO");
                System.out.println("Compensar drift - diferença superior a 30 segundos.  " + diferenca + "  milisegundos");
            } else {
                comunicador.guardarNoHistorico("Relógio PDC_simulador sincronizado com SWPDC.");
                System.out.println("Relógio PDC_simulador sincronizado com SWPDC.");
            } 
        }
    }
    
    
    private String getTime() {
        DateFormat formatoDataTime = new SimpleDateFormat("HHmmssS"); 
        Date date = new Date();
        return formatoDataTime.format(date);
    }
}
