import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_2448 {
    public static char[][] arr;
    public static StringBuilder sb = new StringBuilder();
    public static void recursion(int row, int col, int n){
        // 가장 작은 분할 크기의 삼각형을 표시한다.
        // 재귀 종료 조건
        if (n == 3){
            arr[row][col] = '*';
            arr[row+1][col-1] = '*';
            arr[row+1][col+1] = '*';
            for(int i = col-2;i<=col+2;i++){
                arr[row+2][i] = '*';
            }
            return;
        }
        n = n/2;
        // n을 갱신하며 분할되는 세 삼각형의 좌표를 통해 재귀
        recursion(row,col,n);
        recursion(row+n,col-n,n);
        recursion(row+n,col+n,n);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 맨 꼭대기 점이 (0,n-1)
        arr = new char[n][2*n-1];
        recursion(0,n-1,n);
        for(int i =0;i<n;i++){
            for (int j =0;j<2*n-1;j++){
                if (arr[i][j] != '*'){

                    sb.append(" ");
                }
                else{
                    sb.append("*");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
