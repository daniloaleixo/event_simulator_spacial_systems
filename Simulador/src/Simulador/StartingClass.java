package Simulador;

import java.io.BufferedReader;
import static Library.Escrever.*;
import static Library.Horario.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StartingClass
{
	enum Estados
	{
		DesligadoST, NominalST, DiagnosticoST, SegurancaST, IniciacaoST
	}

	static Estado nom = new Nominal(), ini = new Iniciacao();

	static String instrucao; // TODO teste, ao inves de array de instruçao leio
								// soh uma por vez

	static File aux = new File("."), dir;
	static File instrucoes; // entradas
	static File log;
	static File registroTemp, sysInfo, historico; // saidas

	static FileReader instrucoesReader;
	static BufferedReader instrucoesBuffer;
	static FileWriter logFileWriter;
	static FileWriter registroFileWriter, sysFileWriter, histFileWriter; // filewriter
																			// das
	// saidas
	static PrintWriter logPrintWriter;
	static PrintWriter registroPrintWriter, sysPrintWriter, histPrintWriter; // printwriter
																				// das
	// saidas

	static Estados estado = Estados.DesligadoST; // O estado inicial eh
													// desligado

	static ArrayList<String> instrucoesArray = new ArrayList<String>();

	static int numAmostra = 0;

	public static void main(String args[])
	{
		/*
		 * TODO nao sei o que usar na classe de iniciaçao e quando receber a
		 * isntruçao de iniciar sistema, se nao inicializar nada nao tenho como
		 * ler o arquivo de instruçoes e nem como imprimir o log de erros
		 */

		// Inicializamos o diretorio
		((Iniciacao) ini).inicializaDiretorio();

		// Inicializamos o log
		((Iniciacao) ini).inicializaLog();

		// Inicializamos o interpretador
		if (!((Iniciacao) ini).inicializaInterpretador()) {
			escrever(logPrintWriter, "Falha ao inicializar o interpretador!");
			System.exit(-1);
		}

		while (true) {
			try {
				if ((instrucao = instrucoesBuffer.readLine()) != null) {

					// COMECAR SIMULACAO
					if (instrucao.equals("COMEÇAR SIMULAÇAO")) {
						estado = Estados.IniciacaoST;
						boolean sucesso = ((Iniciacao) ini).inicializaSaidas();
						escrever(histPrintWriter, mostraHorario()
								+ " Emitiu comando: " + instrucao);
						if (sucesso) {
							escrever(sysPrintWriter, "[status] ligado");
							escrever(histPrintWriter, mostraHorario()
									+ " PDC inicializado com sucesso.");
							estado = Estados.NominalST;
						} else {
							escrever(histPrintWriter, mostraHorario()
									+ " PDC inicializado sem sucesso.");
							escrever(logPrintWriter, "PDC nao conseguiu sem inicializado");
						}

						// INFORMAR ESTADO
					} else if (instrucao.equals("INFORMAR ESTADO")) {
						escrever(histPrintWriter, mostraHorario()
								+ " Emitiu comando: " + instrucao);
						escrever(
								histPrintWriter,
								mostraHorario()
										+ " Verificação de estado do PDC realizada com sucesso - status: "
										+ estado);
						escrever(sysPrintWriter, "[modoOp] nominal");

						// OBTER TEMPERATURA
					} else if (instrucao.equals("OBTER TEMPERATURA")) {
						((Nominal) nom).geraTemperatura();

						// historico: instruçao
						escrever(histPrintWriter, mostraHorario()
								+ " Emitiu comando: " + instrucao);
						// historico: obtendo novas amostras
						escrever(histPrintWriter, mostraHorario()
								+ " Obtendo de novas amostras de temperatura");

						if (((Nominal) nom).obterAmostras()) {
							escrever(histPrintWriter, mostraHorario()
									+ " Amostras obtidas com sucesso");
						} else {
							escrever(
									histPrintWriter,
									mostraHorario()
											+ " Nao consegui obter as amostras de temperatura");
							escrever(logPrintWriter,
									"Falha ao obter as amostras de temperatura");
						}
						numAmostra++;
					}
				} else
					break;
			} catch (IOException e) {
				escrever(logPrintWriter, e.getMessage());
			}
		}

		//
		/*
		 * while (estado == Estados.IniciacaoST) { if (Iniciacao.inicializa())
		 * estado = Estados.NominalST; } System.out.println("Inicializado");
		 */

		/*
		 * while (true) { if (estado == Estados.NominalST) {
		 * sysPrintWriter.println("---------------"); nom.mostraHorario();
		 * nom.geraTemperatura(); nom.mostraEstado(); } }
		 */
	}
}
