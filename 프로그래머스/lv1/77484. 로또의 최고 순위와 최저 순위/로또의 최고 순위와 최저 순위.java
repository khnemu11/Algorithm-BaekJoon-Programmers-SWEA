/*
    풀이 알고리즘
    몇개 맞추었나 -> 방문처리
    숫자는 0~45 -> 46개의 배열로 방문처리 가능
    최대 순위 = 7 - (0의 숫자가 모두 맞을 경우 + 현재 맞은 숫자의 개수)
               만약 0의 숫자가 모두 맞을 경우 + 현재 맞은 숫자의 개수가 2보다 작을경우 6
    최소 순위 = 7 - 0현재 맞은 숫자의 개수
               만약 현재 맞은 숫자의 개수가 2보다 작을경우 6
*/

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        boolean visited[] = new boolean[46];
        
        for(int num : win_nums){ //당첨 번호 방문처리
            visited[num]=true;
        }
        
        int zero_cnt = 0;
        int success_cnt = 0;
        
        for(int num : lottos){ //숫자 방문처리
            if(num == 0){
                zero_cnt++;
            }else if(!visited[num]){
                continue;        
            }else{
                success_cnt++;
            }
        }
        
        answer[0] = success_cnt+zero_cnt >1 ? 7-(success_cnt+zero_cnt) : 6;
        answer[1] = success_cnt >1 ? 7-success_cnt : 6;
 
        return answer;
    }
}