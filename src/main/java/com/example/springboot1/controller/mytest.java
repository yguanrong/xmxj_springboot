package com.example.springboot1.controller;

import java.io.*;

public class mytest {


    public static void main(String[] args) {

        //f1,f2分别为需要剪切的歌曲路径

        File f1 = new File("D:\\CloudMusic\\千与千寻.mp3");

        //f为合并的歌曲

        File f = new File("C:\\Users\\GuanRong\\Desktop\\千与千寻-铃声.mp3");

        cut1(f1,f);

    }



    public static void cut1(File f1,File f){

        BufferedInputStream bis1 = null;

        BufferedOutputStream bos = null;

        //第一首歌剪切部分起始字节
        int start1 = 390600;//320kbps（比特率）*10s*1024/8=2375680 比特率可以查看音频属性获知

        int end1 = 4915200;//320kbps*120s*1024/8=4915200


        int tatol1 = 0;

        try {

            //两个输入流

            bis1 = new BufferedInputStream(new FileInputStream(f1));

            //缓冲字节输出流（true表示可以在流的后面追加数据，而不是覆盖！！）

            bos = new BufferedOutputStream(new FileOutputStream(f,true));



            //第一首歌剪切、写入

            byte[] b1= new byte[512];

            int len1 = 0;

            while((len1 = bis1.read(b1))!=-1){

                tatol1+=len1;   //累积tatol

                if(tatol1<start1 ){  //tatol小于起始值则跳出本次循环
                    continue;
                }
                bos.write(b1);   //写入的都是在我们预先指定的字节范围之内

                if(tatol1>=end1 ){  //当tatol的值超过预先设定的范围，则立刻刷新bos流对象，并结束循环
                    bos.flush();
                    break;
                }



            }

            System.out.println("歌曲剪切完成！");


        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }finally{

            try {//切记要关闭流！！

                if(bis1!=null) bis1.close();

//                if(bis2!=null) bis2.close();

                if(bos!=null) bos.close();

            } catch (IOException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }

        }

    }
}
