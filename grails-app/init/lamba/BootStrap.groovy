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

        def users = [
                new User(username: 'Naruto', password: 'password'),
                new User(username: 'Sasuke', password: 'password'),
                new User(username: 'Sakura', password: 'password'),
        ]

        users.each {
            def user ->
                user.save(flush: true, failOnError: true)
                UserRole.create(user, clientRole)
                UserRole.withSession {
                    it.flush()
                    it.clear()
                }
        }

        users[0].addToPanier(new Product(name: 'Trondro', price: 99, image: 'trondro.jpg'))
                .save()

        [
                new Product(name: 'Lens', price: 10, image: 'lens.jpg', quantity: 10),
                new Product(name: 'Coton', price: 9, image: 'coton.jpg', quantity: 9),
                new Product(name: 'Kofehy', price: 15, image: 'kofehy.jpg', quantity: 8),
                new Product(name: 'Fil', price: 17, image: 'fil.jpg', quantity: 7),
                new Product(name: 'Floor', price: 25, image: 'floor.jpg', quantity: 6),
                new Product(name: 'Aiguille', price: 88, image: 'aiguille.jpg', quantity: 5),
                new Product(name: 'Sosety', price: 77, image: 'sosety.jpg', quantity: 4),
                new Product(name: 'Sosice', price: 66, image: 'sosice.jpg', quantity: 3),
                new Product(name: 'Soldat', price: 55, image: 'soldat.jpg', quantity: 2),
                new Product(name: 'Soda', price: 44, image: 'soda.jpg', quantity: 1),
                new Product(name: 'Coca', price: 33, image: 'coca.jpg', quantity: 2),
                new Product(name: 'Cocaine', price: 22, image: 'cocaine.jpg', quantity: 3),
                new Product(name: 'Fanta', price: 11, image: 'fanta.jpg', quantity: 4),
                new Product(name: 'Goku', price: 11, image: 'goku.jpg', quantity: 5),
                new Product(name: 'Vegeta', price: 12, image: 'vegeta.jpg', quantity: 6),
                new Product(name: 'Trunks', price: 22, image: 'trunks.jpg', quantity: 7),
                new Product(name: 'Goten', price: 22, image: 'goten.jpg', quantity: 8),
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
