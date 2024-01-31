/*
    포탑의 공격력은 변화가능
    0이 되면 더이상 공격 X

    시나리오
    
    1. 공격력이 가장 약한 포탑이 공격자로 선정
    우선순위)
    공격력이 낮음 > 가장 최근에 공격함 > 행+열의 합이 가장 큼 > 열의 값이 가장 큼
    
    2. 선정된 포탑의 공격력이 맵의 행의 크기 * 맵의 열의 크기 만큼 증가
    
    3. 가장 강한 포탑을 공격
    우선순위)
    공격력이 높음 > 가장 나중에 공격함 > 행+열의 합이 가장 작음 > 열의 값이 가장 작음
    
    4. 레이저 공격
    이동 규칙)
    1. 상하좌우
    2. 부서진 포탑으 이동 X
    3. 맨 끝으로 도달하면 처음으로
    4. 목표 대상에 도달하면 목표 대상에 공격자의 공격력 만큼 데미지
    4.1 경로에 존재하는 포탑에는 공격자의 공격력의 절반 데미지      
    4.2 도달하지 못하면 레이저 공격 실패

    5. 포탄 공격
    1. 공격 대상에 포탄을 8방향공격
    2. 공격자는 피해 X
    3. 가장자리인 경우 반대쪽에 피해
    4. 중앙에는 공격 포탑의 공격력만큼 피해
    5. 나머지는 절반의 피해

    6. 공격력 0이하면 부서짐
    7. 공격력이 0 이하가 아니며 공격과 무관한(공격자X, 피해자 X) 포탑의 공격력을 1 증가

    가장 강한 포탑의 공격력 출력
*/

import java.io.*;
import java.util.*;

public class Main {
	final static int EMPTY = 0;
	final static int COLOR = 1;	
	
	public static void main(String[] agrs) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] splitInput = br.readLine().split(" ");
		int n = Integer.parseInt(splitInput[0]);
		int m = Integer.parseInt(splitInput[1]);
		
		int[][]map = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		List<Coordinate> colorStartList = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			splitInput = br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(splitInput[j]);
				
				if(map[i][j] == COLOR) {
					colorStartList.add(new Coordinate(i,j));
				}
			}
		}
		
		int maxSize = 0;
		int count = 0;
		for(Coordinate start : colorStartList) {
			if(visited[start.row][start.col]) {
				continue;
			}
			
			int size = getSize(map,visited,start);
			maxSize = maxSize < size ? size : maxSize;
			count++;
		}
		
		System.out.println(count);
		System.out.println(maxSize);
	}
	
	public static int getSize(int[][]map, boolean[][]visited, Coordinate start) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		int size = 0;
		
		Queue<Coordinate> q = new LinkedList<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			Coordinate curr = q.poll();
			size++;
			visited[curr.row][curr.col] = true;
			
			for(int i=0;i<dx.length;i++) {
				Coordinate next = new Coordinate(curr.row+dx[i],curr.col + dy[i]);
				
				if(outOfArr(next, map)) {
					continue;
				}if(visited[next.row][next.col]) {
					continue;
				}if(map[next.row][next.col] == EMPTY) {
					continue;
				}
				
				visited[next.row][next.col] = true;
				q.add(next);
			}
		}
		
		return size;
	}
	
	public static boolean outOfArr(Coordinate coord, int[][] arr) {
		if(coord.row < 0 || coord.col <0 || coord.row >=arr.length || coord.col >=arr[0].length) {
			return true;
		}
		return false;
	}
}

class Coordinate{
	int row;
	int col;
	
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}