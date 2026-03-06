import java.util.*;

class TokenBucket{

    int tokens;
    long lastRefill;
    int max=1000;

    TokenBucket(){
        tokens=max;
        lastRefill=System.currentTimeMillis();
    }
}

public class RateLimiter{

    HashMap<String,TokenBucket> clients=new HashMap<>();

    public synchronized boolean checkRateLimit(String clientId){

        clients.putIfAbsent(clientId,new TokenBucket());
        TokenBucket bucket=clients.get(clientId);

        long now=System.currentTimeMillis();

        if(now-bucket.lastRefill>3600000){
            bucket.tokens=bucket.max;
            bucket.lastRefill=now;
        }

        if(bucket.tokens>0){
            bucket.tokens--;
            return true;
        }

        return false;
    }
}