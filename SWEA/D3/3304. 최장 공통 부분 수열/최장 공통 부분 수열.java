import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 테스트 케이스당 시간 제한 = 0.2
	문자열의 길이 = 1,000 -> O(n^2)까지 가능
	dp[현재 문자][현재 비교 대상 문자] = 현재 문자를 사용하거나 말았을 때 가장 긴 부분 수열의 길이
	
	
	dp[현재 문자][현재 비교 대상 문자] = max(dp[현재 문자][이전 비교 대상 문자],dp[이전 문자][현재 비교 대상 문자])
	if(현재 문자 =-= 현재 비교 대상 문자)
	dp[현재 문자][현재 비교 대상 문자] = max(dp[현재 문자][현재 비교 대상 문자],dp[이전 문자][이전 비교 대상 문자]+1)
	
	0	a	c	a	y	k	p
0	0	0	0	0	0	0	0
c	0	0	1	1	1	1	1
a	0	1	1	2	2	2	2
p	0	1	1	2	2	2	3
c	0	1	2	2	2	2	3
a	0	1	2	3	3	3	3
k	0	1	2	3	3	4	4
	
*/
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			char a[] = st.nextToken().toCharArray();
			char b[] = st.nextToken().toCharArray();

			int LCS[][] = new int[a.length + 1][b.length + 1];

			for (int i = 1; i <= a.length; i++) {
				for (int j = 1; j <= b.length; j++) {
					int aIdx = i - 1;
					int bIdx = j - 1;

					LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);

					if (a[aIdx] == b[bIdx]) {	//현재 문자와 비교 대상 문자가 같다면 길이 증가 후 현재 최대 길이 비교
						LCS[i][j] = Math.max(LCS[i][j], LCS[i - 1][j - 1] + 1);
					}
				}
			}

			System.out.println("#" + testcase + " " + LCS[a.length][b.length]);
		}
	}
}