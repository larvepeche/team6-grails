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
    public static String uploadUserImage(Long userId, MultipartFile multipartFile){
        if (userId && multipartFile){
            String contactImagePath = "${getRootPath()}contact-image/"
            makeDirectory(contactImagePath)
            multipartFile.transferTo(new File(contactImagePath, userId + "-" + multipartFile.originalFilename))
            return multipartFile.originalFilename
        }
        return ""
    }

    public static String uploadBannerImage(Long bannerId, MultipartFile multipartFile){
        if (bannerId && multipartFile){
            String bannerImagePath = "${getRootPath()}banner-image/"
            makeDirectory(bannerImagePath)
            multipartFile.transferTo(new File(bannerImagePath, bannerId + "-" + multipartFile.originalFilename))
            return multipartFile.originalFilename
        }
        return ""
    }
}
