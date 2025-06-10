package empire.digiprem.handler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.util.HtmlUtils
import java.io.IOException
import java.time.LocalDateTime

class ServerWebSocketHandler : TextWebSocketHandler() {
    var sessions: MutableCollection<WebSocketSession> = ArrayList<WebSocketSession>()

    @Throws(Exception::class)
    protected override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        super.handleTextMessage(session, message)

        if (!sessions.contains(session)) {
            sessions.add(session)
        }
        val request: String = message.getPayload()
        println("Server received: $request")

        val response = String.format("response from server to '%s'" + session.getId(), HtmlUtils.htmlEscape(request))
        // session.sendMessage(new TextMessage(response));
        for (session2 in sessions) {
            if (session2.isOpen() && !session.getId().equals(session2.getId())) {
                // System.out.println("Server sends:{},"+maessage);
                session2.sendMessage(TextMessage(response))
            }
        }
    }


    @Scheduled(fixedRate = 5000)
    @Throws(IOException::class)
    fun sendPeriodicMessages() {
        sessionMessage("Server periodic message" + LocalDateTime.now())
    }

    @Throws(IOException::class)
    private fun sessionMessage(maessage: String) {
        for (session in sessions) {
            if (session.isOpen()) {
                // System.out.println("Server sends:{},"+maessage);
                session.sendMessage(TextMessage(maessage))
            }
        }
    }
}