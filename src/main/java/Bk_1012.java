import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Position{
    int x;
    int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Bk_1012 {
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1,};
    private static int[][] map;
    private static int n;
    private static int m;
    private static StringBuilder sb = new StringBuilder();
    public static void bfs(int row, int col){
        Deque<Position> deque = new ArrayDeque<>();
        deque.add(new Position(row,col));
        // map의 값을 바꾸며 방문 여부 체크
        map[row][col] = 2;
        while (!deque.isEmpty()){
            Position pos = deque.poll();
            for (int i =0; i<4; i++){
                int x = dx[i] + pos.x;
                int y = dy[i] + pos.y;
                if (0<= x && x<n && 0<=y && y<m && map[x][y] == 1){
                    deque.add(new Position(x,y));
                    map[x][y] = 2;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i<t ; i++){
            String[] arr = br.readLine().split(" ");
            m = Integer.parseInt(arr[0]);
            n = Integer.parseInt(arr[1]);
            int k = Integer.parseInt(arr[2]);
            int result = 0;
            map = new int[n][m];
            for (int j = 0; j<k; j++){
                String[] arr1 = br.readLine().split(" ");
                int col = Integer.parseInt(arr1[0]);
                int row = Integer.parseInt(arr1[1]);
                map[row][col] = 1;
            }
            for(int q = 0; q<n; q++){
                for (int p =0; p<m; p++){
                    if (map[q][p] == 1){
                        bfs(q,p);
                        result++;
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
}
