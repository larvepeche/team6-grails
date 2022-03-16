package lamba.rest

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import lamba.Banner
import lamba.Pagination

@Secured(value=["hasRole('ROLE_ADMIN')"])
class BannerRestController extends RestfulController<Banner> {
    static responseFormats = ['json', 'xml']

    def bannerService

    BannerRestController() {
        super(Banner)
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
        def max = params.max ?: 3
        respond bannerService.getTop(max), model: [bannerCount: bannerService.count()]
    }

    @Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
    def count() {
        def pagination = new Pagination();
        pagination.total = bannerService.count()
        respond pagination
    }
}
