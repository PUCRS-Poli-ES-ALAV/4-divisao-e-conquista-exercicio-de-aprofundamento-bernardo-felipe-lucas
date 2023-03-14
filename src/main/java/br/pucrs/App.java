package br.pucrs;
import java.util.Random;
import java.util.regex.MatchResult;
import java.lang.Math;

class App {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    int tam=2048;
    long[] vet32 = randVetor(tam);
    maxVal2(vet32, 0, tam-1);
    long finish = System.currentTimeMillis();
    long total = finish - start;
    System.out.println("tam 32 leva "+total+"ms");
  }

  public static long[] randVetor(int tam){
    long[] vet = new long [tam];
    int bound=tam*5;
    Random ran=new Random();
    for(int i=0; i<tam-1;i++){
      vet[i]= ran.nextInt(bound);
    }
    return vet;
  }

  public static long maxVal2(long A[], int init, int end) {  
    if (end - init <= 1)
        return Math.max(A[init], A[end]);  
    else {
          int m = (init + end)/2;
          long v1 = maxVal2(A,init,m);   
          long v2 = maxVal2(A,m+1,end);  
          return Math.max(v1,v2);
         }
}
}
