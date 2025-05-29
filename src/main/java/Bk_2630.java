import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_2630 {
    public static int[][] map;
    public static int[] result;
    // 주어진 종이가 다 같은 색깔인지 확인하는 메서드
    public static int check(int row, int col, int n){
        int save = map[row][col];
        for(int i=row;i<row+n;i++){
            for(int j =col;j<col+n;j++){
                if(map[i][j] != save){
                    // 같은 색이 아니라면 -1 return
                    return -1;
                }
            }
        }
        // 같은 색일 경우 해당 색을 나타내는 숫자 return
        return save;
    }

    public static void recursion(int row, int col, int n){
        int answer = check(row,col,n);
        // 주어진 종이가 같은 색일 경우
        if(answer != -1){
            result[answer] +=1;
            return;
        }
        // 주어진 종이가 다른 색일 경우 재귀를 반복
        int base = n;
        n = n/2;
        for(int i = row; i<row+base; i+=n){
            for(int j = col; j<col+base; j+=n){
                recursion(i,j,n);
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i=0;i<n;i++){
            String[] input = br.readLine().split(" ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        result = new int[2];
        recursion(0,0,n);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
