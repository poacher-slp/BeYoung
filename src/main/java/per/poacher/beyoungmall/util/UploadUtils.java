package per.poacher.beyoungmall.util;

import org.springframework.web.multipart.MultipartFile;
import per.poacher.beyoungmall.controller.ex.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author poacher
 * @create 2022-05-10-12:48
 */
public class UploadUtils {

    private static final String DIR = "E:/IDEA/poacher/beyoungmall/src/main/resources/static/images/";
    public static final int IMG_MAX_SIZE = 10 * 1024 * 1024;    //图像上传的最大值
    public static final List<String> IMG_TYPE = new ArrayList<>();  //图片上传的类型
    static{
        IMG_TYPE.add("image/jpeg");
        IMG_TYPE.add("image/png");
        IMG_TYPE.add("image/bmp");
        IMG_TYPE.add("image/gif");
        IMG_TYPE.add("image/jpg");
    }

    public static String uploadImg(MultipartFile file,String itemName) {

        if(file.isEmpty()) {
            throw new FileEmptyException("图片为空");
        }
        if(file.getSize() > IMG_MAX_SIZE) {
            throw new FileSizeException("图片超出限制");
        }
        if(!IMG_TYPE.contains(file.getContentType())) {
            throw new FileTypeException("图片类型不支持");
        }
        //获取文件名
        String filename = file.getOriginalFilename();
        filename = UUID.randomUUID() + filename;
//        System.out.println("UUID后的名字" + filename);
//        获取项目目录名
        itemName = itemName + "/";
//        创建目录
        File fileDir = new File(DIR + itemName);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File filePath = new File(fileDir + "/" + filename);
        //方式一：通过transferTo上传文件
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }
        //方式二：通过流读取文件
//        byte[] buffer = new byte[1024 * 1024];
//        InputStream is = null;
//        FileOutputStream os = null;
//        try {
//            is = file.getInputStream();
//            os = new FileOutputStream(filePath);
//            int len = 0;
//            while ((len = is.read(buffer)) > 0) {
//                os.write(buffer, 0, len);
//            }
//            os.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return itemName + filename;
    }
}
