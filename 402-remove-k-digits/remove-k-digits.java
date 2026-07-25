class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> st=new Stack<>();
        for(char ch:num.toCharArray()){
            while(k>0 && !st.isEmpty()&&st.peek()>ch){
                st.pop();
                k--;
            }
               st.push(ch);
        }
 
        while(k>0){
            st.pop();
            k--;
        }
  StringBuilder ans=new StringBuilder();
 int i=0;
while(!st.isEmpty()){
    ans.append(st.pop());
}
ans.reverse();
while(i<ans.length()&& ans.charAt(i)=='0'){
    i++;
}
if (i == ans.length()) {
    return "0";
}
return ans.substring(i);
    }
}