public class EditDistance {
    private static int min(int x,int y,int z)
    {
        if (x < y && x <z) return x;
        if (y < x && y < z) return y;
        else return z;
    }


    public static int minEditDist(String str1, String str2) {
       return minEdit(str1, str2, str1.length(), str2.length());
    }
    public static int minEdit(String str1, String str2, int m, int n){
        // Create a table to store results of subproblems
        int dp[][] = new int[m+1][n+1];

        // Fill d[][] in bottom up manner
        for (int i=0; i<=m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is to
                // isnert all characters of second string until that pt
                if (i == 0)
                    dp[i][j] = j;  // Min. operations = j

                    // If second string is empty, only option is to
                    // remove all characters of second string
                else if (j == 0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last char
                    // and recur for remaining string
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];

                    // If last character are different, consider all
                    // possibilities and find minimum
                else
                    dp[i][j] = 1 + min(dp[i][j - 1],  // Insert
                            dp[i - 1][j],  // Remove
                            dp[i - 1][j - 1]); // Replace
            }
        }
        return dp[m][n];

    }

    public static int minEditDist2(String a, String b) {
        int[][] memo = new int[a.length()+1][b.length()+1];

        for (int i = 0; i <a.length()+1; i++) {
            for (int j = 0; j <b.length()+1 ; j++) {
                memo[i][j] = -1;
                if(i==0) memo[i][j] = j;
                if(j==0) memo[i][j] = i;
            }
        }

        for (int i = 1; i < a.length()+1 ; i++) {
            for (int j = 1; j < b.length()+1; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) memo[i][j] = 0;
                if(memo[i][j] == -1)  memo[i][j] = min(memo[i-1][j-1], memo[i][j-1], memo[i-1][j]) +1;
                if(memo[i][j] == 0) memo[i][j] = memo[i-1][j-1];
            }
        }
        return memo[a.length()][b.length()];
    }

}
