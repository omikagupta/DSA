import java.util.*;

class Solution {

    static class Group {
        int start, length;

        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    static class SparseTable {
        int[][] st;
        int[] log;

        SparseTable(int[] arr) {
            int n = arr.length;
            log = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                log[i] = log[i / 2] + 1;
            }

            int k = log[n] + 1;
            st = new int[k][n];

            for (int i = 0; i < n; i++)
                st[0][i] = arr[i];

            for (int j = 1; j < k; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    st[j][i] = Math.max(st[j - 1][i],
                            st[j - 1][i + (1 << (j - 1))]);
                }
            }
        }

        int query(int l, int r) {
            int j = log[r - l + 1];
            return Math.max(st[j][l], st[j][r - (1 << j) + 1]);
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        List<Group> groups = new ArrayList<>();
        int[] groupIdx = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    groups.get(groups.size() - 1).length++;
                } else {
                    groups.add(new Group(i, 1));
                }
            }
            groupIdx[i] = groups.size() - 1;
        }

        List<Integer> ans = new ArrayList<>();

        if (groups.isEmpty()) {
            for (int i = 0; i < queries.length; i++)
                ans.add(ones);
            return ans;
        }

        int[] merge = new int[Math.max(0, groups.size() - 1)];
        for (int i = 0; i + 1 < groups.size(); i++) {
            merge[i] = groups.get(i).length + groups.get(i + 1).length;
        }

        SparseTable st = merge.length == 0 ? null : new SparseTable(merge);

        for (int[] q : queries) {

            int l = q[0];
            int r = q[1];

            int left = -1;
            if (groupIdx[l] != -1) {
                Group g = groups.get(groupIdx[l]);
                left = g.length - (l - g.start);
            }

            int right = -1;
            if (groupIdx[r] != -1) {
                Group g = groups.get(groupIdx[r]);
                right = r - g.start + 1;
            }

            int startAdj = groupIdx[l] + 1;
            int endGroup = (s.charAt(r) == '1') ? groupIdx[r] : groupIdx[r] - 1;
            int endAdj = endGroup - 1;

            int best = ones;

            if (s.charAt(l) == '0'
                    && s.charAt(r) == '0'
                    && groupIdx[l] + 1 == groupIdx[r]) {
                best = Math.max(best, ones + left + right);
            } else if (st != null && startAdj <= endAdj) {
                best = Math.max(best, ones + st.query(startAdj, endAdj));
            }

            if (s.charAt(l) == '0'
                    && groupIdx[l] + 1 <= ((s.charAt(r) == '1') ? groupIdx[r] : groupIdx[r] - 1)) {
                best = Math.max(best,
                        ones + left + groups.get(groupIdx[l] + 1).length);
            }

            if (s.charAt(r) == '0'
                    && groupIdx[l] < groupIdx[r] - 1) {
                best = Math.max(best,
                        ones + right + groups.get(groupIdx[r] - 1).length);
            }

            ans.add(best);
        }

        return ans;
    }
}