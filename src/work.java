import java.util.*;

public class AnalyticsDashboard {

    HashMap<String,Integer> pageViews = new HashMap<>();
    HashMap<String,Set<String>> uniqueVisitors = new HashMap<>();
    HashMap<String,Integer> trafficSource = new HashMap<>();

    public void processEvent(String url,String user,String source){

        pageViews.put(url,pageViews.getOrDefault(url,0)+1);

        uniqueVisitors.putIfAbsent(url,new HashSet<>());
        uniqueVisitors.get(url).add(user);

        trafficSource.put(source,trafficSource.getOrDefault(source,0)+1);
    }

    public void showTopPages(){

        pageViews.entrySet()
                .stream()
                .sorted((a,b)->b.getValue()-a.getValue())
                .limit(10)
                .forEach(e->System.out.println(e.getKey()+" : "+e.getValue()));
    }
}