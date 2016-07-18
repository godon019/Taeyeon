package godon.Product;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-04.
 */
public class Products {

    ArrayList<Product> productArr;

    public ArrayList<Product> getProductArr() {
        return productArr;
    }

    public Products(){

        productArr = new ArrayList<Product>();
        productArr.add(new Product("일련번호", Product.IOType.WRITE_ONLY));
        productArr.add(new ComparableProduct("모델명", Product.IOType.READ_AND_WRITE, ComparableProduct.ValueType.STRING));
        productArr.add(new ComparableProduct("최저가", Product.IOType.READ_AND_WRITE, ComparableProduct.ValueType.NUMBER));
        productArr.add(new ComparableProduct("갱신된 최저가", Product.IOType.WRITE_ONLY, ComparableProduct.ValueType.NUMBER));
        productArr.add(new Product("로그", Product.IOType.WRITE_ONLY));
        try {
            setPrimitiveProduct("모델명");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setPrimitiveProduct(String name) throws Exception{
        int hasName = 0;
        for(Product product : productArr){
            if(product.getName().equals(name)){
                hasName ++;
                product.setItAsPrimitive();
            }
        }

        if(hasName == 0){
            throw new Exception("Primitive 를 설정할  Name : " + name + " 을 가진 Product 가 없습니다");
        }
        else if(hasName > 1){
            throw new Exception("Primitive 를 설정할  Name : " + name + " 을 가진 Product 가 여러개 입니다");
        }
    }


    public void initializeNullValuesOfProductToProperValuesWithPrimeProduct() throws Exception{

        int sizeOfNotNullValue = 0;
        sizeOfNotNullValue = getPrimeProduct().getValues().size();
        initializeProductsWithNotNullValue(sizeOfNotNullValue);
    }

    Product getPrimeProduct() throws Exception{
        Product productToReturn = null;
        int primeProductCount = 0;
        for(Product product : productArr){
            if(product instanceof PrimeProduct){
                productToReturn = product;
                primeProductCount++;
            }
        }

        if(primeProductCount == 0){
            throw new Exception("There is no Prime Product");
        }
        if(primeProductCount > 1){
            throw new Exception("There are more than one Prime Product");
        }

        return productToReturn;
    }

    void initializeProductsWithNotNullValue(int sizeOfNotNullValue){
        for(Product product : productArr){
            if(product.getValues() == null){
                ArrayList<String> values = new ArrayList<String>();
                for(int i = 0; i<sizeOfNotNullValue; i++){
                    values.add("갱신 안됨");
                }
                product.setValues(values);
            }
        }
    }


    public void setProductArr(String name, ArrayList<String> value) throws Exception{
        for(Product product : productArr){
           if(product.getName().equals(name)){
               product.setValues(value);
               return;
           }
        }
        throw new Exception("해당 하는 이름 :" +name+" 의 set할 Product가 존재 하지 않습니다");
    }

    public Product getProductArr(String name)throws Exception{
            for (Product product : productArr) {
                if (product.getName().equals(name)) {
                    return product;
                }
            }
        throw new Exception("해당 하는 이름 :" +name+" 의 get할 Product가 존재 하지 않습니다");

    }

}
