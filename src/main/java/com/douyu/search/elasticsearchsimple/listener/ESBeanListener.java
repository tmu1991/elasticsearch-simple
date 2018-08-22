package com.douyu.search.elasticsearchsimple.listener;

import com.douyu.search.elasticsearchsimple.annotation.Doucument;
import com.douyu.search.elasticsearchsimple.annotation.FieldType;
import java.lang.reflect.Field;
import java.util.Map;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author xialian
 * @Title: ESBeanListener
 * @ProjectName elasticsearch-simple
 * @Description:
 * @date 2018/8/13 15:31
 */
@Component
public class ESBeanListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> documentMap = event.getApplicationContext().getBeansWithAnnotation(Doucument.class);
        for(String key:documentMap.keySet()){
            Field[] declaredFields = documentMap.get(key).getClass().getDeclaredFields();
            for(Field field:declaredFields){
                com.douyu.search.elasticsearchsimple.annotation.Field fieldAnnotation = field
                    .getAnnotation(com.douyu.search.elasticsearchsimple.annotation.Field.class);
                FieldType fieldType = fieldAnnotation.fieldType();
                String name = fieldAnnotation.name();
                String analyzer = fieldAnnotation.analyzer();
                String searchAnalyzer = fieldAnnotation.searchAnalyzer();
            }
        }
    }
}
