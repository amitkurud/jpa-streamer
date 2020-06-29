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
package com.speedment.jpastreamer.field;

import com.speedment.jpastreamer.field.trait.HasAttributeConverterClass;
import com.speedment.runtime.compute.ToBoolean;
import com.speedment.jpastreamer.field.comparator.BooleanFieldComparator;
import com.speedment.jpastreamer.field.internal.BooleanFieldImpl;
import com.speedment.jpastreamer.field.method.BooleanGetter;
import com.speedment.jpastreamer.field.trait.HasBooleanOperators;
import com.speedment.jpastreamer.field.trait.HasBooleanValue;

import javax.persistence.AttributeConverter;

/**
 * A field that represents a primitive {@code boolean} value.
 * <p>
 * Generated by com.speedment.sources.pattern.FieldPattern.
 * 
 * @param <ENTITY> entity type
 * @param <D>      database type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
public interface BooleanField<ENTITY, D> extends Field<ENTITY>,
        HasBooleanValue<ENTITY, D>,
        ToBoolean<ENTITY>,
        BooleanFieldComparator<ENTITY, D>,
        HasBooleanOperators<ENTITY>,
        HasAttributeConverterClass<Boolean, D> {
    
    /**
     * Creates a new {@link BooleanField} using the default implementation.
     * 
     * @param <ENTITY>   entity type
     * @param <D>        database type
     * @param table      the table that the field belongs to
     * @param columnName the name of the database column the field represents
     * @param getter     method reference to getter in entity
     * @param attributeConverterClass the attribute converter class
     * @param unique     if column only contains unique values
     * @return           the created field
     */
    static <ENTITY, D> BooleanField<ENTITY, D> create(
    Class<ENTITY> table,
            String columnName,
            BooleanGetter<ENTITY> getter,
            Class<? extends AttributeConverter<Boolean, ? super D>> attributeConverterClass,
            boolean unique) {
        return new BooleanFieldImpl<>(
            table, columnName, getter, attributeConverterClass, unique
        );
    }
    
    @Override
    default boolean applyAsBoolean(ENTITY entity) {
        return getAsBoolean(entity);
    }
    
    @Override
    default BooleanField<ENTITY, D> getField() {
        return this;
    }
}