/*
    각 던전을 모두 겹치지 않게 가도록 dfs탐색 실행
    이때 더이상 피로도를 소모할 수 없으면 방문한 개수(dfs 실행 깊이)의 최대값 계산
    던전의 개수는 최대 8이므로 O(n) = 2^8 의 시간복잡도로 가능
*/

class Solution {
    static boolean visited[];
    static int maxCnt;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];    
        goDungeon(0,k,dungeons);
        return maxCnt;
    }
    
    public void goDungeon(int cnt,int health,int[][] dungeons){
        maxCnt = Math.max(maxCnt,cnt);
        
        for(int i=0;i<dungeons.length;i++){
            if(visited[i]){ //이미 방문한 던전인 경우
                continue;
            }
            if(dungeons[i][0] > health){ //최소 입장 필요 체력이 없을 경우
                continue;
            }
            
            visited[i]=true;
            goDungeon(cnt+1,health - dungeons[i][1],dungeons);
            visited[i]=false;
        }
    }
}