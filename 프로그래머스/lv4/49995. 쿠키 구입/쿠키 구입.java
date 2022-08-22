class Solution {
    public int solution(int[] cookie) {
          int answer = 0,total=0;
			 for(int i=0;i<cookie.length;i++) {
				 total = total+cookie[i];
			 }
			// System.out.println(total);
			 for(int mid=0;mid<cookie.length-1;mid++) {
				// System.out.println("mid : "+mid);
				 int l=mid,m=mid+1,lCookie=cookie[l],rCookie=cookie[m];
		
				 for(int i=l-1;i>=0;i--) {
					 if(lCookie+cookie[i]>total/2) {
						 break;
					 }
					 l=i;
					 lCookie+=cookie[i];
				 }
				
				 for(int i=m+1;i<cookie.length;i++) {
					 if(rCookie+cookie[i]>total/2) {
						 break;
					 }
					 m=i;
					 rCookie+=cookie[i];
				 }
				// System.out.println("init L and R "+lCookie+" "+rCookie);
				 while(true) {
					// System.out.println("--> current L and R "+lCookie+" "+rCookie);
					 if(lCookie==rCookie) {
					//	 System.out.println("find new answer " +rCookie);
						 if(answer < rCookie) {
						//	 System.out.println("change new answer " +answer + "to" +rCookie);
							 answer = rCookie;
						 }
						 break;
					 }
					 else if(lCookie>rCookie) {
						 lCookie-=cookie[l];
						 l++;
					 }
					 else if(rCookie>lCookie) {
						 rCookie-=cookie[m];
						 m--;
					 }
					 if(l>mid || m<=mid) {
						 break;
					 }
				 }
				// System.out.println("answer " +answer);
			 }
			 
			  
			 return answer;
    }
}