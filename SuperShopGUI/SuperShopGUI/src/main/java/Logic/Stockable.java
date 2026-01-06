package Logic;
public interface Stockable {

    boolean checkStock(int n);
    void availableQuantity(int n); // Product purchase er pore stock theke minus korar jonno
    void addQuantity(int n);

}
