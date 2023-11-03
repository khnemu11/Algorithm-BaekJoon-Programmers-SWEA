import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int currWeight=1;
		int prevWeight=1;
		int max = 1;
		boolean valid = false;

		//가능한 가장 큰 현재 몸무게 구하기
		while(true){
			double minDiffer = Math.pow(max,2) - Math.pow(max-1,2);
			if(minDiffer > G){
				max--;
				break;
			}
			max++;
		}
		//차이가 G인 경우 구하기
		while(currWeight <= max){
			double differ = Math.pow(currWeight,2) - Math.pow(prevWeight,2);

			if(differ == G){
				valid=true;
				System.out.println(currWeight);
			}

			if(differ < G){
				currWeight++;
			}else{
				prevWeight++;
			}
		}

		//한번도 가능한 경우가 없는경우
		if(!valid){
			System.out.println(-1);
		}
	}
}