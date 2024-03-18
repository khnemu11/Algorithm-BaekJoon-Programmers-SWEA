import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * 
 * 줄은 2개
 * 대기 열, 라인
 * 
 * 목표)
 * 대기열과 라인 중 가장 작은 번호를 간식 받는 곳으로 옮겨야함
 * 
 * 조건)
 * 대기열과 라인 중 가장 앞 부분만 간식 받는 곳으로 옮길 수 있음
 * 이때 대기열은 가장 먼서 줄을 선 사람, 라인은 가장 늦게 줄을 선 사람이 "앞부분"이다. 
 * 
 * 대기열에 사람이 모두 먼저 서 있으므로 대기열 부터 처리하고 라인을 처리한다.
 * 
 * 
 * 과정)
 * 1) 대기열 사람을 모두 라인이나 간식 받는 곳으로 옮김
 * 
 * while(대기열에 사람이 있는경우){
 * 		while(라인의 앞사람이 현재 번호인 경우){
 * 			간식 받는 곳으로 라인의 앞사람을 옮김
 * 		}
 *		if(대기열의 사람이 이번 차례인 경우){
 *			간식받는 곳으로 앞사람을 옮김
 *		}else{
 *			라인으로 앞사람을 옮김
 *		}
 * }
 * 
 * 2) 남은 라인의 사람을 간식 받는 곳으로 옮김
 * 
 * while(아직 라인의 사람이 남아 있고 라인의 앞사람이 현재 번호인 경우){
 * 	간식 받는 곳으로 라인의 앞사람을 옮김
 * }
 * 
 * */

public class Main {
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       int N = Integer.parseInt(br.readLine());
       int[] students = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
       Stack<Integer> line = new Stack<>();
       Queue<Integer> readyQ = new LinkedList<>();
       
       //학생들을 대기열로 옮김
       for(int num : students) {
    	   readyQ.add(num);
       }
       
       //가장 작은 번호 탐색을 위한 정렬
       Arrays.sort(students);
       //현재 차례인 학생의 인덱스
       int targetIdx = 0;
       
       List<Integer> snackList = new ArrayList<>();
       
       //대기열의 학생을 라인이나 간식 받은 곳으로 옮김
       while(!readyQ.isEmpty()) {
    	   //라인의 앞사람이 현재 학생인경우 처리 
    	   while(!line.isEmpty() && line.peek() == students[targetIdx]) {	   
    		   snackList.add(line.pop());
        	   targetIdx++;
           }
    	   //대기열의 앞사람이 현재 학생인 경우 처리
    	   if(readyQ.peek() == students[targetIdx]) {
    		   snackList.add(readyQ.poll());
    		   targetIdx++;
    	   }
    	   //대기열의 앞사람이 현재 학생이 아닌경우 처리
    	   else {
    		   line.add(readyQ.poll());
    	   }
       }
       
       //라인에 남은 사람을 간식 받는곳으로 옮김
       while(!line.isEmpty() && line.peek() == students[targetIdx]) {	   
		   snackList.add(line.pop());
    	   targetIdx++;
       }
       
       //모든 학생이 간식받는 곳으로 간 경우
       if(snackList.size() == N) {
    	   System.out.println("Nice");
       }
       //모두 가지 못한 경우
       else {
    	   System.out.println("Sad");
       }
	}
}