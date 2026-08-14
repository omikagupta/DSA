/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        return binarysearch(1,n);
    }
    private int binarysearch( int left,int right){
        if(left>right) return left;
        int mid=left+(right-left)/2;
        if(isBadVersion(mid)) return binarysearch(left,mid-1);
        return binarysearch(mid+1,right);
    }
}