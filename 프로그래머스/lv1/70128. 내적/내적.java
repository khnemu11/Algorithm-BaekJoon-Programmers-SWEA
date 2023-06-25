class Solution {
    public int solution(int[] a, int[] b) {
        int product=0;
        for(int i=0;i<a.length;i++){
            product += a[i]*b[i];
        }
        
        return product;
    }
}