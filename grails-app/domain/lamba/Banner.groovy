package lamba

class Banner {

    String textContent
    String image
    Integer sliderOrder

    static constraints = {
        textContent nullable: false, blank: false
        image nullable: true, blank: true
        sliderOrder nullable: true, unique: true
    }
}
