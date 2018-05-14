package hello.world.helloworld;

import hello.world.helloworld.cache.CacheBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class HelloWorldEndpoint {

    @Autowired(required = false)
    CacheBean cacheBean;

    @GetMapping(path = "/hello", produces = "text/text")
    public String helloWorld(@RequestParam(defaultValue = "world") String name, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (cacheBean != null) {
            cacheBean.addRequestDate(new Date());
            return "hello " + name + ", number of request til now: " + cacheBean.getNumberOfDatesInCache();
        }
        return "hello " + name;
    }
}
