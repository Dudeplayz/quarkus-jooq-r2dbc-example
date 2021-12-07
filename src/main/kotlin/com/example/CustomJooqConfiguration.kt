package com.example

import io.quarkiverse.jooq.runtime.JooqCustomContext
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.jboss.logging.Logger
import org.jooq.Configuration

class CustomJooqConfiguration : JooqCustomContext {

    private val LOGGER: Logger = Logger.getLogger(CustomJooqConfiguration::class.java)


    override fun apply(configuration: Configuration) {
        super.apply(configuration)
        LOGGER.info("CustomJooqConfiguration: create")
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