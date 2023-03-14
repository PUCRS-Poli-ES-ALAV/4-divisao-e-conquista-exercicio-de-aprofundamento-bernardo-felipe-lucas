package br.pucrs;

import java.util.Locale;

import br.pucrs.maxvals.MaxVals;
import br.pucrs.multipow.MultiPower;
import br.pucrs.sorts.Sorts;
import br.pucrs.util.ContagemRes;

public class Demo {

	private static int ELEMENTOS = 100_000;
	private static int ITERACOES = 5;
	private static int QTD_TESTS_MULT = 3;

	public static void exibe(long[] v, long limite){
		for (int i = 1; i <= v.length; i++)
			if (i % limite == 0)
				System.out.println(v[i-1] + " ");
			else
				System.out.print(v[i-1] + " ");
		System.out.println("");
	}

	public static void exibe(double[] v, int limite){
		for (int i = 1; i <= v.length; i++)
			if (i % limite == 0)
				System.out.println(v[i-1] + " ");
			else
				System.out.print(v[i-1] + " ");

		System.out.println("\n");
	}

	public static void iteraTestes(int SORT_LIM, int TAM_VETOR, int QTDE_TESTS_MULT) {

		Sorts srt = new Sorts();
		MaxVals mxVal = new MaxVals();
		MultiPower mul = new MultiPower();
		long [] vet, vetAux;

		long [] resIterMerge = new long[SORT_LIM];
		long [] resIterBubble0 = new long[SORT_LIM];
		long [] resIterMaxValRep = new long[SORT_LIM];
		long [] resIterMaxValDiv = new long[SORT_LIM];
		long [] resIterMultiply = new long[QTDE_TESTS_MULT];


		long [] resInstrMerge = new long[SORT_LIM];
		long [] resInstrBubble0 = new long[SORT_LIM];
		long [] resInstrMaxValRep = new long[SORT_LIM];
		long [] resInstrMaxValDiv = new long[SORT_LIM];
		long [] resInstrMultiply = new long[QTDE_TESTS_MULT];

		double [] resClockMerge = new double[SORT_LIM];
		double [] resClockBubble0 = new double[SORT_LIM];
		double [] resClockMaxValRep = new double[SORT_LIM];
		double [] resClockMaxValDiv = new double[SORT_LIM];
		double [] resClockMultiply = new double[QTDE_TESTS_MULT];

		long [] resResultsMultiply = new long[QTDE_TESTS_MULT];

		ContagemRes sResMerge, sResBubble0, sResMaxValRep, sResMaxValDiv, sResMultiply;
		int pos;

		System.out.println("Execuções sobre Vetores Iniciando!");
		for (pos = 0; pos < SORT_LIM; pos ++) {
			System.out.print("#");
			vet = srt.geraVetor(TAM_VETOR, TAM_VETOR / 2);
			vetAux = srt.geraVetorInv(TAM_VETOR);

			sResMerge = srt.mergeSort(vet);
			System.out.print(".");
			sResBubble0 = srt.bubbleSort0(vet);
			System.out.print(".");
			sResMaxValRep = mxVal.maxValRep(vet, TAM_VETOR);
			System.out.print(".");
			sResMaxValDiv = mxVal.maxValDiv(vet, TAM_VETOR);
			System.out.print(".");

			resIterMerge[pos] = sResMerge.getIteracoes();
			resIterBubble0[pos] = sResBubble0.getIteracoes();
			resIterMaxValRep[pos] = sResMaxValRep.getIteracoes();
			resIterMaxValDiv[pos] = sResMaxValDiv.getIteracoes();

			resInstrMerge[pos] = sResMerge.getInstrucoes();
			resInstrBubble0[pos] = sResBubble0.getInstrucoes();
			resInstrMaxValRep[pos] = sResMaxValRep.getInstrucoes();
			resInstrMaxValDiv[pos] = sResMaxValDiv.getInstrucoes();

			resClockMerge[pos] = sResMerge.getTime();
			resClockBubble0[pos] = sResBubble0.getTime();
			resClockMaxValRep[pos] = sResMaxValRep.getTime();
			resClockMaxValDiv[pos] = sResMaxValDiv.getTime();

		}
				
		System.out.println("\nExecuções sobre Vetores Feitas!");

		System.out.println("\nBubble Sort - Dois laços fixos");
		System.out.println("Nro iter pela classe complexidade - n ^ 2: " +
				TAM_VETOR * TAM_VETOR);
		System.out.println("Nro iteracoes:");
		exibe(resIterBubble0, 20);
		System.out.println("Nro instrucoes:");
		exibe(resInstrMerge, 20);
		System.out.println("Tempo em segundos:");
		exibe(resClockBubble0, 20);

		System.out.println("\nMerge Sort");
		System.out.println("Nro iter pela classe complexidade - Melhor Caso - n log n: " +
				TAM_VETOR * (Math.log10(TAM_VETOR)/Math.log10(2)) +
				"\nNro iter pela classe complexidade - Pior Caso - n log n: " +
				TAM_VETOR * (Math.log10(TAM_VETOR)/Math.log10(2)));
		System.out.println("Nro iteracoes:");
		exibe(resIterMerge, 20);
		System.out.println("Nro instrucoes:");
		exibe(resInstrMerge, 20);
		System.out.println("Tempo em segundos:");
		exibe(resClockMerge, 20);

		System.out.println("\nMax Val com repetições");
		System.out.println("Nro iter pela classe complexidade - Melhor Caso - n: " +
				TAM_VETOR + 
				"\nNro iter pela classe complexidade - Pior Caso - n: " +
				TAM_VETOR);
		System.out.println("Nro iteracoes:");
		exibe(resIterMaxValRep, 20);
		System.out.println("Nro instrucoes:");
		exibe(resInstrMaxValRep, 20);
		System.out.println("Tempo em segundos:");
		exibe(resClockMaxValRep, 20);

		System.out.println("\nMax Val com Divisão e Conquista");
		System.out.println("Nro iter pela classe complexidade - Melhor Caso - n: " +
				TAM_VETOR + 
				"\nNro iter pela classe complexidade - Pior Caso - n: " +
				TAM_VETOR);
		System.out.println("Nro iteracoes:");
		exibe(resIterMaxValDiv, 20);
		System.out.println("Nro instrucoes:");
		exibe(resInstrMaxValDiv, 20);
		System.out.println("Tempo em segundos:");
		exibe(resClockMaxValDiv, 20);

		// Iniciando os testes sem vetores
				
		System.out.println("\nExecuções sobre Escalares Iniciando!");

		sResMultiply = mul.multiply(3, 4, 4);
		System.out.print(".");
		resIterMultiply[0] = sResMultiply.getIteracoes();
		resInstrMultiply[0] = sResMultiply.getInstrucoes();
		resClockMultiply[0] = sResMultiply.getTime();
		resResultsMultiply[0] = sResMultiply.getResult()[0];
		
		sResMultiply = mul.multiply(127, 100, 8);
		System.out.print(".");
		resIterMultiply[1] = sResMultiply.getIteracoes();
		resInstrMultiply[1] = sResMultiply.getInstrucoes();
		resClockMultiply[1] = sResMultiply.getTime();
		resResultsMultiply[1] = sResMultiply.getResult()[0];
		
		sResMultiply = mul.multiply(12_345_678, 10_000_000, 64);
		System.out.print(".");
		resIterMultiply[2] = sResMultiply.getIteracoes();
		resInstrMultiply[2] = sResMultiply.getInstrucoes();
		resClockMultiply[2] = sResMultiply.getTime();
		resResultsMultiply[2] = sResMultiply.getResult()[0];

		System.out.println("\nExecuções sobre Escalares Feitas!");

		System.out.println("\nMultiply com Divisão e Conquista");
		System.out.println("Nro iter pela classe complexidade - Melhor Caso - n * log2 n: " +
				TAM_VETOR * (Math.log10(TAM_VETOR)/Math.log10(2)) + 
				"\nNro iter pela classe complexidade - Pior Caso - n * log2 n: " +
				TAM_VETOR * (Math.log10(TAM_VETOR)/Math.log10(2)));

		System.out.println("Resultados: 4 bits, 8 bits, 64 bits");
		exibe(resResultsMultiply, 20);
		System.out.println("Nro iteracoes: 4 bits, 8 bits, 64 bits");
		exibe(resIterMultiply, 20);
		System.out.println("Nro instrucoes: 4 bits, 8 bits, 64 bits");
		exibe(resInstrMultiply, 20);
		System.out.println("Tempo em segundos: 4 bits, 8 bits, 64 bits");
		exibe(resClockMultiply, 20);
	}

	public static void main(String argc[]) {

		QTD_TESTS_MULT = 3;

		ITERACOES = 1;
		ELEMENTOS = 32;
		System.out.printf(Locale.FRANCE,"********* Nro de Iterações - %,8d execuções de %,8d elementos \n", ITERACOES, ELEMENTOS);
		iteraTestes(ITERACOES, ELEMENTOS, QTD_TESTS_MULT);

		ITERACOES = 1;
		ELEMENTOS = 2_048;
		System.out.printf(Locale.FRANCE,"********* Nro de Iterações - %,8d execuções de %,8d elementos \n", ITERACOES, ELEMENTOS);
		iteraTestes(ITERACOES, ELEMENTOS, QTD_TESTS_MULT);

		ITERACOES = 1;
		ELEMENTOS = 1_048_576;
		System.out.printf(Locale.US,"********* Nro de Iterações - %,8d execuções de %,8d elementos \n", ITERACOES, ELEMENTOS);
		iteraTestes(ITERACOES, ELEMENTOS, QTD_TESTS_MULT);
	}
}
