package dados;

/**
 *
 * @author cachutti
 */
public interface IGerenciadorDados {
    
    // Adiciona dados a um buffer específico, destino - Indicador do buffer de
    // destino, o qual receberá os dados, dado - Ponteiro para o início da área 
    // a ser copiada (inserida) no buffer, tamanho - A quantidade de bytes a ser
    // inserida no buffer. Retorno - A quantidade de bytes efetivamente inserida
    // no buffer.
    
    public double adicionar( TipoBufferEnum destino, int dado, byte tamanho);
        
    // Altera ponteiros do buffer de descarga de memória. 
    // endInicial - Endereço Inicial, endFinal - Endereço Final.
    
    public void altBufDmp(double endInicial, double endFinal);

    //Altera endereço inicial do buffer de descarga.
    //endInicial - Endereço Inicial.

    public void altEnderecoInicialDmp(double endInicial);

    //Altera seleção de memória que se pretende fazer dump.
    //mem - Indicador da memória a ser selecionada.

    public void altSelecaoMemDmp(int mem);
    
    //Efetua os procedimentos de iniciação do gerenciador de dados.

    public void iniciar(); 

    // Efetua os procedimentos de iniciação do buffer de carga.
    //endereco - Endereço inicial para carga.

    public void iniciarCga(double endereco);

    //Efetua os procedimentos de iniciação do buffer de diagnóstico da câmera HXI 2.

    public void iniciarDgeHx2();

    //Efetua os procedimentos de iniciação do buffer de descarga de memória.

    public void iniciarDmp();

    //Efetua os procedimentos de iniciação do buffer de teste.
    
    public void iniciarTst();
    
    //Obtém endereço inicial do buffer de descarga.
    // retorno - O valor do endereço inicial do buffer de descarga.
    
    public double obtEnderecoInicialDmp(); 

    //Obtém seleção de memória que se pretende fazer dump.
    //retorno - O valor do indicador da memória selecionada.    
    
    public int obtSelecaoMemDmp();

    //Obtém quantidade de pacotes ainda disponíveis.
    //subtipo - Subtipo dos dados de resposta.
    // retorno - O valor do CSR (Controle de Seqüência de Resposta) corrente.    
    
    public double obterCSR(int subtipo);
    
    // Retorna último indicador de pulling dos conjuntos EPP Hi - HXi (idHX).
    //retorno - Valor do indicador idHXi.
    
    public int obterUltimoIdHx();

    //Insere no buffer de relato de eventos um dado evento, juntamente com sua 
    //informação associada, tipo - Indicador do tipo do relato de evento. info -
    //Informação associada ao evento. Deve ser obrigatoriamente um vetor de 5 bytes.

    public void relatarEvento( TipoRelatoEventoEnum tipo, int[] info);

    //Remove dados de um buffer específico, local - Indicador do buffer de
    // origem dos dados a serem removidos, dado - Ponteiro para a área de 
    // memória de destino, o qual serão copiados os bytes removidos do buffer.
    // tamanho - Quantidade de bytes a serem removidos do buffer.
    //retorno - A quantidade de bytes efetivamente removida do buffer.
    
    public double remover( TipoBufferEnum local, int dado, byte tamanho);


}
