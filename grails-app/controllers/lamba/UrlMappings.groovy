package lamba

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        group "/api", {
            "/users" (resources: "userRest")
            "/annonces" (resources: "annonce")
        }



        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
