import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* 
 * 
 * 최장 증가 부분 수열(LIS)
 * 
 * 1) dp를 활용한 최장 증가 수열
 * 백준 11055 가장 긴 증가하는 부분 수열
 * https://www.acmicpc.net/problem/11055
 * 
 * 
 *
 * 
 * dp(끝 번호를 사용했을 때 시작~ 끝까지의 수열의 최대 길이) =  max(dp(시작~중간까지의 수열의 길이) + 1(끝 번호 숫자) ,  dp(시작~ 끝까지의 수열의 최대 길이))
 *
 * dp[3] = 3번째 숫자을 사용했을 때 최장 길이
 * dp[1] = 1번째 숫자을 사용했을 때 최장 길이
 * 
 * ex)
 * 
 * 배열 : {1, 50, 3, 5}
 * 
 * 0) 아무것도 사용하지 않았을 때
 * 최대 길이 = { } = 0
 * 
 * 1)
 * 1을 사용했을 때
 * 1 ~ 1까지의 최대 길이 = {1} => 1
 * 
 * 2) 50을 사용했을 때
 * 1~50까지의 최대길이 = {50} , {1 , 50} =>2
 * 
 * 3) 3을 사용했을 때
 * 1~3까지의 최대길이 = {3} ,{1,3} , {1,50,3}(X) => {1,3} => 2
 * 
 * 4) 5를 사용했을 때 ={5}, {1,5}, {1,50,5}(X), {1,3,5} => {1,3,5} =>3
 * 
 * 정답 : {1,3,5} =>3
 * 
 * 
 * max = 1 <= 초기 최대값은 숫자 1개만 사용한 경우
 * 
 * for(int end =0 ; end < 수열의 길이 ; end++){
 *         dp[end] = 1 <= num[end]만 사용하면 1의 길이의 수열이 된다.
 * 
 *         for(int mid=0; mid < end; mid++ ){
 *             if(mid번째 값보다 end번째 값이 큰 경우){
 *                     dp[1~end까지 수열의 길이] = max(dp[1~mid까지 수열의 길이] + 1 , dp[1~end까지 수열의 길이])
 *                 }
 *         }
 * 
 *         max = (dp[end],max)
 * }
 * 
 * 
 * 시간 복잡도 O(n) = 이중 for 문  = n^2
 * 
 * 
 * 2) 이분탐색을 활용한 최장 증가 수열
 * 백준 12738 가장 긴 증가하는 부분 수열 3
 * https://www.acmicpc.net/problem/12738
 * 
 * dp 방식은  1~N까지 사용했을 때 최장거리를 구하는 방식
 * =>모든 경우의 수를 따지는 방법이라 시간 초과 우려가 있음
 * =>현재 숫자를 사용하는 것이 이득인 위치를 "완전 탐색"이 아닌 다른 방법으로
 * =>가장 최근의 최장거리 배열 1개로만 비교할 순 없을까??
 * 
 * 
 * 배열 { 10, 20, 7, 30 , 29 }
 * 
 * 0) 아무것도 사용 안함
 * 최장 배열 : { } => 0
 * 
 * 1) 10 사용
 * 
 * 최장 배열의 될 가능성이 있는 배열 : { 10 } => 1
 *  
 * 2) 20 사용
 * 
 * 최장 배열의 될 가능성이 있는 배열  : { 10, 20 } => 2
 *
 * 3) 7 사용
 * 
 * { 10, 20 }의 최대값인 20보다 7은 작으므로 7을 추가할 수 없음
 * => 최대 길이는 변함이 없음
 * 
 * 7을 사용할 수 있는 방법)
 * 
 * 증가 배열이기 때문에 7보다 크거나 같은 숫자 오른쪽에 7을 배치할 수 없다
 * ex) { 10, 7 } (X)
 * => 7보다 작은 숫자의 오른쪽에만 배치가 가능하다
 * 
 * 현재까지 나온 숫자 중 7보다 작은 수가 없기 때문에 7은 첫번째 숫자로 밖에 사용할 수 없다.
 * 7을 첫번째 숫자로 사용할 경우 10보다 작기 때문에 10을 첫번째 숫자로 쓰는 경우보다 최장 수열이 될 가능성이 큼 
 *
 * ex) 이후 숫자가 8 ,9 인 경우
 * { 7, 8, 9 } > {10, 20} => 최장 배열 : { 7, 8, 9 }, 길이 : 3
 * 
 *
 * 최장 배열의 될 가능성이 있는 배열  :  { 10, 20 }(x), { 7 , 20 } => 2
 * 
 * dp배열 : 최장 수열을 만들 때 가장 이득(가장 작은 숫자)이 되는 자리수를 가지고 있는 배열
 * dp[1] : 첫번째 수를 정할 때 최장 수열이 될 수 있는 가장 이득이 되는 수(가장 작은 숫자)
 * dp[n] : n번째 수를 정할 때 최장 수열이 될 수 있는 가장 이득이 되는 수(가장 작은 숫자)
 * 
 * 4) 30 사용
 * 
 * { 7 , 20 } 의 최대값인 20 보다 30이 크므로 30 추가 가능
 * 
 * dp 배열  :  { 7, 20, 30 } => 3
 * 
 * 5) 29 사용
 * 
 * { 7 , 30 } 의 최대값인 30 보다 29가 작으므로 추가 불가
 * 
 * 29를 사용할 수 있는 방법)
 * 
 * 증가 배열이기 때문에 29보다 크거나 같은 숫자 오른쪽에 29을 배치할 수 없다
 * => 29보다 작은 숫자의 오른쪽에만 배치가 가능하다
 * 
 * 나올 수 있는 배열)
 * 
 * { 7, 20, 30 }
 * 
 * 가장 이득이 되는 수 : 가장 작은 숫자
 * 1번째 자리 수 : 7보다 29가 크기 때문에 29는 1번째 자리 수가 될 수 없다. = 7
 * 2번째 자리 수 : 20 VS 29 = 20 < 29 = 20
 * 3번째 자리 수 : 29 VS 30 = 29 < 30 = 29
 * 
 * dp 배열 : { 7 ,29 ,30 } => 3
 * 
 * 최장 배열의 길이 = dp배열의 길이 = { 7 ,20 , 29 }의 길이 => 3
 * 
 * 
 * 규칙)
 * 현재 숫자 = n
 * 1. 현재 dp 배열의 최대값보다 n보다가 크다면 배열에 추가
 * 2. 현재 dp 배열의 최대값보다 n가 작다면 (n보다 작은 숫자 중 가장 큰 숫자) + 1 번째 수와 교체
 * 	  => 증가 수열이여야 하기 때문에 (n보다 큰 숫자) <= n <= (n 보다 큰 숫자) 는 불가능
 *    => (n보다 작은 숫자) < n < (n보다 큰 숫자)
 *  	  
 *    ex) { 7 , 20, 30 } 에 29를 넣는다
 *    29보다 작은 수 = 7 , 20
 *    이 중 가장 큰 수 = 20
 *    20 + 1 번째 수 = 30
 *    { 7 , 20, 30-> 29} = { 7 , 20, 29 }
 *      
 *    => 자신보다 작은 숫자 + 1 번째 숫자를 찾는 알고리즘 = lower bound = 이분탐색
 *    
 *    lower bound )
 *    왼쪽 포인터를 이용해서 목표 숫자보다 처음으로 같거나 큰 숫자를 구하는 이분탐색 알고리즘
 *    
 *    while(왼쪽 < 오른쪽){
 *    	중간 = (왼쪽+오른쪽)/2
 *    
 *    	if(중간 값 <  목표 숫자){
 *   		왼쪽  = 중간 + 1
 *      }else{
 *       	오른쪽 = 중간
 *      }
 *    }
 *    
 *    dp[왼쪽] = 넣으려는 숫자
 * 
 * 이분 탐색의 시간 복잡도 O(n) = logn
 * 
 * 총 시간 복잡도 = 숫자의 개수 * lower bound 탐색 시간 = n*logn
 * 
 * 3) dp + 경로탐색을 활용한 최장 증가 수열
 * 백준 14002 가장 긴 증가하는 부분 수열 4
 * https://www.acmicpc.net/problem/14002
 * 
 * 4) 이분탐색 + 경로탐색을 활용한 최장 
 * 백준 14003 가장 긴 증가하는 부분 수열 5
 * https://www.acmicpc.net/problem/14003
 *  
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		int num[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int idxs[] = new int[size];
		int dp[] = new int[size];
		int length = 1;

		dp[0] = num[0];
		idxs[0] = 0;
		for (int i = 1; i < num.length; i++) {
			if (dp[length - 1] < num[i]) {
				length++;
				dp[length - 1] = num[i];
				idxs[i] = length - 1;
			} else {
				int mid = 0;
				int l = 0;
				int r = length - 1;
				while (l < r) {
					mid = (l + r) / 2;

					if (dp[mid] < num[i]) {
						l = mid + 1;
					} else {
						r = mid;
					}
				}
				dp[l] = num[i];
				idxs[i] = l;
			}
		}
// 	인덱스 스택을 활용한 최장 배열을 구하는 코드
//		Stack<Integer> stack = new Stack<>();	
//		int curr = length - 1;
//		for (int i = size - 1; i >= 0; i--) {
//			if (idxs[i] == curr) {
//				curr--;
//				stack.add(num[i]);
//			}
//			if (curr < 0) {
//				break;
//			}
//		}
//		StringBuilder sb = new StringBuilder();
//		while (!stack.isEmpty()) {
//			sb.append(stack.pop() + " ");
//		}

		System.out.println(length);
//		최장 배열 출력 부분
//		System.out.println(sb.deleteCharAt(sb.length() - 1).toString()); 
	}
}