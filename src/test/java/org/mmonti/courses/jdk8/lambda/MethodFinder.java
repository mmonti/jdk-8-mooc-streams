package org.mmonti.courses.jdk8.lambda;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by mmonti on 9/16/15.
 */
public interface MethodFinder extends Serializable {

    String WRITE_REPLACE = "writeReplace";
    String FWD_SLASH = "/";
    String DOT = ".";

    default SerializedLambda serialized() {
        try {
            Method replaceMethod = getClass().getDeclaredMethod(WRITE_REPLACE);
            replaceMethod.setAccessible(true);
            return (SerializedLambda) replaceMethod.invoke(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Class<?> getContainingClass() {
        try {
            String className = serialized().getImplClass().replaceAll(FWD_SLASH, DOT);
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Method method() {
        SerializedLambda lambda = serialized();
        Class<?> containingClass = getContainingClass();
        return Arrays.asList(containingClass.getDeclaredMethods())
                .stream()
                .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName()))
                .findFirst()
                .orElseThrow(UnableToGuessMethodException::new);
    }

    default Parameter parameter(int n) {
        return method().getParameters()[n];
    }

    default Object defaultValueForParameter(int n) {
        return DefaultValue.ofType(parameter(n).getType());
    }

    class UnableToGuessMethodException extends RuntimeException {}
}
