
package final_ims_project.Models;

/**
 * @author Ty
 */
public class InHouse extends Part {
    
// Class variables
    private int machineID;
    private int partID;
    private String partName;
    private double price;
    private int inStock;
    private int min;
    private int max;
    private static int inHousePartCount = 0;
    
    //Constructor(s)
    public InHouse(){}
    public InHouse(int partID, int machineID, String partName, double price, int inStock, int min, int max) {
        this.partID = partID;
        this.machineID = machineID;
        this.partName = partName;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        this.inHousePartCount += 1;
    }
    
    //Getters and Setters
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
    
    public int getMachineID(){return this.machineID;}
    
    public void setMachineID(int machineID){this.machineID = machineID;}
    
    public int getPartCount(){return this.inHousePartCount;}
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("In House Part:");
        sb.append("\n Part Id: ");
        sb.append(this.getPartID());
        sb.append("\n Machine Id: ");
        sb.append(this.getMachineID());
        sb.append("\n Part name: ");
        sb.append(this.getName());
        sb.append("\n Price: ");
        sb.append(this.getPrice());
        sb.append("\n In Stock: ");
        sb.append(this.getInStock());
        sb.append("\n InPart count: ");
        sb.append(this.getPartCount());
        sb.append("\n Min: ");
        sb.append(this.getMin());
        sb.append("\n Max: ");
        sb.append(this.getMax());
        
        return sb.toString();
  
    }
}
