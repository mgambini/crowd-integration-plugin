package it.ditech.go.authorization.crowd.annotation;

public interface Metadata {
    boolean isRequired();

    boolean isSecure();

    FieldType getType();
}
