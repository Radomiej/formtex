package pl.radomiej.formtex;

public interface TxTransformator<I, O> {
    void transform(I input, O output);
}
