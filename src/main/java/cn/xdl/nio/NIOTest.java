package cn.xdl.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class NIOTest {
    public static void main(String[] args) throws IOException {
        //srcCopy(new File("F:\\Java34\\1- JavaSE\\day01\\choose"),new File("F:/666"));
        //p
        fileCopy(new File("F:\\Java34\\1- JavaSE\\day01\\choose\\book/Java编程思想第四版完整中文高清版.pdf"),new File("F:/111/Java编程思想第四版完整中文高清版.pdf"));
    }

    /* 递归完成文件目录的复制 */
    public static void srcCopy(File source,File target) throws IOException {
        if(source.isDirectory()) {
            target.mkdirs();
            DirectoryStream<Path> paths = Files.newDirectoryStream(source.toPath());
            for (Path p : paths) {
                srcCopy(p.toFile(),new File(target,p.getFileName().toString()));
            }
        }else if(source.isFile()){
            System.out.println("正在拷贝："+source.getName());
            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(target);

            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024*8);

            while(true){
                int res = inChannel.read(buffer);
                if(res == -1){
                    break;
                }
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            System.out.println(source.getName()+" :拷贝完毕");
            outChannel.close();
            inChannel.close();
            fos.close();
            fis.close();
        }
    }

    /* 文件的拷贝 Files copy(String ) */
    public static void filesCopy(File source,File target) throws IOException {
        Files.copy(source.toPath(),target.toPath());
        System.out.println("拷贝完毕");
    }
    /* 文件的拷贝 transferTo */
    public static void fileCopyTo(String inSrc,String outSrc) throws IOException {
        FileChannel in = new FileInputStream(inSrc).getChannel();
        FileChannel out = new FileOutputStream(outSrc).getChannel();
        in.transferTo(0, in.size(), out);
        System.out.println("拷贝成功！");
    }
    /* 文件的拷贝 transferFrom */
    public static void fileCopyFrom(String inSrc,String outSrc) throws IOException {
        FileChannel in = new FileInputStream(inSrc).getChannel();
        FileChannel out = new FileOutputStream(outSrc).getChannel();
        out.transferFrom(in,0, in.size());
        System.out.println("拷贝成功！");
    }
    /* 文件的拷贝 */
    public static void fileCopy(File inSrc,File outSrc) throws IOException {

        if(!inSrc.exists()){
            System.out.println("要拷贝的文件路径不存在！！！");
            return;
        }
        //如果要拷贝到的文件不存在则新建
        if(!outSrc.exists()){
            outSrc.createNewFile();
            System.out.println("要拷贝到的文件不存在，已经新建！");
        }
        //声明文件
        FileInputStream fis = new FileInputStream(inSrc);
        FileOutputStream fos = new FileOutputStream(outSrc);

        //建立通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        //建立容器 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024*8);
        System.out.println("开始拷贝");
        while(true){
            int res = inChannel.read(buffer);
            System.out.println("缓冲区的位置："+buffer.position());
            if(res == -1){
                break;
            }
            System.out.println("缓冲区的位置："+buffer.position());
            System.out.println("缓冲区的限制："+buffer.limit());
            System.out.println("缓冲区的容量："+buffer.capacity());
            buffer.flip();
            System.out.println("已拷贝："+outChannel.size()/inChannel.size()+"%");
            outChannel.write(buffer);
            buffer.clear();
            System.out.println("缓冲区的位置："+buffer.position());
            System.out.println("缓冲区的限制："+buffer.limit());
            System.out.println("缓冲区的容量："+buffer.capacity());
        }
        outChannel.close();
        inChannel.close();
        fos.close();
        fis.close();
        System.out.println("拷贝结束");
    }


}
