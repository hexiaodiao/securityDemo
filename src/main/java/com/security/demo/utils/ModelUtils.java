package com.security.demo.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Relic
 */
@Component
public class ModelUtils {

    public static <T> List<T> copyList(List<?> doList, Class<T> voClass) {
        List<T> voList = new ArrayList<>();
        try {
            T voObj;
            for (Object doObj : doList) {
                voObj = voClass.newInstance();
                BeanUtils.copyProperties(doObj, voObj);
                voList.add(voObj);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return voList;
    }

    public static <T> T copyProperties(Object source, Class<T> tClass) {
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert t != null;
        BeanUtils.copyProperties(source, t);
        return t;
    }
}
