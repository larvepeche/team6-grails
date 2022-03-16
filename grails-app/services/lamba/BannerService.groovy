package lamba

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

import javax.servlet.http.HttpServletRequest

interface IBanner {

    Banner get(Serializable id)

    List<Banner> list(Map args)

    Long count()

    void delete(Serializable id)

    Banner save(Banner banner)

}

@Service(Banner)
@Transactional
abstract class BannerService implements IBanner {
    def uploadImage(Banner banner, HttpServletRequest request) {
        if (request.getFile("bannerImage") && !request.getFile("bannerImage").filename.equals("")){
            String image = FileUtil.uploadImage(banner.id, request.getFile("bannerImage"), "banner-image")
            if (!image.equals("")){
                banner.image = image
                banner.save(flush:true)
            }
        }
    }

    def save(GrailsParameterMap params, HttpServletRequest request) {
        Banner banner = new Banner(params)
        def response = AppUtil.saveResponse(false, banner)
        if(banner.validate()) {
            banner.save(flush: true)
            if(!banner.hasErrors()) {
                response.isSuccess = true
                uploadImage(banner, request)
            }
        }
        return response
    }

    def getTop(Integer max) {
        return Banner.executeQuery("from Banner as b where b.sliderOrder BETWEEN :min AND :max order by slideOrder", [min: 1], [max: max])
    }
}