package exercice_1;

import org.junit.Assert;
import org.junit.Test;

public class CurrencyExchangerTest {
    @Test
    public void euro_to_dollar() {
        //Assert.assertEquals(0, Double.compare(0.75, CurrencyExchanger.exchange(1)));
        Assert.assertEquals(0, Double.compare(1.50, CurrencyExchanger.exchange(2)));
    }

}