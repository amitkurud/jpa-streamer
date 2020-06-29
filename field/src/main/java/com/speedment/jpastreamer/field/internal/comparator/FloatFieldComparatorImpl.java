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
package com.speedment.jpastreamer.field.internal.comparator;

import com.speedment.jpastreamer.field.comparator.FieldComparator;
import com.speedment.jpastreamer.field.comparator.FloatFieldComparator;
import com.speedment.jpastreamer.field.comparator.NullOrder;
import com.speedment.jpastreamer.field.trait.HasFloatValue;

import java.util.Objects;

import static com.speedment.common.invariant.NullUtil.requireNonNulls;
import static java.util.Objects.requireNonNull;

/**
 * @param <ENTITY> entity type
 * @param <D>      database type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
public final class FloatFieldComparatorImpl<ENTITY, D>
extends AbstractFieldComparator<ENTITY> 
implements FloatFieldComparator<ENTITY, D> {
    
    private final HasFloatValue<ENTITY, D> field;
    private final boolean reversed;
    
    public FloatFieldComparatorImpl(HasFloatValue<ENTITY, D> field) {
        this(field, false);
    }
    
    FloatFieldComparatorImpl(HasFloatValue<ENTITY, D> field, boolean reversed) {
        this.field    = requireNonNull(field);
        this.reversed = reversed;
    }
    
    @Override
    public HasFloatValue<ENTITY, D> getField() {
        return field;
    }
    
    @Override
    public NullOrder getNullOrder() {
        return NullOrder.NONE;
    }
    
    @Override
    public boolean isReversed() {
        return reversed;
    }
    
    @Override
    public FloatFieldComparatorImpl<ENTITY, D> reversed() {
        return new FloatFieldComparatorImpl<>(field, !reversed);
    }
    
    @Override
    public int compare(ENTITY first, ENTITY second) {
        requireNonNulls(first, second);
        final float a = field.getAsFloat(first);
        final float b = field.getAsFloat(second);
        return applyReversed(Float.compare(a, b));
    }
    
    @Override
    public int hashCode() {
        return (4049 + Objects.hashCode(this.field.table())) * 3109
            + Boolean.hashCode(reversed);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FieldComparator)) return false;
        
        @SuppressWarnings("unchecked")
        final FieldComparator<ENTITY> casted =
            (FieldComparator<ENTITY>) obj;
        
        return reversed == casted.isReversed()
            && Objects.equals(
                field.table(),
                casted.getField().table()
            );
    }
    
    @Override
    public String toString() {
        return "(order by " + field.table() + " " +
            (reversed ? "descending" : "ascending") + ")";
    }
    
    private int applyReversed(float compare) {
        if (compare == 0) {
            return 0;
        } else {
            if (reversed) {
                if (compare > 0) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (compare > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
}