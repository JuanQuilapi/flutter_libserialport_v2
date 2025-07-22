package org.sigrok.flutter_libserialport

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FlutterLibserialportPlugin */
class FlutterLibserialportPlugin: FlutterPlugin, MethodCallHandler {
    // El canal para comunicarse con Dart. Lo hacemos una variable de la clase.
    private var channel: MethodChannel? = null

    // Este método se llama cuando el plugin se conecta al motor de Flutter.
    // Reemplaza al antiguo `registerWith`.
    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        // El nombre del canal debe ser el mismo que el del código antiguo.
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_libserialport")
        channel?.setMethodCallHandler(this)
    }

    // Este es el manejador de llamadas desde Dart. Su lógica interna no necesita cambiar.
    override fun onMethodCall(call: MethodCall, result: Result) {
        // La lógica original de este método se puede copiar aquí si es necesario,
        // pero para empezar, podemos dejarlo simple.
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else {
            result.notImplemented()
        }
    }

    // Este método se llama cuando el plugin se desconecta. Es para limpiar.
    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel?.setMethodCallHandler(null)
        channel = null
    }
}
