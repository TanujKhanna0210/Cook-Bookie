package com.example.cookbookie.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbookie.R
import com.example.cookbookie.domain.model.Recipe


@Composable
fun RecipeCard(
    recipe: Recipe,
    navigateToDetailsScreen: (recipeId: Int) -> Unit,
    onDelete: () -> Unit
) {
    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
                .clickable {
                    navigateToDetailsScreen(recipe.id)
                },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {
                // Add Image on the left side of the card
                val bitmap = recipe.image?.let {
                    android.graphics.BitmapFactory.decodeByteArray(
                        recipe.image,
                        0,
                        it.size
                    )
                }

                if (bitmap != null) {
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(top = 6.dp, bottom = 6.dp, end = 6.dp)
                            .clip(MaterialTheme.shapes.medium),
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(top = 6.dp, bottom = 6.dp, end = 6.dp)
                            .clip(MaterialTheme.shapes.medium),
                        painter = painterResource(id = R.drawable.placeholder_image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = recipe.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = recipe.category,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = recipe.instructions,
                        style = MaterialTheme.typography.bodyLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .alpha(.5f)
                    )
                }
            }
        }
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .padding(end = 28.dp, bottom = 28.dp)
                .size(20.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    onDelete()
                }
        )
    }
}