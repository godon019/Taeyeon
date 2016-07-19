package godon.Product;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-19.
 */
public class ProductArrayFunctions {
    protected ArrayList<Product> productArr;

    ProductArrayFunctions(){
        productArr = new ArrayList<>();
    }

    public void addProduct(Product product){
        productArr.add(product);
    }

    public void removeProduct(String name){
        for(Product product : productArr){
            if(product.getName() == name){
                productArr.remove(product);
            }
        }
    }

    public String toString(){
        StringBuilder productLists = new StringBuilder();
        productLists.append("Product lists : ");
        for(Product product: productArr){
            productLists.append(product.getName() +", ");
        }

        productLists.append("\n");
        return productLists.toString().trim();
    }
}
