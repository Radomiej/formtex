package pl.radomiej.formtex;

import java.util.Collection;
import java.util.LinkedList;

public class TxTransformer<I, O> {
    private Collection<TxTransformator<I, O>> transformers;


    public TxTransformer() {
        transformers = new LinkedList<>();
    }

    public void addTransformator(TxTransformator<I, O> transformator) {
        transformers.add(transformator);
    }

    public O createAndTransform(I modelToTransform, Class<O> clazz) throws IllegalAccessException, InstantiationException {
        O outputModel = clazz.newInstance();
        return transform(modelToTransform, outputModel);
    }

    public O transform(I modelToTransform, O outputModel) {
        transformers.forEach(transformator -> transformator.transform(modelToTransform, outputModel));
        return outputModel;
    }


}
