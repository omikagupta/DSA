class Solution {
    public int sumFourDivisors(int[] nums) {
   int answer=0;

        for(int n:nums){
            int count=0;
            int csum=0;

            for(int i =1;i*i<=n;i++){
            if(n%i == 0){
                count++;
                csum+=i;
            
            if(i !=n/i){
                count++;
                csum+=n/i;
            }
            }
        }
          if(count == 4){
        answer+=csum;
        }
        }
      
       
  return answer;      
}
}