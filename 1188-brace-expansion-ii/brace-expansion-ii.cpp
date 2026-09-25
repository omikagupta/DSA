#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <stack>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<string> braceExpansionII(string expression) {
        stack<set<string>> setStack;
        stack<vector<set<string>>> listStack;
        
        set<string> currentSet;
        vector<set<string>> currentList;
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression[i];
            
            if (isalpha(c)) {
                // Letter represents a singleton set {c}
                set<string> letterSet = {string(1, c)};
                currentSet = concatenate(currentSet, letterSet);
            } else if (c == '{') {
                // Save current state to stacks
                setStack.push(currentSet);
                listStack.push(currentList);
                
                currentSet.clear();
                currentList.clear();
            } else if (c == ',') {
                // Union the current concatenation set into currentList
                currentList.push_back(currentSet);
                currentSet.clear();
            } else if (c == '}') {
                // Finish the current comma-separated list
                currentList.push_back(currentSet);
                
                // Union all sets inside the braces
                set<string> unionSet = performUnion(currentList);
                
                // Restore state from stacks
                currentSet = setStack.top(); setStack.pop();
                currentList = listStack.top(); listStack.pop();
                
                // Concatenate the evaluated brace group with previous prefix
                currentSet = concatenate(currentSet, unionSet);
            }
        }
        
        // Add remaining set to list and perform final union
        currentList.push_back(currentSet);
        set<string> resultSet = performUnion(currentList);
        
        // std::set automatically keeps elements sorted and unique
        vector<string> result(resultSet.begin(), resultSet.end());
        return result;
    }

private:
    // Concatenates every word in set1 with every word in set2 (Cartesian Product)
    set<string> concatenate(const set<string>& set1, const set<string>& set2) {
        if (set1.empty()) return set2;
        if (set2.empty()) return set1;
        
        set<string> res;
        for (const string& s1 : set1) {
            for (const string& s2 : set2) {
                res.insert(s1 + s2);
            }
        }
        return res;
    }
    
    // Combines (unions) all sets in the list into a single set
    set<string> performUnion(const vector<set<string>>& list) {
        set<string> res;
        for (const auto& s : list) {
            res.insert(s.begin(), s.end());
        }
        return res;
    }
};