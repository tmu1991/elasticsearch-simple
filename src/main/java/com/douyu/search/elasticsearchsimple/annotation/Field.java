package com.douyu.search.elasticsearchsimple.annotation;

/**
 * @author xialian
 * @Title: Field
 * @ProjectName elasticsearch-simple
 * @Description: TODO
 * @date 2018/8/13 15:14
 */
public @interface Field {

    String name() default "";
    boolean store() default false;
    String analyzer() default "";
    String searchAnalyzer() default "";
    FieldType fieldType() default FieldType.NONE;

}
