package com.example

import io.quarkiverse.jooq.runtime.JooqCustomContext
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.jboss.logging.Logger
import org.jooq.Configuration
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named
import javax.ws.rs.Produces

@ApplicationScoped
class CustomJooqFactory {

    private val LOGGER: Logger = Logger.getLogger(CustomJooqFactory::class.java)

    @ApplicationScoped
    @Produces
    @Named("customJooqConfiguration")
    fun create(): JooqCustomContext {
        LOGGER.info("CustomJooqFactory: create")
        return object : JooqCustomContext {
            override fun apply(configuration: Configuration) {
                super.apply(configuration)
                val connectionFactory: ConnectionFactory = ConnectionFactories.get(
                        ConnectionFactoryOptions
                            .parse("r2dbc:h2:mem:///jooq-test")
                            .mutate()
                            .option(ConnectionFactoryOptions.USER, "sa")
                            .option(ConnectionFactoryOptions.PASSWORD, "")
                            .build()
                )
                configuration.set(connectionFactory)
            }
        }
    }

}