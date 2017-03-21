package dados;

/**
 *
 * @author cachutti
 */

//Representação de um relato de evento. Atributos da classe RelatoEvento:
public class RelatoEvento {

    public TipoRelatoEventoEnum idTipo; //Indicador do tipo do relato de evento.
    public int[] info; //Informação associada ao relato de evento.
    public int timestamp; //Tempo do sistema quando da geração do relato de evento    

}
