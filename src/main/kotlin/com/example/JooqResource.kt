package com.example

import io.smallrye.mutiny.Uni
import org.jooq.DSLContext
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/jooq")
class JooqResource {

    @Inject
    lateinit var dsl: DSLContext

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun jooq(): Uni<Int> {
        return Uni.createFrom().publisher(dsl.createTableIfNotExists("jooq"))
    }

}