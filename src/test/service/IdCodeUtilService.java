package test.service;

/**身份证号码操作类
 * @author YANGHAO
 */
public interface IdCodeUtilService {
    /**获取年龄
     * @param idCode 身份证
     * @return 年龄
     */
    int getAgeByIdCode(String idCode);

    /**获取性别
     * @param idCode 身份证
     * @return 性别
     */
    String getGenderByIdCoder(String idCode);
}
