package br.pucrs;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        int size = 1048576;
        long[] vet = randVetor(size);

        long start = System.currentTimeMillis();
        maxVal1(vet, size);
        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("O vetor de tamanho "+ size +" sem divisao e conquista leva "+total+" de milisegundos para executar\n");
      

        long start2 = System.currentTimeMillis();
        maxVal2(vet, 0, size-1);
        long finish2 = System.currentTimeMillis();
        long total2 = finish2 - start2;
        System.out.println("O vetor de tamanho "+size+" com divisao e conquista leva "+total2+" de milisegundos para executar");
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

    public static long maxVal1(long A[], int n) {  
        long max = A[0];
        for (int i = 1; i < n; i++) {  
            if( A[i] > max ) 
                max = A[i];
        }
            return max;
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



