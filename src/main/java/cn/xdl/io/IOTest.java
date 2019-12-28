package cn.xdl.io;


import java.io.*;

public class IOTest {
    public static void main(String[] args) throws IOException {
        //fileRead("F:/a.txt","F:/b.txt");
        //demo("F:/a.txt","F:/b.txt");
        //srcCopy(new File("F:\\文件拷贝测试文件夹"),new File("F:\\文件拷贝测试文件夹"));
        //copyFile(new File("F:\\文件拷贝测试文件夹"),new File("F:\\文件拷贝测试文件夹"));
        srcCopy(new File("F:\\潭州-前端作业\\index"),new File("F:\\文件拷贝测试文件夹"));
    }

    /* 递归实现文件目录的复制 */
    public static void srcCopy(File source,File target) throws IOException {
        if(source.isDirectory()){
            target.mkdir();
            File[] files = source.listFiles();
            for(File fileSrc : files){
                System.out.println("==="+fileSrc);
                System.out.println(new File(target,fileSrc.getName()).getPath());
                srcCopy(fileSrc,new File(target,fileSrc.getName()));
            }
        }else if(source.isFile()){
            File file3 = new File(target.getAbsolutePath());
            file3.createNewFile();

        }

    }


    /* 字符流 读文件*/
    public static void demo(String fileInputPath, String fileOutputPath) throws IOException {
        PrintStream ps = new PrintStream(new FileOutputStream(fileInputPath));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileOutputPath)));

        String str = null;
        while((str = br.readLine()) != null){
            System.out.println(str);
        }
        br.close();
    }

    /* 通过字节流完成文件的拷贝 */
    public static void fileRead(String fileInputPath, String fileOutputPath) {
        FileInputStream fit = null;
        FileOutputStream fos = null;
        try {
            fit = new FileInputStream(fileInputPath);           //创建输入流
            fos = new FileOutputStream(fileOutputPath);         //创建输出流
            byte[] bArr = new byte[1024*8];                       //定义一个数组做缓冲区，限制每次读取的字节的大小
            int res = 0;                                        //定义每次读取的字节个数
            while((res = fit.read(bArr)) != -1){                //循环读取当读取的字节个数为0时停止
                fos.write(bArr,0,res);                     //每次读取到多少就写入多少
            }
            fit.close();
            fos.close();
            System.out.println("拷贝完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void file() throws IOException {
        File file = new File("F:/a.txt");
        if(file.exists()){
            System.out.println("存在,并创建了一个单层目录和多层目录。");

            File f1 = new File("F:/aaa");
            f1.mkdir();
            /*File f2 = new File("F:/AAA/BBB/CCC/DDD");
            f2.mkdirs();*/
        }else{
            System.out.println("不存在，新建了一个。");
            file.createNewFile();
        }
    }
}
