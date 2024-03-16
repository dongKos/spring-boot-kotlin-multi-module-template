rootProject.name = "demo"

// api
include("api:rest-api")

// domain
include("domain:user")
include("api:query")
include("common")
include("infrastructure")
include("infrastructure:jpa")
include("common:util")
findProject(":common:util")?.name = "util"
include("api:command")
findProject(":api:command")?.name = "command"
include("infrastructure:security")
findProject(":infrastructure:security")?.name = "security"
include("api:signing")
findProject(":api:signing")?.name = "signing"
