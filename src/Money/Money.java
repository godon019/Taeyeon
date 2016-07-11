package Money;

/**
 * Created by Godon on 2016-06-30.
 */
public class Money implements Expression{
    protected int amount = 0;
    protected String currency;

    public Money times(int multiplier){
        return new Money(amount*multiplier, currency);
    }

    public Money(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    public String currency(){
        return currency;
    }

    public static Money dollar(int amount){
        return new Money(amount, "USD");

    }

    public static Money franc(int amount){
        return new Money(amount, "CHF");
    }

    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount
                && currency().equals(money.currency());
    }

    public String toString(){
        return amount + " " + currency;
    }

    public Expression plus(Money addend){
        return new Sum(this, addend);
    }

    public Money reduce(String to){
        return this;
    }
}
