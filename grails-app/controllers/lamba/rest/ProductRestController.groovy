package lamba.rest

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import lamba.Banner
import lamba.Pagination
import lamba.Product
import lamba.User
import lamba.UserRole

@Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
class ProductRestController extends RestfulController<Product> {
    static responseFormats = ['json', 'xml']

    def productService

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

    @Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
    def top() {
        def max = params.max ?: 5
        respond productService.getTop(max)
    }

    @Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
    def count() {
        def pagination = new Pagination();
        pagination.total = productService.count()
        respond pagination
    }

    @Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
    def list(Integer page) {
        respond productService.getByPage(page)
    }
}
