package com.example.springboot1.service;

import com.example.springboot1.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;


public interface Service {

     ResultInfo uploadImg(String base64, Integer dangerId ,Integer flag);

     List<Danger> queryDanger(Integer dangerLevel,
                              Integer dangerType,
                              Integer dangerState,
                              Integer companyId,
                              Integer itemId,
                              Integer zhenggaiPId,
                              Integer createPId);

     ResultInfo queryDangerImg(Integer dangerId, Integer flag);

     User login(String userName, String password);

     Company queryCompanyByUser(Integer userId);

     List<Company> queryCompany(String companyName,Integer companyType);

     List<Item> queryItems(String itemName,Integer userId);

     Integer queryCountDanger(Integer itemId, Integer companyId,
                              Integer createPId,Integer zhenggaiPId,Integer dangerState, Integer level);

     List<Data> queryData(String table);

     List<Data> queryUserByCompany(Integer companyId);

     Integer newDanger(String dangerName, Integer dangerAreaId, Integer companyId,
                       Integer dangerType, String dangerDec, String zhenggaiDec,
                       Integer dangerState, Integer itemId, Integer dangerLevel,
                       Date limitTime, Integer zhenggaiPId, Integer createPId);

     int zhenggai(String zhenggaiLog,Integer dangerState, Date updateTime,Integer dangerId);

     List<Data> queryDangerType();

     List<Data> queryDangerArea();

     List<Data> queryCompanyPart();

     int review(String reviewLog, Integer dangerState, Date reviewTime,
                Integer dangerId,Integer reviewPId);

     Integer updateDanger(String dangerId, String dangerName, Integer dangerAreaId,
                          Integer companyId, Integer dangerType,
                          String dangerDec, String zhenggaiDec,
                          Integer dangerState, Integer itemId,
                          Integer dangerLevel, Date limitTime,
                          Integer zhenggaiPId, Integer createPId);

     List<CountForWeek> queryDangerByState(Integer dangerLevel,Integer itemId,Integer companyId);

     List<CountForCompany> queryCountForCompany(Integer itemId);

     List<DangerForType> queryDangerForType(Integer itemId, Integer companyId);

     ResultInfo queryUserImg(Integer userId);

     ResultInfo updateUserImg(Integer userId, String imgData);

     List<Data> queryUser(Integer identify, String companyId,String name);

     int addItem(String itemName, String companyId, Integer manageId, String saferOfficeId, String responserId, Integer state);

     int updateItem(Integer id,String itemName, String companyId, Integer manageId, String saferOfficeId, String responserId, Integer state);

     int reEdit(Integer dangerId, Date limitTime, String zhenggaiDec,
                Integer zhenggaiPId,String reformLog,String dangerData);

     int updateUserImgByWeb(MultipartFile file, Integer userId);

     List<User> queryUserForWeb(Integer identify, Integer companyId, String name);

     int addUser(String name, String password, Integer identify, Integer companyId);

     int editUser(Integer id, String name, String password,
                  Integer identify, Integer companyId,
                  String address,String phone,String session,String img);

     List<DangerForArea> queryDangerForArea(Integer itemId, Integer companyId);

     List<DangerForUser> queryCountForUser(Integer itemId);

     List<Danger> queryDanger1();

     int updateDangerState(Integer dangerId);

     int addCompany(String company_name, String companyDec, Integer type);

     int editCompany(String id, String company_name, String companyDec, Integer type);

     int updateUser(Integer id, String name, String phone, String address);

     int updateItemState();

     int insertReport(Integer userId, String report,
                      Date createDate ,Integer month,Integer state);

    List<Report> queryReport(Integer userId, String report,
                             Date createDate ,Integer month,Integer state);

     int updateReport(Integer userId, String report, Date createDate, Integer month, Integer state);

    int updatePassword(Integer id, String password);

     void insertReports();

     List<Reform> queryReform(Integer id, Integer dangerId, Integer zhenggaiPId);

}
