package lamba

import grails.gorm.transactions.Transactional

class BootStrap {

    def init = { servletContext ->
        initData()
    }
    def destroy = {
    }

    @Transactional
    void initData() {

        def adminRole = new Role(authority: "ROLE_ADMIN").save()
        def clientRole = new Role(authority: "ROLE_CLIENT").save()

        def admin = new User(username: "admin", password: "admin").save()

        UserRole.create(admin, adminRole)

        ["Naruto", "Sasuke", "Sakura"].each {
            String username ->
                def userInstance = new User(username: username, password: "password")
                userInstance.save(flush: true, failOnError: true)
                UserRole.create(userInstance,clientRole)
                UserRole.withSession {
                    it.flush()
                    it.clear()
                }
        }
    }
}
