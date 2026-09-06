
class Solution {

    public List<String> restoreIpAddresses(String s) {

        List<String> res = new ArrayList<>();
        List<String> path = new ArrayList<>();

        backtrack(res, s, path, 0);

        return res;
    }

    void backtrack(
        List<String> res,
        String s,
        List<String> path,
        int start
    ) {

        
        if (path.size() == 4) {

            if (start == s.length()) {
                res.add(String.join(".", path));
            }

            return;
        }

        for (int end = start;
             end < s.length() && end < start + 3;
             end++) {

            String part = s.substring(start, end + 1);

  
            if (part.length() > 1 && part.charAt(0) == '0') {
                break;
            }

            if (Integer.parseInt(part) > 255) {
                break;
            }

            
            path.add(part);

          
            backtrack(res, s, path, end + 1);

            
            path.remove(path.size() - 1);
        }
    }
}
