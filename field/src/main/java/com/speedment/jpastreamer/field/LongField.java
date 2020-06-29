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

import com.speedment.jpastreamer.field.internal.LongFieldImpl;
import com.speedment.jpastreamer.field.method.LongGetter;
import com.speedment.jpastreamer.field.trait.HasAttributeConverterClass;
import com.speedment.jpastreamer.field.trait.HasComparableOperators;
import com.speedment.jpastreamer.field.trait.HasLongValue;
import com.speedment.runtime.compute.ToLong;
import com.speedment.jpastreamer.field.comparator.LongFieldComparator;

import javax.persistence.AttributeConverter;

/**
 * A field that represents a primitive {@code long} value.
 * <p>
 * Generated by com.speedment.sources.pattern.FieldPattern.
 * 
 * @param <ENTITY> entity type
 * @param <D>      database type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
public interface LongField<ENTITY, D> extends
        Field<ENTITY>,
        HasLongValue<ENTITY, D>,
        HasComparableOperators<ENTITY, Long>,
        ToLong<ENTITY>,
        LongFieldComparator<ENTITY, D>,
        HasAttributeConverterClass<Long, D>
{
    
    /**
     * Creates a new {@link LongField} using the default implementation.
     * 
     * @param <ENTITY>   entity type
     * @param <D>        database type
     * @param table      the table that this field belongs to
     * @param columnName the name of the database column the field represents
     * @param getter     method reference to getter in entity
     * @param attributeConverterClass the attribute converter class
     * @param unique     if column only contains unique values
     * @return           the created field
     */
    static <ENTITY, D> LongField<ENTITY, D> create(
            Class<ENTITY> table,
            String columnName,
            LongGetter<ENTITY> getter,
            Class<? extends AttributeConverter<Long, ? super D>> attributeConverterClass,
            boolean unique) {
        return new LongFieldImpl<>(
                table, columnName, getter, attributeConverterClass, unique
        );
    }

    @Override
    default long applyAsLong(ENTITY entity) {
        return getAsLong(entity);
    }
    
    @Override
    LongFieldComparator<ENTITY, D> comparator();
    
    @Override
    default LongFieldComparator<ENTITY, D> reversed() {
        return comparator().reversed();
    }
    
    @Override
    default LongField<ENTITY, D> getField() {
        return this;
    }
}