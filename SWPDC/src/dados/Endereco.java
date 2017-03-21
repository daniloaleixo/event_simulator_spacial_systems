package dados;

/**
 *
 * @author cachutti
 */
//Representação de um endereço virtual em página e deslocamento dentro da página.
public class Endereco {
    
    public int paginaLSB; //Byte menos significativo do endereço da página. 
                            //De acordo com o esquema de paginação utilizado 
                            //nessa versão do SWPDC, esse byte assumirá valores 
                            // de 0 a 7, indicando qual página deverá ser referenciada.
    public int paginaMSB; //Byte mais significativo do endereço da página.
                            //De acordo com o esquema de paginação utilizado 
                            //nessa versão do SWPDC, esse byte será sempre zero.
    public int valor; //Endereço dentro da página selecionada. 
                            //De acordo com o esquema de paginação utilizado, 
                            // o endereço poderá variar de 8000h à FFFFh.
}
