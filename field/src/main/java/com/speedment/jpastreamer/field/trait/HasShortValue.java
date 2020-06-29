/*
 *
 * Copyright (c) 2006-2020, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.jpastreamer.field.trait;

import com.speedment.jpastreamer.field.Field;
import com.speedment.jpastreamer.field.method.GetShort;

/**
 * A representation of an Entity field that is a primitive {@code short} type.
 * 
 * @param <ENTITY> entity type
 * @param <D>      database type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
public interface HasShortValue<ENTITY, D> extends Field<ENTITY> {
    
    @Override
    GetShort<ENTITY, D> getter();

    /**
     * Gets the value from the Entity field.
     * 
     * @param entity the entity
     * @return       the value of the field
     */
    default short getAsShort(ENTITY entity) {
        return getter().applyAsShort(entity);
    }

}