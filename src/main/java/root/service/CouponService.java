package root.service;

import root.model.Coupon;
import root.repository.CouponRepository;

import java.util.Set;

public final class CouponService {
    private static CouponService instance;
    private final CouponRepository couponRepository;
    
    private CouponService() {
        couponRepository = new CouponRepository();
    }
    
    public static CouponService getInstance() {
        if (null == instance) {
            instance = new CouponService();
        }
        return instance;
    }
    
    public Set<Coupon> getCoupons() {
        return couponRepository.getCoupons();
    }
    
    public boolean addCoupon(final Coupon coupon) {
        return couponRepository.add(coupon);
    }
    
    public boolean removeCoupon(final int id) {
        return couponRepository.remove(id);
    }
    
    public Coupon getCouponById(final int id) {
        return couponRepository.getCouponById(id);
    }
    
    public Set<Coupon> getCouponsByDiscount(final float discount) {
        return couponRepository.getCouponsByDiscount(discount);
    }
    
    public boolean setCouponDiscount(final int id, final float discount) {
        return couponRepository.setCouponDiscount(id, discount);
    }
    
    public Set<Coupon> getCouponsByUsedState(final boolean state) {
        return couponRepository.getCouponsByUsedState(state);
    }
    
    public boolean setCouponUsedState(final int id, final boolean state) {
        return couponRepository.setCouponUsedState(id, state);
    }
}
