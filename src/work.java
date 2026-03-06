import java.util.*;

public class MultiLevelCache {

    LinkedHashMap<String,String> L1=new LinkedHashMap<>(10000,0.75f,true);
    HashMap<String,String> L2=new HashMap<>();

    public String getVideo(String id){

        if(L1.containsKey(id)){
            return "L1 HIT";
        }

        if(L2.containsKey(id)){
            L1.put(id,L2.get(id));
            return "L2 HIT → promoted to L1";
        }

        String data="VideoData-"+id;

        L2.put(id,data);

        return "L3 Database HIT";
    }
}