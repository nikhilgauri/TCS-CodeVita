import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;
class Main{
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String[] line1 = scanner.nextLine().split(" ");	
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		int a = Integer.parseInt(line1[0]);
		int b = Integer.parseInt(line1[1]);
		String line2 = scanner.nextLine();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		String line3 = scanner.nextLine();
		double[] values = Stream.of(line3.split(" ")).mapToDouble(Double::parseDouble).toArray();
		// System.out.println(Arrays.toString(values));
		double first[] = new double[values.length-a+1];
		double second[] = new double[values.length-b+1];
		double sumOfFirstA = 0.0;
		for(int i=0; i<a;i++){
			sumOfFirstA += values[i];
		}
		double sumOfFirstB = 0.0;
		for(int i=0; i<b;i++){
			sumOfFirstB += values[i];
		}
		// System.out.println(sumOfFirstA);
		first[0] = sumOfFirstA/a;
		for(int i=1; i<first.length;i++){
			sumOfFirstA = sumOfFirstA - values[i-1] + values[i+a-1];
			first[i] = sumOfFirstA/a;
			// System.out.println(sumOfFirstA+" "+values[i-1]+" "+values[i+a-1]);
		}
		second[0] = sumOfFirstB/b;
		for(int i=1; i<second.length;i++){
			sumOfFirstB = sumOfFirstB - values[i-1] + values[i+b-1];
			second[i] = sumOfFirstB/b;
		}
		// System.out.println(Arrays.toString(first));
		// System.out.println(Arrays.toString(second));

		boolean[] flags = new boolean[second.length];
		for(int i=0; i<second.length; i++){
			flags[flags.length-i-1] = first[first.length-1-i]<second[second.length-1-i];
		}
		// System.out.println(Arrays.toString(flags));
		int counter = 0;
		for(int i=1; i<flags.length;i++){
			if(flags[i]!=flags[i-1])
				counter++;
		}
		System.out.println(counter);
	}
}