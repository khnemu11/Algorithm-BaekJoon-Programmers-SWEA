
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
            
            List<int[][]>boardList = new ArrayList<>();
            
            boardList.add(rotate(keyBoard));
            // boardList.add(moveTop(keyBoard));
            // boardList.add(moveBottom(keyBoard));
            // boardList.add(moveLeft(keyBoard));
            // boardList.add(moveRight(keyBoard));
            
            for(int[][] next : boardList){
                String nextKeyString = ArrToString(next);
                
                if(visited.contains(nextKeyString)){
                    continue;
                }
                
                q.add(nextKeyString);
            }
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
     public int[][] moveRight(int key[][]){
        int[][] moved = new int[key.length][key[0].length];
         
        for(int i=0;i<key.length;i++){
            for(int j=key.length-1;j>0;j--){
                moved[i][j] = key[i][j-1];
            }   
            moved[i][0]=EMPTY;
        }
         
        return moved;
    }
     public int[][] moveTop(int key[][]){
        int[][] moved = new int[key.length][key[0].length];
         
        for(int j=0;j<key.length;j++){
            for(int i=0;i<key.length-1;i++){
                moved[i][j] = key[i+1][j];
            }   
            moved[key.length-1][j]=EMPTY;
        }
         
        return moved;
    }
    public int[][] moveBottom(int key[][]){
        int[][] moved = new int[key.length][key[0].length];
         
        for(int j=0;j<key.length;j++){
           for(int i=key.length-1;i>0;i--){
                moved[i][j] = key[i-1][j];
            }     
            moved[0][j]=EMPTY;
        }
         
        return moved;
    }
     public int[][] moveLeft(int key[][]){
        int[][] moved = new int[key.length][key[0].length];
         
        for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length-1;j++){
                moved[i][j] = key[i][j+1];
            }   
            moved[i][key.length-1]=EMPTY;
        }
         
        return moved;
    }
    public void printArr(int key[][]){
        for(int i=0;i<key.length;i++){
            for(int j=0;j<key[i].length;j++){
                System.out.print(key[i][j]+" ");
            }   
            System.out.println();
        }
        System.out.println();
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