package br.pucrs;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App
{
	/**
	 * Timer used for benchmarking, in ns.
	 * Should not be read between resetTimer and stopTimer.
	 */
	private static long timer;
	private	static long iter_maxVal1;
	private	static long iter_maxVal2;
	private	static long iter_mergeSort;
	private	static long iter_karatsuba;

	public static void main(String[] args) {
		App.benchmark(32, 4);
		App.benchmark(2048, 16);
		App.benchmark(1_048_576, 64);
	}

	public static List<Integer> randomList(int size){
		List<Integer> list = new ArrayList<>(size);
		Random random = new Random();
		int bound = size * 5;

		for(int _i = 0; _i < size; _i++){
			list.add(random.nextInt(bound));
		}

		return list;
	}

	public static Integer maxVal1(List<Integer> list) {
		Integer max = list.get(0);

		for (Integer x : list) {
			App.iter_maxVal1++;

			if (x > max) {
				max = x;
			}
		}

		return max;
	}

	public static Integer maxVal2(
		List<Integer> list, Integer start, Integer end
	) {
		App.iter_maxVal2++;

		if (end - start <= 1) {
			return Math.max(list.get(start), list.get(end));
		} else {
			int m = (start + end) / 2;
			int v1 = maxVal2(list, start, m);
			int v2 = maxVal2(list, m + 1, end);
			return Math.max(v1, v2);
		}
	}

	/**
	 * Sorts a list of comparable elements using the merge sort algorithm.
	 *
	 * @param <T> the type of elements in the list
	 * @param list the list of elements to sort
	 * @return a new list with the elements from list in ascending order
	*/
	public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
		App.iter_mergeSort++;

		if (list.size() < 2) {
			return list;
		}

		List<T> a = mergeSort(list.subList(0, list.size() / 2));
		List<T> b = mergeSort(list.subList(list.size() / 2, list.size()));

		// Merge both arrays.
		List<T> res = new ArrayList<>(list.size());
		int i = 0;
		int j = 0;

		// While both arrays are not exhausted,
		// add the smallest element.
		while (i < a.size() && j < b.size()) {
			if (a.get(i).compareTo(b.get(j)) <= 0) {
				res.add(a.get(i));
				i++;
			} else {
				res.add(b.get(j));
				j++;
			}
		}

		// Add the remaining elements.
		while (i < a.size()) {
			res.add(a.get(i));
			i++;
		}

		while (j < b.size()) {
			res.add(b.get(j));
			j++;
		}

		return res;
	}

	public static long karatsuba(long x, long y, int size) {
		App.iter_karatsuba++;

		if (size == 1) {
			return x * y;
		} else {
			int m = (int) Math.ceil(size / 2);
			long a = (long) (Math.floor(x / Math.pow(2, m)));
			long b = (long) (x % Math.pow(2, m));
			long c = (long) (Math.floor(x / Math.pow(2, m)));
			long d = (long) (y % Math.pow(2, m));
			long e = karatsuba(a, c, m);
			long f = karatsuba(b, d, m);
			long g = karatsuba(b, c, m);
			long h = karatsuba(a, d, m);

			return (long) (Math.pow(2, 2 * m)) * e
				+ (long) (Math.pow(2, m)) * (g + h) + f;
		}
	}

	private static void benchmark(int list_size, int int_size) {
		App.resetIterations();
		System.out.println("Array Size: " + list_size);
		System.out.println("Integer Size: " + int_size + "\n");
		List<Integer> list = App.randomList(list_size);

		// Benchmarks max without divide and conquer.
		App.resetTimer();
		App.maxVal1(list);
		double time = App.stopTimer();
		System.out.println("max (no divide and conquer): " + App.iter_maxVal1 + " iters (" + time + " ms)");

		// Benchmarks max with divide and conquer.
		App.resetTimer();
		App.maxVal2(list, 0, list_size - 1);
		time = App.stopTimer();
		System.out.println("max (with divide and conquer): " + App.iter_maxVal2 + " iters (" + time + " ms)");

		// Benchmarks merge sort.
		App.resetTimer();
		App.mergeSort(list);
		time = App.stopTimer();
		System.out.println("merge sort: " + App.iter_mergeSort + " iters (" + time + " ms)");

		// Benchmarks the Karatsuba algorithm.
		App.resetTimer();
		App.karatsuba(Long.MAX_VALUE, Long.MAX_VALUE, int_size);
		time = App.stopTimer();
		System.out.println("karatsuba: " + App.iter_karatsuba + " iters (" + time + " ms)");

		System.out.println("\n");
	}

	/**
	 * Resets the class timer.
	 * It then should not be read before stopTimer is called.
	 */
	private static void resetTimer() {
		App.timer = System.nanoTime();
	}

	/**
	 * Stops the class timer, storing the time elapsed in ns
	 * since it was last reset inside the field.
	 *
	 * @return the time elapsed in ms
	 */
	private static double stopTimer() {
		App.timer = System.nanoTime() - App.timer;
		return App.timer / 1e6;
	}

	private static void resetIterations() {
		App.iter_maxVal1 = 0;
		App.iter_maxVal2 = 0;
		App.iter_mergeSort = 0;
		App.iter_karatsuba = 0;
	}
}
