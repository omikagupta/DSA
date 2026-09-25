import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> setStack = new Stack<>();
        Stack<List<Set<String>>> listStack = new Stack<>();
        
        Set<String> currentSet = new HashSet<>();
        List<Set<String>> currentList = new ArrayList<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (Character.isLetter(c)) {
                // Letter represents a singleton set {c}
                Set<String> letterSet = new HashSet<>();
                letterSet.add(String.valueOf(c));
                currentSet = concatenate(currentSet, letterSet);
            } else if (c == '{') {
                // Save current state to stacks
                setStack.push(currentSet);
                listStack.push(currentList);
                
                currentSet = new HashSet<>();
                currentList = new ArrayList<>();
            } else if (c == ',') {
                // Union the current concatenation set into currentList
                currentList.add(currentSet);
                currentSet = new HashSet<>();
            } else if (c == '}') {
                // Finish the current comma-separated list
                currentList.add(currentSet);
                
                // Union all sets inside the braces
                Set<String> unionSet = union(currentList);
                
                // Restore state from stacks
                currentSet = setStack.pop();
                currentList = listStack.pop();
                
                // Concatenate the evaluated brace group with previous prefix
                currentSet = concatenate(currentSet, unionSet);
            }
        }
        
        // Add remaining set to list and perform final union
        currentList.add(currentSet);
        Set<String> resultSet = union(currentList);
        
        // Return sorted list of unique words
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }
    
    // Concatenates every word in set1 with every word in set2 (Cartesian Product)
    private Set<String> concatenate(Set<String> set1, Set<String> set2) {
        if (set1.isEmpty()) return set2;
        if (set2.isEmpty()) return set1;
        
        Set<String> res = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                res.add(s1 + s2);
            }
        }
        return res;
    }
    
    // Combines (unions) all sets in the list into a single set
    private Set<String> union(List<Set<String>> list) {
        Set<String> res = new HashSet<>();
        for (Set<String> set : list) {
            res.addAll(set);
        }
        return res;
    }
}