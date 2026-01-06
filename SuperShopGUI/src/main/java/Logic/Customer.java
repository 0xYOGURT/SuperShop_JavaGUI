package Logic;
public class Customer extends ShopRole implements Purchasable{
    private double discount ;
    private String contactInfo;

    public Customer()
    {
        
    }
    public Customer(String name,double discount,String contactInfo)
    {
        super(name);
        this.discount=discount;
        this.contactInfo=contactInfo;
    }

    public void setDiscountRate(double discount)
    {
        this.discount=discount;
    }
    public void setContactInfo(String contact)
    {
        contactInfo = contact;
    }
    public String getContact()
    {
        return contactInfo;
    }
    public double getDiscount()
    {
        return discount;
    }

    @Override
    public double applyDiscount(double amount)
    {
        if(amount>1000)
        {
            discount = amount * 2 / 100 ;
        }
        else if(amount>2000)
        {
            discount = amount * 2.5 / 100 ;
        }
        else if(amount>5000)
        {
            discount = amount * 3 / 100 ;
        }
        else if(amount>10000)
        {
            discount = amount * 5.5 / 100 ;
        }
        else
        {
            discount = amount * 0 / 100 ;
        }
        return discount;
    }

    @Override
    public void Display()
    {
        System.out.println("Customer name: "+getName());
        System.out.println("Contact Info: "+contactInfo);
        System.out.println("Discount: "+discount);
    }
}
