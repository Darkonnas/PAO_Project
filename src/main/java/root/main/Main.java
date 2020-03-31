package root.main;

import root.service.*;

public class Main {
    public static void main(String[] args) {
        CashierService cashierService = CashierService.getInstance();
        RegisterService registerService = RegisterService.getInstance();
        ProductCategoryService productCategoryService = ProductCategoryService.getInstance();
        ProductService productService = ProductService.getInstance();
        ReceiptService receiptService = ReceiptService.getInstance();
        SoldProductService soldProductService = SoldProductService.getInstance();
        System.out.println(cashierService.getCashiers());
        System.out.println(productCategoryService.getProductCategories());
        System.out.println(productService.getProducts());
        System.out.println(registerService.getRegisters());
        System.out.println(receiptService.getReceipts());
        System.out.println(soldProductService.getSoldProducts());
    }
}
