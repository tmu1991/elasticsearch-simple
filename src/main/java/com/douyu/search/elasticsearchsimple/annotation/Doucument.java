package com.douyu.search.elasticsearchsimple.annotation;

/**
 * @author xialian
 * @Title: Doucument
 * @ProjectName elasticsearch-simple
 * @Description: TODO
 * @date 2018/8/13 15:12
 */
public @interface Doucument {

    String indexName() default "";
    String typeName() default "";
    int replicaNum() default 1;

}
