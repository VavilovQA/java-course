package demoqaApiTests;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Arsentiy Vavilov
 */
@Target({ElementType.METHOD, ElementType.TYPE})  // Можно вешать на метод или класс
@Retention(RetentionPolicy.RUNTIME)
public @interface WithLogin {

}
