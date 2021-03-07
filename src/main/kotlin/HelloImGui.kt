import imgui.ImGui
import imgui.demo.ShowDemoWindowLayout.f
import imgui.demo.ShowDemoWindowWidgets.buf

fun main() {
  with(ImGui) {
    text("Hello, world %d", 123)
    button("Save")
    inputText("string", buf)
    sliderFloat("float", ::f, 0f, 1f)
  }
}