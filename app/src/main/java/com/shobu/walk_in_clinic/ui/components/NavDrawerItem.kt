import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.ui.navigation.nav_drawer.NavDrawerItems
import com.shobu.walk_in_clinic.ui.navigation.nav_drawer.getNavList


@Preview
@Composable
fun NavDrawerItemPrev() {
    NavDrawerItem(item = getNavList()[0]) {

    }
}

@Composable
fun NavDrawerItem(
    item: NavDrawerItems,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }) {

        Spacer(modifier = Modifier.width(20.dp))

        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = item.icon),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = "item_image"
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = stringResource(id = item.title),
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        )

    }
}