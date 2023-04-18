import java.util.*;

/*
    목표 지점까지 가는 완전 탐색 문제
    해당 지점을 갔을 때 이전 도착 시간보다 늦으면 더이상 볼 필요가 없으므로 가지치기
    ->방문배열을 int형으로
    
    다음 좌표가 배열에 벗어나거나 장애물이 있을 경우까지 좌표 재생성
*/

class Solution {
	int min = Integer.MAX_VALUE;    //최소 경로값
	int visited[][];    //각 좌표에 도착한 최소 시간
	int dx[] = { -1, 1, 0, 0 }; //좌표 행 움직임
	int dy[] = { 0, 0, -1, 1 }; //좌표 열 움직임

	public int solution(String[] board) {
		//방문배열 초기화
        visited = new int[board.length][board[0].length()];
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
        //시작 좌표을 찾았는지 판단하는 변수
		boolean find = false;
        
        //시작좌표 찾는 반복문
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {
				//시작좌표를 찾았으면 경로탐색 시작
                if (board[i].charAt(j) == 'R') {
					goNext(new Coordinate(i, j), board, 0);
					find = true;
					break;
				}
			}
            //시작좌표를 찾았으면 탐색 중단
			if (find) {
				break;
			}
		}
        //초기 최대값과 최소 경로값이 같으면 -1
		if (min == Integer.MAX_VALUE) {
			return -1;
		}
        
		return min;
	}
    //경로를 dfs로 탐색하여 좌표가 G일때 까지 좌표를 탐색하는 메소드
    //(좌표, 맵 배열, 현재 경로 비용)
	public void goNext(Coordinate curr, String board[], int cnt) { 
        //도착지면 최소 경로값을 최소값으로 설정
		if (board[curr.row].charAt(curr.col) == 'G') {
			min = Math.min(cnt, min);
			return;
		} 
        //현재 경로 비용이 최소값보다 크면 더이상 탐색 불필요
        else if (cnt >= min) {
			return;
		}
        else {
			visited[curr.row][curr.col] = cnt;
			for (int k = 0; k < dx.length; k++) {
				Coordinate next = new Coordinate(curr.row, curr.col);
                //장애물에 만날 때 까지 or 배열을 벗어날 때 까지 직진
				while (true) {
					Coordinate nextnext = new Coordinate(next.row + dx[k], next.col + dy[k]);
					if (outOfArray(board, nextnext) || board[nextnext.row].charAt(nextnext.col) == 'D') {
						break;
					}
					next = nextnext;
				}
                //현재 탐색 비용보다 다음 좌표 비용이 큰 경우 가지치기
				if (visited[next.row][next.col] < cnt + 1) {
					continue;
				}
                //다음 좌표로 이동
				goNext(next, board, cnt + 1);
			}
		}
	}
    //맵 배열에 벗어나는지 판단하는 메소드
	public boolean outOfArray(String[] board, Coordinate coord) {
		if (coord.row < 0 || coord.col < 0 || coord.row >= board.length || coord.col >= board[0].length()) {
			return true;
		}

		return false;
	}
}
//좌표 클래스
class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
