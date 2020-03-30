package root.main;

import root.service.*;

import java.util.prefs.PreferenceChangeEvent;

public class Main {
    public static void main(String[] args) {
        CashierService cashierService = CashierService.getInstance();
        RegisterService registerService = RegisterService.getInstance(cashierService);
        ProductCategoryService productCategoryService = ProductCategoryService.getInstance();
        ProductService productService = ProductService.getInstance(productCategoryService);
        ReceiptService receiptService = ReceiptService.getInstance(registerService, cashierService);
        SoldProductService soldProductService = SoldProductService.getInstance(productService, receiptService);
        System.out.println(cashierService.getCashiers());
        System.out.println(productCategoryService.getProductCategories());
        System.out.println(productService.getProducts());
        System.out.println(registerService.getRegisters());
        System.out.println(receiptService.getReceipts());
        System.out.println(soldProductService.getSoldProducts());
    }
}
