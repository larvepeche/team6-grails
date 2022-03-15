package lamba.rest

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import lamba.User
import lamba.UserRole

@Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
class UserRestController extends RestfulController<User> {
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
}
