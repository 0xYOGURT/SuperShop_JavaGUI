package Logic;
public class Shop {
    private Category c[] = new Category[5] ;

    public Shop()
    {
        for(int i=0;i<c.length;i++)
        {
            c[i] = new Category();
        }
        
        c[0].setType("Select Item...");
        c[1].setType("Grocery");
        c[2].setType("Dairy");
        c[3].setType("Household");
        c[4].setType("Cosmetics");

        c[0].setProductNumber(1);
        c[0].getProduct()[0].setName("Select category first...");
        c[0].getProduct()[0].setPrice(0);
        c[0].getProduct()[0].setQuantity(0);

        c[1].setProductNumber(3);
        c[1].getProduct()[0].setName("Rice");
        c[1].getProduct()[1].setName("Daal");
        c[1].getProduct()[2].setName("Onion");
        c[1].getProduct()[0].setPrice(90);
        c[1].getProduct()[1].setPrice(120);
        c[1].getProduct()[2].setPrice(70);
        c[1].getProduct()[0].setQuantity(20);
        c[1].getProduct()[1].setQuantity(15);
        c[1].getProduct()[2].setQuantity(10);

        c[2].setProductNumber(2);
        c[2].getProduct()[0].setName("Yogurt");
        c[2].getProduct()[1].setName("Butter");
        c[2].getProduct()[0].setPrice(50);
        c[2].getProduct()[1].setPrice(120);
        c[2].getProduct()[0].setQuantity(10);
        c[2].getProduct()[1].setQuantity(10);

        c[3].setProductNumber(2);
        c[3].getProduct()[0].setName("Detergent");
        c[3].getProduct()[1].setName("Toothpaste");
        c[3].getProduct()[0].setPrice(200);
        c[3].getProduct()[1].setPrice(150);
        c[3].getProduct()[0].setQuantity(20);
        c[3].getProduct()[1].setQuantity(25);

        c[4].setProductNumber(3);
        c[4].getProduct()[0].setName("Facewash");
        c[4].getProduct()[1].setName("Moisturizer");
        c[4].getProduct()[2].setName("Sunscreen");
        c[4].getProduct()[0].setPrice(500);
        c[4].getProduct()[1].setPrice(620);
        c[4].getProduct()[2].setPrice(900);
        c[4].getProduct()[0].setQuantity(12);
        c[4].getProduct()[1].setQuantity(15);
        c[4].getProduct()[2].setQuantity(11);
    }

    public Category[] getCategories()
    {
        return c;
    }


}
