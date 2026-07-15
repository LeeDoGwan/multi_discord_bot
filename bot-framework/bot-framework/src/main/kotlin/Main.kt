package org.example
import io.github.cdimascio.dotenv.Dotenv

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

fun main() {
    val dotenv = Dotenv.configure().ignoreIfMissing().load()
    val discordToken = dotenv["DISCORD_BOT_TOKEN"]


    JDABuilder.createDefault(discordToken)
        .enableIntents(GatewayIntent.GUILD_MESSAGES
            , GatewayIntent.MESSAGE_CONTENT)
        .addEventListeners(MessageListener())
        .build()

    println("bot activated!")
}
