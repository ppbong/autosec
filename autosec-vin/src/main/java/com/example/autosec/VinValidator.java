package com.example.autosec;

import java.util.regex.Pattern;

/**
 * VIN验证工具类
 * 基于GB 16735-2019标准
 */
public class VinValidator {
    /**
     * 允许使用的字符正则表达式
     * 只能使用：0-9, A-Z（除了I、O、Q）
     */
    private static final Pattern VALID_VIN_PATTERN = Pattern.compile("^[A-HJ-NPR-Z0-9]{17}$");

    /**
     * 验证VIN格式是否满足长度和字符要求
     * @param vin 车辆识别代号
     * @return 是否有效
     */
    public boolean isValid(String vin) {
        if (vin == null || vin.length() != 17) {
            return false;
        }

        return VALID_VIN_PATTERN.matcher(vin).matches();
    }

    /**
     * 验证VIN格式和检验位是否正确
     * @param vin 车辆识别代号
     * @return 是否有效
     */
    public boolean isValidWithCheckDigit(String vin) {
        if (!isValid(vin)) {
            return false;
        }

        // 计算检验位
        char calculatedCheckDigit = new VinCalculator().calculateCheckDigit(vin);

        // 验证检验位（第9位）
        return calculatedCheckDigit == vin.charAt(8);
    }
}
