import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 좌표 정보를 담기 위해 사용
class Position{
    int x;
    int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Bk_11967 {
    // 이차원 배열 생성
    static ArrayList<Position>[][] graph;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][] light;
    static boolean[][] visited;
    // String 형태로 값을 저장할 수 있는 newSet 생성
    static Set<String> newSet = new HashSet<>();

    static int bfs(int n){
        light = new boolean[n][n];
        visited = new boolean[n][n];
        // que 생성
        Queue<Position> que = new LinkedList<>();
        que.add(new Position(0,0));
        visited[0][0] = true;
        light[0][0] = true;
        int count = 1;
        while (!que.isEmpty()){
            // poll을 사용해 que의 맨앞에 값 제거하며 반환
            Position pos = que.poll();
            int row = pos.x;
            int col = pos.y;
            // 방문한 곳에서 켤 수 있는 방의 스위치 켜기
            for(Position cur : graph[row][col]) {
                String x = String.valueOf(cur.x);
                String y = String.valueOf(cur.y);
                if (!light[cur.x][cur.y]) {
                       light[cur.x][cur.y] = true;
                       count++;
                       // 원래 newSet.contains(cur) -> 이렇게 비교했지만 값이 같아도 주소값이 달라 원하는 결과를 얻을 수 없었음
                      // newSet에 String 타입으로 받으므로써 주소값이 달라도 값이 같으면 que에 값을 넣을 수 있었음
                       if(newSet.contains(x+","+y)){
                           que.add(cur);
                       }
                }
            }
            for (int i =0; i<4; i++){
                int x = row+dx[i];
                int y = col+dy[i];
                if (0<=x && x < n && 0<= y && y < n && !visited[x][y]){
                    // 방문하지 않고, 불이 켜져 있는 곳
                    if (light[x][y]){
                        que.add(new Position(x,y));
                        visited[x][y] = true;
                    }
                    // 방문하지 않았고, 불이 꺼져있는 방 -> 추후에 불이 켜지면 방문가능한 곳이므로 newSet에 좌표 저장
                    else{
                        newSet.add(x+","+y);
                    }
                }
            }

        }
        return count;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split(" ");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);

        graph = new ArrayList[n][n];
        for (int i=0; i<n ; i++){
            for (int j=0 ; j<n ; j++){
                graph[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++){
            String input1 = br.readLine();
            String[] arr1 = input1.split(" ");
            int x = Integer.parseInt(arr1[0]);
            int y = Integer.parseInt(arr1[1]);
            int a = Integer.parseInt(arr1[2]);
            int b = Integer.parseInt(arr1[3]);
            graph[x-1][y-1].add(new Position(a-1,b-1));
        }
        System.out.println(bfs(n));
    }
}
