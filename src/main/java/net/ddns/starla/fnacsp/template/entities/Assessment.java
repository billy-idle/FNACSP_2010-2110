package net.ddns.starla.fnacsp.template.entities;

public interface Assessment {
    /**
     * @return true, if it belongs to the valid interval or range.
     */
    boolean isItValid();

    /**
     * It should throw a runtime exception if it doesn't belong to the valid interval or range.
     */
    void assesInput();
}
