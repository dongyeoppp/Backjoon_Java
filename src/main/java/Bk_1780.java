import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_1780 {
    public static String[][] map;
    public static int[] result;
    // 모두 같은 수로 되어 있는지 체크
    public static String check(int row, int col, int n){
        String save = map[row][col];
        for(int i = row; i<row+n;i++){
            for(int j = col; j<col+n;j++){
                if (!map[i][j].equals(save)){
                    return "error";
                }
            }
        }
        return save;
    }

    public static void recursion(int row,int col,int n){
        // 모두 같은 수로 되어 있을 경우
        if (!check(row,col,n).equals("error")){
            String answer = map[row][col];
            if (answer.equals("-1")){
                result[0] += 1;
            }
            if (answer.equals("0")){
                result[1] += 1;
            }
            if (answer.equals("1")){
                result[2] += 1;
            }
            return;
        }
        // 종이가 모두 같은 수로 되어 있지 않을 경우 (2) 번 과정 수행
        int base = n ;
        n = n / 3;
        for(int i=row;i<row+base;i+=n){
            for(int j =col;j<col+base;j+=n){
                recursion(i,j,n);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new String[n][n];
        for(int i=0;i<n;i++){
            map[i] = br.readLine().split(" ");
        }
        // 결과값 저장하는 배열
        result = new int[3];
        recursion(0,0,n);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }
}
