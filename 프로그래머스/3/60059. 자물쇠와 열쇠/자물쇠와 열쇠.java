/*
    걸린시간 : 2시간 8초
*/
import java.util.*;

class Solution {
    Set<String> visited = new HashSet<>();
    int EMPTY = 2;
    int HOLL = 0;
    int FILLED = 1;
    int M,N,hollCount;
    int[] dx = {-1,-1,-1,0,0,0,1,1,1};
    int[] dy = {-1,0,1,-1,0,1,-1,0,1};
    
    public boolean solution(int[][] key, int[][] lock) {
        int[][] keyBoard = new int[lock.length*3][lock.length*3];
        M = key.length;
        N = lock.length;
        
        for(int i=0;i<keyBoard.length;i++){
            Arrays.fill(keyBoard[i],EMPTY);
        }
        
        for(int i=0;i<M;i++){
            for(int j=0;j<M;j++){
                keyBoard[N+i][N+j] = key[i][j];
            }
        }
        
        for(int i=0;i<lock.length;i++){
            for(int j=0;j<lock.length;j++){
                if(lock[i][j] == HOLL){
                    hollCount++;
                }
            }    
        }
        
        Queue<String> q = new LinkedList<>();
        String keyString = ArrToString(keyBoard);
        q.add(keyString);
        boolean answer = false;
        
        while(!q.isEmpty()){
            keyString = q.poll();
            
            if(visited.contains(keyString)){
                continue;
            }
            
            visited.add(keyString);
            
            keyBoard = StringToArr(keyString);
            
            for(int k=0;k<dx.length;k++){
                for(int i=0;i<N;i++){
                     for(int j=0;j<N;j++){
                        int startRow = N+dx[k]*i;
                        int startCol = N+dy[k]*j;
                        if(isSame(lock,keyBoard,startRow,startCol)){
                            return true;
                        }
                    }  
                }   
            }

            int[][] rotated = rotate(keyBoard);
            String nextKeyString = ArrToString(rotated);
                
            if(visited.contains(nextKeyString)){
                continue;
            }
                
            q.add(nextKeyString);
        }

        return false;
    }
    public boolean isSame(int[][] lock, int[][] key,int startRow, int startCol){
        int count=0;
        
        for(int i=0;i<lock.length;i++){
            for(int j=0;j<lock.length;j++){
                if(lock[i][j] == FILLED && key[startRow+i][startCol+j] == FILLED){
                    return false;
                }else if(lock[i][j] == HOLL && key[startRow+i][startCol+j] == FILLED){
                    count++;
                }
            }   
        }
        
        if(count == hollCount){
            return true;
        }
        
        return false;
    }

    public int[][] StringToArr(String key){
         int[][] keyBoard = new int[3*N][3*N];
        
        for(int i=0;i<key.length();i++){
            keyBoard[i/keyBoard.length][i%keyBoard.length] = key.charAt(i) - '0';
        }
        
        return keyBoard;
    }
    public String ArrToString(int[][] keyBoard){
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<keyBoard.length;i++){
            for(int j=0;j<keyBoard.length;j++){
                sb.append(keyBoard[i][j]);
            }   
        }
        
        return sb.toString();
    }
    public int[][] rotate(int[][] key){
        int[][] rotated = new int[key.length][key[0].length];
        
        for(int i=0;i<key.length;i++){
            for(int j=0;j<key[i].length;j++){
                rotated[j][key.length-1-i] = key[i][j];
            }   
        }
        
        return rotated;
    }
}