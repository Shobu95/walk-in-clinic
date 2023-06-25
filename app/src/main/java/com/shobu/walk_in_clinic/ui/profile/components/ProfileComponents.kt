package com.shobu.walk_in_clinic.ui.profile.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.walk_in_appointment.ui.theme.DividerBg
import com.example.walk_in_appointment.ui.theme.TextLight
import com.shobu.walk_in_clinic.R

@Preview
@Composable
fun PersonalDetailsPrev() {
    PersonalDetails("Shoaib Ahmed")
}

@Composable
fun PersonalDetails(
    fullName: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = Color.White)
    ) {

        Text(
            text = "Profile",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.roboto),
                ),
                fontWeight = FontWeight.ExtraBold
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        val profilePic = painterResource(id = R.drawable.profile_pic)
        Image(
            painter = profilePic,
            contentDescription = "profile pic",
            Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = fullName,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    Font(R.font.roboto),
                ),
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}


@Preview
@Composable
fun ProfileDetailsItemPrev() {
    ProfileDetailsItem(
        R.drawable.profile_pic,
        "Description",
        "Heading"
    )
}


@Composable
fun ProfileDetailsItem(
    @DrawableRes detailIcon: Int,
    detailName: String,
    detailLabel: String
) {
    Column(
        modifier =
        Modifier
            .padding(start = 30.dp, end = 30.dp, bottom = 20.dp)
            .background(color = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            val detailPic = painterResource(id = detailIcon)
            Icon(
                painter = detailPic,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .size(45.dp)
                    .padding(10.dp)
            )

            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = detailName,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    )
                )

                Text(
                    text = detailLabel,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = TextLight,
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }

        Divider(color = DividerBg, thickness = 1.dp, modifier = Modifier.padding(top = 10.dp))
    }
}