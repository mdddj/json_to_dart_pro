package layout


import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea
import org.fife.ui.rsyntaxtextarea.SyntaxConstants
import org.fife.ui.rtextarea.RTextScrollPane
import org.hildan.krossbow.stomp.StompClient
import org.hildan.krossbow.websocket.sockjs.SockJSClient
import util.CovertUtil
import util.HandleModels
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Panel
import javax.swing.JButton
import javax.swing.JToolBar

const val testJson = """

{
	"name": "梁典典",
	"age": 18,
	"address": {
		"city": "广州"
	}
}
"""

/**
 * json视图
 */
class JSONViewLayout(private val handleModels: HandleModels) : Panel(BorderLayout()) {
    private val jsonEdit = RSyntaxTextArea()
    private val toolBar = JToolBar()
    private val formatButton = JButton("格式化")
    private val generateButton = JButton("生成模型")
    private val socketButton = JButton("Socket连接")

    init {
        jsonEdit.text = testJson
        minimumSize = Dimension(500,600)
        jsonEdit.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_JSON
        jsonEdit.isCodeFoldingEnabled = false
        add(RTextScrollPane(jsonEdit),BorderLayout.CENTER)
        initToolbar()
        socketConnect()
    }
    private val client = HttpClient() {
        install(WebSockets)
    }

    /**
     * 连接socket
     * http://192.168.199.32/idea-chat
     */
    private fun socketConnect() {


        ///

        val stompClient = StompClient(SockJSClient())
        GlobalScope.launch {
           val session = stompClient.connect("http://192.168.199.78/idea-chat")

        }


    }

    /**
     * 工具条
     */
    private fun initToolbar(){

        //格式化
        formatButton.addActionListener {
        }
        toolBar.add(formatButton)

        //生成dart模型
        generateButton.addActionListener {
            val models = CovertUtil.parse(testJson)
            handleModels.invoke(models)
        }


        socketButton.addActionListener {
            socketConnect()
        }

        toolBar.add(generateButton)
        toolBar.add(socketButton)
        add(toolBar,BorderLayout.PAGE_START)
    }

}