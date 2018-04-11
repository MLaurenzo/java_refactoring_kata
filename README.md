# java refactoring kata
The goal of this project is to practice refactoring skills through different exercice.

## Exercice 1 - the currency exchanger
Before begining, let's have a look at the tests.
```
    @Test
    public void euro_to_dollar() {
        //Assert.assertEquals(0, Double.compare(0.75, CurrencyExchanger.exchange(1)));
        Assert.assertEquals(0, Double.compare(1.50, CurrencyExchanger.exchange(2)));
    }
```
There is only one test which ensure the exchange rate is 1 dollar for 0.75 euro.
Before changing the code, make sure the test pass.

### Task 1
Objectives:
- extract variable
- extract constant

Un comment the first assertion and make it pass.
To do so, extract the "1.50" hardcoded value into a variable.
Then change it to "dollar * 0.75", your test is now suppose to pass.
To be cleaner, extract the "0.75" into a constant.

This kind of pattern is also called "triangulation":
you wait for the second test to refactor the hardcoded value to the real implementation.

### Task 2
Objectives:
- extract method object
- rename method
- extract field

Now your test looks like this:
```
    @Test
    public void euro_to_dollar() {
        Assert.assertEquals(0, Double.compare(0.75, CurrencyExchanger.exchange(1)));
        Assert.assertEquals(0, Double.compare(1.50, CurrencyExchanger.exchange(2)));
    }
```
Extract the "0.75" with method object.
Name the object Euro then rename the invoke method to getAmount.
Extract the "0.75" into a field,
move the value to field declaration,
move the field initializer to constructor,
extract the value to field
and finally move the class in the exercice_1 package.

All these steps should create a Euro class like this one:
```
package exercice_1;

class Euro {

    private double amount;

    Euro(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

```
Do the same task to extract the "1" into a Dollar object,
then modify the second line as well.

Your test should now looks like this:
```
    @Test
    public void euro_to_dollar() {
        Assert.assertEquals(0, Double.compare(new Euro(0.75).getAmount(), CurrencyExchanger.exchange(new Dollar(1).getAmount())));
        Assert.assertEquals(0, Double.compare(new Euro(1.50).getAmount(), CurrencyExchanger.exchange(new Dollar(2).getAmount())));
    }

```