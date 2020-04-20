package root.main;

import root.service.*;

public class Main {
    public static void main(String[] args) {
        //Declaration
        CashierService cashierService = CashierService.getInstance();
        RegisterService registerService = RegisterService.getInstance();
        ProductCategoryService productCategoryService = ProductCategoryService.getInstance();
        ProductService productService = ProductService.getInstance();
        CouponService couponService = CouponService.getInstance();
        ReceiptService receiptService = ReceiptService.getInstance();
        SoldProductService soldProductService = SoldProductService.getInstance();
    
        CashierIOService cashierIOService = CashierIOService.getInstance();
        RegisterIOService registerIOService = RegisterIOService.getInstance();
        ProductCategoryIOService productCategoryIOService = ProductCategoryIOService.getInstance();
        ProductIOService productIOService = ProductIOService.getInstance();
        CouponIOService couponIOService = CouponIOService.getInstance();
        ReceiptIOService receiptIOService = ReceiptIOService.getInstance();
        SoldProductIOService soldProductIOService = SoldProductIOService.getInstance();
    
        //Data loading
        cashierIOService.loadCashiers();
        registerIOService.loadRegisters();
        productCategoryIOService.loadProductCategories();
        productIOService.loadProducts();
        couponIOService.loadCoupons();
        receiptIOService.loadReceipts();
        soldProductIOService.loadSoldProducts();
    
        //Actions
        System.out.println(cashierService.getCashiers());
        System.out.println(registerService.getRegisters());
        System.out.println(productCategoryService.getProductCategories());
        System.out.println(productService.getProducts());
        System.out.println(couponService.getCoupons());
        System.out.println(receiptService.getReceipts());
        System.out.println(soldProductService.getSoldProducts());
    
        //Data saving
        cashierIOService.saveCashiers();
        registerIOService.saveRegisters();
        productCategoryIOService.saveProductCategories();
        productIOService.saveProducts();
        couponIOService.saveCoupons();
        receiptIOService.saveReceipts();
        soldProductIOService.saveSoldProducts();
    }
}
