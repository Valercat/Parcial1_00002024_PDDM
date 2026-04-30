package com.example.orderupapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.orderupapp.screen.Orden
import com.example.orderupapp.screen.Pedido
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun PedidoApp() {
    val backStack = rememberNavBackStack(Routes.Home)

    var cantidades by rememberSaveable() { mutableStateOf<Map<Int, Int>>(mapOf()) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<Routes.Home> {
                Pedido(
                    navigateToOrden = { nuevasCantidades ->
                        cantidades = nuevasCantidades
                        backStack.add(Routes.Orden)
                    }
                )
            }

            entry<Routes.Orden> {
                Orden(
                    navigateBack = { backStack.removeLastOrNull() },
                    cantidad = cantidades,
                    //al confirmar la orden cantidades se limpia
                    onConfirm = { cantidades = mapOf() }
                )
            }
        }
    )
}