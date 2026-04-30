package com.example.orderupapp.screen

import android.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.orderupapp.AppScaffold
import com.example.orderupapp.List.menu
import com.example.orderupapp.components.PedidoItem
import com.example.orderupapp.model.TipoProducto
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.orderupapp.Routes

@Composable
fun Pedido() {
    var contador by rememberSaveable() { mutableIntStateOf(0)}
        AppScaffold(title = "Pedidos") { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
            ) {
                items(menu) { menu ->
                    PedidoItem(
                        menu = menu,
                        onClick = { contador++ }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }

