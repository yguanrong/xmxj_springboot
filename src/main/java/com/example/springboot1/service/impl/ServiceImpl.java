package com.example.springboot1.service.impl;

import com.example.springboot1.mapper.Mapper;
import com.example.springboot1.model.*;
import com.example.springboot1.service.Service;

import com.example.springboot1.utils.DateUtil;
import com.example.springboot1.utils.ImageUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;


@org.springframework.stereotype.Service
@Transactional
public class ServiceImpl implements Service {

    //注入mapper接口
    @Resource       //使用@Autowried报错，用Resource代替
    private Mapper mapper;

    @Override
    public User login(String userName, String password) {

//        UUID uuid = UUID.randomUUID();
//        mapper.insertSession(uuid.toString(),userName);
        User user = mapper.login(userName);
        if (user != null) {

            if (password.equals(user.getPassword())){
                if (user.getImg()!=null)
                {
                    user.setImg(user.getImg()+".jpg");
                }
                user.setPassword("");

                return user;
            }
            else {
                user.setIdentify(-1);//密码错误
                return user;
            }

        }else {
            User user1 = new User();
            user1.setIdentify(-2);//用户不存在
            return user1;
        }
    }



    @Override
    public Company queryCompanyByUser(Integer userId) {
        return mapper.queryCompanyByUser(userId);
    }

    @Override
    public List<Company> queryCompany(String companyName,Integer companyType) {
        return mapper.queryCompany(companyName,companyType);
    }

    @Override
    public List<Item> queryItems(String itemName,Integer userId) {

        return mapper.queryItems(itemName,userId);
    }

    @Override
    public Integer queryCountDanger(Integer itemId, Integer companyId,
                                    Integer createPId,Integer zhenggaiPId,
                                    Integer dangerState , Integer level) {
        return mapper.queryCountDanger(itemId,companyId,createPId,zhenggaiPId, dangerState , level);
    }

    @Override
    public List<Data> queryData(String table) {
        return mapper.queryData(table);
    }

    @Override
    public List<Data> queryUserByCompany(Integer companyId) {
        return mapper.queryUserByCompany(companyId);
    }

    @Override
    public Integer newDanger(String dangerName, Integer dangerAreaId, Integer companyId,
                             Integer dangerType, String dangerDec, String zhenggaiDec,
                             Integer dangerState, Integer itemId, Integer dangerLevel,
                             Date limitTime, Integer zhenggaiPId, Integer createPId) {

        Date createTime = new Date(System.currentTimeMillis());
        Danger danger = new Danger();
        danger.setDangerName(dangerName);
        danger.setDangerAreaId(dangerAreaId);
        danger.setCompanyId(companyId);
        danger.setDangerType(dangerType);
        danger.setDangerDec(dangerDec);
        danger.setZhenggaiDec(zhenggaiDec);
        danger.setDangerState(dangerState);
        danger.setItemId(itemId);
        danger.setDangerLevel(dangerLevel);
        danger.setLimitTime(limitTime);
        danger.setCreateTime(createTime);
        danger.setZhenggaiPId(zhenggaiPId);
        danger.setCreatePId(createPId);

        mapper.newDanger(danger);

        return danger.getDangerId();
    }

    @Override
    public int zhenggai(String zhenggaiLog,Integer dangerState, Date updateTime,Integer dangerId ) {

        return mapper.zhenggai(zhenggaiLog,dangerState,updateTime,dangerId);
    }

    @Override
    public List<Data> queryDangerType() {
        return mapper.queryDangerType();
    }

    @Override
    public List<Data> queryDangerArea() {
        return mapper.queryDangerArea();
    }

    @Override
    public List<Data> queryCompanyPart() {
        return mapper.queryCompanyPart();
    }

    @Override
    public int review(String reviewLog, Integer dangerState, Date reviewTime, Integer dangerId
            , Integer reviewPId) {
        Date reviewDate = new Date(System.currentTimeMillis());
        return mapper.review(reviewLog,dangerState,reviewDate,dangerId,reviewPId);
    }

    @Override
    public Integer updateDanger(String dangerId,String dangerName, Integer dangerAreaId, Integer companyId, Integer dangerType, String dangerDec, String zhenggaiDec, Integer dangerState, Integer itemId, Integer dangerLevel, Date limitTime, Integer zhenggaiPId, Integer createPId) {
        return mapper.updateDanger(dangerId,dangerName, dangerAreaId, companyId,
                dangerType, dangerDec,zhenggaiDec,
                dangerState, itemId, dangerLevel,
                limitTime, zhenggaiPId, createPId);
    }

