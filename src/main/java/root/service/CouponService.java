package root.service;

import root.model.Coupon;
import root.repository.CouponRepository;

import java.sql.Timestamp;
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
        LogService.getInstance().log("Requested coupons", new Timestamp(System.currentTimeMillis()));
        return couponRepository.getCoupons();
    }
    
    public int addCoupon(final Coupon coupon) {
        LogService.getInstance().log("Added a coupon", new Timestamp(System.currentTimeMillis()));
        return couponRepository.add(coupon);
    }
    
    public int removeCoupon(final int id) {
        LogService.getInstance().log("Removed a coupon", new Timestamp(System.currentTimeMillis()));
        return couponRepository.remove(id);
    }
    
    public Coupon getCouponById(final int id) {
        LogService.getInstance().log("Requested coupon by id", new Timestamp(System.currentTimeMillis()));
        return couponRepository.getCouponById(id);
    }
    
    public Set<Coupon> getCouponsByDiscount(final float discount) {
        LogService.getInstance().log("Requested coupon by discount", new Timestamp(System.currentTimeMillis()));
        return couponRepository.getCouponsByDiscount(discount);
    }
    
    public int setCouponDiscount(final int id, final float discount) {
        LogService.getInstance().log("Set coupon discount", new Timestamp(System.currentTimeMillis()));
        return couponRepository.setCouponDiscount(id, discount);
    }
    
    public Set<Coupon> getCouponsByUsedState(final boolean state) {
        LogService.getInstance().log("Requested coupons by used state", new Timestamp(System.currentTimeMillis()));
        return couponRepository.getCouponsByUsedState(state);
    }
    
    public int setCouponUsedState(final int id, final boolean state) {
        LogService.getInstance().log("Set coupon used state", new Timestamp(System.currentTimeMillis()));
        return couponRepository.setCouponUsedState(id, state);
    }
}
