# PAO_Project
Java Project for PAO Laboratory

Model list:

- Product
- ProductCategory
- SoldProduct
- Cashier
- AssistedRegister
- SelfRegister
- Receipt

The Product model:

- fields: id PK, name UNIQUE, category_id FK, price, sale, count
- methods: setters and getters, equals(), compareTo(), toString()
Via ProductService, SQL Linkage with ProductRepository:
- services: get all products, get products by id/name/category/price/sale/count (returned as Set<Product> implemented with TreeSet)
            change product name/category/price/sale/count
            add product, remove product

The ProductCategory model:

- fields: id PK, name UNIQUE
- methods: setters and getters, equals(), compareTo(), toString()
Via ProductCategoryService, SQL Linkage via ProductCategoryRepository:
- services: gell all categories, get categories by id/name (returned as Set<ProductCategory> implemented with TreeSet)
            set category name 
            add category, remove category
            
The SoldProduct model:

- fields: (receiptId FK + productId FK) PK, count
- methods: setters and getters, equals(), compareTo(), toString()
Via SoldProductService, SQL Linkage via SoldProductRepository:
- services: get all sold products, get sold products by receiptId/productId/count (returned as List<SoldProduct> implemented with Vector)
            add sold product, remove sold product
            
The Cashier model:

- fields: id PK, first_name, last_name
- methods: setters and getters, equals(), compareTo(), toString()
Via CashierService, SQL Linkage via CashierRepository:
- services: get all cashiers, get cashiers by id/first_name/last_name (returned as Set<Cashier> implemented with TreeSet)
            set cashier first_name, cashier set last_name
            add cashier, remove cashier
            
The AssistedRegister model:

- fields: id PK, active, inUse -> inherited from Register base, cashierId FK(which is -1 if the Register doesn't have one currently i.e active = false)
- methods: setters and getters, equals(), compareTo() -> inherited from Register base, toString()
Via RegisterService, SQL Linkage via RegisterRepository:
- services: get assisted registers (returned as Set<Register>, implemented with TreeSet) assign new cashier, drop current cashier 
          + get all registers, get registers by id/active state/inUse state (returned as Set<Register>, implemented with TreeSet)
          set register active state/inUse state
          
The SelfRegister model:

- fields: id PK, active, inUse -> inherited from Register base, active is always true
- methods: setters and getters, equals(), compareTo() -> inherited from Register base, toString()
Via RegisterService, SQL Linkage via RegisterRepository:
- services: get self registers (returned as Set<Register>, implemented with TreeSet) 
          + get all registers, get registers by id/active state/inUse state (returned as Set<Register>, implemented with TreeSet)
          set register active state/inUse state

The Receipt model:

- fields: id PK, registerId FK, cashierId FK (last is -1 if the receipt was printed from a SelfRegister)
- methods: setters and getters, equals(), compareTo(), to String()
Via ReceiptService, SQL Linkage via ReceiptRepository:
- services: get all receipts, get receipts by id/registerId/cashierId
            add receipt, remove receipt
