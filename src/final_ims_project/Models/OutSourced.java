
package final_ims_project.Models;

/**
 * @author Ty
 */
public class OutSourced extends Part {

   //Class variables
    private int partID;
    private String companyName;
    private String partName;
    private double price;
    private int inStock;
    private int min;
    private int max;
    private static int outSourcedPartCount = 0;
    
    
    //Constructor(s)
    public OutSourced(){}
    public OutSourced(int partID, String companyName, String partName, double price, int inStock, int min, int max) {
        this.partID = partID;
        this.companyName = companyName;
        this.partName = partName;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        this.outSourcedPartCount += 1;
    }
    
    //Getters and Setters
    public String getCompanyName() {return this.companyName;}

    public void setCompanyName(String companyName) {this.companyName = companyName;}

    public int getPartID() {return this.partID;}

    public void setPartID(int partID) {this.partID = partID;}

    public String getName() {return this.partName;}

    public void setName(String partName) {this.partName = partName;}

    public double getPrice() {return this.price;}

    public void setPrice(double price) {this.price = price;}

    public int getInStock() {return this.inStock;}

    public void setInStock(int inStock) {this.inStock = inStock;}

    public int getMin() {return this.min;}

    public void setMin(int min) {this.min = min;}

    public int getMax() {return this.max;}

    public void setMax(int max) {this.max = max;}
    
    public int getPartCount(){return this.outSourcedPartCount;}
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Out Sourced Part");
        sb.append("\n Part Id: ");
        sb.append(this.getPartID());
        sb.append("\nCompany Name: ");
        sb.append(this.getCompanyName());
        sb.append("\n Part name: ");
        sb.append(this.getName());
        sb.append("\n Price: ");
        sb.append(this.getPrice());
        sb.append("\n In Stock: ");
        sb.append(this.getInStock());
        sb.append("\n OutPart Count: ");
        sb.append(this.getPartCount());
        sb.append("\n Min: ");
        sb.append(this.getMin());
        sb.append("\n Max: ");
        sb.append(this.getMax());
        
        return sb.toString();
  
    }    
}
