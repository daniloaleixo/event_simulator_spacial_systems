package Library;

import java.io.PrintWriter;

public class Escrever
{
	public static void escrever(PrintWriter pw, String texto)
	{
		pw.print(texto + "\n");
		pw.flush();
	}

}
