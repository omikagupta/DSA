/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
      return binarySearch(1,n);
    }
    public int binarySearch(int left,int right){
        int mid=left+(right-left)/2;
        int result=guess(mid);
        if(result==0) return mid;
    if(result == 1)
        return binarySearch(mid + 1, right);

    return binarySearch(left, mid - 1);
    }
}