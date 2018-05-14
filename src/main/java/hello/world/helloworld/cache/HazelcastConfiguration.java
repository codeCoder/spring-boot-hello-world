package hello.world.helloworld.cache;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(HazelcastConfiguration.class);

    @Value("${HZ_XML_CONFIG:hazelcast-kubernetes.xml}")
    String xmlConfig;

    @Bean
    public Config hazelCastConfig() {
        ClasspathXmlConfig classpathXmlConfig = new ClasspathXmlConfig(xmlConfig);
/*
        EntryListenerConfig listenerConfig = new EntryListenerConfig();
        listenerConfig.setImplementation(new EntryListener() {
            @Override
            public void mapEvicted(MapEvent event) {
                LOGGER.info("mapEvicted on member " + event.getMember().getUuid());
            }

            @Override
            public void mapCleared(MapEvent event) {
                LOGGER.info("mapCleared on member " + event.getMember().getUuid());
            }

            @Override
            public void entryUpdated(EntryEvent event) {
                LOGGER.info("entryUpdated on member " + event.getMember().getUuid() + ", event new value: " + event.getValue() + " old value:" + event.getOldValue());
            }

            @Override
            public void entryRemoved(EntryEvent event) {
                LOGGER.info("entryRemoved on member " + event.getMember().getUuid() + ", event value: " + event.getValue());
            }

            @Override
            public void entryEvicted(EntryEvent event) {
                LOGGER.info("entryEvicted on member " + event.getMember().getUuid() + ", event value: " + event.getValue());
            }

            @Override
            public void entryAdded(EntryEvent event) {
                LOGGER.info("entryAdded on member " + event.getMember().getUuid() + ", event value: " + event.getValue());
            }
        });

        listenerConfig.setLocal(false);
        classpathXmlConfig.addListenerConfig(listenerConfig);
        */
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
