package Simulador;

import static Library.Escrever.escrever;
import static Library.Horario.mostraHorario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Iniciacao implements Estado
{

	/*
	 * O metodo inicializa o sistema, pegando o diretorio utilizado pelo projeto
	 * e usamos ele como diretorio para nosso sistema. Retornamos true caso a
	 * inicializacao foi um sucesso
	 */
	boolean inicializaDiretorio()
	{
		// Pegamos o diretorio onde esta localizado o projeto em java
		try {
			StartingClass.dir = new File(System.getProperty("user.dir"));
			System.out.println(">>>Diretorio existe:"
					+ StartingClass.dir.exists()); // Depuraçao
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

		return true;
	}

	boolean inicializaLog()
	{
		try {

			// Criamos o arquivo log.txt
			StartingClass.log = new File(StartingClass.dir, "log.txt");
			if (!StartingClass.log.exists())
				StartingClass.log.createNewFile();

			// Inicializamos o FileWriter e o PrintWriter do log
			StartingClass.logFileWriter = new FileWriter(StartingClass.log,
					true);
			StartingClass.logPrintWriter = new PrintWriter(
					StartingClass.logFileWriter);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/*
	 * O metodo cria os FileWriters e PrintWriters dos arquivos de saida:
	 * registroTemp, historico e sysInfo. Retorna true caso a operacao seja bem
	 * sucedida
	 */
	boolean inicializaSaidas()
	{
		// Criamos os arquivos fisicamente, seus filewriters e printwriters
		try {
			// Criamos o arquivo registroTemp.txt
			StartingClass.registroTemp = new File(StartingClass.dir,
					"registroTemp.txt");
			if (!StartingClass.registroTemp.exists())
				StartingClass.registroTemp.createNewFile();

			// Inicializamos o FileWriter e o PrintWriter do registoTemp
			StartingClass.registroFileWriter = new FileWriter(
					StartingClass.registroTemp, false);
			StartingClass.registroPrintWriter = new PrintWriter(
					StartingClass.registroFileWriter);

			// -----------------

			// Criamos o arquivo sysInfo.txt
			StartingClass.sysInfo = new File(StartingClass.dir, "sysInfo.txt");
			if (!StartingClass.sysInfo.exists())
				StartingClass.sysInfo.createNewFile();

			// Inicializamos o FileWriter e o PrintWriter do sysInfo
			StartingClass.sysFileWriter = new FileWriter(StartingClass.sysInfo,
					false);
			StartingClass.sysPrintWriter = new PrintWriter(
					StartingClass.sysFileWriter);

			// -----------------

			// Criamos o arquivo historico.txt
			StartingClass.historico = new File(StartingClass.dir,
					"historico.txt");
			if (!StartingClass.historico.exists())
				StartingClass.historico.createNewFile();

			// Inicializamos o FileWriter e o PrintWriter do historico
			StartingClass.histFileWriter = new FileWriter(
					StartingClass.historico, true);
			StartingClass.histPrintWriter = new PrintWriter(
					StartingClass.histFileWriter);

		} catch (Exception e) {
			escrever(StartingClass.logPrintWriter, e.getMessage());
			return false;
		}

		return true;

	}

	/*
	 * A funçao abre os arquivos de entrada instrucoes.txt e deixa o programa
	 * pronta pra ler as instrucoes Retorna true se a operaçao ocorrer com
	 * sucesso
	 */
	boolean inicializaInterpretador()
	{
		// Pegamos o arquivo instruçoes.txt que esta presente na pasta do
		// projeto
		try {
			StartingClass.instrucoes = new File(StartingClass.dir,
					"instrucoes.txt");
			if (!StartingClass.instrucoes.exists()) {
				escrever(StartingClass.histPrintWriter, mostraHorario()
						+ " Arquivo de instrução nao encontrado!");
				return false;
			}
		} catch (Exception e) {
			escrever(StartingClass.logPrintWriter, e.getMessage());
			return false;
		}

		// Inicializamos o FileReader e o BufferedReader
		try {
			StartingClass.instrucoesReader = new FileReader(
					StartingClass.instrucoes);
			StartingClass.instrucoesBuffer = new BufferedReader(
					StartingClass.instrucoesReader);

			/*
			 * while ((linha = instrucoesBuffer.readLine()) != null) {
			 * instrucoesArray.add(linha); }
			 */

		} catch (Exception e) {
			escrever(StartingClass.logPrintWriter, e.getMessage());
			return false;
		}
		return true;
	}
}
