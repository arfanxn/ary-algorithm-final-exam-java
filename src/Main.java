import Models.Customer;
import Models.Product;
import Models.ProductTransaction;
import Models.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerIn = new Scanner(System.in);

        // Prepare data
        ArrayList<Product> products = new ArrayList<>();

        Customer customer = new Customer();
        Transaction transaction = new Transaction();
        ArrayList<ProductTransaction> productTransactions = new ArrayList<>();

        boolean continueInput = true;
        while (continueInput) {
            Product product = new Product();
            ProductTransaction productTransaction = new ProductTransaction();

            System.out.println("Enter product code: ");
            product.setCode(scannerIn.next());

            for (Product p: products) {
                // if product already exists
                if (p.getCode() == product.getCode()) {
                    product = p;
                }
            }

            // if product doesnt exists
            if (product.getName() == "" || product.getName() == null) {
                System.out.println("Product "+product.getCode()+" doesnt exists, please create a new product.");
                System.out.println("New product code: " + product.getCode());

                System.out.println("Enter new product name:");
                product.setName(scannerIn.next());

                System.out.println("Enter new product price:");
                product.setPrice(scannerIn.nextFloat());

                products.add(product);
            }

            // Set product transaction's product
            productTransaction.setProduct(product);

            System.out.println("Enter product "+product.getCode()+" buy quantity:");
            productTransaction.setQuantity(scannerIn.nextInt());

            productTransaction.calculateSubTotal();

            boolean isPTAlreadyAdded = false;
            for (ProductTransaction pt: productTransactions) {
                // if product product already added in transaction
                if (pt.getProduct().getCode() == product.getCode()) {
                    isPTAlreadyAdded = true;
                    pt.setQuantity(pt.getQuantity() + productTransaction.getQuantity());
                    pt.calculateSubTotal();
                }
            }
            if (isPTAlreadyAdded == false) {
                productTransactions.add(productTransaction);
            }

            System.out.println("Continue ?");
            continueInput = scannerIn.next().toLowerCase().matches("(yes|true).*") ? true : false;
        }

        System.out.println("Enter customer name:");
        customer.setName(scannerIn.next());

        System.out.println("Enter customer address:");
        customer.setAddress(scannerIn.next());

        System.out.println("Enter customer phone number:");
        customer.setPhoneNumber(scannerIn.next());

        // Set customer code based on customer fields
        customer.setCode(
                customer.getName().toUpperCase().replaceAll("\\s+","").substring(0,1)
                + customer.getAddress().toUpperCase().replaceAll("\\s+","").substring(0,1)
                + customer.getPhoneNumber().toUpperCase().replaceAll("\\s+","").substring(0,1)
        );

        // Create the transaction
        transaction.setCode(UUID.randomUUID().toString());
        transaction.setProductTransactions(productTransactions);
        transaction.setCustomer(customer);
        transaction.calulateGrandTotal();

        try {
            System.out.println((new ObjectMapper()).writeValueAsString(transaction));;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}