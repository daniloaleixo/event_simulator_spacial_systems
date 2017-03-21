package Simulador;

import java.util.Random;
import static Library.Escrever.*;

public class Nominal implements Estado, Runnable
{
	static Random rand = new Random();

	private static float[] temperaturas = new float[52];
	private static int tempForaIntervalo = 0;

	/*
	 * O metodo metodo gera um numero de 0 a 39 para ser o indice do array onde
	 * se colhera as 12 amostras de temperatura. O metodo retorna true se a
	 * operaçao occorrer com sucesso
	 */
	boolean obterAmostras()
	{
		try {
			int i = rand.nextInt(40);
			int fim = i + 12;
			String temps = "";
			for (; i < fim; i++)
				temps += String.format("%.1f, ", temperaturas[i]);

			escrever(StartingClass.registroPrintWriter, "[numAm] "
					+ StartingClass.numAmostra + " " + "[dados] " + " " + temps);
		} catch (Exception e) {
			escrever(StartingClass.logPrintWriter, e.getMessage());
			return false;
		}

		return true;
	}

	/*
	 * O metodo gera 50 temperaturas para preencher o array de temperaturas,
	 * sendo que a cada 20 uma temperatura esta fora do intervalo (10 a 40 ºC) e
	 * a cada 3 temperaturas geradas uma eh negativa
	 */
	void geraTemperatura()
	{
		for (int i = 0; i < temperaturas.length; i++) {
			if (tempForaIntervalo % 20 != 0) // a cada 20 interacoes existe uma
			// temperatura fora do intervalo
			{
				temperaturas[i] = 30 * rand.nextFloat() + 10;
			} else {
				temperaturas[i] = 200 * rand.nextFloat();
			}

			/*if (tempForaIntervalo % 3 == 0)
				temperaturas[i] = -1 * temperaturas[i];*/

			tempForaIntervalo++;
		}
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub

	}

}