    @Override
    public List<CountForWeek> queryDangerByState(Integer dangerLevel, Integer itemId, Integer companyId) {

        List<DangerByDate> dangerByDates = mapper.queryDangerByState(dangerLevel,itemId,companyId);
        List<String> dates = DateUtil.printDate();
        int[] importantDanger= new int[7];//紧要
        int[] seriousDanger = new int[7];//严重
        int[] commonDanger = new int[7];//一般
        int[] count = new int[7];//总数


        for (DangerByDate dangerByDate: dangerByDates ) {

            String date = dangerByDate.getCreateTime().toString().substring(5);

            if ( date.equals(dates.get(0)) ){
                //System.out.println("date = " + dangerByDate.getDangerLevel());
                switch (dangerByDate.getDangerLevel()){
                    case 1://一般
                        commonDanger[0]=commonDanger[0]+dangerByDate.getCount();
                        break;
                    case 2://严重
                        seriousDanger[0]=seriousDanger[0]+dangerByDate.getCount();
                        break;
                    case 3://紧要
                        importantDanger[0]=importantDanger[0]+dangerByDate.getCount();
                        break;
                }
                count[0] = commonDanger[0] + seriousDanger[0] + importantDanger[0];
            }else if ( date.equals(dates.get(1)) ){

                switch (dangerByDate.getDangerLevel()){
                    case 1://一般
                        commonDanger[1]=commonDanger[1]+dangerByDate.getCount();
                        break;
                    case 2://严重
                        seriousDanger[1]=seriousDanger[1]+dangerByDate.getCount();
                        break;
                    case 3://紧要
                        importantDanger[1]=importantDanger[1]+dangerByDate.getCount();
                        break;
                }
                count[1] = commonDanger[1] + seriousDanger[1] + importantDanger[1];
            }else if ( date.equals(dates.get(2)) ){

                switch (dangerByDate.getDangerLevel()){
                    case 1://一般
                        commonDanger[2]=commonDanger[2]+dangerByDate.getCount();
                        break;
                    case 2://严重
                        seriousDanger[2]=seriousDanger[2]+dangerByDate.getCount();
                        break;
                    case 3://紧要
                        importantDanger[2]=importantDanger[2]+dangerByDate.getCount();
                        break;
                }
                count[2] = commonDanger[2] + seriousDanger[2] + importantDanger[2];
            }else if ( date.equals(dates.get(3)) ){

                switch (dangerByDate.getDangerLevel()){
                    case 1://一般
                        commonDanger[3]=commonDanger[3]+dangerByDate.getCount();
                        break;
                    case 2://严重
                        seriousDanger[3]=seriousDanger[3]+dangerByDate.getCount();
                        break;
                    case 3://紧要
                        importantDanger[3]=importantDanger[3]+dangerByDate.getCount();
                        break;
                }
                count[3] = commonDanger[3] + seriousDanger[3] + importantDanger[3];
            }else if ( date.equals(dates.get(4)) ){

                switch (dangerByDate.getDangerLevel()){
                    case 1://一般
                        commonDanger[4]=commonDanger[4]+dangerByDate.getCount();
                        break;
                    case 2://严重
                        seriousDanger[4]=seriousDanger[4]+dangerByDate.getCount();
                        break;
                    case 3://紧要
                        importantDanger[4]=importantDanger[4]+dangerByDate.getCount();
                        break;
                }
                count[4] = commonDanger[4] + seriousDanger[4] + importantDanger[4];
            }else if ( date.equals(dates.get(5)) ){

                switch (dangerByDate.getDangerLevel()){
                    case 1://一般
                        commonDanger[5]=commonDanger[5]+dangerByDate.getCount();
                        break;
                    case 2://严重
                        seriousDanger[5]=seriousDanger[5]+dangerByDate.getCount();
                        break;
                    case 3://紧要
                        importantDanger[5]=importantDanger[5]+dangerByDate.getCount();
                        break;
                }
                count[5] = commonDanger[5] + seriousDanger[5] + importantDanger[5];
            }else if ( date.equals(dates.get(6)) ){

                switch (dangerByDate.getDangerLevel()){
                    case 1://一般
                        commonDanger[6]=commonDanger[6]+dangerByDate.getCount();
                        break;
                    case 2://严重
                        seriousDanger[6]=seriousDanger[6]+dangerByDate.getCount();
                        break;
                    case 3://紧要
                        importantDanger[6]=importantDanger[6]+dangerByDate.getCount();
                        break;
                }
                count[6] = commonDanger[6] + seriousDanger[6] + importantDanger[6];
            }
        }
        List<CountForWeek> countForWeeks = new ArrayList<>();
        CountForWeek countForWeek =new CountForWeek();
        countForWeek.setCount(count);
        countForWeek.setCommonDanger(commonDanger);
        countForWeek.setDates(dates);
        countForWeek.setImportantDanger(importantDanger);
        countForWeek.setSeriousDanger(seriousDanger);
        countForWeeks.add(countForWeek);

        return countForWeeks;
    }

