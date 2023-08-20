package algorithms.set;

class KnapsackProblem {
  static int max(int a, int b) { return (a > b) ? a : b; }

//  Time Complexity: O(2^N)
//  Auxiliary Space: O(N), Stack space required for recursion
  static int resursionKnapSack(int W, int wt[], int val[], int n) {
    // Base Case
    if (n == 0 || W == 0)
      return 0;
    if (wt[n - 1] > W)
      return resursionKnapSack(W, wt, val, n - 1);
    else
      return max(val[n - 1]
              + resursionKnapSack(W - wt[n - 1], wt,
              val, n - 1),
          resursionKnapSack(W, wt, val, n - 1));
  }

  //Top down approch(Memoization)
//  Time Complexity: O(N * W). As redundant calculations of states are avoided.
//  Auxiliary Space: O(N * W) + O(N). The use of a 2D array data structure for storing intermediate states and O(N) auxiliary stack space(ASS) has been used for recursion stack
  static int dpTopDownKnapSack(int W, int wt[], int val[], int n, int[][] dp) {
    if (n == 0 || W == 0)
      return 0;
    if (dp[n][W] != -1)
      return dp[n][W];

    if (wt[n - 1] > W)
      return dp[n][W]
          = dpTopDownKnapSack(W, wt, val, n - 1, dp);
    else

      // Return value of table after storing
      return dp[n][W]
          = max((val[n - 1]
              + dpTopDownKnapSack(W - wt[n - 1], wt, val,
              n - 1, dp)),
          dpTopDownKnapSack(W, wt, val, n - 1, dp));
  }

//  Time Complexity: O(N * W). where ‘N’ is the number of elements and ‘W’ is capacity.
//  Auxiliary Space: O(N * W). The use of a 2-D array of size ‘N*W’.
  static int bottomUpKnapSack(int W, int wt[], int val[], int n)
  {
    int i, w;
    int K[][] = new int[n + 1][W + 1];

    // Build table K[][] in bottom up manner
    for (i = 0; i <= n; i++) {
      for (w = 0; w <= W; w++) {
        if (i == 0 || w == 0)
          K[i][w] = 0;
        else if (wt[i - 1] <= w)
          K[i][w]
              = max(val[i - 1]
                  + K[i - 1][w - wt[i - 1]],
              K[i - 1][w]);
        else
          K[i][w] = K[i - 1][w];
      }
    }

    return K[n][W];
  }
    public static void main(String args[]){
      int profit[] = new int[] { 60, 100, 120 };
    int weight[] = new int[] { 10, 20, 30 };
    int W = 50;
    int N = profit.length;
      int dp[][] = new int[N + 1][W + 1];
      for (int i = 0; i < N + 1; i++)
        for (int j = 0; j < W + 1; j++)
          dp[i][j] = -1;

      //  System.out.println(resursionKnapSack(W, weight, profit, N));
      //System.out.println(dpTopDownKnapSack(W, weight, profit, N, dp));
      System.out.println(bottomUpKnapSack(W, weight, profit, N));
    }
}