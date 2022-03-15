package lamba

class Banner {

    String textContent
    String image

    static constraints = {
        textContent nullable: false, blank: false
        image nullable: true, blank: true
    }
}
