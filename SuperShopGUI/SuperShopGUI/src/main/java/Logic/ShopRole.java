package Logic;
public abstract class ShopRole {
    private String name;
    
    public ShopRole()
    {
        
    }
    public ShopRole(String name)
    {
        this.name = name ;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    
    public String getName()
    {
        return name;
    }

    public abstract void Display();

}
