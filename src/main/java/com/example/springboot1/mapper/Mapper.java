package com.example.springboot1.mapper;


import com.example.springboot1.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Date;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    User login( @Param(value = "userName") String userName);

    Company queryCompanyByUser(@Param(value = "userId") Integer userId);

    int uploadDangerImg(@Param( value = "dangerId") Integer dangerId,
                        @Param( value = "uuid") String uuid );

    int uploadZhenggaiImg(@Param( value = "dangerId") Integer dangerId,
                          @Param( value = "uuid") String uuid);

    List<Danger> queryDanger(
            @Param(value = "dangerLevel")Integer dangerLevel,
            @Param(value = "dangerType")Integer dangerType,
            @Param(value = "dangerState") Integer dangerState,
            @Param(value = "companyId") Integer companyId,
            @Param(value = "itemId") Integer itemId,
            @Param(value = "zhenggaiPId") Integer zhenggaiPId,
            @Param(value = "createPId") Integer createPId);

    String queryDangerImg( @Param(value = "dangerId") Integer dangerId);

    List<Company> queryCompany(
            @Param(value = "companyName") String companyName,
            @Param(value = "companyType") Integer companyType);

    List<Item> queryItems(
            @Param( value = "itemName") String itemName,
            @Param( value = "userId") Integer userId);

    Integer queryCountDanger(@Param( value = "itemId")Integer itemId,
                             @Param( value = "companyId")Integer companyId,
                             @Param( value = "createPId")Integer createPId,
                             @Param( value = "zhenggaiPId")Integer zhenggaiPId,
                             @Param( value = "dangerState")Integer dangerState,
                             @Param( value = "level")Integer level);

    List<Data> queryData(
            @Param(value = "table") String table);

    List<Data> queryUserByCompany(
            @Param(value = "companyId") Integer companyId);

