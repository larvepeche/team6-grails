package lamba.rest

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import lamba.Banner
import lamba.Product
import lamba.User
import lamba.UserRole

@Secured(value=["hasRole('ROLE_ADMIN')"])
class ProductRestController extends RestfulController<Product> {
    static responseFormats = ['json', 'xml']

    ProductRestController() {
        super(Product)
    }

    @Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
    @Override
    Object show() {
        return super.show()
    }

    @Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
    @Override
    Object index(Integer max) {
        return super.index(max)
    }
}
