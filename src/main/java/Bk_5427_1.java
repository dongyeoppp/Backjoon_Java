import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class Bk_5427_1 {
    public static int[][] map;
    public static int start_row;
    public static int start_col;
    public static int w,h;
    public static Deque<int[]> deque;
    public static boolean[][] visited;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int result;
    public static HashSet<int[]> set;
    public static StringBuilder sb = new StringBuilder();
    // 상근이 위치에 대한 bfs
    public static int bfs_me(){
        deque = new ArrayDeque<>();
        visited = new boolean[h][w];
        deque.add(new int[] {start_row,start_col,0});
        visited[start_row][start_col] = true;
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            for(int i =0;i<4;i++){
                int x = dx[i] + removed[0];
                int y = dy[i] + removed[1];
                if (0 > x || x >= h || 0 > y || y >= w){
                    // map을 벗어났을 경우 최단 거리 반영
                    return removed[2]+1;
                }
                // 방문하지 않은 곳 + 벽이 아닌 곳 + 불이 번지지 않은 곳
                if (!visited[x][y] && map[x][y] != -1 && map[x][y]> removed[2]+1){
                    deque.add(new int[] {x,y,removed[2]+1});
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }

    // 불의 위치에 대한 bfs
    public static void bfs_bull(){
        while(!deque.isEmpty()){
            int[] arr = deque.poll();
            for(int i=0; i<4;i++){
                int x= dx[i] + arr[0];
                int y= dy[i] + arr[1];
                if (0<=x && x<h && 0<=y && y<w && !visited[x][y] && map[x][y] != -1){
                    deque.add(new int[] {x,y});
                    visited[x][y] = true;
                    map[x][y] = map[arr[0]][arr[1]] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++){
            String[] input = br.readLine().split(" ");
            w = Integer.parseInt(input[0]);
            h = Integer.parseInt(input[1]);
            map = new int[h][w];
            deque = new ArrayDeque<>();
            visited = new boolean[h][w];
            for(int j=0;j<h;j++){
                String input1 = br.readLine();
                for(int k=0;k<w;k++){
                    char ans = input1.charAt(k);
                    // 상근이 위치
                    if (ans == '@'){
                        start_row = j;
                        start_col = k;
                    }
                    // 불 위치
                    if(ans == '*'){
                        deque.add(new int[]{j, k});
                        visited[j][k] = true;
                        map[j][k] = 0;
                        continue;
                    }
                    // 벽 위치
                    if (ans == '#'){
                        map[j][k] = -1;
                        continue;
                    }
                    // 벽 이외의 값은 0
                    map[j][k] = Integer.MAX_VALUE;
                }
            }
            // 불 먼저 이동하며 map에 불의 거리 표시
            bfs_bull();
            result = bfs_me();
            // 탈출이 가능 할 경우 최단 거리 출력
            if (result != -1){
                sb.append(result).append("\n");
            }
            // 탈출 불가능 할 경우
            else {
                sb.append("IMPOSSIBLE").append("\n");
            }
        }
        System.out.println(sb.toString().trim());
    }
}
