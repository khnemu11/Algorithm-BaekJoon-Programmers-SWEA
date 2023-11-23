/*
    걸린시간 : 2시간 8초
    
    열쇠가 자물쇠 밖으로 나간 상태로 확인하는 부분 
    -> 자물쇠의 9배 크기의 배열(keyBoard)을 생성해서 정 가운데에 열쇠를 둠
    -> 그 후 9방향 벡터를 keyBoard의 N,N(= 열쇠 좌표의 0,0)부터 자물쇠크기 만큼 탐색하여 같은지 확인
    
*/
import java.util.*;

class Solution {
    int EMPTY = 2;
    int HOLL = 0; //구멍
    int FILLED = 1; //돌기
    int M,N,hollCount;
    int[] dx = {-1,-1,-1,0,0,0,1,1,1};
    int[] dy = {-1,0,1,-1,0,1,-1,0,1};
    
    public boolean solution(int[][] key, int[][] lock) {
        int[][] keyBoard = new int[lock.length*3][lock.length*3];
        M = key.length;
        N = lock.length;
        
        //열쇠 배열 초기화
        for(int i=0;i<keyBoard.length;i++){
            Arrays.fill(keyBoard[i],EMPTY);
        }
        
        //정 가운데에 열쇠 값을 저장
        for(int i=0;i<M;i++){
            for(int j=0;j<M;j++){
                keyBoard[N+i][N+j] = key[i][j];
            }
        }
        
        //자물쇠의 구멍 개수를 확인
        for(int i=0;i<lock.length;i++){
            for(int j=0;j<lock.length;j++){
                if(lock[i][j] == HOLL){
                    hollCount++;
                }
            }    
        }
        
        //탐색 시작
        //4번 돌리기
        for(int r=0;r<4;r++){
            //9방향 벡터로 열쇠를 이동
            for(int k=0;k<dx.length;k++){
                //행을 i만큼 이동
                for(int i=0;i<N;i++){
                    //열을 j만큼 이동
                     for(int j=0;j<N;j++){
                        int startRow = N+dx[k]*i;
                        int startCol = N+dy[k]*j;
                        if(isSame(lock,keyBoard,startRow,startCol)){
                            return true;
                        }
                    }  
                }   
            }
            
            //열쇠를 돌리기
            keyBoard = rotate(keyBoard);
        }

        return false;
    }
    //시작점 부터 열쇠를 확인해서 같은지 확인
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
    
    //시계방향으로 90도 돌리기
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