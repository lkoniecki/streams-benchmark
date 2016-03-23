package benchmarks;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@Fork(value = 1)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public abstract class AbstractBenchmark {
	private static final int NUMBER_OF_ELEMENTS = 900_000;
	private static final int NUMBER_OF_RANDOM_ELEMENTS = 450_000;

	protected final ArrayList<Integer> intList;

	protected final ForkJoinPool pool = new ForkJoinPool(4);

	public AbstractBenchmark() {
		intList = copyIntArray(createIntArray());
	}

	private static int[] createIntArray() {
		return createRandomIntArray(NUMBER_OF_ELEMENTS, NUMBER_OF_RANDOM_ELEMENTS);
	}

	private static int[] createRandomIntArray(int numberOfElements, int numberOfRandomElements) {
		Random random = new Random();
		int[] array = new int[numberOfElements];
		for (int i = 0; i < numberOfElements; i++) {
			if (i < numberOfRandomElements) {
				array[i] = random.nextInt();
			} else {
				array[i] = array[i % numberOfRandomElements];
			}
		}
		return array;
	}

	private static ArrayList<Integer> copyIntArray(int[] array) {
		ArrayList<Integer> list = new ArrayList<>(array.length);
		for (int element : array) {
			list.add(element);
		}
		return list;
	}
}
