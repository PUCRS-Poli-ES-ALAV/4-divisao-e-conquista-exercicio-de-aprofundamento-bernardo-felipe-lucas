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

	public static void main(String[] args) {
		App.benchmark(32);
		App.benchmark(2048);
		App.benchmark(1_048_576);
	}

	public static long[] randomVector(int size){
		long[] vec = new long[size];
		int bound = size * 5;
		Random random = new Random();

		for(int i = 0; i < size - 1; i++){
			vec[i] = random.nextInt(bound);
		}

		return vec;
	}

	public static long maxVal1(long vec[], int n) {
		long max = vec[0];

		for (int i = 1; i < n; i++) {
			if( vec[i] > max ) {
				max = vec[i];
			}
		}

		return max;
	}

	public static long maxVal2(long vec[], int start, int end) {
		if (end - start <= 1) {
			return Math.max(vec[start], vec[end]);
		} else {
			int m = (start + end) / 2;
			long v1 = maxVal2(vec, start, m);
			long v2 = maxVal2(vec, m + 1, end);
			return Math.max(v1, v2);
		}
	}

	/**
	 * Sorts a list of comparable elements using the merge sort algorithm.
	 *
	 * @param <T> the type of elements in the list
	 * @param v the list of elements to sort
	 * @return a new list with the elements from v, sorted in ascending order
	*/
	public static <T extends Comparable<T>> List<T> mergeSort(List<T> v) {
		if (v.size() < 2) {
			return v;
		}

		List<T> a = mergeSort(v.subList(0, v.size() / 2));
		List<T> b = mergeSort(v.subList(v.size() / 2, v.size()));

		// Merge both arrays.
		List<T> res = new ArrayList<>(v.size());
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

	private static void benchmark(int size) {
		System.out.println("Size: " + size + "\n");

		long[] vec = randomVector(size);

		App.resetTimer();
		maxVal1(vec, size);
		double time = App.stopTimer();

		System.out.println(
			"max (no divide and conquer): " + time + " ms"
		);

		App.resetTimer();
		maxVal2(vec, 0, size - 1);
		time = App.stopTimer();

		System.out.println(
			"max (with divide and conquer): " + time + " ms"
		);

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
}
