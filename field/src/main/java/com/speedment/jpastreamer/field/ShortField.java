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

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.jpastreamer.field.method.ShortSetter;
import com.speedment.jpastreamer.field.trait.HasComparableOperators;
import com.speedment.runtime.compute.ToShort;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.jpastreamer.field.comparator.ShortFieldComparator;
import com.speedment.jpastreamer.field.internal.ShortFieldImpl;
import com.speedment.jpastreamer.field.method.ShortGetter;
import com.speedment.jpastreamer.field.trait.HasShortValue;
import com.speedment.runtime.typemapper.TypeMapper;

/**
 * A field that represents a primitive {@code short} value.
 * <p>
 * Generated by com.speedment.sources.pattern.FieldPattern.
 * 
 * @param <ENTITY> entity type
 * @param <D>      database type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
@GeneratedCode(value = "Speedment")
public interface ShortField<ENTITY, D> extends Field<ENTITY>, HasShortValue<ENTITY, D>, HasComparableOperators<ENTITY, Short>, ToShort<ENTITY>, ShortFieldComparator<ENTITY, D> {
    
    /**
     * Creates a new {@link ShortField} using the default implementation.
     * 
     * @param <ENTITY>   entity type
     * @param <D>        database type
     * @param identifier column that this field represents
     * @param getter     method reference to getter in entity
     * @param setter     method reference to setter in entity
     * @param typeMapper type mapper that is applied
     * @param unique     if column only contains unique values
     * @return           the created field
     */
    static <ENTITY, D> ShortField<ENTITY, D> create(
    ColumnIdentifier<ENTITY> identifier,
            ShortGetter<ENTITY> getter,
            ShortSetter<ENTITY> setter,
            TypeMapper<D, Short> typeMapper,
            boolean unique) {
        return new ShortFieldImpl<>(
            identifier, getter, setter, typeMapper, unique
        );
    }
    
    @Override
    ShortField<ENTITY, D> tableAlias(String tableAlias);
    
    @Override
    default short applyAsShort(ENTITY entity) {
        return getAsShort(entity);
    }
    
    @Override
    ShortFieldComparator<ENTITY, D> comparator();
    
    @Override
    default ShortFieldComparator<ENTITY, D> reversed() {
        return comparator().reversed();
    }
    
    @Override
    default ShortField<ENTITY, D> getField() {
        return this;
    }
}