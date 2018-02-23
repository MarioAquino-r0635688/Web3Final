package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Product;

public class ProductDbInMemory implements ProductDb {
	private Map<Integer, Product> records = new HashMap<Integer, Product>();
	
	public ProductDbInMemory () {
		Product rose = new Product("Rose", "Thorny plant", 2.25);
		Product cola = new Product("Cola", "beverage", 1.50);
		add(rose);
		add(cola);
	}
	
	/* (non-Javadoc)
	 * @see db.ProductDb#get(int)
	 */
	@Override
	public Product get(int id){
		if(id < 0){
			throw new DbException("No valid id given");
		}
		return records.get(id);
	}
	
	/* (non-Javadoc)
	 * @see db.ProductDb#getAll()
	 */
	@Override
	public List<Product> getAll(){
		return new ArrayList<Product>(records.values());	
	}

	/* (non-Javadoc)
	 * @see db.ProductDb#add(domain.Product)
	 */
	@Override
	public void add(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		int id = records.size() + 1;
		product.setProductId(id);
		if (records.containsKey(product.getProductId())) {
			throw new DbException("Product already exists");
		}
		records.put(product.getProductId(), product);
	}
	
	/* (non-Javadoc)
	 * @see db.ProductDb#update(domain.Product)
	 */
	@Override
	public void update(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		if(!records.containsKey(product.getProductId())){
			throw new DbException("No product found");
		}
		records.put(product.getProductId(), product);
	}
	
	/* (non-Javadoc)
	 * @see db.ProductDb#delete(int)
	 */
	@Override
	public void delete(int id){
		if(id < 0){
			throw new DbException("No valid id given");
		}
		records.remove(id);
	}
}
