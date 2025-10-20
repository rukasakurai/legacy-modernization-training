package legacy;

public class SumCalculator {
	public int sum(int[] values) {
		int total = 0;
		for (int v : values) {
			total += v;
		}
		return total;
	}
}
