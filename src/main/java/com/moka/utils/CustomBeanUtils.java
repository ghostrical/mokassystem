package com.moka.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class CustomBeanUtils<T> {
    public T copyNonNullProperties(T source, T destination) {
        if (source == null || destination == null || source.getClass() != destination.getClass()) {
            return null;
        }

        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper dest = new BeanWrapperImpl(destination);

        // BeanWrapper 은  Java 객체의 속성 값을 동적으로 읽거나 설정하는 기능을 제공하는 유틸리티 클래스입니다.
        // 객체간 데이터 복사나 객체간 필드 체크할 때 쓴다고 한다.
        // 여기서는 getPropertyValue, setPropertyValue 기능을 쓰려고 사용했다.

        for (final Field property : source.getClass().getDeclaredFields()) {
            Object sourceProperty = src.getPropertyValue(property.getName());
            System.out.println("property.getName : "+property.getName());
            if (sourceProperty != null && !(sourceProperty instanceof Collection<?>) && !property.getName().contains("Dttm")) {
                dest.setPropertyValue(property.getName(), sourceProperty);
            }
        }
        // source.getClass().getDeclaredFields() 는 source에 해당하는 클래스(source.getClass()) 의 필드들(private int a, private int b 같은거)
        // 뽑는다. 그런 getDeclaredFields 를 Field property 에 for 마다 하나씩 필드를 담음.
        // 만약 MokaCommonLocation moka 일 때, moka.getClass().getDelaredFields() 는 [commonLocationCode, commonLocationName, commonLocationMapx, commonLocationMapy] 다.
        // !(sourceProperty instanceof Collection<?>)는 sourceProperty 가 컬렉션타입인 경우 거른다는 거다.
        // 예를 들어 class A { int a; List<String> b; } 라면 a는 통과하지만 b는 걸린다. b가 널이라면 널로 유지될 것이다.

        return destination;
    }
}
