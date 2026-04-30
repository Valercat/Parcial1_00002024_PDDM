package com.example.orderupapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.orderupapp.AppScaffold
import com.example.orderupapp.List.menu
import kotlinx.coroutines.launch


//1.Mostrar la lista de productos que el cliente ha pedido, con la cantidad pedida, el precio
//    unitario y el subtotal de cada uno.
//2.Permitir eliminar un producto completo de la orden.
//3.Mostrar el total general a cobrar.
//4.Incluir un botón para confirmar la orden.
// Al confirmar, debe mostrarse un mensaje de
//    éxito y la
//5.orden debe vaciarse para iniciar una nueva.
//6.Debe permitir regresar a la pantalla del menú.

@Composable

fun Orden(navigateBack: () -> Unit, cantidad: Map<Int, Int>, onConfirm: () -> Unit) {
    val orden = menu.filter { (cantidad[it.id] ?: 0) > 0 }
    val total = orden.sumOf { (cantidad[it.id] ?: 0) * it.precio }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    AppScaffold(
        title = "Orden",
        snackbarHostState = snackbarHostState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                //hago otro items pa recorrer mi lista y que los id q coincidan sean anadidos
                items(orden) { producto ->
                    //si encuentra el id del producto hace eso, sino 0
                    val cant = cantidad[producto.id] ?: 0
                        Column {
                            Text(text = producto.nombre,
                                fontWeight = FontWeight.Bold)
                            Text(text = "Cantidad: $cant x $${producto.precio}")
                        }
                        Text(
                            text = "$${String.format("%.2f", cant * producto.precio)}",
                            style = MaterialTheme.typography.titleMedium
                        )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Total: $${String.format("%.2f", total)}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch { snackbarHostState.showSnackbar("Orden confirmada") }
                        onConfirm() // limpia datos
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirmar Orden")
                }
            TextButton(
                onClick = { navigateBack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Regresar al Menú")
            }
        }
    }
}