package com.daviddev16.core.profiles;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/* Define uma classe como configuração de ambiente de produção */
@Profile( value = "prod" )
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProductionProfile { }
