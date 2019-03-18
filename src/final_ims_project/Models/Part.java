
package final_ims_project.Models;

public abstract class Part {
    
    public abstract int getPartCount();
    
    public abstract int getPartID();

    public abstract void setPartID(int partID);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract double getPrice();

    public abstract void setPrice(double price);

    public abstract int getInStock();

    public abstract void setInStock(int inStock);

    public abstract int getMin();

    public abstract void setMin(int min);

    public abstract int getMax();

    public abstract void setMax(int max);
    
    public abstract String toString();
    
}
