package br.pucrs.sorts;

import java.util.Random;

import br.pucrs.util.ContagemRes;

public class Sorts {
	public long[] geraVetor(int nroElem, int lim){
		int dummy;
		long [] res = null;
		int cont = 0;
		Random rnd = new Random(System.nanoTime() * System.currentTimeMillis());

		if (nroElem >= 0) {

			res = new long[nroElem];

			while (cont < nroElem) {
				dummy = rnd.nextInt(lim)+1;
				dummy = rnd.nextInt(lim)+1;
				
				dummy = rnd.nextInt(lim)+1;
				res[cont++] = (long) dummy;
			}
		}
		return res;
	}

	public long[] geraVetorInv(int nroElem){
		long [] res = null;
		
		if (nroElem > 0) {

			res = new long[nroElem];

			for (int i = nroElem -1; i >= 0; i--)
				res[i] = (long) nroElem - i;
		}
		return res;
	}


	// Insira os métodos de sort aqui.

	public ContagemRes bubbleSort0(long [] vet) {
		ContagemRes res = new ContagemRes();
		long[] v = vet.clone();

		res.resetCounters();
		bubbleSort0(v, res);
		res.getClockSec();
		res.setResult(vet);

		return res;
	}

	private void bubbleSort0(long [] vet, ContagemRes contRes) {
		long aux; 
		int i;

		contRes.incrInstrucoes(1);
		for (int j = 0; j < vet.length - 1; j++) {
			contRes.incrInstrucoes(5);
			for (i = 0; i < vet.length - 1; i++) {
				contRes.incrIteracoes(1);
				contRes.incrInstrucoes(5);
				contRes.incrInstrucoes(4);
				if (vet[ i ] > vet[ i + 1 ]) {
					contRes.incrInstrucoes(9);
					aux = vet[i];
					vet[ i ] = vet[ i + 1 ];
					vet[i + 1] = aux;
				}		         
			}

			if (i == 0)
				contRes.incrIteracoes(1);
		}
	}

	public ContagemRes mergeSort(long [] vet)
	{
		ContagemRes res = new ContagemRes();
		long [] v = vet.clone();

		res.resetCounters();
		mergeSort(v, 0, v.length-1, res);
		res.getClockSec();
		res.setResult(v);

		return res;
	}

	private void mergeSort(long [] v, int inicio, int fim, ContagemRes contRes)
	{
		contRes.incrIteracoes(1);
		contRes.incrInstrucoes(1);
		if (inicio < fim)
		{
			int meio = (inicio + fim) / 2;
			mergeSort(v, inicio, meio, contRes);
			mergeSort(v, meio+1, fim, contRes);
			merge(v, inicio, meio, fim, contRes);
			contRes.incrInstrucoes(7);
		}
	}

	private void merge(long [] v, int inicio, int meio, int fim, ContagemRes contRes) {
		int nL = meio-inicio+1; int nR = fim-meio;
		long [] L = new long[nL]; long [] R = new long[nR];

		System.arraycopy(v, inicio, L, 0, nL);
		contRes.incrIteracoes(nL);

		System.arraycopy(v, meio+1, R, 0, nR);
		contRes.incrIteracoes(nR);

		contRes.incrInstrucoes(10);

		contRes.incrInstrucoes(1);
		int iL = 0; int iR = 0;
		for (int k=inicio; k<=fim; k++) {

			contRes.incrIteracoes(1);
			contRes.incrInstrucoes(4);

			if (iR < nR) {
				contRes.incrInstrucoes(1);
				if (iL < nL) {
					contRes.incrInstrucoes(7);
					if (L[iL] < R[iR]) v[k] = L[iL++];
					else v[k] = R[iR++];
				}
				else {
					v[k] = R[iR++];
					contRes.incrInstrucoes(5);
				}
			}
			else {
				v[k] = L[iL++];
				contRes.incrInstrucoes(5);
			}
		}
	}
}
