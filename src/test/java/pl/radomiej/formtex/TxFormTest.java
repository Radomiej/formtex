package pl.radomiej.formtex;

import org.junit.jupiter.api.Test;
import pl.radomiej.formtex.exceptions.TxValidateException;
import pl.radomiej.formtex.models.ExampleModelOne;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TxFormTest {

    @Test
    void validationWorkingExampleTest(){
        ExampleModelOne exampleModelOne = new ExampleModelOne();
        exampleModelOne.setId(2);
        exampleModelOne.setName("test");
        exampleModelOne.setEmail("test@test.pl");
        exampleModelOne.setPassword("testPass");

        TxForm<ExampleModelOne> formExample = new TxForm<ExampleModelOne>();
        formExample.addValidator(e -> e.getName() != null);
        formExample.addValidator(e -> e.getEmail().contains("@"));
        formExample.addValidator(e -> e.getPassword().length() > 4);

        assertEquals(true, formExample.validate(exampleModelOne));

    }

    @Test
    void validationNotWorkingExampleTest(){
        ExampleModelOne exampleModelOne = new ExampleModelOne();
        exampleModelOne.setId(2);
        exampleModelOne.setName(null);
        exampleModelOne.setEmail("test@test.pl");
        exampleModelOne.setPassword("testPass");

        TxForm<ExampleModelOne> formExample = new TxForm<ExampleModelOne>();
        formExample.addValidator(e -> e.getName() != null);

        assertEquals(false, formExample.validate(exampleModelOne));

    }

    @Test
    void validationNotWorkingExampleShouldReturnExceptionTest() throws TxValidateException {
        ExampleModelOne exampleModelOne = new ExampleModelOne();
        exampleModelOne.setId(2);
        exampleModelOne.setName(null);
        exampleModelOne.setEmail("test@test.pl");
        exampleModelOne.setPassword("testPass");

        TxForm<ExampleModelOne> formExample = new TxForm<ExampleModelOne>();
        formExample.addValidator(e -> {
            if(e.getName() == null) throw new TxValidateException();
            return true;
        });

        assertThrows(TxValidateException.class, () -> formExample.validate(exampleModelOne));

    }

}
