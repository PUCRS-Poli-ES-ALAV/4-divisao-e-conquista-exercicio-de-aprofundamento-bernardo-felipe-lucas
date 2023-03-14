package br.pucrs.maxvals;

import br.pucrs.util.ContagemRes;

public class MaxVals {
    public ContagemRes maxValRep(long[] vet, int n) {
        ContagemRes contRes = new ContagemRes();
        long[] vetRes = new long[1];

        contRes.resetCounters();
        vetRes[0] = maxValRep(vet, n, contRes);
        contRes.getClockSec();
        contRes.setResult(vetRes);

        return contRes;
    }

    private long maxValRep(long[] vet, int n, ContagemRes contRes) {
        long max = -1;

        contRes.incrInstrucoes(3);
        if (vet.length == 0)
            throw new IllegalArgumentException("Vetor vazio!");
        else
            max = vet[0];
        contRes.incrInstrucoes(2);

        contRes.incrInstrucoes(1);
        for (int i = 1; i < n; i++) {
            contRes.incrIteracoes(1);
            contRes.incrInstrucoes(5);
            if (vet[i] > max) {
                max = vet[i];
                contRes.incrInstrucoes(2);
            }
        }
        contRes.incrInstrucoes(1);
        return max;
    }

    public ContagemRes maxValDiv(long[] vet, int n) {
        ContagemRes contRes = new ContagemRes();
        long[] vetRes = new long[1];

        contRes.resetCounters();
        vetRes[0] = maxValDiv(vet, 0, n - 1, contRes);
        contRes.getClockSec();
        contRes.setResult(vetRes);

        return contRes;
    }

    public long maxValDiv(long vet[], int init, int end, ContagemRes contRes) {
        contRes.incrInstrucoes(2);
        contRes.incrIteracoes(1);
        if (end - init <= 1) {
            contRes.incrInstrucoes(4);
            return Math.max(vet[init], vet[end]);
        } else {
            int m = (init + end) / 2;
            long v1 = maxValDiv(vet, init, m, contRes);
            long v2 = maxValDiv(vet, m + 1, end, contRes);
            contRes.incrInstrucoes(12);
            return Math.max(v1, v2);
        }
    }

}
