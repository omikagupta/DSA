class Solution {
    public boolean isHappy(int n) {
int slow=square(n);
int fast=square(square(n));
while(fast != slow){
    slow=square(slow);
    fast=square(square(fast));
}
return slow==1;
      
    }
    public int square(int n){
        int sum=0;
        while(n > 0){  
        int digit=n%10;
        sum+=(digit*digit);
        n=n/10;
        }
return sum;
    
    }
}