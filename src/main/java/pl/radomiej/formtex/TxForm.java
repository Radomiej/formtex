package pl.radomiej.formtex;


import lombok.Builder;
import lombok.Singular;

import java.util.Collection;
import java.util.LinkedList;


public class TxForm<T> {

    private Collection<TxValidator<T>> validators;

    public TxForm() {
        validators = new LinkedList<>();
    }

    public void addValidator(TxValidator<T> validator){
        validators.add(validator);
    }

    public boolean validate(T model){
        return validators.stream().allMatch(validator -> validator.valid(model));
    }
}
