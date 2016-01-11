import org.lwjgl.*
import org.lwjgl.glfw.*
import org.lwjgl.opengl.*

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL11.*

fun main(args : Array<String>){
    run()
}

var window : Long = 0
var keyCallback = GLFWKeyCallback.create({
    window, key, scancode, action, mods ->
    if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
        glfwSetWindowShouldClose(window, GLFW_TRUE)

})
var errorCallback = GLFWErrorCallback.createPrint()

fun run() {
    println("Hello LWJGL " + Version.getVersion() + "!")
    try {
        init()
        loop()
        glfwDestroyWindow(window)
        keyCallback.release()
    } finally {
        glfwTerminate()
    }
}

fun init() {
    glfwSetErrorCallback(errorCallback)

    if( glfwInit() != GLFW_TRUE ) throw IllegalStateException("Fail")

    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

    var WIDTH = 300
    var HEIGHT = 300
    var TITLE = "Hello"

    window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, 0, 0)
    glfwSetKeyCallback(window, keyCallback)

    var vidmode : GLFWVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor())

    glfwSetWindowPos(
            window,
            (vidmode.width() - WIDTH) / 2,
            (vidmode.height() - HEIGHT) /2
    )

    glfwMakeContextCurrent(window)
    glfwSwapInterval(1)

    glfwShowWindow(window)

}

fun loop() {
    GL.createCapabilities()

    glClearColor(1.0f, 0.0f, 0.0f, 0.0f)

    while( glfwWindowShouldClose(window) == GLFW_FALSE ) {
        glClear(GL_COLOR_BUFFER_BIT)
        glfwSwapBuffers(window)
        glfwPollEvents()
    }
}
