package Library;

public class Horario
{
	// Mostra o horario atual com precisao de milisegundos
	public static  String mostraHorario()
	{
		try {
			//Coloca a data no formato desejado
			java.util.Date data = new java.util.Date();
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
			
			return formater.format ( data ); 
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return null;
		
	}

}
