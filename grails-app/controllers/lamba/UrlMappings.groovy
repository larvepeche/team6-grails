package lamba

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        group "/backoffice", {
            "/user/$action?/$id?(.$format)?" (controller: "user")
            "/banner/$action?/$id?(.$format)?" (controller: "banner")
            "/product/$action?/$id?(.$format)?" (controller: "product")
            "/users" (controller: "user")
            "/banners" (controller: "banner")
            "/products" (controller: "product")
        }

        group "/api", {
            "/users" (resources: "userRest")
            "/banners" (resources: "bannerRest")
            "/products" (resources: "productRest")
            "/user" (resources: "userRest")
            "/banner" (resources: "bannerRest")
            "/product" (resources: "productRest")
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
