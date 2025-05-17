import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_10026 {
    private static String[] arr;
    private static StringBuilder sb = new StringBuilder();
    private static boolean[][] visited;
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    private static int n;

    public static void bfs(int row, int col){
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {row,col});
        visited[row][col] = true;
        // save에 알파벳 저장
        char save = arr[row].charAt(col);
        while(!deque.isEmpty()){
            int[] arr1 = deque.poll();
            for(int i = 0; i<4; i++){
                int x = dx[i] + arr1[0];
                int y = dy[i] + arr1[1];
                if (0<= x && x<n && 0<=y && y<n && !visited[x][y] && arr[x].charAt(y) == save){
                    deque.add(new int[] {x,y});
                    visited[x][y] = true;
                }
            }
        }
    }

    // 구역의 개수 찾는 메서드
    private static int find(){
        visited = new boolean[n][n];
        int count = 0;
        for(int i =0 ; i<n; i++){
            for(int j=0; j<n; j++){
                if (!visited[i][j]) {
                    bfs(i,j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new String[n];
        for (int i = 0 ; i<n; i++){
            String input = br.readLine();
            arr[i] = input;
        }

        sb.append(find()).append(" ");
        // 적록색약일 경우를 고려하여 문자열의 'G'문자를 'R'로 교체
        for(int i= 0; i<n; i++){
            arr[i] = arr[i].replace('G','R');
        }

        sb.append(find()).append(" ");

        System.out.println(sb.toString().trim());
    }
}
