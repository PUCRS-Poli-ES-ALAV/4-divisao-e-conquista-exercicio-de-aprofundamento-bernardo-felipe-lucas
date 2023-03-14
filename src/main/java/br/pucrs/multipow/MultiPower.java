package br.pucrs.multipow;

import br.pucrs.util.ContagemRes;

public class MultiPower {

    public ContagemRes multiply(long x, long y, int n) {
        ContagemRes contRes = new ContagemRes();
        long[] vetRes = new long[1];

        contRes.resetCounters();
        vetRes[0] = multiply(x, y, n, contRes);
        contRes.getClockSec();
        contRes.setResult(vetRes);

        return contRes;
    }

    private long multiply(long x, long y, int n, ContagemRes contRes) {
        long a, b, c, d, e, f, g, h;
        int m;

        contRes.incrIteracoes(1);
        contRes.incrInstrucoes(1);
        if (n == 1){
            contRes.incrInstrucoes(2);
            return x * y;
        }

        contRes.incrInstrucoes(35);        
        m = n / 2;
        a = x / (long) Math.pow(2, m);
        b = x % (long) Math.pow(2, m);

        c = y / (long) Math.pow(2, m);
        d = y % (long) Math.pow(2, m);

        e = multiply(a, c, m, contRes);
        f = multiply(b, d, m, contRes);
        g = multiply(b, c, m, contRes);
        h = multiply(a, d, m, contRes);

        return (long) Math.pow(2, (2 * m)) * e + (long) Math.pow(2, m) * (g + h) + f;
    }
}
