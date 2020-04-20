package root.service;

import root.model.Coupon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class CouponIOService {
    private static final String DATA_PATH = "src/main/java/root/data/";
    private static final String FILE_HEADER = "id,firstName,lastName";
    private static CouponIOService instance;
    
    private CouponIOService() {
    }
    
    public static CouponIOService getInstance() {
        if (instance == null) {
            instance = new CouponIOService();
        }
        return instance;
    }
    
    public void loadCoupons() {
        CouponService couponService = CouponService.getInstance();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(DATA_PATH + "Coupon.csv"));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    couponService.addCoupon(new Coupon(Float.parseFloat(fields[1]), Boolean.parseBoolean(fields[2])));
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void saveCoupons() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(DATA_PATH + "Coupon.csv");
            fileWriter.write(FILE_HEADER + '\n');
            Set<Coupon> coupons = CouponService.getInstance().getCoupons();
            for (Coupon coupon : coupons) {
                fileWriter.append(String.valueOf(coupon.getId())).append(",");
                fileWriter.append(String.valueOf(coupon.getDiscount())).append(",");
                fileWriter.append(String.valueOf(coupon.isUsed())).append("\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
