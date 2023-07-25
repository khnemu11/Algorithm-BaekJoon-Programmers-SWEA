// 1 4   3 3
// 3 2   3 3
// 4 1

// 2 3 2   5 4 3
// 4 2 4   2 4 1
// 3 1 4   3 1 1

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        for(int i=0;i<answer.length;i++){
            for(int j=0;j<answer[0].length;j++){
                int sum = 0;
                for(int k=0;k<arr1[0].length;k++){
                    sum += arr1[i][k]*arr2[k][j];
                }
                answer[i][j] = sum;
            } 
        }
        
        return answer;
    }
}