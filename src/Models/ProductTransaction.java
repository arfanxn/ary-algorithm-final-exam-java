package Models;

public class ProductTransaction {
    private Product product;
    private int quantity;
    private float subTotal;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void calculateSubTotal() {
        this.subTotal = this.product.getPrice() * this.quantity;
    }

    public float getSubTotal() {
        this.calculateSubTotal();
        return this.subTotal;
    }

}
