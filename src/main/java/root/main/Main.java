package root.main;

import root.model.*;
import root.service.*;

public class Main {
    public static void main(String[] args) {
        CashierService cashierService = CashierService.getInstance();
        cashierService.addCashier(new Cashier("Emily", "Flores"));
        cashierService.addCashier(new Cashier("Lily", "Tang"));
        cashierService.addCashier(new Cashier("Ethan", "Duffy"));
        cashierService.addCashier(new Cashier("Emma", "Wiles"));
        cashierService.addCashier(new Cashier("Owen", "Regan"));
    
        RegisterService registerService = RegisterService.getInstance();
        registerService.addRegister(new CashierRegister());
        registerService.addRegister(new CashierRegister());
        registerService.addRegister(new CashierRegister());
        registerService.addRegister(new CashierRegister());
        registerService.addRegister(new SelfRegister());
        registerService.addRegister(new SelfRegister());
        registerService.addRegister(new SelfRegister());
    
        ProductCategoryService productCategoryService = ProductCategoryService.getInstance();
        productCategoryService.addProductCategory(new ProductCategory("Fruit and vegetables"));
        productCategoryService.addProductCategory(new ProductCategory("Clothing"));
        productCategoryService.addProductCategory(new ProductCategory("Newspapers and magazines"));
        productCategoryService.addProductCategory(new ProductCategory("Cleaning products"));
        productCategoryService.addProductCategory(new ProductCategory("Milk"));
        productCategoryService.addProductCategory(new ProductCategory("Bread"));
    
        ProductService productService = ProductService.getInstance();
        productService.addProduct(new Product(0, "Apple", 4.99F, 30));
        productService.addProduct(new Product(0, "Banana", 7.59F, 20));
        productService.addProduct(new Product(1, "Blue Shirt", 10.69F, 30F, 10));
        productService.addProduct(new Product(2, "The New York Times", 5.99F, 50));
        productService.addProduct(new Product(2, "The Sun", 6.39F, 46));
        productService.addProduct(new Product(3, "Domestos", 7.99F, 35));
        productService.addProduct(new Product(3, "Mr Proper", 9.29F, 10F, 20));
        productService.addProduct(new Product(4, "Soy milk", 5.49F, 20));
        productService.addProduct(new Product(5, "Graham Bread", 3.49F, 25));
    
        CouponService couponService = CouponService.getInstance();
        couponService.addCoupon(new Coupon(30));
        couponService.addCoupon(new Coupon(30));
        couponService.addCoupon(new Coupon(30));
        couponService.addCoupon(new Coupon(30));
        couponService.addCoupon(new Coupon(30));
    
        ReceiptService receiptService = ReceiptService.getInstance();
        receiptService.addReceipt(new Receipt(0, 0, -1));
        receiptService.addReceipt(new Receipt(2, 2, 0));
        receiptService.addReceipt(new Receipt(4, -1, -1));
        receiptService.addReceipt(new Receipt(5, -1, 2));
        receiptService.addReceipt(new Receipt(3, 3, -1));
    
        SoldProductService soldProductService = SoldProductService.getInstance();
        soldProductService.addSoldProduct(new SoldProduct(0, 63476538, 5));
        soldProductService.addSoldProduct(new SoldProduct(0, 1982479237, 6));
        soldProductService.addSoldProduct(new SoldProduct(1, 1485382747, 2));
        soldProductService.addSoldProduct(new SoldProduct(2, 1802160646, 1));
        soldProductService.addSoldProduct(new SoldProduct(2, 111220270, 2));
        soldProductService.addSoldProduct(new SoldProduct(2, 63476538, 3));
        soldProductService.addSoldProduct(new SoldProduct(3, 468349617, 2));
        soldProductService.addSoldProduct(new SoldProduct(3, 312642015, 1));
        soldProductService.addSoldProduct(new SoldProduct(3, 1982479237, 3));
        soldProductService.addSoldProduct(new SoldProduct(3, 1850062050, 4));
        soldProductService.addSoldProduct(new SoldProduct(3, 1485382747, 2));
    
        System.out.println(cashierService.getCashiers());
        System.out.println(registerService.getRegisters());
        System.out.println(productCategoryService.getProductCategories());
        System.out.println(productService.getProducts());
        System.out.println(couponService.getCoupons());
        System.out.println(receiptService.getReceipts());
        System.out.println(soldProductService.getSoldProducts());
    }
}
