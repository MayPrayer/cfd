package com.hbnu.interceptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * ClassName: CustomMapper <br/>
 * Description: 解决object类无法序列化
 * date: 2020/2/28 13:36<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public class CustomMapper extends ObjectMapper {
    public CustomMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}