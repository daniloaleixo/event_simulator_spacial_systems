package dados;

/**
 *
 * @author cachutti
 */
public enum TipoRelatoEventoEnum {
    
    treBufDdoCco10,//indica que o buffer de dados científicos está 10% de sua capacidade preenchido
    treBufDdoCco90,//indica que o buffer de dados científicos está 90% de sua capacidade preenchido
    //treBufDdoDga10,//indica que o buffer de dados de diagnóstico está 10% de sua capacidade preenchido
    //treBufDdoDga90,//indica que o buffer de dados de diagnóstico está 90% de sua capacidade preenchido
    //treBufDdoDge10,//indica que o buffer de dados de descarga de memória está 10% de sua capacidade preenchido
    //treBufDdoDge90,//indica que o buffer de dados de descarga de memória está 90% de sua capacidade preenchido
    treBufDdoHke10,//indica que o buffer de dados de housekeeping está 10% de sua capacidade preenchido
    treBufDdoHke90,//indica que o buffer de dados de housekeeping está 90% de sua capacidade preenchido
    //treComEP_CKS,//erro de resposta do EPP: Valor de Checksum inválido
    //treComEP_DI,//erro de resposta do EPP: Delimitados de início inválido
    //treComEP_EvPack,//erro de resposta do EPP: Event Pack inválido
    //treComEP_IdD,//erro de resposta do EPP: Identificador de destino inválido
    //treComEP_IdO,//erro de resposta do EPP: Identificador de origem inválido
    //treComEP_Timeout,//erro de comando do EPP: Timeout
    treComOP_CKS,//erro de comando do OBDH (PDC): Valor de checksum inválido
    treComOP_CSC,//erro de comando do OBDH (PDC): CSC inválido (erro de sequenciamento de comandos)
    treComOP_Com,//erro de comando do OBDH (PDC): Comprimento inválido
    treComOP_DI, //erro de comando do OBDH (PDC): Delimitados de início inválido
    treComOP_Dados,//erro de comando do OBDH (PDC): campo de dados inválido
    treComOP_IO, //erro de comando do OBDH (PDC): Identificador do origem inválido 
    treComOP_Timeout,//erro de comando do OBDH (PDC): Timeout
    treComOP_Tipo,//erro de comando do OBDH (PDC): Tipo inválido
    //treEHXI2Off,
    //treEHXI2On,
    //treEHXIOff,
    //treEHXIOn,
    treEndCgaDifPtrCga,// valor de endereço de carga diferent
    treEndIniIguFinDga,// valor de endereço inicial igual ao endereço final de descarga de dados
    treEndIniMaiFinDga,// valor de endereço inicial maior do que endereço final de descarga de dados
    //treErrCG, // erro no circuito cão de guarda
    //treErrDup, //erro duplo na memória SRAM
    //treErrParModProComOBDH,
    treErrSim, //erro simples na memória SRAM
    //treErrSubAtuProComOBDH,
    //treErrTipProComOBDH,
    treLimMemPri,//Limpeza (preenchimento com 0) da memória principal
    //treManVarCfg,//Manipulação de variáveis de configuração POST
    treModOpePDC,//Indica Modo de operação do PDC
    //treOpeCGPOST,//Verificação correta operação do circuito de cão-de-guarda - POST
    trePtrIniCga,//valoe de enddereço inicial para carga de programa inválido
    trePwrON,//indica que o PDC foi ligado
    treRST,//indica que ocorreu resset no processador
    //treStaAlmEHXIPOST, //status de alimentação dos 2 conjuntos EPP-HXI-POST
    treStaAlmPDCPOST, //status de alimentacão PDC-POST
    treTtaFoaLim,// Indica que a temperatura do PDC está fora dos limites especificados
    treTtaPDCPOST, //Temperatura interna do PDC-POST
    //treTxDdoCfoHxOff,
    //treTxDdoHxIni,
    treVrfMemPrg //Verificcação da Memória do Programa
    //treVrfMemSRAM // Verificação da memória SRAM

}
