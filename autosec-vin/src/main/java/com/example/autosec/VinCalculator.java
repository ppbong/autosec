package com.example.autosec;

import java.util.HashMap;
import java.util.Map;

/**
 * VIN计算工具类
 * 基于GB 16735-2019标准附录A的方法
 */
public class VinCalculator {
    /**
     * VIN位置权重表（第9位为校验位，权重为0）
     */
    private static final int[] WEIGHTS = {8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2};

    /**
     * VIN字符数值映射表
     */
    private static final Map<Character, Integer> CHARACTER_VALUES = new HashMap<>();

    static {
        // 初始化数字字符映射
        for (int i = 0; i <= 9; i++) {
            CHARACTER_VALUES.put((char) ('0' + i), i);
        }

        // 初始化字母字符映射
        CHARACTER_VALUES.put('A', 1);
        CHARACTER_VALUES.put('B', 2);
        CHARACTER_VALUES.put('C', 3);
        CHARACTER_VALUES.put('D', 4);
        CHARACTER_VALUES.put('E', 5);
        CHARACTER_VALUES.put('F', 6);
        CHARACTER_VALUES.put('G', 7);
        CHARACTER_VALUES.put('H', 8);
        CHARACTER_VALUES.put('J', 1);
        CHARACTER_VALUES.put('K', 2);
        CHARACTER_VALUES.put('L', 3);
        CHARACTER_VALUES.put('M', 4);
        CHARACTER_VALUES.put('N', 5);
        CHARACTER_VALUES.put('P', 7);
        CHARACTER_VALUES.put('R', 9);
        CHARACTER_VALUES.put('S', 2);
        CHARACTER_VALUES.put('T', 3);
        CHARACTER_VALUES.put('U', 4);
        CHARACTER_VALUES.put('V', 5);
        CHARACTER_VALUES.put('W', 6);
        CHARACTER_VALUES.put('X', 7);
        CHARACTER_VALUES.put('Y', 8);
        CHARACTER_VALUES.put('Z', 9);
    }

    /**
     * 计算VIN的检验位
     * @param vin 车辆识别代号
     * @return 计算得到的检验位字符
     */
    public char calculateCheckDigit(String vin) {
        if (vin == null || vin.length() != 17) {
            throw new IllegalArgumentException("VIN must be exactly 17 characters");
        }

        int weightedSum = 0;

        // 计算加权和
        for (int i = 0; i < 17; i++) {
            char c = vin.charAt(i);
            Integer value = CHARACTER_VALUES.get(c);

            if (value == null) {
                throw new IllegalArgumentException("Invalid character in VIN: " + c);
            }

            weightedSum += value * WEIGHTS[i];
        }

        // 计算余数
        int remainder = weightedSum % 11;

        // 确定检验位
        if (remainder == 10) {
            return 'X';
        } else {
            return (char) ('0' + remainder);
        }
    }
}
