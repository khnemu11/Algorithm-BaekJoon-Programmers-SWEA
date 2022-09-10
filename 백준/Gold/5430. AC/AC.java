import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String arg[]) throws IOException {
		//r로 배열을 뒤집어야 하는데 입력이 10만자나 있으면 배열을 뒤집는데 시간을 많이 쓰므로 뒤에서나 앞에서부터 접근하기 쉬운 deque를 이용한다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int t = 0; t < T; t++) {
			String command = br.readLine();
			boolean reverse = false; //뒤집혀 있는지 판단하는 변수
			boolean error = false; //비어있을 때 값을 꺼낼때 발생하는 에러를 의미하는 변수
			Deque<String> deque = new ArrayDeque<>(); //입력되는 값을 넣는 덱
			int length = Integer.valueOf(br.readLine());
			
			String arr = br.readLine();
			if(length>0) { //값이 있을경우 []와 ,를 파싱
				arr = arr.replaceAll("[\\[\\]]", ""); //정규식 [\[\]]를 이용해 [,]를 감지하여 공백으로 제거
				String nums [] = arr.split(","); 
				
				for (int i = 0; i < length; i++) {
					deque.addLast(nums[i]);
				}
			}
			for (int i = 0; i < command.length(); i++) {
				if (command.charAt(i) == 'R') { //R인경우 reverse를 현재의 반대값으로 수정
					reverse = !reverse;
				} else {
					if (deque.isEmpty()) { //값이 없는데 값을 꺼내려고 할 겨우 에러를 발생하고 더이상 command를 따지지 않음
						error = true;
						break;
					} else {
						if (reverse) { //반대로 뒤집혀 있을 경우 뒤에서 부터 꺼내
							deque.pollLast();
						} else { //정방향 일경우 앞에서 꺼냄
							deque.pollFirst();
						}
					}
				}
			}
			
			if(error) { //에러인 경우 에러만 출력하고 다음 입력값으로
				bw.write("error");
			}
			else { //에러가 아닌 경우 [ ] , 를 위치에 맞게 StringBuilder를 이용하여 생성
				StringBuilder sb = new StringBuilder("[");
				if(reverse) {
					while(!deque.isEmpty()) {
						sb.append(deque.pollLast());
						sb.append(",");
					}
				}
				else {
					while(!deque.isEmpty()) {
						sb.append(deque.pollFirst());
						sb.append(",");
					}
				}
				if(sb.charAt(sb.length()-1)==',') {
					sb.deleteCharAt(sb.length()-1);
				}
				
				sb.append("]");
				bw.write(sb.toString());
			}
			bw.newLine();
			bw.flush();//버퍼가 넘칠 수 있으므로 한번 입력이 끝날때 마다 출력
		}

		br.close();
		bw.close();
	}

}