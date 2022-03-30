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
                UserRole.create(userInstance, clientRole)
                UserRole.withSession {
                    it.flush()
                    it.clear()
                }
        }

        [
                new Product(name: 'Lens', price: 10, image: 'lens.jpg'),
                new Product(name: 'Coton', price: 9, image: 'coton.jpg'),
                new Product(name: 'Kofehy', price: 15, image: 'kofehy.jpg'),
                new Product(name: 'Fil', price: 17, image: 'fil.jpg'),
                new Product(name: 'Floor', price: 25, image: 'floor.jpg'),
                new Product(name: 'Aiguille', price: 88, image: 'aiguille.jpg'),
                new Product(name: 'Sosety', price: 77, image: 'sosety.jpg'),
                new Product(name: 'Sosice', price: 66, image: 'sosice.jpg'),
                new Product(name: 'Soldat', price: 55, image: 'soldat.jpg'),
                new Product(name: 'Soda', price: 44, image: 'soda.jpg'),
                new Product(name: 'Coca', price: 33, image: 'coca.jpg'),
                new Product(name: 'Cocaine', price: 22, image: 'cocaine.jpg'),
                new Product(name: 'Fanta', price: 11, image: 'fanta.jpg'),
                new Product(name: 'Goku', price: 11, image: 'goku.jpg'),
                new Product(name: 'Vegeta', price: 12, image: 'vegeta.jpg'),
                new Product(name: 'Trunks', price: 22, image: 'trunks.jpg'),
                new Product(name: 'Goten', price: 22, image: 'goten.jpg'),
        ].each {
            def product ->
                product.save(flush: true, failOnError: true)
        }

        [
                new Banner(textContent: 'Balou', image: 'lorem.jpg', sliderOrder: 1),
                new Banner(textContent: 'Bosisou', image: 'roumpa.jpg', sliderOrder: 2),
                new Banner(textContent: 'Pikatchu', image: 'gotcha.jpg', sliderOrder: 3),
                new Banner(textContent: 'Pokemon', image: 'ding.jpg', sliderOrder: 4),
        ].each {
            def banner ->
                banner.save(flush: true, failOnError: true)
        }
    }
}
