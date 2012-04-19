package com.zombiecalypse.downloadsorter;

import java.lang.annotation.Retention;

import com.google.inject.BindingAnnotation;

import static java.lang.annotation.RetentionPolicy.*;

@BindingAnnotation
@Retention(RUNTIME)
public @interface RulesInjection {

}
