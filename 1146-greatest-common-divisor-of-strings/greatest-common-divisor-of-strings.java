class Solution {
    public String gcdOfStrings(String str1, String str2) {

        int m=str1.length();
        int n=str2.length();
        if((str1+str2 ).equals(str2+str1)){
          int len=  gcd(str1,str2,m,n);
            return str1.substring(0,len);
        }
        
        return "";
    }
    public int gcd(String str1, String str2,int a ,int b){
        while(b!=0){
int temp=a%b;
a=b;
b=temp;

        }
        return a;
    }
}