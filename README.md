# java refactoring kata
The goal of this project is to practice refactoring skills through different exercices.

## Exercice 1 - the currency exchanger
Before begining, let's have a look at the tests.
```
    @Test
    public void euro_to_dollar() {
        //Assert.assertEquals(0, Double.compare(0.75, CurrencyExchanger.exchange(1)));
        Assert.assertEquals(0, Double.compare(1.50, CurrencyExchanger.exchange(2)));
    }
```
There is only one test which ensures the exchange rate is 1 dollar for 0.75 euro.
Before changing the code, make sure the test passes.

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

public class Euro {

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

### Task 3
Objectives:
- change signature

Change the signature of the exchange method to make is accept a Dollar object.
You will have to move the call of the getAmount method inside the exchange method.

### Task 4
Objectives:
- extract Superclass
- pull members up

Add the following test in to your test suite:
```
    @Test
    public void equality() {
        Assert.assertEquals(new Euro(1), new Euro(1));
        Assert.assertEquals(new Dollar(1), new Dollar(1));
    }
```
To make it pass you need to implement the equals and hash methods in the Euro and Dollar classes.
For instances, you can  write it this way:
```
package exercice_1;

public class Euro {

    private double amount;

    Euro(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Euro euro = (Euro) o;

        return Double.compare(euro.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(amount);
        return (int) (temp ^ (temp >>> 32));
    }
}
```
Now you have a lot of duplicated code between the Euro and the Dollar classes, let's remove it.

From the Euro class, extract the Superclass Money.
Pull all the members (fields and methods) from the Euro class to Money class.
Adapt the Dollar class to make it extends the Money class.
You should have something like this:
```
package exercice_1;

public class Money {
    protected double amount;

    public Money(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(amount);
        return (int) (temp ^ (temp >>> 32));
    }
}

public class Euro extends Money {
    Euro(double amount) {
        super(amount);
    }
}

public class Dollar extends Money {
    Dollar(double amount) {
        super(amount);
    }
}

```