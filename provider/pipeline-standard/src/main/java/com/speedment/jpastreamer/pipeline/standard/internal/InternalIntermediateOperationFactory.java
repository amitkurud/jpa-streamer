package com.speedment.jpastreamer.pipeline.standard.internal;

import com.speedment.jpastreamer.javanine.Java9StreamUtil;
import com.speedment.jpastreamer.pipeline.IntermediateOperationFactory;
import com.speedment.jpastreamer.pipeline.intermediate.IntermediateOperationType;
import com.speedment.jpastreamer.pipeline.intermediate.IntermediateOperator;

import java.util.Comparator;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class InternalIntermediateOperationFactory implements IntermediateOperationFactory {

    @Override
    public <T> IntermediateOperator<Stream<T>, Stream<T>> createFilter(final Predicate<? super T> predicate) {
        requireNonNull(predicate);
        final UnaryOperator<Stream<T>> function = s -> s.filter(predicate);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.FILTER,
                Stream.class,
                Stream.class,
                function,
                predicate);

    }

    @Override
    public <T, R> IntermediateOperator<Stream<T>, Stream<R>> createMap(final Function<? super T, ? extends R> mapper) {
        requireNonNull(mapper);
        final Function<Stream<T>, Stream<R>> function = s -> s.map(mapper);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.MAP,
                Stream.class,
                Stream.class,
                function,
                mapper);

    }

    @Override
    public <T> IntermediateOperator<Stream<T>, IntStream> createMapToInt(final ToIntFunction<? super T> mapper) {
        requireNonNull(mapper);
        final Function<Stream<T>, IntStream> function = s -> s.mapToInt(mapper);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.MAP_TO,
                Stream.class,
                IntStream.class,
                function,
                mapper);
    }

    @Override
    public <T> IntermediateOperator<Stream<T>, LongStream> createMapToLong(final ToLongFunction<? super T> mapper) {
        requireNonNull(mapper);
        final Function<Stream<T>, LongStream> function = s -> s.mapToLong(mapper);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.MAP_TO,
                Stream.class,
                LongStream.class,
                function,
                mapper);
    }

    @Override
    public <T> IntermediateOperator<Stream<T>, DoubleStream> createMapToDouble(final ToDoubleFunction<? super T> mapper) {
        requireNonNull(mapper);
        final Function<Stream<T>, DoubleStream> function = s -> s.mapToDouble(mapper);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.MAP_TO,
                Stream.class,
                DoubleStream.class,
                function,
                mapper);
    }

    @Override
    public <T, R> IntermediateOperator<Stream<T>, Stream<R>> createFlatMap(final Function<? super T, ? extends Stream<? extends R>> mapper) {
        requireNonNull(mapper);
        final Function<Stream<T>, Stream<R>> function = s -> s.flatMap(mapper);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.FLAT_MAP,
                Stream.class,
                Stream.class,
                function,
                mapper);
    }

    @Override
    public <T> IntermediateOperator<Stream<T>, IntStream> createFlatMapToInt(final Function<? super T, ? extends IntStream> mapper) {
        requireNonNull(mapper);
        final Function<Stream<T>, IntStream> function = s -> s.flatMapToInt(mapper);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.FLAT_MAP_TO,
                Stream.class,
                IntStream.class,
                function,
                mapper);
    }

    @Override
    public <T> IntermediateOperator<Stream<T>, LongStream> createFlatMapToLong(final Function<? super T, ? extends LongStream> mapper) {
        requireNonNull(mapper);
        final Function<Stream<T>, LongStream> function = s -> s.flatMapToLong(mapper);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.FLAT_MAP_TO,
                Stream.class,
                LongStream.class,
                function,
                mapper);
    }

    @Override
    public <T> IntermediateOperator<Stream<T>, DoubleStream> createFlatMapToDouble(final Function<? super T, ? extends DoubleStream> mapper) {
        requireNonNull(mapper);
        final Function<Stream<T>, DoubleStream> function = s -> s.flatMapToDouble(mapper);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.FLAT_MAP_TO,
                Stream.class,
                DoubleStream.class,
                function,
                mapper);
    }

    @Override
    public <T> IntermediateOperator<Stream<T>, Stream<T>> createDistinct() {
        final UnaryOperator<Stream<T>> function = Stream::distinct;
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.DISTINCT,
                Stream.class,
                Stream.class,
                function);

    }

    @Override
    public <T> IntermediateOperator<Stream<T>, Stream<T>> createSorted() {
        final UnaryOperator<Stream<T>> function = Stream::sorted;
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.SORTED,
                Stream.class,
                Stream.class,
                function);
    }

    @Override
    public <T> IntermediateOperator<Stream<T>, Stream<T>> createSorted(final Comparator<? super T> comparator) {
        requireNonNull(comparator);
        final UnaryOperator<Stream<T>> function = s -> s.sorted(comparator);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.SORTED,
                Stream.class,
                Stream.class,
                function,
                comparator);

    }

    @Override
    public <T> IntermediateOperator<Stream<T>, Stream<T>> createPeek(final Consumer<? super T> action) {
        requireNonNull(action);
        final UnaryOperator<Stream<T>> function = s -> s.peek(action);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.PEEK,
                Stream.class,
                Stream.class,
                function,
                action);

    }

    @Override
    public <T> IntermediateOperator<Stream<T>, Stream<T>> createLimit(final long maxSize) {
        if (maxSize < 0)
            throw new IllegalArgumentException(Long.toString(maxSize));

        final UnaryOperator<Stream<T>> function = s -> s.limit(maxSize);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.LIMIT,
                Stream.class,
                Stream.class,
                function,
                maxSize);

    }

    @Override
    public <T> IntermediateOperator<Stream<T>, Stream<T>> createSkip(final long n) {
        if (n < 0)
            throw new IllegalArgumentException(Long.toString(n));

        final UnaryOperator<Stream<T>> function = s -> s.skip(n);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.SKIP,
                Stream.class,
                Stream.class,
                function,
                n);

    }

    @Override
    public <T> IntermediateOperator<Stream<T>, Stream<T>> createTakeWhile(final Predicate<? super T> predicate) {
        requireNonNull(predicate);
        final UnaryOperator<Stream<T>> function = s -> Java9StreamUtil.takeWhile(s, predicate);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.TAKE_WHILE,
                Stream.class,
                Stream.class,
                function,
                predicate);
    }

    @Override
    public <T> IntermediateOperator<Stream<T>, Stream<T>> createDropWhile(final Predicate<? super T> predicate) {
        requireNonNull(predicate);
        final UnaryOperator<Stream<T>> function = s -> Java9StreamUtil.dropWhile(s, predicate);
        return new StandardIntermediateOperator<>(
                IntermediateOperationType.DROP_WHILE,
                Stream.class,
                Stream.class,
                function,
                predicate);
    }
}