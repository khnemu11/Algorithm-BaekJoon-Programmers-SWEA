import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*    
	문제 풀이
    최대힙  최소힙
	( 1 2 3 4) (5 6 7)
	최대힙의 peek값이 중앙값
    
    if r < mid			중앙값이 한칸 오른쪽으로 옮겨짐 = 최대힙의 값을 최소힙으로 이동
    					   중앙값 보다 작은 쪽에(최대힙) 입력 두 숫자 추가
    	최소힙.add(mid)
        최대힙.add(l)
    	최대힙.add(r)
    if l < mid < r		중앙값 변화 X
    	최대힙.add(l)
    	최소힙.add(r)
     if l > mid    중앙값이 한칸 왼쪽으로 옮겨짐 = 최소힙의 값을 최대힙으로 이동
    					   중앙값 보다 큰 쪽에(최소힙) 입력 두 숫자 추가
    	최대힙.add(mid)
        최소힙.add(l)
    	최소힙.add(r)
 */
public class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int mid = Integer.valueOf(st.nextToken());
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			maxHeap.add(mid);
			long sum = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.valueOf(st.nextToken());
				int b = Integer.valueOf(st.nextToken());

				if (Math.max(a, b) <= mid) {
					minHeap.add(maxHeap.poll());
					maxHeap.add(a);
					maxHeap.add(b);
				} else if (Math.min(a, b) >= mid) {
					minHeap.add(a);
					minHeap.add(b);
					maxHeap.add(minHeap.poll());
				} else {
					maxHeap.add(Math.min(a, b));
					minHeap.add(Math.max(a, b));
				}
				mid = maxHeap.peek();
				sum = (sum + mid) % 20171109;
			}
			System.out.println("#" + testcase + " " + sum);
		}
	}
}