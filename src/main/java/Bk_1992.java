import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_1992 {
    public static int[][] map;
    public static StringBuilder sb = new StringBuilder();

    // 압축 가능 여부 확인
    public static int check(int row, int col, int n){
        int save = map[row][col];
        if (n == 1){
            return save;
        }
        for(int i = row; i<row+n;i++){
            for(int j= col; j<col+n;j++){
                // 압축 불가능
                if(map[i][j] != save){
                    return -1;
                }
            }
        }
        return save;
    }

    public static void recursion(int row, int col, int n){
        int answer = check(row,col,n);
        // 압축 가능 할 경우
        if (answer != -1){
            sb.append(answer);
            return;
        }
        // 압축 불 가능할 경우 범위 나눠서 재귀
        sb.append("(");
        int base = n;
        n = n/2;
        for(int i=row;i<row+base;i+=n){
            for(int j=col;j<col+base;j+=n){
                recursion(i,j,n);
            }
        }
        sb.append(")");
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i=0;i<n;i++){
            String input = br.readLine();
            for(int j =0; j<n;j++){
                if (input.charAt(j) == '0'){
                    map[i][j] = 0;
                }
                else{
                    map[i][j] = 1;
                }
            }
        }
        recursion(0,0,n);
        System.out.println(sb.toString().trim());
    }
}
