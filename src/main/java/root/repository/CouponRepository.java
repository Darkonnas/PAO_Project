package root.repository;

import root.model.Coupon;

import java.util.Set;
import java.util.TreeSet;

public class CouponRepository {
    private Set<Coupon> coupons;
    
    public CouponRepository() {
        coupons = new TreeSet<>();
    }
    
    public Set<Coupon> getCoupons() {
        return coupons;
    }
    
    public boolean add(Coupon c) {
        return coupons.add(c);
    }
    
    public boolean remove(int id) {
        Coupon c = getCouponById(id);
        if (c == null) {
            return false;
        }
        return coupons.remove(c);
    }
    
    public Coupon getCouponById(int id) {
        for (Coupon c : coupons) {
            if (id == c.getId()) {
                return c;
            }
        }
        return null;
    }
    
    public Set<Coupon> getCouponsBySale(float sale) {
        Set<Coupon> result = null;
        for (Coupon c : coupons) {
            if (Float.compare(sale, c.getSale()) == 0) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(c);
            }
        }
        return result;
    }
    
    public boolean setCouponSale(int id, float sale) {
        Coupon c = getCouponById(id);
        if (c == null) {
            return false;
        }
        c.setSale(sale);
        return true;
    }
    
    public Set<Coupon> getCouponsByUsedState(boolean state) {
        Set<Coupon> result = null;
        for (Coupon c : coupons) {
            if (state == c.isUsed()) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(c);
            }
        }
        return result;
    }
    
    public boolean setCouponUsedState(int id, boolean state) {
        Coupon c = getCouponById(id);
        if (c == null) {
            return false;
        }
        c.setUsed(state);
        return true;
    }
}
