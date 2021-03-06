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
            "/users/$action?/$id?(.$format)?" (controller: "user")
            "/banner/$action?/$id?(.$format)?" (controller: "banner")
            "/product/$action?/$id?(.$format)?" (controller: "product")
            "/users" (controller: "user")
            "/banners" (controller: "banner")
            "/products" (controller: "product")
        }

        group "/api", {
            "/banners" (controller: "bannerRest", action: "index")
            "/banners/top/$max?" (controller: "bannerRest", action: "top")
            "/banners/count" (controller: "bannerRest", action: "count")
            "/banner" (resources: "bannerRest")
            "/banners" (resources: "bannerRest")

            "/products" (controller: "productRest", action: "index")
            "/products/top/$max?" (controller: "productRest", action: "top")
            "/products/count" (controller: "productRest", action: "count")
            "/products/list" (controller: "productRest", action: "list")
            "/product" (resources: "productRest")
            "/products" (resources: "productRest")

            "/users" (resources: "userRest")
            "/user" (resources: "userRest")
            "/users/panier" (controller: "userRest", action: "panier")
            post "/users/panier/$productId/$quantity" (controller: "userRest", action: "addToCart")
            delete "/users/panier/$productId" (controller: "userRest", action: "removeFromCart")
            "/users/panier/validate/$userId" (controller: "userRest", action: "validateCart")
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
