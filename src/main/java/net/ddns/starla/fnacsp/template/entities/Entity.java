package net.ddns.starla.fnacsp.template.entities;

public abstract class Entity {
    final Object value;

    /**
     * @param value notNull
     */
    public Entity(Object value) {
        this.value = value;
        assesInput();
    }

    /**
     * It should throw a runtime exception if it doesn't belong to the valid interval or range.
     */
    protected abstract void assesInput();

    /**
     * Because of the dependency inversion principle
     *
     * @return The value or magnitude
     */
    public final Object getValue() {
        return value;
    }
}