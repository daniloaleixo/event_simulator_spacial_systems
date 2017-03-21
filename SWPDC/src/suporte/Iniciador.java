package suporte;

/**
 *
 * @author cachutti
 */

public class Iniciador implements IIniciador{

   /*indicam sucessos e fracassos */
   public int m_relPOST = 1;
   Comunicador comunicador; 
   Verificador verificador;
  
    @Override
    public void iniciar() {
       
        verificador = Verificador.instanciar();
        comunicador = Comunicador.instanciar();
        
        comunicador.emitirComando("COMEÇAR SIMULAÇAO");
        
        while (!verificador.verificaStatusAlimentacaoLigado()){
            comunicador.guardarNoHistorico("Aguardando PDC ligar.");
            m_relPOST = 0;
        }
        
        comunicador.guardarNoHistorico("PDC inicializado com sucesso.");
        m_relPOST = 1;
    }

    @Override
    public int obterEstadoPDC() {  
        String estado;
        
        comunicador.emitirComando("INFORMAR ESTADO");
        estado = verificador.verificaEstado();
        comunicador.guardarNoHistorico("Verificação de estado do PDC realizada com sucesso - status: " + estado );
        
        System.out.println("Estado PDC_Simulador: " + estado);
        
        if(estado.equals("nominal")) {
            return 1;
        }
        return 0;
    }

    @Override
    public int ativarModuloDados() {
        //iniciar gerenciamento de dados
        
        return 1;
    }
    
    
 

}
