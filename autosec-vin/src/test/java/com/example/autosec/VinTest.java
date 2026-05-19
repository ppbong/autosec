package com.example.autosec;

import org.junit.Test;
import org.junit.Assert;

/**
 * VIN工具测试类
 */
public class VinTest {
    private final String TEST_VIN = "LSVAG21832B210001";

    @Test
    public void testVinValidator() {
        VinValidator vinValidator = new VinValidator();
        // 测试有效VIN
        Assert.assertTrue(vinValidator.isValid(TEST_VIN));

        // 测试无效VIN（长度错误）
        Assert.assertFalse(vinValidator.isValid("LSVAG2183B210000")); // 长度不足
        Assert.assertFalse(vinValidator.isValid("LSVAG2183B21000012")); // 长度过长

        // 测试无效VIN（包含无效字符）
        Assert.assertFalse(vinValidator.isValid("LSVAG2183B210000I")); // 包含I
        Assert.assertFalse(vinValidator.isValid("LSVAG2183B210000O")); // 包含O
        Assert.assertFalse(vinValidator.isValid("LSVAG2183B210000Q")); // 包含Q

        // 测试带检验位的验证
        Assert.assertTrue(vinValidator.isValidWithCheckDigit(TEST_VIN));
    }

    @Test
    public void testVinCalculator() {
        Assert.assertEquals(TEST_VIN.charAt(8), new VinCalculator().calculateCheckDigit(TEST_VIN));
    }

    @Test
    public void testVinParser() {
        VinParser vinParser = new VinParser(TEST_VIN);

        // 测试解析WMI
        Assert.assertEquals("LSV", vinParser.getWmi());

        // 测试解析地理区域代码
        Assert.assertEquals('L', vinParser.getRegionCode());

        // 测试解析国家或地区代码
        Assert.assertEquals('S', vinParser.getCountryCode());

        // 测试解析制造厂代码
        Assert.assertEquals('V', vinParser.getManufacturerCode());

        // 测试解析VDS
        Assert.assertEquals("AG2183", vinParser.getVds());

        // 测试解析车辆特征代码
        Assert.assertEquals("AG218", vinParser.getVehicleAttributes());

        // 测试解析检验位
        Assert.assertEquals('3', vinParser.getCheckDigit());

        // 测试解析VIS
        Assert.assertEquals("2B210001", vinParser.getVis());

        // 测试解析车型年份代码
        Assert.assertEquals('2', vinParser.getModelYearCode());

        // 测试解析装配厂代码
        Assert.assertEquals('B', vinParser.getAssemblyPlantCode());

        // 测试解析生产顺序号
        Assert.assertEquals("210001", vinParser.getProductionSequence());
    }
}
