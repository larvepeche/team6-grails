package lamba

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import lamba.User
import lamba.UserService

import static org.springframework.http.HttpStatus.*

@Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
class UserController {


    SpringSecurityService springSecurityService
    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def validateCart() {
        User user = User.get(params.id)
        userService.validateCart(user)
        redirect action:"index", method:"GET"
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        User user = User.get(springSecurityService.currentUserId)
        session.active = 'User'
        respond userService.list(params), model:[userCount: userService.count(), curUser: user]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        User user = User.get(springSecurityService.currentUserId)
        respond new User(params), model:  [curUser: user]
    }

    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
//            userService.save(user)
            def response = userService.save(params, request)
            if (response.isSuccess) {
                flash.message = AppUtil.infoMessage("saved")
                redirect(controller: "user", action: "index")
            } else {
                flash.redirectParams = response.model
                flash.message = AppUtil.infoMessage("unable to save")
                redirect(controller: "user", action: "create")
            }
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
//                redirect user
//            }
//            '*' { respond user, [status: CREATED] }
//        }
    }

    def edit(Long id) {
        respond userService.get(id)
    }

    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Collection<UserRole> userRoles = UserRole.findAllByUser(User.get(id))
        userRoles*.delete()
        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
