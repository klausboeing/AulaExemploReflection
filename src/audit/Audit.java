/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para marcação de um método auditável.
 * @author Klaus Boeing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Audit {
    /**
     * Define uma descrição para o método. 
     * Caso o valor não for definido, será considerado o nome do próprio método.
     * @return a descrição do método
     */
    String value() default "";
}
