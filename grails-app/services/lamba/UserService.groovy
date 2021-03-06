package lamba

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

import javax.servlet.http.HttpServletRequest

interface IUser {
    User get(Serializable id)

    List<User> list(Map args)

    Long count()

    void delete(Serializable id)

    User save(User user)
}

@Service(User)
@Transactional
abstract class UserService implements IUser {

    def validateCart(User user) {
        user.panier.each {
            def p ->
                p.product.quantity -= p.cartQty
                p.cartQty= -1
        }
        user.save(flush: true, failOnError:true)
    }

    def removeFromCart(def productId, User user) {
        Product product = Product.get(productId)
        ProductCart productCart = ProductCart.findByProduct(product)
        if(productCart != null) {
            user.removeFromPanier(productCart)
            user.save(failOnError: true, flush: true)
        }
    }

    def updateCart(def productId, User user, def quantity) {
        Product product = Product.get(productId)
        if(product != null && quantity.toInteger() <= product.quantity) {
            ProductCart productCart = ProductCart.findByProduct(product)
            if(productCart != null) {
                productCart.cartQty = quantity.toInteger()
                user.addToPanier(productCart)
            } else {
                user.addToPanier(new ProductCart(product: product, cartQty: quantity.toInteger()))
            }
            user.save(failOnError: true, flush: true)
        }
    }

    def uploadImage(User user, HttpServletRequest request) {
        if (request.getFile("contactImage") && !request.getFile("contactImage").filename.equals("")){
            String image = FileUtil.uploadImage(user.id, request.getFile("contactImage"), "contact-image")
            if (!image.equals("")){
                user.image = image
                user.save(flush:true)
            }
        }
    }

    def save(GrailsParameterMap params, HttpServletRequest request) {
        User user = new User(params)

        def response  = AppUtil.saveResponse(false, user)
        if(user.validate()) {
            user.save(flush: true)
            Role role = Role.findByAuthorityLike('ROLE_CLIENT')
            UserRole.create(user,role)
            UserRole.withSession {
                it.flush()
                it.clear()
            }
            if(!user.hasErrors()) {
                response.isSuccess = true
                uploadImage(user, request)
            }
        }
        return response
    }
}