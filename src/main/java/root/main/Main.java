package root.main;

import root.service.*;

class Main {
    public static void main(final String[] args) {
        //Declaration
        final CashierService cashierService = CashierService.getInstance();
        final RegisterService registerService = RegisterService.getInstance();
        final ProductCategoryService productCategoryService = ProductCategoryService.getInstance();
        final ProductService productService = ProductService.getInstance();
        final CouponService couponService = CouponService.getInstance();
        final ReceiptService receiptService = ReceiptService.getInstance();
        final SoldProductService soldProductService = SoldProductService.getInstance();
        
        final CashierIOService cashierIOService = CashierIOService.getInstance();
        final RegisterIOService registerIOService = RegisterIOService.getInstance();
        final ProductCategoryIOService productCategoryIOService = ProductCategoryIOService.getInstance();
        final ProductIOService productIOService = ProductIOService.getInstance();
        final CouponIOService couponIOService = CouponIOService.getInstance();
        final ReceiptIOService receiptIOService = ReceiptIOService.getInstance();
        final SoldProductIOService soldProductIOService = SoldProductIOService.getInstance();
        
        
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
