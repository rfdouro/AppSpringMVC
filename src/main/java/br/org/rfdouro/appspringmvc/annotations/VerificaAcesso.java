/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author romulo.douro
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD}) //can use in method only.
public @interface VerificaAcesso {

 String[] value();
}
