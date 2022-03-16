package lamba

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        group "/backoffice", {
            "/user/$action?/$îd?(.$format)?" (controller: "user")
            "/banner/$action?/$îd?(.$format)?" (controller: "banner")
            "/users" (controller: "user")
            "/banners" (controller: "banner")
        }

        group "/api", {
            "/users" (resources: "userRest")
            "/annonces" (resources: "annonce")
        }

        "/backoffice" {
            controller = "user"
            action = "index"
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
