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
        
        //Actions
        System.out.println(cashierService.getCashiers());
        System.out.println(registerService.getRegisters());
        System.out.println(productCategoryService.getProductCategories());
        System.out.println(productService.getProducts());
        System.out.println(couponService.getCoupons());
        System.out.println(receiptService.getReceipts());
        System.out.println(soldProductService.getSoldProductsByQuantity(2));
    }
}
