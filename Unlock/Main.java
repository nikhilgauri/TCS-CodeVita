import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;
class Main{
	public static Scanner scanner = new Scanner(System.in);
	public static ArrayList<ArrayList<Integer>> getter(int[][] matrix){
		int top = 0;
		int bottom = matrix.length-1;
		int left = 0;
		int right = matrix[0].length-1;
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		while(top<bottom && left<right){
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i=left;i<=right;i++){
				temp.add(matrix[top][i]);
			}
			top++;
			for(int i=top;i<=bottom;i++){
				temp.add(matrix[i][right]);
			}
			right--;
			for(int i=right; i>=left; i--){
				temp.add(matrix[bottom][i]);
			}
			bottom--;
			for(int i=bottom; i>=top;i--){
				temp.add(matrix[i][left]);
			}
			left++;
			list.add(temp);
		}
		return list;
	}
	public static ArrayList<ArrayList<Integer>> rotater(ArrayList<ArrayList<Integer>> list, int[] times){
		for(int i=0; i<list.size();i++){
			int localTimes = times[i]%list.get(i).size();
			if(i%2==0){
				Collections.rotate(list.get(i),list.get(i).size()-localTimes);
			}
			else{
				Collections.rotate(list.get(i),localTimes);
			}
		}
		return list;
	}
	public static int[][] setter(ArrayList<ArrayList<Integer>> list, int[][] matrix){
		int top = 0;
		int bottom = matrix.length-1;
		int left = 0;
		int right = matrix[0].length-1;
		int j = 0;
		while(top<bottom && left<right){
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i=left;i<=right;i++){
				matrix[top][i] = list.get(j).remove(0);
			}
			top++;
			for(int i=top;i<=bottom;i++){
				matrix[i][right] = list.get(j).remove(0);
			}
			right--;
			for(int i=right; i>=left; i--){
				matrix[bottom][i] = list.get(j).remove(0);
			}
			bottom--;
			for(int i=bottom; i>=top;i--){
				matrix[i][left] = list.get(j).remove(0);
			}
			left++;
			j++;
		}
		return matrix;
	}
	public static void main(String[] args) {
		// int[][] matrix = new int[][] {
		// 	{1,2,3,4},
		// 	{2,3,4,5},
		// 	{2,4,5,6},
		// 	{2,3,4,5}
		// };
		// int[][] matrix = new int[][]{
		// 	{1,2},
		// 	{3,4}
		// };
		// ArrayList<ArrayList<Integer>> list = getter(matrix);
		// list.forEach(System.out::println);
		// ArrayList<ArrayList<Integer>> list2 = rotater(list,new int[]{2,2});
		// list2.forEach(System.out::println);
		// int[][] returned = setter(list2,matrix);
		// for(int i=0; i<matrix.length;i++){
		// 	System.out.println(Arrays.toString(matrix[i]));
		// }
		String[] line1 = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		int m = Integer.parseInt(line1[0]);
		int n = Integer.parseInt(line1[1]);
		int[][] matrix = new int[m][n];
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				matrix[i][j] = scanner.nextInt();
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			}
		}
		int[] rotations = Stream.of(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		matrix = setter(rotater(getter(matrix),rotations),matrix);
		for(int i=0; i<m; i++){
			System.out.println(Arrays.stream(matrix[i]).boxed().map(String::valueOf).collect(Collectors.joining(" ")));
		}
	}

}
