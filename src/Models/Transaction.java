package Models;

import java.util.ArrayList;

public class Transaction {

    private String code;
    private Customer customer;
    private ArrayList<ProductTransaction> productTransactions;
    private float grandTotal;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<ProductTransaction> getProductTransactions() {
        return productTransactions;
    }

    public void setProductTransactions(ArrayList<ProductTransaction> productTransactions) {
        this.productTransactions = productTransactions;
    }

    public void calulateGrandTotal () {
        for (ProductTransaction pt:this.productTransactions) {
            this.grandTotal += pt.getSubTotal();
        }
    }

    public float getGrandTotal () {
        this.calulateGrandTotal();
        return grandTotal;
    }
}
