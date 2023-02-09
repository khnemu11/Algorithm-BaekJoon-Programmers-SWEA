import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/*
	풀이 알고리즘
	모든 남극의 단어는 anta와 tica를 포함하므로 알파벳 a,n,t,i,c 5개는 무조건 들어가 있다.
	-> K가 5보다 작으면 어떤 문자도 읽을 수 없다.
	K가 26개면 모든 알파벳을 배울 수 있으므로 모든 단어를 읽을 수 있다.
	단어의 길이가 최대 15인데 5개는 포함해야 하므로 서로 다른 최대의 알파벳이 나오는 수는 10개
	최대 시간 복잡도 = 10C5(알파벳 10개 중 5개를 순서 없이 고름) * 50(최대 단어 개수)*15(단어 길이) = 약 19만	
*/

public class Main {
	static boolean alphaExist[][];
	static int maxCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int K = Integer.valueOf(st.nextToken());
		alphaExist = new boolean[N][26];
		Set<Character> set = new HashSet<>();
		// 판정해야 하는 알파벳 구하기

		for (int i = 0; i < N; i++) {
			char words[] = br.readLine().toCharArray();
			alphaExist[i]['a' - 'a'] = true;
			alphaExist[i]['t' - 'a'] = true;
			alphaExist[i]['i' - 'a'] = true;
			alphaExist[i]['c' - 'a'] = true;
			alphaExist[i]['n' - 'a'] = true;

			for (int alpha = 4; alpha < words.length - 4; alpha++) {
				alphaExist[i][words[alpha] - 'a'] = true;

				if (words[alpha] != 'a' && words[alpha] != 't' && words[alpha] != 'i' && words[alpha] != 'c'
						&& words[alpha] != 'n') {
					set.add(words[alpha]);
				}
			}
		}

		char[] alphaList = new char[set.size()];
		int idx = 0;
		for (char alpha : set) {
			alphaList[idx] = alpha;
			idx++;
		}

		if (K < 5) {
			maxCnt = 0;
		} else if (alphaList.length + 5 <= K) {
			maxCnt = N;
		} else {
			boolean selected[] = new boolean[26];
			selected['a' - 'a'] = true;
			selected['t' - 'a'] = true;
			selected['i' - 'a'] = true;
			selected['n' - 'a'] = true;
			selected['c' - 'a'] = true;

			select(alphaList, selected, 0, 0, K - 5);
		}

		System.out.println(maxCnt);
	}

	public static void select(char[] alphaList, boolean selected[], int idx, int selectCnt, int K) {
		if (selectCnt >= K) {
			int readCnt = 0;

			for (int i = 0; i < alphaExist.length; i++) {
				boolean canRead = true;
				for (int j = 0; j < selected.length; j++) {
					if (alphaExist[i][j] && !selected[j]) {
						canRead = false;
						break;
					}
				}
				if (canRead) {
					readCnt++;
				}
			}

			maxCnt = Math.max(maxCnt, readCnt);
		} else {
			for (int i = idx; i < alphaList.length; i++) {
				selected[alphaList[i] - 'a'] = true;
				select(alphaList, selected, i + 1, selectCnt + 1, K);
				selected[alphaList[i] - 'a'] = false;
			}
		}
	}
}