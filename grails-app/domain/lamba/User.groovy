package lamba

import grails.plugin.springsecurity.annotation.Secured
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
@Secured(value=["hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')"])
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String image

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static hasMany = [panier: Product]

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        image nullable: true, blank: true
    }

    static mapping = {
	    password column: '`password`'
    }
}
