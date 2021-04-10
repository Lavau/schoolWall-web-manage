package top.leeti.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static String getRootDirectory(int typeId, String uuid){
        return new StringBuilder("G:\\schoolWall\\").append(typeId).append("\\").append(uuid).toString();
    }

    public static List<String> findAllPictureNames(int typeId, String uuid) {
        String path = getRootDirectory(typeId, uuid);
        List<String> fileNameList = new ArrayList<>();
        File f = new File(path);
        getFile(f, fileNameList);
        return fileNameList;
    }

    private static void getFile(File file, List<String> fileNameList){
        if(file != null){
            File[] files = file.listFiles();
            if(files != null){
                for(File f : files){
                    getFile(f, fileNameList);
                }
            }else{
                fileNameList.add(file.getName());
            }
        }
    }

    public static List<String> obtainListOfPictureUrl(int typeId, String uuid){
        List<String> pictureNameList = FileUtil.findAllPictureNames(typeId, uuid);
        List<String> pictureUrlList = new ArrayList<>();
        for(String pictureName : pictureNameList){
            pictureUrlList.add(new StringBuilder("http://127.0.0.1:8080/miniprogram/picture/obtain?typeId=").append(typeId).
                    append("&uuid=").append(uuid).append("&fileName=").append(pictureName).toString());
        }
        return pictureUrlList;
    }
}
