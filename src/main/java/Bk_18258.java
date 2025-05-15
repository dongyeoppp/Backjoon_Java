import java.io.*;
import java.util.*;
public class Bk_18258 {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> que = new LinkedList<>();
        // 아래와 같이 deque를 사용할 수도 있음
        // deque를 사용할 경우 큐읭 마지막 값을 getLast()로 확인 가능
//        Deque<Integer> que = new ArrayDeque<>();
        int number = 0;
        for(int i = 0 ; i < n ; i++){
            String[] arr = br.readLine().split(" ");
            if(arr[0].equals("push")){
                number = Integer.parseInt(arr[1]);
                que.add(number);
            }
            else if (arr[0].equals("pop")){
                if (que.isEmpty()){
                    sb.append(-1).append("\n");
                }
                else{
                    // 맨 앞 값을 없애고 반환
                    sb.append(que.poll()).append("\n");
                }
            }
            else if (arr[0].equals("size")){
                sb.append(que.size()).append("\n");
            }
            else if (arr[0].equals("empty")){
                if (que.isEmpty()){
                    sb.append(1).append("\n");
                }
                else{
                    sb.append(0).append("\n");
                }
            }
            else if (arr[0].equals("front")){
                if(que.isEmpty()){
                    sb.append(-1).append("\n");
                }
                else{
                    // 맨 앞 값이 무엇인지 만 확인
                    sb.append(que.peek()).append("\n");
                }
            }
            else if (arr[0].equals("back")){
                if(que.isEmpty()){
                    sb.append(-1).append("\n");
                }
                else{
                    sb.append(number).append("\n");
                }
            }
        }
        System.out.println(sb.toString().trim());
    }
}
