package benchmarks;

import java.util.concurrent.ExecutionException;

import org.openjdk.jmh.annotations.Benchmark;

public class ListOperationsBenchmark extends AbstractBenchmark {
	@Benchmark
	public int list_max_for() {
		Integer m = Integer.MIN_VALUE;
		for (Integer i : intList) {
			if (i > m) {
				m = i;
			}
		}
		return m;
	}

	@Benchmark
	public int list_max_stream() {
		return intList.stream().max(Integer::compare).get();
	}

	@Benchmark
	public int list_max_stream_parallel() {
		return intList.stream().parallel().max(Integer::compare).get();
	}

	@Benchmark
	public int list_max_stream_parallel_4() throws InterruptedException, ExecutionException {
		return pool.submit(() -> intList.stream().parallel().max(Integer::compare).get()).get();
	}
}