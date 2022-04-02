package lamba.rest

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import lamba.User
import lamba.UserRole
import lamba.UserService

@Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
class UserRestController extends RestfulController<User> {
    SpringSecurityService springSecurityService
    UserService userService

    static responseFormats = ['json', 'xml']
    UserRestController() {
        super(User)
    }

    @Secured(value=["hasRole('ROLE_ADMIN')"])
    @Override
    Object delete() {
        Collection<UserRole> userRoles = UserRole.findAllByUser(User.get(params.id))
        userRoles*.delete()
        return super.delete()
    }

    def panier() {
        User user = springSecurityService.currentUser
        respond user.panier
    }

    def addToCart() {
//        if(request.method == 'POST') {
            User user = springSecurityService.currentUser
            userService.addToCart(params.productId, user)
            respond user.panier
//        }
    }

    def removeFromCart() {
        User user = springSecurityService.currentUser
        userService.removeFromCart(params.productId, user)
        respond user.panier
    }

    def validateCart() {
        User user = User.get(params.userId)
        userService.validateCart(user)
        respond user.panier
    }
}
