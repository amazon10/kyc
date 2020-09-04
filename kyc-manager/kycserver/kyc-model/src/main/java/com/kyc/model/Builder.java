package com.kyc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Builder<T> {

    private final Supplier<T> instantiator;

    private List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> Builder<T> of(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }

    public <P1> Builder<T> with(Consumer1<T, P1> consumer, P1 p1) {
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }

    public <P1, P2> Builder<T> with(Consumer2<T, P1, P2> consumer, P1 p1, P2 p2) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3> Builder<T> with(Consumer3<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4> Builder<T> with(Consumer4<T, P1, P2, P3, P4> consumer, P1 p1, P2 p2, P3 p3, P4 p4) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4, P5> Builder<T> with(Consumer5<T, P1, P2, P3, P4, P5> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4, P5, P6> Builder<T> with(Consumer6<T, P1, P2, P3, P4, P5, P6> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5, p6);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4, P5, P6, P7> Builder<T> with(Consumer7<T, P1, P2, P3, P4, P5, P6, P7> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5, p6, p7);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4, P5, P6, P7, P8> Builder<T> with(Consumer8<T, P1, P2, P3, P4, P5, P6, P7, P8> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5, p6, p7, p8);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4, P5, P6, P7, P8, P9> Builder<T> with(Consumer9<T, P1, P2, P3, P4, P5, P6, P7, P8, P9> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5, p6, p7, p8, p9);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> Builder<T> with(Consumer10<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        modifiers.add(c);
        return this;
    }

    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }

    /**
     * 1 参数 Consumer
     */

    @FunctionalInterface

    public interface Consumer1<T, P1> {
        void accept(T t, P1 p1);
    }

    /**
     * 2 参数 Consumer
     */

    @FunctionalInterface

    public interface Consumer2<T, P1, P2> {
        void accept(T t, P1 p1, P2 p2);
    }

    /**
     * 3 参数 Consumer
     */

    @FunctionalInterface
    public interface Consumer3<T, P1, P2, P3> {
        void accept(T t, P1 p1, P2 p2, P3 p3);
    }

    @FunctionalInterface
    public interface Consumer4<T, P1, P2, P3, P4> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    @FunctionalInterface
    public interface Consumer5<T, P1, P2, P3, P4, P5> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }

    @FunctionalInterface
    public interface Consumer6<T, P1, P2, P3, P4, P5, P6> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);
    }

    @FunctionalInterface
    public interface Consumer7<T, P1, P2, P3, P4, P5, P6, P7> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7);
    }

    @FunctionalInterface
    public interface Consumer8<T, P1, P2, P3, P4, P5, P6, P7, P8> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8);
    }

    @FunctionalInterface
    public interface Consumer9<T, P1, P2, P3, P4, P5, P6, P7, P8, P9> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9);
    }

    @FunctionalInterface
    public interface Consumer10<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10);
    }
}