    @Override
    public ResultInfo uploadImg(String base64, Integer dangerId ,Integer flag) {
        // 这是服务器路径
//        String UPLOAD_PATH = "D:/xmxj/upload/phone_upload/";
        String UPLOAD_PATH = "C:/xmxj/upload/phone_upload/";
        ResultInfo resultInfo = new ResultInfo();
        File f = new File(UPLOAD_PATH + "/");
        if(!f.exists()) {
            f.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
        String path = UPLOAD_PATH + "/" + uuid +".jpg";
        //保存到磁盘文件
        ImageUtil.base64ToImage(base64,path);

        //插入数据库
        int result;
        if (flag == 1){
            result = mapper.uploadDangerImg(dangerId,uuid);
        }
        else {
            result = mapper.uploadZhenggaiImg(dangerId,uuid);
        }

        if (result == 1)
        {
            resultInfo.setState(1);
            resultInfo.setMsg("上传成功");
        }
        else
        {
            resultInfo.setState(0);
            resultInfo.setMsg("上传失败");
        }

        return resultInfo;

    }

    @Override
    public List<Danger> queryDanger(Integer dangerLevel, Integer dangerType,
                                    Integer dangerState, Integer companyId,
                                    Integer itemId, Integer zhenggaiPId, Integer createPId) {
        return mapper.queryDanger(dangerLevel,dangerType,dangerState,companyId,itemId,zhenggaiPId,createPId);
    }

    @Override
    public ResultInfo queryDangerImg(Integer dangerId, Integer flag) {
        String dangerImg;

        if (flag == 1){
            dangerImg =  mapper.queryDangerImg(dangerId);
        }else {
            dangerImg =  mapper.queryUpdateImg(dangerId);
        }
        ResultInfo resultInfo = new ResultInfo();
        resultInfo = imgUtil(dangerImg);

        return resultInfo;
    }

    @Override
    public List<CountForCompany> queryCountForCompany(Integer itemId) {

        List<ListForCompany> listForCompanies = mapper.queryCountForCompany(itemId);
        List<CountForCompany> list = new ArrayList<>();
        List<String> dangerCompany = new ArrayList<>();


        for (ListForCompany listForCompany : listForCompanies){
            dangerCompany.add(listForCompany.getCompanyName());
        }
        dangerCompany = new ArrayList<>(new LinkedHashSet<>(dangerCompany));

        int[] overdueData = new int[dangerCompany.size()];
        int[] sumData = new int[dangerCompany.size()];
        for (ListForCompany listForCompany : listForCompanies) {

            for (int i = 0; i < dangerCompany.size(); i++) {

                if(dangerCompany.get(i).equals(listForCompany.getCompanyName())){
                    if (listForCompany.getDangerState() == 0){
                        overdueData[i] = overdueData[i]+listForCompany.getCount();
                    }
                    sumData[i] = sumData[i] + listForCompany.getCount();
                }

            }
        }

        CountForCompany countForCompany = new CountForCompany();
        countForCompany.setDangerCompany(dangerCompany);
        countForCompany.setOverdueData(overdueData);
        countForCompany.setSumData(sumData);
        list.add(countForCompany);

        return list;
    }

    @Override
    public List<DangerForType> queryDangerForType(Integer itemId, Integer companyId) {

        List<CountForType> countForTypes = mapper.queryDangerForType(itemId,companyId);
        List<DangerForType> list = new ArrayList<>();

        for (CountForType countForType : countForTypes) {

            DangerForType dangerForType = new DangerForType();
            dangerForType.setName(countForType.getTypeName());
            dangerForType.setValue(countForType.getCount());
            list.add(dangerForType);
        }

        return list;
    }

    @Override
    public ResultInfo queryUserImg(Integer userId) {

        String userImg;

        userImg = mapper.queryUserImg(userId);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo = imgUtil(userImg);

        return resultInfo;
    }

    private ResultInfo imgUtil(String userImg) {
        ResultInfo resultInfo = new ResultInfo();
        if (userImg != null)
        {
            resultInfo.setMsg(userImg+".jpg");
            resultInfo.setState(1);
        }else {
            resultInfo.setMsg("没有图片");
            resultInfo.setState(-1);
        }
        return resultInfo;
    }

    @Override
    public ResultInfo updateUserImg(Integer userId, String imgData) {
        // 这是服务器路径
//        String UPLOAD_PATH = "D:/xmxj/upload/phone_upload/";
        String UPLOAD_PATH = "C:/xmxj/upload/phone_upload/";
        ResultInfo resultInfo = new ResultInfo();
        File f = new File(UPLOAD_PATH + "/");
        if(!f.exists()) {
            f.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
        String path = UPLOAD_PATH + "/" + uuid +".jpg";
        //保存到磁盘文件
        ImageUtil.base64ToImage(imgData,path);

        //插入数据库
        int a=mapper.updateUserImg(userId,uuid);
        resultInfo.setState(a);
        resultInfo.setMsg(uuid+".jpg");
        return resultInfo;

    }

    @Override
    public List<Data> queryUser(Integer identify, String companyId,String name) {
        return mapper.queryUser(identify,companyId,name);
    }

    @Override
    public int addItem(String itemName, String companyId, Integer manageId,
                       String saferOfficeId, String responserId, Integer state) {
        return mapper.addItem(itemName,companyId,manageId,saferOfficeId,responserId,state);
    }

    @Override
    public int updateItem(Integer id,String itemName, String companyId, Integer manageId,
                          String saferOfficeId, String responserId, Integer state) {
        return mapper.updateItem(id,itemName,companyId,manageId,saferOfficeId,responserId,state);
    }

    @Override
    public int reEdit(Integer dangerId, Date limitTime, String zhenggaiDec,
                      Integer zhenggaiPId,String reformLog,String dangerData) {
        Integer dangerState = 1;
        Date reformDate = new Date(System.currentTimeMillis());
        int rs = mapper.insertReform(dangerId, zhenggaiPId, reformLog,reformDate,dangerData);
        int rs1 = mapper.reEdit(dangerId,limitTime,zhenggaiDec,dangerState);
        if(rs ==1 && rs1 ==1){
            return 1;
        }else {
            return 0;
        }

    }

    @Override
    public int updateUserImgByWeb(MultipartFile file, Integer userId) {

        // 这是服务器路径
//        String UPLOAD_PATH = "D:/xmxj/upload/phone_upload/";

        String UPLOAD_PATH = "C:/xmxj/upload/phone_upload/";
        File f = new File(UPLOAD_PATH + "/");
        if(!f.exists()) {
            f.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
        String path = UPLOAD_PATH + "/" + uuid +".jpg";
        //保存到磁盘文件
        //插入数据库
        int re = mapper.updateUserImgByWeb(uuid,userId);
        if (re ==1){
            OutputStream out = null;
            try {
                out = new FileOutputStream(path);
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<User> queryUserForWeb(Integer identify, Integer companyId, String name) {
        return mapper.queryUserForWeb(identify, companyId, name);
    }

    @Override
    public int addUser(String name, String password, Integer identify, Integer companyId) {
        return mapper.addUser(name,password,identify,companyId);
    }

    @Override
    public int editUser(Integer id, String name, String password,
                        Integer identify, Integer companyId,
                        String address,String phone,String session,String img) {
        return mapper.editUser(id,name,password,identify,companyId,address,phone,session,img);
    }

    @Override
    public List<DangerForArea> queryDangerForArea(Integer itemId, Integer companyId) {

        List<DangerForArea> list = new ArrayList<>();
        List<CountForArea> countForAreas = mapper.queryDangerForArea(itemId,companyId);
        
        List<String> dangerArea = new ArrayList<>((new LinkedHashSet<>()));

        for (CountForArea countForArea : countForAreas){

            dangerArea.add(countForArea.getDangerArea());
        }
        dangerArea = new ArrayList<>(new LinkedHashSet<>(dangerArea));
        System.out.println("dangerArea.size() = " + dangerArea.size());
        int[] importantArea= new int[dangerArea.size()];
        int[] seriousArea = new int[dangerArea.size()];
        int[] countArea= new int[dangerArea.size()];
        for (CountForArea countForArea : countForAreas) {

            for (int i = 0; i < dangerArea.size(); i++){

                if (dangerArea.get(i).equals(countForArea.getDangerArea())){

                    //严重
                    if (countForArea.getDangerLevel() == 2){
                        seriousArea[i] = seriousArea[i] + countForArea.getCount();
                    }

                    //紧要
                    if (countForArea.getDangerLevel() == 3){
                        importantArea[i] = importantArea[i] + countForArea.getCount();
                    }
                    countArea[i] = countArea[i] + countForArea.getCount();
                }
            }
        }

        DangerForArea dangerForArea = new DangerForArea();
        dangerForArea.setCountArea(countArea);
        dangerForArea.setDangerArea(dangerArea);
        dangerForArea.setImportantArea(importantArea);
        dangerForArea.setSeriousArea(seriousArea);
        list.add(dangerForArea);
        return list;
    }

    @Override
    public List<DangerForUser> queryCountForUser(Integer itemId) {

        List<DangerForUser> list = new ArrayList<>();
        List<CountForUser> countForUsers = mapper.queryCountForUser(itemId);

        List<String> dangerUser = new ArrayList<>();
        for (CountForUser countForUser : countForUsers){
            dangerUser.add(countForUser.getUserName());
        }
        dangerUser = new ArrayList<>(new LinkedHashSet<>(dangerUser));
        int[] overdueData = new int[dangerUser.size()];
        int[] sumData = new int[dangerUser.size()];

        for (CountForUser countForUser : countForUsers){
            for (int i = 0; i < dangerUser.size(); i++){
                if (dangerUser.get(i).equals(countForUser.getUserName())){

                    if (countForUser.getDangerState() == 0){
                        overdueData[i] = overdueData[i] + countForUser.getCount();
                    }
                    sumData[i] = sumData[i] + countForUser.getCount();
                }
            }
        }
        DangerForUser dangerForUser = new DangerForUser();
        dangerForUser.setDangerUser(dangerUser);
        dangerForUser.setOverdueData(overdueData);
        dangerForUser.setSumData(sumData);
        list.add(dangerForUser);

        return list;
    }

    @Override
    public List<Danger> queryDanger1() {

        Date date = new Date(System.currentTimeMillis());
        List<Danger> list = mapper.queryDanger2();

        for (Danger danger: list) {
            if (date.after(danger.getLimitTime())){
                updateDangerState(danger.getDangerId());
            }
        }
        return list;
    }

    @Override
    public int updateDangerState(Integer dangerId) {
        return mapper.updateDangerState(dangerId);
    }

    @Override
    public int addCompany(String company_name, String companyDec, Integer type) {
        return mapper.addCompany(company_name,companyDec,type);
    }

    @Override
    public int editCompany(String id, String company_name, String companyDec, Integer type) {
        return mapper.editCompany(id,company_name,companyDec,type);
    }

    @Override
    public int updateUser(Integer id, String name, String phone, String address) {
        return mapper.updateUser(id,name,phone,address);
    }

    @Override
    public int updateItemState() {
        List<Item> items = mapper.queryItems(null,null);
        int a = 0;
        for ( Item item: items) {
            int countForAll = mapper.queryCountDanger(item.getId(),null,null,
                    null,null,null);
            int countForCom = mapper.queryCountDanger(item.getId(),null,null,
                    null,3,null);
            if (countForAll != 0){
                if (countForAll == countForCom){
                    a = mapper.updateItemState(2,item.getId());
                }
                else {
                    a = mapper.updateItemState(1,item.getId());
                }
            }

        }
        return a;
    }

    @Override
    public int insertReport(Integer userId, String report, Date createDate ,
                            Integer month,Integer state) {
        return mapper.insertReport(userId,report,createDate,month,state);
    }

    @Override
    public List<Report> queryReport(Integer userId, String report, Date createDate ,
                                    Integer month,Integer state) {
        return mapper.queryReport(userId,report,createDate,month,state);
    }

    @Override
    public int updateReport(Integer userId, String report, Date createDate, Integer month, Integer state) {
        return mapper.updateReport(userId,report,createDate,month,state);
    }

    @Override
    public int updatePassword(Integer id, String password) {
        return mapper.updatePassword(id,password);
    }

    @Override
    public void insertReports() {
        Calendar c = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());

        List<User> users = mapper.queryUserForWeb(null,null,null);
        for (User user: users) {
            mapper.insertReport(user.getId(),null,date,c.get(Calendar.MONTH)+1,0);
        }
    }

    @Override
    public List<Reform> queryReform(Integer id, Integer dangerId, Integer zhenggaiPId) {
        return mapper.queryReform(id,dangerId,zhenggaiPId);
    }



}
