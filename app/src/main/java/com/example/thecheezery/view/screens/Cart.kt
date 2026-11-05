package com.example.thecheezery.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thecheezery.model.domain.Product
import com.example.thecheezery.model.domain.data.coldDrinks
import com.example.thecheezery.model.domain.data.salties
import com.example.thecheezery.ui.theme.Less_Purple
import com.example.thecheezery.ui.theme.Pinky
import com.example.thecheezery.ui.theme.Very_purple
import com.example.thecheezery.ui.theme.helloBunny
import com.example.thecheezery.viewmodel.CartUiState
import com.example.thecheezery.viewmodel.CartViewModel


@Composable
fun Cart(
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by cartViewModel.uiState.collectAsState()

    CartContent(
        uiState = uiState,
        onRemove = cartViewModel::removeProduct,
        modifier = modifier
    )
}

@Composable
fun CartContent(
    uiState: CartUiState,
    onRemove: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Order",
            fontFamily = helloBunny,
            fontSize = 88.sp,
            lineHeight = 96.sp,
            color = Pinky,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        if (uiState.isEmpty) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add some products to your order!",
                    fontSize = 20.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                items(uiState.items, key = { it.name }) { product ->
                    CartItem(product = product, onRemove = { onRemove(product) })
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Total",
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                modifier = Modifier.alignByBaseline()
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = "\$${uiState.total}",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Less_Purple,
                modifier = Modifier.alignByBaseline()
            )
        }
    }
}

@Composable
private fun CartItem(product: Product, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = product.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "\$${product.price}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Very_purple
        )
        IconButton(onClick = onRemove) {
            Icon(
                imageVector = Icons.Outlined.Cancel,
                contentDescription = "Eliminar ${product.name}",
                tint = Pinky
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CartPreview() {
    CartContent(
        uiState = CartUiState(
            items = listOf(salties[2], coldDrinks[2], coldDrinks[3], salties[4]),
            total = 18f
        ),
        onRemove = {}
    )
}

@Composable
@Preview(showBackground = true)
fun EmptyCartPreview() {
    CartContent(uiState = CartUiState(), onRemove = {})
}
