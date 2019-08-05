package cn.mzhong.janytask.admin.frame.core;

import cn.mzhong.janytask.admin.frame.vo.ProviderVO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class ProviderManager implements InitializingBean, ApplicationContextAware {

    final List<ProviderVO> providers = new ArrayList<>();
    private ApplicationContext applicationContext;

    public List<ProviderVO> getProviders() {
        return providers;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Provider.class);
        Iterator<Object> iterator = beansWithAnnotation.values().iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            Provider annotation = next.getClass().getAnnotation(Provider.class);
            providers.add(new ProviderVO(annotation.name(), annotation.url()));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
