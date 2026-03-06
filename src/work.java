import java.util.*;

class DNSEntry {
    String ip;
    long expiryTime;

    DNSEntry(String ip, int ttl) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttl * 1000;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class DNSCache {

    HashMap<String, DNSEntry> cache = new HashMap<>();
    int hits = 0, misses = 0;

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                hits++;
                return "Cache HIT → " + entry.ip;
            }
        }

        misses++;

        // simulate upstream DNS
        String newIP = "172.217." + new Random().nextInt(100) + ".1";

        cache.put(domain, new DNSEntry(newIP, 300));

        return "Cache MISS → " + newIP;
    }

    public void stats() {
        int total = hits + misses;
        double hitRate = (hits * 100.0) / total;

        System.out.println("Hit Rate: " + hitRate + "%");
    }
}