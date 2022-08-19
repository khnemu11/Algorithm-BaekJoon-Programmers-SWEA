import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] inputFirst = Arrays.stream(br.readLine().split(" ")).mapToInt(n->Integer.valueOf(n)).toArray();
		double[] prop = Arrays.stream(br.readLine().split(" ")).mapToDouble(n->Double.valueOf(n)).toArray();
		
		double[][] pred =  new double[4][inputFirst[0]];
		
		if(inputFirst[1]==1) {
			pred[0][0]=prop[2];
			pred[1][0]=prop[3];
		}
		else {
			pred[0][0]=prop[0];
			pred[1][0]=prop[1];
		}
		
		for(int i=1;i<inputFirst[0];i++) {
			pred[0][i] = pred[0][i-1]*prop[0] + pred[1][i-1]*prop[2];
			pred[1][i] = pred[0][i-1]*prop[1] + pred[1][i-1]*prop[3];
		}
		
		System.out.println(Math.round(pred[0][inputFirst[0]-1]*1000));
		System.out.println(Math.round(pred[1][inputFirst[0]-1]*1000));
	}
}