import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Bk_2577 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 1;
        for(int i = 0 ; i<3; i++){
            String input = br.readLine();
            ans *= Integer.parseInt(input);
        }
        // HashMap 사용해 풀이
        String str_ans = Integer.toString(ans);
        HashMap<String,Integer> map = new HashMap<>();
        for(int i = 0; i<10; i++){
            map.put(Integer.toString(i),0);
        }
        for(char answer : str_ans.toCharArray()){
            String key = Character.toString(answer);
            map.put(key,map.get(key)+1);
        }
        for (String key : map.keySet()){
            System.out.println(map.get(key));
        }
    }
}
