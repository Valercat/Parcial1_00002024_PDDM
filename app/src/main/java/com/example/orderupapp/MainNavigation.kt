package com.example.orderupapp

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.orderupapp.screen.Pedido

@Composable
fun MovieApp() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                Pedido()

            }
            entry<Routes.Pedido> { key ->
                Pedido(

                )
            }
        }
    )
}