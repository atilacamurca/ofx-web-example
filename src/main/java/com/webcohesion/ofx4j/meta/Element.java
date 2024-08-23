/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webcohesion.ofx4j.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * An OFX element, applied to a javabean property.
 *
 * @author Ryan Heaton
 */
@Target ( ElementType.METHOD )
@Retention ( RetentionPolicy.RUNTIME)
public @interface Element {

  /**
   * The name of the element.
   *
   * @return The name of the element.
   */
  String name();

  /**
   * Whether this element is required.
   *
   * @return Whether this element is required.
   */
  boolean required() default false;

  /**
   * The order this element comes in its parent aggregate.
   *
   * @return The order this element comes in its parent aggregate.
   */
  int order();
}
