package com.example.autosec;

/**
 * VIN解析工具类
 * 基于GB 16735-2019标准
 */
public class VinParser {
    // 17位编码
    private String vin;

    private VinParser() {}

    public VinParser(String vin) {
        this.vin = vin == null ? "" : vin.trim().toUpperCase();
    }

    /**
     * 获取车辆识别代号（VIN）
     * @return VIN（17位）
     */
    public String getVin() {
        return vin;
    }

    /**
     * 获取世界制造厂识别代号（WMI）
     * @return WMI（3位）
     */
    public String getWmi() {
        return vin.substring(0, 3);
    }

    /**
     * 获取车辆说明部分（VDS）
     * @return VDS（6位）
     */
    public String getVds() {
        return vin.substring(3, 9);
    }

    /**
     * 获取车辆指示部分（VIS）
     * @return VIS（8位）
     */
    public String getVis() {
        return vin.substring(9, 17);
    }

    /**
     * 获取地理区域代码
     * @return WMI的第1位
     */
    public char getRegionCode() {
        return getWmi().charAt(0);
    }

    /**
     * 获取国家或地区代码
     * @return WMI的第2位
     */
    public char getCountryCode() {
        return getWmi().charAt(1);
    }

    /**
     * 获取制造厂代码
     * @return WMI的第3位
     */
    public char getManufacturerCode() {
        return getWmi().charAt(2);
    }

    /**
     * 获取车辆特征代码
     * @return VDS的前5位
     */
    public String getVehicleAttributes() {
        return getVds().substring(0, 5);
    }

    /**
     * 获取检验位
     * @return VDS的第6位
     */
    public char getCheckDigit() {
        return getVds().charAt(5);
    }

    /**
     * 获取车型年份代码
     * @return VIS的第1位
     */
    public char getModelYearCode() {
        return getVis().charAt(0);
    }

    /**
     * 获取车型年份（仅支持2001 ~ 2030年份）
     * @param modelYearCode 车型年份代码
     * @return 年份
     */
    public String getModelYear(char modelYearCode) {
        String modelYearCodes = "123456789ABCDEFGHJKLMNPRSTVWXY";
        int pos = modelYearCodes.indexOf(modelYearCode);

        if (pos == -1) {
            throw new IllegalArgumentException("model year code invalid");
        }

        // 30年一个周期
        return String.valueOf(2001 + pos);
    }

    /**
     * 获取装配厂代码
     * @return VIS的第2位
     */
    public char getAssemblyPlantCode() {
        return getVis().charAt(1);
    }

    /**
     * 获取生产顺序号
     * @return VIS的后6位
     */
    public String getProductionSequence() {
        return getVis().substring(2, 8);
    }
}
