package lamba

import grails.util.Holders
import org.springframework.web.multipart.MultipartFile

class FileUtil {

    public static String getRootPath(){
        return Holders.servletContext?.getRealPath("")
    }


    public static File makeDirectory(String path){
        File file = new File(path)
        if (!file.exists()){
            file.mkdirs()
        }
        return file
    }

//    request.getFile("productFile")
    public static String uploadImage(Long id, MultipartFile multipartFile, String folderName){
        if (id && multipartFile){
            String imagePath = "${getRootPath()}contact-image/"
            makeDirectory(imagePath)
            multipartFile.transferTo(new File(imagePath, id + "-" + multipartFile.originalFilename))
            return multipartFile.originalFilename
        }
        return ""
    }
}
