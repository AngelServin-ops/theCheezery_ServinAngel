package com.example.thecheezery.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thecheezery.model.domain.Product
import com.example.thecheezery.model.domain.data.salties
import com.example.thecheezery.ui.theme.DisabledGrey
import com.example.thecheezery.ui.theme.Less_Purple
import com.example.thecheezery.ui.theme.Pinky
import com.example.thecheezery.ui.theme.PurpleGrey40
import com.example.thecheezery.ui.theme.Very_purple
import com.example.thecheezery.ui.theme.helloBunny
import com.example.thecheezery.viewmodel.CartViewModel
import com.example.thecheezery.viewmodel.ProductsViewModel


@Composable
fun Products(
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier,
    productsViewModel: ProductsViewModel = viewModel()
) {
    val cartState by cartViewModel.uiState.collectAsState()

    ProductsContent(
        title = productsViewModel.title,
        products = productsViewModel.products,
        cartItems = cartState.items,
        onAddToCart = cartViewModel::addProduct,
        modifier = modifier
    )
}

@Composable
fun ProductsContent(
    title: String,
    products: List<Product>,
    cartItems: List<Product>,
    onAddToCart: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = title,
                fontFamily = helloBunny,
                fontSize = 88.sp,
                lineHeight = 96.sp,
                color = Pinky,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(products, key = { it.name }) { product ->
            ProductItem(
                product = product,
                isInCart = product in cartItems,
                onAddToCart = { onAddToCart(product) }
            )
        }
    }
}

@Composable
private fun ProductItem(
    product: Product,
    isInCart: Boolean,
    onAddToCart: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.name,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Less_Purple
            )
            product.description?.let { description ->
                Text(
                    text = description,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp,
                    color = Color.DarkGray,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(Modifier.width(8.dp))
        Text(
            text = "\$${product.price}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Very_purple
        )
        Spacer(Modifier.width(8.dp))
        FilledIconButton(
            onClick = onAddToCart,
            enabled = !isInCart,
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Very_purple,
                contentColor = Color.White,
                disabledContainerColor = DisabledGrey,
                disabledContentColor = PurpleGrey40
            )
        ) {
            Icon(
                imageVector = Icons.Filled.AddShoppingCart,
                contentDescription = "Agregar al carrito"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductsPreview() {
    ProductsContent(
        title = "Salties",
        products = salties,
        cartItems = listOf(salties[2]),
        onAddToCart = {}
    )
}
