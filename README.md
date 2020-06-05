# PAO_Project
Java Project for PAO Laboratory; Uses a MariaDB Database.

## Model list:

- [Product](https://github.com/Darkonnas/PAO_Project#the-product-model)
- [ProductCategory](https://github.com/Darkonnas/PAO_Project#the-productcategory-model)
- [SoldProduct](https://github.com/Darkonnas/PAO_Project#the-soldproduct-model)
- [Cashier](https://github.com/Darkonnas/PAO_Project#the-cashier-model)
- [AssistedRegister](https://github.com/Darkonnas/PAO_Project#the-assistedregister-model)
- [SelfRegister](https://github.com/Darkonnas/PAO_Project#the-selfregister-model)
- [Coupon](https://github.com/Darkonnas/PAO_Project#the-coupon-model)
- [Receipt](https://github.com/Darkonnas/PAO_Project#the-receipt-model)


### The `Product` model:

- fields: id PK, name UNIQUE, category_id FK, price, discount, quantity
- methods: setters and getters, equals(), compareTo(), toString()
- Via ProductService, SQL Linkage with ProductRepository:
    - get all products, get products by id/name/category/price/discount/quantity (returned as Set<Product> implemented with TreeSet)
    - change product name/category/price/discount/quantity
    - add a product, remove a product
- Via LogService:
    - log actions to *Log.csv*  

### The `ProductCategory` model:

- fields: id PK, name UNIQUE
- methods: setters and getters, equals(), compareTo(), toString()
- Via ProductCategoryService, SQL Linkage via ProductCategoryRepository:
    - get all categories, get categories by id/name (returned as Set<ProductCategory> implemented with TreeSet)
    - change category name 
    - add a category, remove a category
- Via LogService:
    - log actions to *Log.csv*
            
### The `SoldProduct` model:

- fields: (receiptId FK + productId FK) PK, quantity
- methods: setters and getters, equals(), toString()
- Via SoldProductService, SQL Linkage via SoldProductRepository:
    - get all sold products, get sold products by receiptId/productId/quantity (returned as Set<SoldProduct> implemented with HashSet)
    - add a sold product, remove a sold product
- Via LogService:
    - log actions to *Log.csv*
            
### The `Cashier` model:

- fields: id PK, first_name, last_name
- methods: setters and getters, equals(), compareTo(), toString()
- Via CashierService, SQL Linkage via CashierRepository:
    - get all cashiers, get cashiers by id/first_name/last_name (returned as Set<Cashier> implemented with TreeSet)
    - change cashier first_name/ last_name
    - add a cashier, remove a cashier
- Via LogService:
    - log actions to *Log.csv*
            
### The `AssistedRegister` model:

- fields: id PK, active, inUse -> inherited from Register base, cashierId OPTIONAL FK
- methods: setters and getters, equals(), compareTo() -> inherited from Register base, toString()
- Via RegisterService, SQL Linkage via RegisterRepository:
    - get assisted registers (returned as Set<Register>, implemented with TreeSet)
    - assign a new cashier, drop the current cashier 
    - get all registers, get registers by id/active state/inUse state (returned as Set<Register>, implemented with TreeSet)
    - change register active state/inUse state
    - add a register, remove a register
- Via LogService:
    - log actions to *Log.csv*
          
### The `SelfRegister` model:

- fields: id PK, active, inUse -> inherited from Register base, active is always true
- methods: setters and getters, equals(), compareTo() -> inherited from Register base, toString()
- Via RegisterService, SQL Linkage via RegisterRepository:
    - get self registers (returned as Set<Register>, implemented with TreeSet) 
    - get all registers, get registers by id/active state/inUse state (returned as Set<Register>, implemented with TreeSet)
    - change register active state/inUse state
    - add a register, remove a register
- Via LogService:
    - log actions to *Log.csv*

### The `Coupon` model:

- fields: id PK, discount, used -> usage check when id used as FK
- methods: setters and getters, equals(), compareTo(), toString()
- Via CouponService, SQL Linkage via CouponRepository:
    - get all coupons (returned as Set<Coupon>, implemented with TreeSet), get coupons by id/discount/used state
    - change coupon discount/usedState
    - add a coupon, remove a coupon
- Via LogService:
    - log actions to *Log.csv*

### The `Receipt` model:

- fields: id PK, registerId FK, cashierId OPTIONAL FK, couponId OPTIONAL FK
- methods: setters and getters, equals(), compareTo(), to String()
- Via ReceiptService, SQL Linkage via ReceiptRepository:
    - get all receipts, get receipts by id/registerId/cashierId/couponId
    - add a receipt, remove a receipt
- Via LogService:
    - log actions to *Log.csv*