import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_7562 {
    public static int[][] map;
    public static int l;
    public static Deque<int[]> deque;
    public static int[] dx = {-2,-2,-1,1,2,2,1,-1};
    public static int[] dy = {-1,1,2,2,1,-1,-2,-2};
    public static StringBuilder sb = new StringBuilder();
    public static void bfs(int start_row, int start_col, int end_row, int end_col){
        deque.add(new int[] {start_row,start_col});
        // map에 이동할 때마다 1씩 더해주며 최단거리와 방문 여부를 동시에 체크한다.
        // 어느 지점에 도착했을 경우 처음 도착했을 때가 최단거리 .. 백트래킹이나 최단 거리를 갱신할 필요는 없다.
        map[start_row][start_col] = 1;
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            int row = removed[0];
            int col = removed[1];
            // 도착 지점 일 경우
            if (row == end_row && col == end_col){
                return;
            }
            for(int i =0;i<8;i++){
                int x = dx[i] + row;
                int y = dy[i] + col;
                if (0<=x && x<l && 0<=y && y<l && map[x][y] == 0){
                    deque.add(new int[] {x,y});
                    map[x][y] = map[row][col] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++){
            l = Integer.parseInt(br.readLine());
            map = new int[l][l];
            String[] start = br.readLine().split(" ");
            String[] end = br.readLine().split(" ");
            int start_row = Integer.parseInt(start[0]);
            int start_col = Integer.parseInt(start[1]);
            int end_row = Integer.parseInt(end[0]);
            int end_col = Integer.parseInt(end[1]);
            deque = new ArrayDeque<>();
            bfs(start_row,start_col,end_row,end_col);
            sb.append(map[end_row][end_col]-1).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
}
