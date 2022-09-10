import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
        //기존 숫자들의 deque, 하나는 숫자를 찾기 위해 빼낸 숫자들의 deque 2개를 만든다.
        //일단 앞으로 숫자를 빼내는데 숫자를 빼낸 deque의 크기가 남은 기존 숫자들의 deque+1(뒤에서 숫자를 빼내서 찾았을 경우 찾는 숫자를 앞으로 일단 옮기고 빼내야 되기 때문에 1를 더한다.)
        //보다 큰 경우 뒤에서 빼는 경우가 더 적게 빼내서 숫자를 찾을 수 있는 경우이다.
        //다시 빼낸 숫자의 deque를 기존 deque에 빼낸 순서대로 넣으면 문제 해결 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Deque<Integer> deque = new ArrayDeque<>();
		int total = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.valueOf(st.nextToken());
		int length = Integer.valueOf(st.nextToken());

		int finds[] = new int[length];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < length; i++) {
			finds[i] = Integer.valueOf(st.nextToken());
		}
		for (int i = 1; i <= size; i++) {
			deque.add(i);
		}

		Deque<Integer> other = new ArrayDeque<>(); // 빼낸 숫자들의 deque
		for (int i = 0; i < length; i++) {
			int count = 0; //각 숫자별 빼낸 횟수
			while (true) { // 일단 처음부터 빼내서 앞부분에 찾는 숫자가 있을때 까지 other에 숫자를 넣는 과정
				if (finds[i] == deque.peekFirst()) { //해당 숫자면 deque에서 제외하고 루프를 탈출한다.
					deque.pollFirst();
					break;
				} else { //아니면 other에 숫자를 빼낸 순서대로 넣는다.
					count++; 
					other.addFirst(deque.pollFirst());
				}
			}

			if (count > deque.size() + 1) { //남은 deque가 count보다 작은 경우 뒤에서부터 빼내는게 쉬운 경우이다.
				count = deque.size() + 1; //이때 뒤에서부터 때내면 해당 숫자를 앞으로 옮긴 후 poll해야 되므로 1를 더한다.
			}
			while (!other.isEmpty()) { //other를 deque에 빼낸 순서에 맞게 push한다.
				deque.addLast(other.pollLast());
			}
			total += count; //최종 결과에 count를 더한다.
		}
		bw.write(String.valueOf(total));
		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
	}

}