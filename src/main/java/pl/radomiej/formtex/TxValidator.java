package pl.radomiej.formtex;

public interface TxValidator<T> {
    boolean valid(T objectToValidate);
}
