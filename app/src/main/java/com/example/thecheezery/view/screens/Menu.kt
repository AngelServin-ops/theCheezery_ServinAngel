package com.example.thecheezery.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thecheezery.R
import com.example.thecheezery.model.domain.Category
import com.example.thecheezery.ui.theme.Brighter_Pink
import com.example.thecheezery.ui.theme.Less_Purple
import com.example.thecheezery.ui.theme.Pinky
import com.example.thecheezery.ui.theme.Very_purple

private data class MenuOption(val label: String, val onClick: () -> Unit)

@Composable
fun Menu(
    modifier: Modifier = Modifier,
    onCategoryClick: (Category) -> Unit = {},
    onCartClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = onCartClick,
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Pinky),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Carrito",
                    tint = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.grupo2),
                contentDescription = "Logo de The Cheezery",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MenuColumn(
                options = listOf(
                    MenuOption("Hot drinks") { onCategoryClick(Category.HOT_DRINKS) },
                    MenuOption("Salties") { onCategoryClick(Category.SALTIES) },
                    MenuOption("Combos") {}
                ),
                modifier = Modifier.weight(1f)
            )
            MenuColumn(
                options = listOf(
                    MenuOption("Cold drinks") { onCategoryClick(Category.COLD_DRINKS) },
                    MenuOption("Sweets") { onCategoryClick(Category.SWEETS) },
                    MenuOption("Add new product") {}
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun MenuColumn(options: List<MenuOption>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(
            Brush.verticalGradient(listOf(Brighter_Pink, Less_Purple, Very_purple))
        )
    ) {
        options.forEach { option ->
            TextButton(
                onClick = option.onClick,
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
            ) {
                Text(text = option.label, color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MenuPreview() {
    Menu()
}
