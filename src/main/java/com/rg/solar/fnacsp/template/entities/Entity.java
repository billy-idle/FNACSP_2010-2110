package com.rg.solar.fnacsp.template.entities;

public abstract class Entity<T> {
    final T t;

    /**
     * @param t Magnitude notNull
     */
    public Entity(T t) {
        this.t = t;
        assesInput();
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    protected abstract void assesInput();

    /**
     * Because of the dependency inversion principle
     *
     * @return Magnitude
     */
    public final T getT() {
        return this.t;
    }
}

class EntityException extends RuntimeException {
    EntityException(String message) {
        super(message);
    }
}