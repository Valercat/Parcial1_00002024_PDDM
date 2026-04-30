package com.example.orderupapp.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.orderupapp.AppScaffold
import com.example.orderupapp.List.menu
import com.example.orderupapp.components.PedidoItem

@Composable
fun Pedido(navigateToOrden: (Map<Int, Int>) -> Unit) {
    var contadores by rememberSaveable { mutableStateOf(mapOf<Int, Int>()) }

    AppScaffold(
        title = "Pedidos",
        onFabClick = { navigateToOrden(contadores) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {
            items(menu) { item ->
                PedidoItem(
                    menu = item,
                    onClick = {
                        //if. si esta el id pasa a sumar, si no, se queda en 0 (?: 0)
                        val actual = contadores[item.id] ?: 0
                        //para map (id, contador++) [menu.id to actual + 1] une ambos
                        //contadores = contadores + hace que aumente una posicion en el map
                        //sobreescribiento el anterior
                        contadores = contadores + (item.id to actual + 1)
                    },
                    contador = contadores
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
