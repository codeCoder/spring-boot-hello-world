package hello.world.helloworld.cache;

import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Profile("hazelcast")
public class CacheBean {

    @Autowired
    private Cache dateCache;

    public void addRequestDate(Date date) {
        dateCache.put(date.hashCode(), date);
    }

    public int getNumberOfDatesInCache() {
        return ((IMap) dateCache.getNativeCache()).size();
    }
}
