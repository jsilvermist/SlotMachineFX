SlotMachineFX
=============

A simple Java slot machine game developed with
[VSCode](https://code.visualstudio.com/) and
[SceneBuilder](https://gluonhq.com/products/scene-builder/),
using [JavaFX 12](https://openjfx.io/) as a GUI library.

## Usage

To run or debug this program, download and extract the
[JavaFX 12 SDK](https://gluonhq.com/products/javafx/) and point to the
JavaFX SDK `lib` directory in the module path in VSCode's launch configuration:

> .vscode/launch.json

```json
{
  "type": "java",
  "name": "SlotMachine",
  "request": "launch",
  "mainClass": "slotmachine.Main",
  "projectName": "SlotMachineFX",
  "vmArgs": "--module-path D:\\Development\\Java\\javafx-sdk-12.0.1\\lib\\ --add-modules javafx.controls,javafx.fxml"
}
```

You will also need to edit the `.classpath` file to point to the JavaFX SDK libraries:

> .classpath

```xml
<classpathentry kind="lib" path="D:\Development\Java\javafx-sdk-12.0.1\lib\javafx.base.jar"/>
<classpathentry kind="lib" path="D:\Development\Java\javafx-sdk-12.0.1\lib\javafx.graphics.jar"/>
<classpathentry kind="lib" path="D:\Development\Java\javafx-sdk-12.0.1\lib\javafx.controls.jar"/>
<classpathentry kind="lib" path="D:\Development\Java\javafx-sdk-12.0.1\lib\javafx.fxml.jar"/>
```

You should now be able to run and debug using VSCode's CodeLens or debugger!
