package com.test.designtest2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FinanceCalculatorScreen() {

    val navController = rememberNavController()

    var selectedItem by remember {
        mutableStateOf(Screens.RequestStatusScreen.route)
    }

    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.background_color))
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 55.dp))
                .wrapContentHeight()
                .background(Color.White)
                .padding(10.dp)
        ) {
            // Horizontal Layout 1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .wrapContentHeight()
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_button),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = "Get Support",
                    textAlign = TextAlign.End,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
            Text(
                text = "Finance Calculator",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray,
                modifier = Modifier.padding(5.dp)
            )

            Text(
                text = "Request #123456",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(5.dp)
            )

        }

        NavHost(
            navController,
            startDestination = Screens.RequestStatusScreen.route
        ) {

            composable(route = Screens.RequestStatusScreen.route) {
                RequestStatusScreen()
            }

            composable(route = Screens.RequestDetailsScreen.route) {
                RequestDetailsScreen()
            }
        }



    }
    Box {
        BottomNavigationButtons(
            modifier = Modifier.align(Alignment.BottomCenter), selectedItem = selectedItem
        ) {
            navController.navigate(it) {
                popUpTo(0)
                selectedItem = it
            }
        }
    }
}

@Composable
fun BottomNavigationButtons(
    modifier: Modifier,
    selectedItem: String,
    onButtonClick: (String) -> Unit,
) {
    Card(
        modifier
            .fillMaxWidth()
            .height(72.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedItem == Screens.RequestStatusScreen.route) MaterialTheme.colorScheme.primary
                    else Color.White
                ), onClick = { onButtonClick(Screens.RequestStatusScreen.route) }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Card(
                        modifier = Modifier.size(16.dp),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(Color.Blue)
                    ) {
                        Icon(
                            tint = if (selectedItem == Screens.RequestDetailsScreen.route) Color.White else MaterialTheme.colorScheme.primary,
                            painter = painterResource(id = R.drawable.request_status),
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "Request Status",
                        color = if (selectedItem == Screens.RequestStatusScreen.route) Color.White
                        else Color.Black
                    )
                }
            }

            Button(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedItem == Screens.RequestDetailsScreen.route)
                        MaterialTheme.colorScheme.primary
                    else Color.White
                ),
                onClick = { onButtonClick(Screens.RequestDetailsScreen.route) },
                content = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Card(
                            modifier = Modifier.size(16.dp),
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(Color.Blue)
                        ) {
                            Icon(
                                tint = if (selectedItem == Screens.RequestDetailsScreen.route) Color.White else MaterialTheme.colorScheme.primary,
                                painter = painterResource(id = R.drawable.request_details),
                                contentDescription = null
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "Request Details",
                            color = if (selectedItem == Screens.RequestDetailsScreen.route) Color.White
                            else Color.Black,
                            fontSize = 14.sp
                        )
                    }
                })
        }
    }
}

@Composable
fun RequestStatusScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color))
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(colorResource(id = R.color.background_color))
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.done),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )

            Column (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 10.dp)

            ){
                Text(
                    text = "Application Submitted",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Text(
                    text = "12 Dec 2023 - 10:10 AM",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(10.dp)
                )

            }
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(colorResource(id = R.color.background_color))
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                modifier = Modifier.size(15.dp)
            )

            Column (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 10.dp)
            ){
                Text(
                    text = "Application Review",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Text(
                    text = "12 Dec 2023 - 10:10 AM",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "A representative from JB will contact your registered mobile number within 72 hours.",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(10.dp)

                )

            }
        }
    }
}

