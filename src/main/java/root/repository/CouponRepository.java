package root.repository;

import root.model.Coupon;
import root.service.CouponIOService;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class CouponRepository {
    private final Set<Coupon> coupons;
    
    public CouponRepository() {
        coupons = CouponIOService.getInstance().loadCoupons();
    }
    
    public Set<Coupon> getCoupons() {
        return Collections.unmodifiableSet(coupons);
    }
    
    public boolean add(final Coupon coupon) {
        return coupons.add(coupon);
    }
    
    public boolean remove(final int id) {
        final Coupon coupon = getCouponById(id);
        if (null == coupon) {
            return false;
        }
        return coupons.remove(coupon);
    }
    
    public Coupon getCouponById(final int id) {
        for (final Coupon coupon : coupons) {
            if (id == coupon.getId()) {
                return coupon;
            }
        }
        return null;
    }
    
    public Set<Coupon> getCouponsByDiscount(final float discount) {
        Set<Coupon> result = null;
        for (final Coupon coupon : coupons) {
            if (0 == Float.compare(discount, coupon.getDiscount())) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(coupon);
            }
        }
        return result;
    }
    
    public boolean setCouponDiscount(final int id, final float discount) {
        final Coupon coupon = getCouponById(id);
        if (null == coupon) {
            return false;
        }
        coupon.setDiscount(discount);
        return true;
    }
    
    public Set<Coupon> getCouponsByUsedState(final boolean state) {
        Set<Coupon> result = null;
        for (final Coupon coupon : coupons) {
            if (state == coupon.isUsed()) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(coupon);
            }
        }
        return result;
    }
    
    public boolean setCouponUsedState(final int id, final boolean state) {
        final Coupon coupon = getCouponById(id);
        if (null == coupon) {
            return false;
        }
        coupon.setUsed(state);
        return true;
    }
}
