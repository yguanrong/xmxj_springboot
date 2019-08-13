package com.example.springboot1.controller;

import com.example.springboot1.model.*;
import com.example.springboot1.service.Service;

import com.example.springboot1.websocket.MyWebSocket;
import com.example.springboot1.websocket.model.WebSocketMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {

    @Autowired
    private Service service;

    /**测试thymeleaf
     * **/
    @GetMapping("/test")
    public String mytest(@RequestParam(value = "msg",required = false) String msg){

        MyWebSocket.sendAll(msg);
        return msg;

    }

    @PostMapping("/login")
    public User login(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password){

        return service.login(userName, password );
    }

    @GetMapping("/queryCompanyByUser")
    public Company queryCompanyByUser(
            @RequestParam(value = "userId") Integer userId){

        return service.queryCompanyByUser(userId);
    }

    @GetMapping("/queryCompany")
    public List<Company> queryCompany(
            @RequestParam(value = "companyName",required = false) String companyName,
            @RequestParam(value = "companyType",required = false) Integer companyType)
    {
        return service.queryCompany(companyName,companyType);
    }

    @GetMapping("/queryItems")
    public List<Item> queryItems(
            @RequestParam(value = "itemName" ,required = false) String itemName,
            @RequestParam(value = "userId" ,required = false) Integer userId)
    {
        return service.queryItems(itemName,userId);
    }

    @GetMapping("/queryCountDanger")
    public int queryCountDanger(
            @RequestParam(value = "itemId" ,required = false) Integer itemId,
            @RequestParam(value = "companyId" ,required = false) Integer companyId,
            @RequestParam(value = "createPId" ,required = false) Integer createPId,
            @RequestParam(value = "zhenggaiPId" ,required = false) Integer zhenggaiPId,
            @RequestParam(value = "dangerState",required = false) Integer dangerState,
            @RequestParam(value = "level",required = false) Integer level){
        return service.queryCountDanger(itemId,companyId,createPId,zhenggaiPId, dangerState , level);
    }

    @PostMapping("/uploadImg")
    public ResultInfo uploadImg(
            @RequestParam(value = "base64") String base64,
            @RequestParam(value = "dangerId") Integer dangerId,
            @RequestParam(value = "flag") Integer flag){

        //如果flag等于1：表示是隐患照片，如果flag=2表示是整改照片
        return service.uploadImg(base64, dangerId,flag);
    }

    @GetMapping("/queryDanger")
    public List<Danger> queryDanger(
            @RequestParam(value = "dangerLevel",required = false) Integer dangerLevel,
            @RequestParam(value = "dangerType",required = false) Integer dangerType,
            @RequestParam(value = "dangerState",required = false) Integer dangerState,
            @RequestParam(value = "companyId",required = false) Integer companyId,
            @RequestParam(value = "itemId",required = false) Integer itemId,
            @RequestParam(value = "zhenggaiPId",required = false) Integer zhenggaiPId,
            @RequestParam(value = "createPId",required = false) Integer createPId)
    {
        return service.queryDanger(dangerLevel,dangerType,dangerState,companyId,itemId,zhenggaiPId,createPId);
    }

    @GetMapping("/queryDangerImg")
    public ResultInfo queryDangerImg(
            @RequestParam(value = "dangerId") Integer dangerId,
            @RequestParam(value = "flag") Integer flag)
    {
        //当flag=1是查询隐患照片，当flag=2查询整改照片
        return service.queryDangerImg(dangerId,flag);
    }

    @GetMapping("/queryDangerType")
    public List<Data> queryDangerType(){
        return service.queryDangerType();
    }
    @GetMapping("/queryDangerArea")
    public List<Data> queryDangerArea(){
        return service.queryDangerArea();
    }
    @GetMapping("/queryCompanyPart")
    public List<Data> queryCompanyPart(){
        return service.queryCompanyPart();
    }

    @GetMapping("/queryUserByCompany")
    public List<Data> queryUserByCompany(
            @RequestParam(value = "companyId") Integer companyId){
        return service.queryUserByCompany(companyId);
    }
    @GetMapping("/queryUser")
    public List<Data> queryUser(
            @RequestParam(value = "identify" ,required = false) Integer identify,
            @RequestParam(value = "companyId" ,required = false) String companyId,
            @RequestParam(value = "name" ,required = false) String name
    ){
        return service.queryUser(identify,companyId,name);
    }
    @GetMapping("/queryUserForWeb")
    public List<User> queryUserForWeb(
            @RequestParam(value = "identify" ,required = false) Integer identify,
            @RequestParam(value = "companyId" ,required = false) Integer companyId,
            @RequestParam(value = "name" ,required = false) String name
    ){
        return service.queryUserForWeb(identify,companyId,name);
    }
    @PostMapping("/newDanger")
    public Integer newDanger(
            @RequestParam(value = "dangerName") String dangerName,
            @RequestParam(value = "dangerAreaId") Integer dangerAreaId,
            @RequestParam(value = "companyId") Integer companyId,
            @RequestParam(value = "dangerType") Integer dangerType,
            @RequestParam(value = "dangerDec") String dangerDec,
            @RequestParam(value = "zhenggaiDec") String zhenggaiDec,
            @RequestParam(value = "dangerState") Integer dangerState,
            @RequestParam(value = "itemId") Integer itemId,
            @RequestParam(value = "dangerLevel") Integer dangerLevel,
            @RequestParam(value = "limitTime") Date limitTime,
            @RequestParam(value = "zhenggaiPId") Integer zhenggaiPId,
            @RequestParam(value = "createPId") Integer createPId
            ){
        MyWebSocket.sendAll("更新隐患数目");
        return service.newDanger(dangerName, dangerAreaId, companyId,
                dangerType, dangerDec,zhenggaiDec,
                dangerState, itemId, dangerLevel,
                limitTime, zhenggaiPId, createPId);
    }

    @PostMapping("/updateDanger")
    public Integer updateDanger(@RequestParam(value = "dangerId") String dangerId,
                                @RequestParam(value = "dangerName") String dangerName,
                                @RequestParam(value = "dangerAreaId") Integer dangerAreaId,
                                @RequestParam(value = "companyId") Integer companyId,
                                @RequestParam(value = "dangerType") Integer dangerType,
                                @RequestParam(value = "dangerDec") String dangerDec,
                                @RequestParam(value = "zhenggaiDec") String zhenggaiDec,
                                @RequestParam(value = "dangerState") Integer dangerState,
                                @RequestParam(value = "itemId") Integer itemId,
                                @RequestParam(value = "dangerLevel") Integer dangerLevel,
                                @RequestParam(value = "limitTime") Date limitTime,
                                @RequestParam(value = "zhenggaiPId") Integer zhenggaiPId,
                                @RequestParam(value = "createPId") Integer createPId)
    {
        return service.updateDanger(dangerId, dangerName, dangerAreaId, companyId,
                dangerType, dangerDec,zhenggaiDec,
                dangerState, itemId, dangerLevel,
                limitTime, zhenggaiPId, createPId);
    }

    @PostMapping("/reform")
    public int reform(
            @RequestParam(value = "zhenggaiLog") String zhenggaiLog,
            @RequestParam(value = "dangerState") Integer dangerState,
            @RequestParam(value = "updateTime") Date updateTime,
            @RequestParam(value = "dangerId") Integer dangerId)
    {
        return service.zhenggai(zhenggaiLog,dangerState,updateTime,dangerId);
    }

    @PostMapping("/review")
    public int review(
            @RequestParam(value = "reviewLog") String reviewLog,
            @RequestParam(value = "dangerState") Integer dangerState,
            @RequestParam(value = "reviewTime") Date reviewTime,
            @RequestParam(value = "reviewPId") Integer reviewPId,
            @RequestParam(value = "dangerId") Integer dangerId)
    {
        return service.review(reviewLog,dangerState,reviewTime,dangerId,reviewPId);
    }

    @GetMapping("/queryDangerByWeek")
    public List<CountForWeek> queryDangerByState(
            @RequestParam(value = "dangerLevel", required = false) Integer dangerLevel,
            @RequestParam(value = "itemId", required = false) Integer itemId,
            @RequestParam(value = "companyId", required = false) Integer companyId
            ){

        return service.queryDangerByState(dangerLevel,itemId,companyId);
    }

    @GetMapping("/queryCountForCompany")
    public List<CountForCompany> queryCountForCompany(
            @RequestParam(value = "itemId", required = false) Integer itemId){
        return service.queryCountForCompany(itemId);
    }

    @GetMapping("/queryCountForUser")
    public List<DangerForUser> queryCountForUser(
            @RequestParam(value = "itemId", required = false) Integer itemId){
        return service.queryCountForUser(itemId);
    }

    @GetMapping("/queryDangerForType")
    public List<DangerForType> queryDangerForType(
            @RequestParam(value = "itemId", required = false) Integer itemId,
            @RequestParam(value = "companyId", required = false) Integer companyId
    ){

        return service.queryDangerForType(itemId,companyId);
    }

    @GetMapping("/queryDangerForArea")
    public List<DangerForArea> queryDangerForArea(
            @RequestParam(value = "itemId", required = false) Integer itemId,
            @RequestParam(value = "companyId", required = false) Integer companyId
    ){

        return service.queryDangerForArea(itemId,companyId);
    }

    @GetMapping("/queryUserImg")
    public ResultInfo queryUserImg(
            @RequestParam(value = "userId", required = false) Integer userId
    ){
        return service.queryUserImg(userId);
    }

    @PostMapping("/updateUserImg")
    public ResultInfo updateUserImg(
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "imgData", required = false) String imgData
    ){
        return service.updateUserImg(userId,imgData);
    }
    @PostMapping("/updateUserImgByWeb")
    public int updateUserImgByWeb(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "userId", required = false) Integer userId
    ){
        return service.updateUserImgByWeb(file,userId);
    }

    @PostMapping("/addItem")
    public int addItem(
            @RequestParam(value = "itemName", required = false) String itemName,
            @RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "manageId", required = false) Integer manageId,
            @RequestParam(value = "saferOfficeId", required = false) String saferOfficeId,
            @RequestParam(value = "responserId", required = false) String responserId,
            @RequestParam(value = "state", required = false) Integer state
    ){
        return service.addItem(itemName,companyId,manageId,saferOfficeId,responserId,state);
    }
    @PostMapping("/updateItem")
    public int updateItem(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "itemName", required = false) String itemName,
            @RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "manageId", required = false) Integer manageId,
            @RequestParam(value = "saferOfficeId", required = false) String saferOfficeId,
            @RequestParam(value = "responserId", required = false) String responserId,
            @RequestParam(value = "state", required = false) Integer state
    ){
        return service.updateItem(id,itemName,companyId,manageId,saferOfficeId,responserId,state);
    }

    //重新整改，对超期的隐患处理
    @PostMapping("/reEdit")
    public int reEdit(
            @RequestParam(value = "dangerId", required = false) Integer dangerId,
            @RequestParam(value = "limitTime", required = false) Date limitTime,
            @RequestParam(value = "zhenggaiDec", required = false) String zhenggaiDec,
            @RequestParam(value = "reformLog", required = false) String reformLog,
            @RequestParam(value = "zhenggaiPId", required = false) Integer zhenggaiPId,
            @RequestParam(value = "dangerData", required = false) String dangerData

    ){
        System.out.println("dangerData = " + dangerData);
        return service.reEdit(dangerId,limitTime,zhenggaiDec,zhenggaiPId,reformLog,dangerData);
    }
    //添加用户
    @PostMapping("/addUser")
    public int addUser(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "identify", required = false) Integer identify,
            @RequestParam(value = "companyId", required = false) Integer companyId

    ){
        return service.addUser(name,password,identify,companyId);
    }

    //修改
    @PostMapping("/editUser")
    public int editUser(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "identify", required = false) Integer identify,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "img", required = false) String img,
            @RequestParam(value = "company_id", required = false) Integer companyId
    ){
        return service.editUser(id,name,password,identify,companyId,address,phone,session,img);
    }

    @PostMapping("/addCompany")
    public int addCompany(
            @RequestParam(value = "company_name", required = false) String company_name,
            @RequestParam(value = "companyDec", required = false) String companyDec,
            @RequestParam(value = "type", required = false) Integer type){

        return service.addCompany(company_name,companyDec,type);
    }

    @PostMapping("/editCompany")
    public int editCompany(
            @RequestParam(value = "id", required = false ) String id,
            @RequestParam(value = "company_name", required = false) String company_name,
            @RequestParam(value = "companyDec", required = false) String companyDec,
            @RequestParam(value = "type", required = false) Integer type){

        return service.editCompany(id,company_name,companyDec,type);
    }

    @PostMapping("/updateUser")
    public int updateUser(
            @RequestParam(value = "id", required = false ) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "address", required = false) String address){

        return service.updateUser(id,name,phone,address);
    }

    @PostMapping("/insertReport")
    public int insertReport(
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "report", required = false) String report,
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "createDate", required = false) Date createDate){
        return service.insertReport(userId,report,createDate,month,state);
    }
    @PostMapping("/updateReport")
    public int updateReport(
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "report", required = false) String report,
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "createDate", required = false) Date createDate){
        return service.updateReport(userId,report,createDate,month,state);
    }

    @GetMapping("/queryReport")
    public List<Report> queryReport(
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "report", required = false) String report,
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "createDate", required = false) Date createDate){
        return service.queryReport(userId,report,createDate,month,state);
    }

    @PostMapping("/updatePassword")
    public int updatePassword(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "password", required = false) String password
    ){
        return service.updatePassword(id,password);
    }

    @GetMapping("/queryReform")
    public List<Reform> queryReform(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "dangerId", required = false) Integer dangerId,
            @RequestParam(value = "zhenggaiPId", required = false) Integer zhenggaiPId
    ){
        return service.queryReform(id,dangerId,zhenggaiPId);
    }

    @GetMapping("/sendMsg")
    public String sendMsg(
            @RequestParam(value = "msg", required = false) String msg
    ){
        System.out.println("msg = " + msg);
        MyWebSocket.sendAll(msg);
        return "1";
    }


}
