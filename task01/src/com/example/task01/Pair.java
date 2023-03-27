package com.example.task01;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<F,S> {
    final private F first;
    final private S second;
    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
    public F getFirst() {
        return first;
    }
    public S getSecond() {
        return second;
    }

    public boolean equals(Pair pair){
        if (pair.getFirst() == first && pair.getSecond() == second)
            return true;
        else
            return false;
    }
    public static <F,S> Pair<F,S> of(F first, S second) {
        return new Pair<F,S>(first, second);
    }
    public void ifPresent(BiConsumer<? super F, ? super S> consumer) {
        if(first != null && second != null){
            consumer.accept(first, second);
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    // TODO напишите реализацию
}
