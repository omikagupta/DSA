class Solution {
    public boolean isPalindrome(String s) {
s=s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
String String_reversed=new StringBuilder(s).reverse().toString(); 
 if(s.equals(String_reversed)){
    return true;
 }

return false;
    }

}