//     @Param(value = "dangerName") String dangerName,
//     @Param(value = "dangerAreaId") Integer dangerAreaId,
//     @Param(value = "companyId") Integer companyId,
//     @Param(value = "dangerType") Integer dangerType,
//     @Param(value = "dangerDec") String dangerDec,
//     @Param(value = "zhenggaiDec") String zhenggaiDec,
//     @Param(value = "dangerState") Integer dangerState,
//     @Param(value = "itemId") Integer itemId,
//     @Param(value = "dangerLevel") Integer dangerLevel,
//     @Param(value = "createTime") Date createTime,
//     @Param(value = "limitTime") Date limitTime,
//     @Param(value = "zhenggaiPId") Integer zhenggaiPId,
//     @Param(value = "createPId") Integer createPId

    Integer newDanger(Danger danger);

    int zhenggai(@Param(value = "zhenggaiLog") String zhenggaiLog,
                 @Param(value = "dangerState") Integer dangerState,
                 @Param(value = "updateTime") Date updateTime,
                 @Param(value = "dangerId") Integer dangerId);

    List<Data> queryDangerType();

    List<Data> queryDangerArea();

    List<Data> queryCompanyPart();

    int review(@Param(value = "reviewLog") String reviewLog,
               @Param(value = "dangerState") Integer dangerState,
               @Param(value = "reviewTime") Date reviewTime,
               @Param(value = "dangerId") Integer dangerId,
               @Param(value = "reviewPId") Integer reviewPId);

    String queryUpdateImg(@Param(value = "dangerId") Integer dangerId);

    Integer updateDanger(@Param(value = "dangerId") String dangerId,
                         @Param(value = "dangerName") String dangerName,
                         @Param(value = "dangerAreaId") Integer dangerAreaId,
                         @Param(value = "companyId") Integer companyId,
                         @Param(value = "dangerType") Integer dangerType,
                         @Param(value = "dangerDec") String dangerDec,
                         @Param(value = "zhenggaiDec") String zhenggaiDec,
                         @Param(value = "dangerState") Integer dangerState,
                         @Param(value = "itemId") Integer itemId,
                         @Param(value = "dangerLevel") Integer dangerLevel,
                         @Param(value = "limitTime") Date limitTime,
                         @Param(value = "zhenggaiPId") Integer zhenggaiPId,
                         @Param(value = "createPId") Integer createPId);


    List<DangerByDate> queryDangerByState(
            @Param(value = "dangerLevel") Integer dangerLevel,
            @Param(value = "itemId")Integer itemId,
            @Param(value = "companyId")Integer companyId);

    List<ListForCompany> queryCountForCompany(@Param(value = "itemId") Integer itemId);

    List<CountForType> queryDangerForType(
            @Param(value = "itemId") Integer itemId,
            @Param(value = "companyId") Integer companyId);

    String queryUserImg(@Param(value = "userId") Integer userId);

    int updateUserImg(@Param(value = "userId") Integer userId,
                      @Param(value = "imgData") String imgData);

    List<Data> queryUser(@Param(value = "identify") Integer identify,
                         @Param(value = "companyId") String companyId,
                         @Param(value = "name") String name);

    List<User> queryUserForWeb(@Param(value = "identify") Integer identify,
                               @Param(value = "companyId") Integer companyId,
                               @Param(value = "name") String name);

    int addItem(@Param(value = "itemName")String itemName,
                @Param(value = "companyId")String companyId,
                @Param(value = "manageId")Integer manageId,
                @Param(value = "saferOfficeId")String saferOfficeId,
                @Param(value = "responserId")String responserId,
                @Param(value = "state")Integer state);

    int updateItem(@Param(value = "id")Integer id,
                   @Param(value = "itemName")String itemName,
                   @Param(value = "companyId")String companyId,
                   @Param(value = "manageId")Integer manageId,
                   @Param(value = "saferOfficeId")String saferOfficeId,
                   @Param(value = "responserId")String responserId,
                   @Param(value = "state")Integer state);

    int reEdit(@Param(value = "dangerId")Integer dangerId,
               @Param(value = "limitTime")Date limitTime,
               @Param(value = "zhenggaiDec")String zhenggaiDec,
               @Param(value = "dangerState")Integer dangerState
               );

    int updateUserImgByWeb(@Param(value = "uuid")String uuid,
                           @Param(value = "userId")Integer userId);

    int addUser(@Param(value = "name")String name,
                @Param(value = "password")String password,
                @Param(value = "identify")Integer identify,
                @Param(value = "companyId")Integer companyId);

    int editUser(@Param(value = "id")Integer id,
                 @Param(value = "name")String name,
                 @Param(value = "password")String password,
                 @Param(value = "identify")Integer identify,
                 @Param(value = "companyId")Integer companyId,
                 @Param(value = "address") String address,
                 @Param(value = "phone") String phone,
                 @Param(value = "session") String session,
                 @Param(value = "img") String img);

    List<CountForArea> queryDangerForArea(
            @Param(value = "itemId") Integer itemId,
            @Param(value = "companyId") Integer companyId);

    List<CountForUser> queryCountForUser(Integer itemId);

    List<Danger> queryDanger2();

    int updateDangerState(Integer dangerId);

    int addCompany(
            @Param(value = "company_name") String company_name,
            @Param(value = "companyDec") String companyDec,
            @Param(value = "type") Integer type);

    int editCompany(
            @Param(value = "id") String id,
            @Param(value = "company_name") String company_name,
                    @Param(value = "companyDec") String companyDec,
                    @Param(value = "type") Integer type);

    int updateUser(
            @Param( value = "id")Integer id,
            @Param( value = "name")String name,
            @Param( value = "phone")String phone,
            @Param( value = "address")String address);

    int updateItemState(@Param( value = "state") Integer state,
                        @Param( value = "itemId") Integer itemId);

    void insertSession(@Param( value = "session") String session,
                       @Param( value = "name") String name);

    int insertReport(@Param( value = "userId") Integer userId,
                     @Param( value = "report")String report,
                     @Param( value = "createDate")Date createDate,
                     @Param( value = "month") Integer month,
                     @Param( value = "state") Integer state
    );

    List<Report> queryReport(
            @Param( value = "userId") Integer userId,
            @Param( value = "report") String report,
            @Param( value = "createDate") Date createDate,
            @Param( value = "month") Integer month,
            @Param( value = "state") Integer state
    );

    int updateReport(
            @Param( value = "userId") Integer userId,
            @Param( value = "report")String report,
            @Param( value = "createDate")Date createDate,
            @Param( value = "month") Integer month,
            @Param( value = "state") Integer state
    );

    int updatePassword(
            @Param( value = "id") Integer id,
            @Param( value = "password") String password);

    List<Reform> queryReform(
            @Param(value = "id") Integer id,
            @Param(value = "dangerId")Integer dangerId,
            @Param(value = "zhenggaiPId")Integer zhenggaiPId);

    int insertReform(
            @Param(value = "dangerId")Integer dangerId,
            @Param(value = "zhenggaiPId")Integer zhenggaiPId,
            @Param(value = "reformLog")String reformLog,
            @Param(value = "reformDate")Date reformDate,
            @Param(value = "dangerData")String dangerData
    );
}
