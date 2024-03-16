rootProject.name = "multi-demo"

// api
include("api:rest-api")
include("api:query")
include("api:command")
include("api:signing")

// domain
include("domain:user")

// infrastructure
include("infrastructure:jpa")
include("infrastructure:security")

// common
include("common:util")
