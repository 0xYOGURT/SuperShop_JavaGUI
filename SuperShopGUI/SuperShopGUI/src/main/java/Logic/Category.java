package Logic;

public class Category
{
    private String type;
    private Product[] product ;

    

    public void setType(String type)
    {
        this.type=type;
    }
    public String getType()
    {
        return type;
    }
    public void setProductNumber(int n)
    {
        product = new Product[n];
        for(int i=0;i<n;i++)
        {
            product[i] = new Product();
        }
    }
    public Product[] getProduct()
    {
        return product;
    }

    
}