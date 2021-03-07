import imgui.ImGui
import imgui.app.*

fun main() {
  with(ImGui) {
    text("Hello, world %d", 123)
    button("Save"){
      // do stuff
    }
    inputText("string", buf)
    sliderFloat("float", ::f, 0f, 1f)
  }
}