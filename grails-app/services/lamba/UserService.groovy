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
        user.password = "new"

        def response = AppUtil.saveResponse(false, user)
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