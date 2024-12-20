package com.localytics.demo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.localytics.androidx.Localytics
import com.localytics.demo.R

@Composable
fun AboutScreen(modifier: Modifier) {

    val uriHandler = LocalUriHandler.current
    val versionNumber = Localytics.getLibraryVersion()


    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, end = 16.dp, start = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.about_screen),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Purpose:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Text(
            text = "The Localytics SDK helps you target and engage users of your iOS and Android apps. Use the Localytics Dashboard to configure and send personalized Push, In-App, and Inbox messages. You can also investigate and learn about your audience through their Events, Profiles, and other usage analytics.",
            modifier = Modifier.padding(top = 8.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Features:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Text(
            text = "* Push Notifications",
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
        )
        Text(
            text = "* In-App Messages",
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
        )
        Text(
            text = "* Inbox Messages",
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
        )
        Text(
            text = "* Events and Analytics",
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
        )
        Text(
            text = "* Places",
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Push Notifications:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Text(
            text = "Pushes allow you to send personalized notifications and track their delivery via the Localytics dashboard. Using Liquid templating and A/B creatives, you can find the right messaging for each segment of your audience.",
            modifier = Modifier.padding(top = 8.dp),
        )


        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "In-App Messages:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Text(
            text = "In-Apps can be configured through the Localytics dashboard to display full-screen or banner pop-ups within the app. These pop-up messages can be further customized using our In-App Builder or your own JavaScript code.",
            modifier = Modifier.padding(top = 8.dp),
        )


        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Inbox Messages:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Text(
            text = "The Inbox feature allows you to communicate directly with your users, to deliver targeted messages to them without having to reach out over email. This can be used for promo codes or other info relevant to your app.",
            modifier = Modifier.padding(top = 8.dp),
        )



        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Events and Analytics:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Text(
            text = "Tag Event and Profile data throughout your app, and record these values for each individual user. Identify and tie this info together using their Customer ID. These analytics can provide valuable insights about how to interact your audience in the future.",
            modifier = Modifier.padding(top = 8.dp),
        )



        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Places:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Text(
            text = "With Places, you can send notifications to users the instant they enter or exit a specific location. Additionally, you can analyze data about visits to physical locations, giving you access to insights that have never before been available.",
            modifier = Modifier.padding(top = 8.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Version:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Text(
            text = versionNumber, // Consider replacing with actual version dynamically
            style = androidx.compose.material.MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(24.dp)) // Add more spacing before the link
        Text(
            text = "Privacy Policy:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = androidx.compose.material.MaterialTheme.colors.primary)) {
                append("https://uplandsoftware.com/localytics/app-privacy/")
            }
        }
        ClickableText(
            modifier = Modifier.padding(top = 8.dp),
            text = annotatedString, onClick = {
                uriHandler.openUri("https://uplandsoftware.com/localytics/app-privacy/")
            }, style = androidx.compose.material.MaterialTheme.typography.body2.copy(
                fontSize = 16.sp, // Increase font size for better readability
                fontWeight = FontWeight.Medium // Use a medium weight for the link
            )
        )



        Spacer(modifier = Modifier.height(24.dp)) // Add more spacing before the link
        Text(
            text = "Support:", fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        val annotatedStringHelp = buildAnnotatedString {
            withStyle(style = SpanStyle(color = androidx.compose.material.MaterialTheme.colors.primary)) {
                append("https://help.uplandsoftware.com/localytics/help/Home.htm")
            }
        }
        ClickableText(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            text = annotatedStringHelp, onClick = {
                uriHandler.openUri("https://help.uplandsoftware.com/localytics/help/Home.htm")
            }, style = androidx.compose.material.MaterialTheme.typography.body2.copy(
                fontSize = 16.sp, // Increase font size for better readability
                fontWeight = FontWeight.Medium // Use a medium weight for the link
            )
        )

    }
}