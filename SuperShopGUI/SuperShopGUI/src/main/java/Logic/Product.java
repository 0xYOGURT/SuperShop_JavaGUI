package Logic;
public class Product extends ShopRole implements Stockable{
    private double price;
    private double vate;
    private int quantity;

    public Product()
    {
        super();
    }
    
    public Product(String name,double price,int quantity)
    {
        super(name);
        this.price = price;
        this.vate = 2.5;
        this.quantity=quantity;
    }
    
    
    public void setPrice(double price)
    {
        this.price=price;
    }
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }
    public void setVate(double vate)
    {
        this.vate=vate;
    }
    
    public double getVate()
    {
        return vate;
    }

    public double getPrice()
    {
        return price;
    }
    public int getQuantity()
    {
        return quantity;
    }

    @Override
    public boolean checkStock(int n)
    {
        if(quantity<n)
        {
            return false;
        }
        return true;
    }

    @Override
    public void availableQuantity(int n) 
    {
        quantity -= n;
    }
    @Override
    public void addQuantity(int n)
    {
        quantity += n ;
    }
    @Override
    public void Display()
    {
        System.out.println("Product name: "+getName());
        System.out.println("Product price: "+price);
        System.out.println("Vate: "+vate);
        System.out.println("Available Quantity: "+quantity);

    }
}
