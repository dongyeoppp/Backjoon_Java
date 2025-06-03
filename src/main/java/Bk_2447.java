import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_2447 {
    public static char[][] arr;
    public static StringBuilder sb = new StringBuilder();
    public static void recursion(int row, int col, int n){
        // 재귀 종료 조건
        if (n == 3){
            int cnt = 0;
            for(int i= row;i<row+n;i++){
                for(int j= col;j<col+n;j++){
                    // 가운데 공백 구분, 4번째 '*'찍는 부분은 무조건 생략 됨
                    if (cnt == 4){
                        cnt++;
                        continue;
                    }
                    arr[i][j] = '*';
                    cnt++;
                }
            }
            return;
        }
        int base = n;
        n = n/3;
        int count = 0;
        for(int i =row;i<row+base;i+=n){
            for(int j=col;j<col+base;j+=n){
                // 이 부분도 마찬가지로 공백을 제외하고 재귀를 수행
                if (count == 4){
                    count++;
                    continue;
                }
                recursion(i,j,n);
                count++;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        recursion(0,0,n);
        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j] != '*'){
                    sb.append(" ");
                }
                else{
                    sb.append(arr[i][j]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }
}
