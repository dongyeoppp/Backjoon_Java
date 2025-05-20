import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class Bk_5427 {
    public static char[][] map;
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
        deque.add(new int[] {start_row,start_col,0});
        visited[start_row][start_col] = true;
        // 불이 한칸 움직이고 상근이가 한칸 움직이는 것을 보장하기 위해 check 변수를 사용했다.
        // 불이 붙으려는 칸으로도 상근이는 이동할 수 없으므로 불이 먼저 움직이도록 하였다.
        int check = 0;
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            // 불 한번 움직이면 check+1 해주어 상근이가 움직이고 난 후에 다시 움직일 수 있도록 했다.
            if (check == removed[2]){
                bfs_bull();
                check++;
            }
            for(int i =0;i<4;i++){
                int x = dx[i] + removed[0];
                int y = dy[i] + removed[1];
                if (0 > x || x >= h || 0 > y || y >= w){
                    // map을 벗어났을 경우 최단 거리 반영
                    return removed[2]+1;
                }
                // 빈공간일 경우 이동 가능
                if (!visited[x][y] && map[x][y] == '.'){
                    deque.add(new int[] {x,y,removed[2]+1});
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }

    // 불의 위치에 대한 bfs
    public static void bfs_bull(){
        // 현재 불 위치에서 이동한 번진 곳의 불의 위치를 set1에 저장한다.
        HashSet<int[]> set1 = new HashSet<>();
        for(int[] arr: set){
            for(int k=0;k<4;k++){
                int x = arr[0]+dx[k];
                int y = arr[1]+dy[k];
                if (0<=x && x<h && 0<=y && y <w && map[x][y] != '*' && map[x][y] != '#'){
                    // 불이 번진 빈공간은 '.' 에서 '*'로 바꿔준다.
                    map[x][y] = '*';
                    set1.add(new int[] {x,y});
                }
            }
        }
        // 불이 또 번져야할 곳을 갱신해준다.
        set = set1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++){
            String[] input = br.readLine().split(" ");
            w = Integer.parseInt(input[0]);
            h = Integer.parseInt(input[1]);
            map = new char[h][w];
            set = new HashSet<>();
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
                        set.add(new int[]{j, k});
                    }
                    map[j][k] = ans;
                }
            }
            deque = new ArrayDeque<>();
            visited = new boolean[h][w];
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
