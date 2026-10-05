class Solution {
    public int findGCD(int[] nums) {
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
max=Math.max(max,nums[i]);
min=Math.min(min,nums[i]);
        }
        return gcd(min,max,nums);
    }
    public int gcd(int a,int b,int[] nums){
        while(b!=0){
            int temp=a%b;
            a=b;
            b=temp;
        }
        return a;
    }
}