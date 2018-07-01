package test.service.impl;

import test.service.IdCodeUtilService;

import java.util.Calendar;

/**
 * @author YANGHAO
 */
public class IdCodeUtilServiceImpl implements IdCodeUtilService {
    /**
     * 获取年龄
     *
     * @param idCode 身份证532125197710260027
     * @return 年龄
     */
    @Override
    public int getAgeByIdCode(String idCode) {
        int age;
        if (idCode == null || "".equals(idCode)) {
            age = -1;
            return age;
        } else {
            String idYearString = idCode.substring(6, 10);
            int idYearInt = Integer.parseInt(idYearString);
            Calendar calendar = Calendar.getInstance();
            int calendaryear = calendar.get(Calendar.YEAR);
            age = calendaryear - idYearInt;
            return age;

        }
    }

    /**
     * 获取性别
     *
     * @param idCode 身份证
     * @return 性别
     */
    @Override
    public String getGenderByIdCoder(String idCode) {
        return null;
    }

    public static void main(String[] args) {
        IdCodeUtilServiceImpl idCodeUtilService = new IdCodeUtilServiceImpl();
        int age = idCodeUtilService.getAgeByIdCode("532125197710260027");
        System.out.println(age);

    }
}