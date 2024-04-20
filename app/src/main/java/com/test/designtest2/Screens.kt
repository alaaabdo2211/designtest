package com.test.designtest2

sealed class Screens(val route: String) {
     object RequestStatusScreen : Screens("request_status")

     object RequestDetailsScreen : Screens("request_details")
}