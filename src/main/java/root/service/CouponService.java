package root.service;

import root.model.Coupon;
import root.repository.CouponRepository;

import java.util.Set;

public class CouponService {
    private static CouponService instance;
    private CouponRepository couponRepository;
    
    private CouponService() {
        this.couponRepository = new CouponRepository();
    }
    
    public static CouponService getInstance() {
        if (instance == null) {
            instance = new CouponService();
        }
        return instance;
    }
    
    public Set<Coupon> getCoupons() {
        return couponRepository.getCoupons();
    }
    
    public boolean addCoupon(Coupon c) {
        return couponRepository.add(c);
    }
    
    public boolean removeCoupon(int id) {
        return couponRepository.remove(id);
    }
    
    public Coupon getCouponById(int id) {
        return couponRepository.getCouponById(id);
    }
    
    public Set<Coupon> getCouponsBySale(float sale) {
        return couponRepository.getCouponsBySale(sale);
    }
    
    public boolean setCouponSale(int id, float sale) {
        return couponRepository.setCouponSale(id, sale);
    }
    
    public Set<Coupon> getCouponsByUsedState(boolean state) {
        return couponRepository.getCouponsByUsedState(state);
    }
    
    public boolean setCouponUsedState(int id, boolean state) {
        return couponRepository.setCouponUsedState(id, state);
    }
}
