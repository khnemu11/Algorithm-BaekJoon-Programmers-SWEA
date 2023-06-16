class Solution {
    public int solution(int n, int m, int[] section) {
        int count = 0;
        
        boolean unPainted[] = new boolean[n+1]; //색칠되지 않은 영역
        
        //색칠해야 되는 부분 표시
        for(int s : section){
            unPainted[s]=true;
        }
        
        int start = 1;  //색칠 시작점
        
        while(start < unPainted.length){
            //색칠이 이미 되어있는경우
            if(!unPainted[start]){
                start++; 
            }
            //색칠이 안되어 있는경우
            else{
                count++; //색칠해야 하므로 색칠 횟수 증가
                
                //m만큼 색칠
                for(int i=0;i<m;i++){
                    //영역을 벗어나면 색칠 불가능
                    if(start>=unPainted.length){
                        break;
                    }
                    unPainted[start++]=false;
                }
            }   
        }
        
        return count;
    }
}