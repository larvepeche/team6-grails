package lamba

class Product {

    String name
    Double price
    String image

    static constraints = {
        name nullable: false, blank: false
        price nullable: false
        image nullable: true, blank: true
    }
}
