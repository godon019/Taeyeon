package godon.Product;

/**
 * Created by Godon on 2016-07-18.
 */
public class PrimeProduct extends Product{

    public PrimeProduct(String name, IOType iOType) {
        super(name, iOType);
        this.isPrimitive = true;
    }

    public Boolean isPrimitive() {
        return isPrimitive;
    }
}
