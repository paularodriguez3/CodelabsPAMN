package com.example.draganddrop

import android.content.ClipData
import android.graphics.Color
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencia al ImageView de origen
        val imageViewSource = findViewById<ImageView>(R.id.image_view_source)

        // Configurar el evento de arrastre para el ImageView de origen
        imageViewSource.setOnLongClickListener { view ->
            // Crear el ClipData con información de la imagen que se va a arrastrar
            val clipData = ClipData.newPlainText("label", "DragData")
            val dragShadow = View.DragShadowBuilder(view)

            // Iniciar el arrastre
            view.startDragAndDrop(clipData, dragShadow, view, 0)
            view.visibility = View.INVISIBLE // Opcional: ocultar el elemento durante el arrastre
            true
        }

        // Configurar el evento de soltar en la zona de destino
        val dropTarget = findViewById<LinearLayout>(R.id.drop_target)
        dropTarget.setOnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    view.setBackgroundColor(Color.GREEN) // Cambia el color al entrar en la zona de destino
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    view.setBackgroundColor(Color.DKGRAY) // Cambia el color al salir de la zona de destino
                    true
                }
                DragEvent.ACTION_DROP -> {
                    // Restaurar el color de fondo de la zona de destino
                    view.setBackgroundColor(Color.BLUE)

                    // Recuperar la vista arrastrada
                    val draggedView = event.localState as View

                    // Cambiar el parent de la vista arrastrada a la zona de destino
                    val owner = draggedView.parent as ViewGroup
                    owner.removeView(draggedView)
                    (view as LinearLayout).addView(draggedView)

                    // Hacer que la vista sea visible nuevamente
                    draggedView.visibility = View.VISIBLE
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    view.setBackgroundColor(Color.DKGRAY) // Restaurar el color de fondo al final del arrastre
                    val draggedView = event.localState as View
                    draggedView.visibility = View.VISIBLE // Asegurarse de que la vista sea visible nuevamente
                    true
                }
                else -> false
            }
        }
    }
}
