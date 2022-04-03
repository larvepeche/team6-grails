package lamba

class Product {

    String name
    Double price
    String image
    Integer ranking = Math.abs(new Random().nextInt() % 100) + 1
    Integer quantity

    static constraints = {
        name nullable: false, blank: false
        price nullable: false
        image nullable: true, blank: true
        ranking nullable: false
        quantity nullable: false
    }
}
