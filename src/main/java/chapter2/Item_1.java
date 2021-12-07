package chapter2;

import java.math.BigInteger;
import java.util.Random;

public class Item_1 {
    public static void main(String[] args) {
        // naming
        Random random = new Random();
        BigInteger bigIntegerConstructor = new BigInteger(10 ,0 , random);
        BigInteger bigIntegerStaticFactoryMethod = BigInteger.probablePrime(10, random);
        System.out.println("bigIntegerConstructor = " + bigIntegerConstructor);
        System.out.println("bigIntegerStaticFactoryMethod = " + bigIntegerStaticFactoryMethod);

        //create instance

        Boolean bool = Boolean.valueOf(true);
        System.out.println("bool = " + bool);
    }
}
