package org.example

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import io.github.cdimascio.dotenv.Dotenv

class MessageListener : ListenerAdapter() {
    val dotenv = Dotenv.configure().ignoreIfMissing().load()

    val DEEPL_API_KEY = dotenv["DEEPL_API_KEY"]
    val DEEPL_API_URI = dotenv["DEEPL_API_URI"]
    val deepLClient = DeepLClient(
        apiKey = DEEPL_API_KEY,
        apiUrl = DEEPL_API_URI
    )

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author.isBot) {
            return
        }
        val result = deepLClient.translate(event.message.contentRaw, "JA")

        event.channel.sendMessage(result.translations.first().text).queue()
//        if(event.message.contentRaw == "!ping"){
//            event.channel.sendMessage("pong!").queue()
//        }
    }
}