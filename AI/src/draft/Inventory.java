/**
 * 
 */
package draft;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class Inventory {

	int partNumber;
	int quantity;
	String manufacturerId;

	public int getPartNumber() {
		return partNumber;
	}
	
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getManufacturerId()
	{
		return manufacturerId;
	}
	
	public void setManufacturerId(String manufacturerId)
	{
		this.manufacturerId = manufacturerId;
	}

}
