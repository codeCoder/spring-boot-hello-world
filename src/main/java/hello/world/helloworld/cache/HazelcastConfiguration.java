package hello.world.helloworld.cache;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@EnableCaching
@Profile("hazelcast")
public class HazelcastConfiguration {


    @Value("${HZ_XML_CONFIG:hazelcast-kubernetes.xml}")
    String xmlConfig;

    @Bean
    public Config hazelCastConfig() {
        ClasspathXmlConfig classpathXmlConfig = new ClasspathXmlConfig(xmlConfig);
        return classpathXmlConfig;
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(hazelCastConfig());
    }

    @Bean("hazelCastCacheManager")
    @Primary
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }

    @Bean
    public Cache dateCache() {
        return cacheManager().getCache("dateCache");
    }
}
