package windown

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hildan.krossbow.stomp.StompClient
import org.hildan.krossbow.stomp.StompConnectionException
import org.hildan.krossbow.stomp.subscribeText
import org.hildan.krossbow.websocket.ktor.KtorWebSocketClient
import java.awt.BorderLayout
import javax.swing.*

class ChatWindow : JFrame("socket 聊天"){
    init {
        setSize(1000, 700)
        defaultCloseOperation = EXIT_ON_CLOSE
        add(ChatLayout())
    }

    companion object {
        fun show() {
            ChatWindow().isVisible = true
        }
    }
}


class ChatLayout: JPanel(BorderLayout()) {


    private val toolBar = JToolBar()
    private  var tokenField = JTextField("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Njc1MzA2OTMsInVzZXJuYW1lIjoiYWRtaW4ifQ.Tv-6SlXjB6_GG6FhyzaEMaACtnSX6qfDLWhfgsUbiog")
    private var connectBtn = JButton("连接")

    init {
        toolBar.add(tokenField)
        toolBar.add(connectBtn)
        add(toolBar,BorderLayout.NORTH)
        connectBtn.addActionListener {
            socketConnect()
        }
    }

    /**
     * 连接socket
     * http://192.168.199.32/idea-chat
     */
    @OptIn(DelicateCoroutinesApi::class)
    private fun socketConnect() {


        ///


        GlobalScope.launch {
            val stompClient = StompClient(KtorWebSocketClient())
            try{
                val session = stompClient.connect("ws://192.168.199.86/idea-chat?token=${tokenField.text}")
                val subscribeText = session.subscribeText("/room/闲聊吹水")
                launch {
                    subscribeText.collect {msg ->
                        println("收到消息:$msg")

                    }
                }
            }catch (e: StompConnectionException) {
                println("连接出现异常了:$e  ")
            }

        }


    }

}
