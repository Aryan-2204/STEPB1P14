import java.util.*;

public class AutoCompleteSystem {

    HashMap<String,Integer> queryFreq=new HashMap<>();

    public void updateFrequency(String query){

        queryFreq.put(query,queryFreq.getOrDefault(query,0)+1);
    }

    public void search(String prefix){

        queryFreq.entrySet()
                .stream()
                .filter(e->e.getKey().startsWith(prefix))
                .sorted((a,b)->b.getValue()-a.getValue())
                .limit(10)
                .forEach(e->System.out.println(e.getKey()+" "+e.getValue()));
    }
}