package lamba

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

import javax.servlet.http.HttpServletRequest

interface IProduct {

    Product get(Serializable id)

    List<Product> list(Map args)

    Long count()

    void delete(Serializable id)

    Product save(Product product)

}

@Service(Product)
@Transactional
abstract class ProductService implements IProduct {
    def uploadImage(Product product, HttpServletRequest request) {
        if (request.getFile("productImage")){
            if(!request.getFile("productImage").filename.equals("")) {
                String image = FileUtil.uploadBannerImage(product.id, request.getFile("productImage"))
                if (!image.equals("")) {
                    product.image = image
                    product.save(flush: true)
                }
            }
        }
    }

    def save(GrailsParameterMap params, HttpServletRequest request) {
        Product product = new Product(params)
        def response = AppUtil.saveResponse(false, product)
        if(product.validate()) {
            product.save(flush: true)
            if(!product.hasErrors()) {
                response.isSuccess = true
                uploadImage(product, request)
            }
        }
        return response
    }
}