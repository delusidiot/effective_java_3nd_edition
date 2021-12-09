package chapter2.Item_3;

public class StaticFactorySingleton {
    private static final StaticFactorySingleton INSTANCE = new StaticFactorySingleton();
    private StaticFactorySingleton() {
    }

    public static StaticFactorySingleton getInstance() { return INSTANCE; }
    public void leaveTheBuilding() {

    }
}