@Composable
fun RequestDetailsScreen() {
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color))
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .background(colorResource(id = R.color.background_color))
                .padding(20.dp)
        ) {
            Text(
                text = "Financial Overview",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "Employment sector",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                    )

                    Text(
                        text = "Government & Semi-Government",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Row(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(10.dp)
                                .wrapContentHeight()
                        ) {
                            Text(
                                text = "Monthly Income",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                            )

                            Text(
                                text = "20,000 SAR",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(10.dp)
                                .wrapContentHeight()
                        ) {
                            Text(
                                text = "Monthly Obligations",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                            )

                            Text(
                                text = "1,500 SAR",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }
                }


            }
        }
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .background(colorResource(id = R.color.background_color))
                .padding(20.dp)
        ) {
            Text(
                text = "Lease Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .wrapContentHeight()
                ) {
                    Row(
                        modifier = Modifier
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(10.dp)
                                .wrapContentHeight()
                        ) {
                            Text(
                                text = "Car Price ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                            )

                            Text(
                                text = "160,000 SAR",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(10.dp)
                                .wrapContentHeight()
                        ) {
                            Text(
                                text = "Down Payment",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                            )

                            Text(
                                text = "20,500 SAR",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(10.dp)
                                .wrapContentHeight()
                        ) {
                            Text(
                                text = "Final Payment",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                            )

                            Text(
                                text = "20,000 SAR",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(10.dp)
                                .wrapContentHeight()
                        ) {
                            Text(
                                text = "Finance Period",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                            )

                            Text(
                                text = "60 Months",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }
                }


            }
        }
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .background(colorResource(id = R.color.background_color))
                .padding(20.dp)
        ) {
            Text(
                text = "Lease Breakdown",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)
            )


            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(10.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .wrapContentHeight()
                ) {

                    Text(
                        text = "Finance Amount",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                    )

                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .padding(5.dp)
                    ) {

                        Text(
                            text = "700,000",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.main_color),
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = "SAR",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )

                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                colorResource(id = R.color.box_background),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(10.dp)

                    ) {

                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                        ) {

                            Text(
                                text = "Down Payment",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                modifier = Modifier.padding(10.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .wrapContentHeight()
                                    .padding(start = 60.dp, top = 10.dp)
                            ) {

                                Text(
                                    text = "70,000",
                                    textAlign = TextAlign.End,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.main_color),
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                                Text(
                                    text = "SAR",
                                    textAlign = TextAlign.End,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray,
                                    modifier = Modifier.padding(start = 5.dp, top = 5.dp)
                                )

                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp)) // Add space between boxes

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                colorResource(id = R.color.box_background),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(10.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                        ) {

                            Text(
                                text = "Final Payment",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                modifier = Modifier.padding(10.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .wrapContentHeight()
                                    .padding(start = 60.dp, top = 10.dp)
                            ) {

                                Text(
                                    text = "105,140",
                                    textAlign = TextAlign.End,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.main_color),
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                                Text(
                                    text = "SAR",
                                    textAlign = TextAlign.End,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray,
                                    modifier = Modifier.padding(start = 5.dp, top = 5.dp)
                                )

                            }

                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally),
                    )
                    {
                        Text(
                            text = "Insurance",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(10.dp)


                        )
                        Spacer(modifier = Modifier
                            .width(55.dp)
                            .weight(1f))
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(start = 5.dp, top = 10.dp)
                        ) {

                            Text(
                                text = "30,000",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(5.dp),

                                )
                            Text(
                                text = "SAR",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(5.dp),

                                )
                        }
                    }
                    Divider(color = Color.LightGray, thickness = 2.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally),
                    )
                    {
                        Text(
                            text = "Profit Amount",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(10.dp)


                        )
                        Spacer(modifier = Modifier
                            .width(60.dp)
                            .weight(1f))
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(start = 5.dp, top = 10.dp)
                        ) {

                            Text(
                                text = "94,068.78",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(5.dp),

                                )
                            Text(
                                text = "SAR",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(5.dp),

                                )
                        }
                    }
                    Divider(color = Color.LightGray, thickness = 2.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally),
                    )
                    {
                        Column {
                            Text(text = "Total Leasing Amount",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(10.dp))
                            Text(text = "including the Insurance",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(10.dp))
                        }
                        Spacer(modifier = Modifier
                            .width(20.dp)
                            .weight(1f))
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(start = 5.dp, top = 10.dp)
                        ) {

                            Text(
                                text = "758,681",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(5.dp),

                                )
                            Text(
                                text = "SAR",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(5.dp),

                                )
                        }

                    }

                    Divider(color = Color.LightGray, thickness = 2.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally),
                    )
                    {
                        Text(
                            text = "Processing Fees",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(10.dp)


                        )
                        Spacer(modifier = Modifier
                            .width(60.dp)
                            .weight(1f))
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(start = 5.dp, top = 10.dp)
                        ) {

                            Text(
                                text = "5,750",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(5.dp),

                                )
                            Text(
                                text = "SAR",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(5.dp),

                                )
                        }

                    }

                    Divider(color = Color.LightGray, thickness = 2.dp)


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    {
                        Text(
                            text = "APR Rate",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(10.dp)


                        )
                        Spacer(modifier = Modifier
                            .width(90.dp)
                            .weight(1f))
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(start = 5.dp, top = 10.dp)
                        ) {

                            Text(
                                text = "12.4",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(5.dp),

                                )
                            Text(
                                text = "%",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(5.dp),

                                )
                        }
                    }


                }

            }
            Spacer(modifier = Modifier.height(80.dp))

        }

    }
}