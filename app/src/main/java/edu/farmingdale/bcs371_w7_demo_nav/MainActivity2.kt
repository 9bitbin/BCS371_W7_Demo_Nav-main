package edu.farmingdale.bcs371_w7_demo_nav

/**
 * Name: Himal Shrestha
 * Prof: ALrajab
 * Class: BCS371 Mobile Application Development
 * Week 07 Assignment
 */

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.farmingdale.bcs371_w7_demo_nav.ui.theme.BCS371_W7_Demo_NavTheme

// Main activity class for the application
class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge display for the app
        setContent {
            BCS371_W7_Demo_NavTheme { // Apply the app's theme
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Call the BasicOperations composable function with inner padding
                    BasicOperations(
                        name = "Activity 1", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Composable function for basic operations
@Composable
fun BasicOperations(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current // Get the current context
    var switchState by remember { mutableStateOf(true) } // State for the switch (default is true)

    Column {
        Spacer(modifier = Modifier.padding(50.dp)) // Add space at the top

        // Button to show location of Farmingdale State College
        Button(
            onClick = {
                val newInt = Intent(Intent.ACTION_VIEW) // Create an intent to view a location
                newInt.setData(Uri.parse("geo:0,0?q=Farmingdale State College, NY")) // Set the URI for the location
                context.startActivity(newInt) // Start the intent
            }, modifier = Modifier.padding(start = 40.dp, end = 40.dp), enabled = switchState
        ) { // Enable button based on switch state
            Icon(
                imageVector = Icons.Default.LocationOn, contentDescription = "Location"
            ) // Icon for location
            Text("Show me Farmingdale") // Button text
        }

        HorizontalDivider(thickness = DividerDefaults.Thickness) // Divider between buttons

        // Button to initiate a phone call
        Button(
            onClick = {
                // ToDo 1: create implicit intent to open a web page or call a phone number
                // Create an intent to make a phone call
                val newInt = Intent(Intent.ACTION_DIAL) // Change to ACTION_VIEW if using a URL
                newInt.setData(Uri.parse("tel:1234567890")) // Set the URI for the phone number
                context.startActivity(newInt) // Start the intent
            }, modifier = Modifier.padding(start = 40.dp, end = 40.dp), enabled = switchState
        ) { // Enable button based on switch state
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Phone",
                modifier = Modifier.padding(end = 10.dp)
            ) // Icon for phone, with 10dp padding
            Text("Call Me") // Button text
        }

        HorizontalDivider(thickness = DividerDefaults.Thickness) // Divider between buttons

        // Button to navigate to another activity
        Button(
            onClick = {
                // ToDo 2: create explicit intent to open a new activity
                // Create an intent to start another activity
                val newInt = Intent(
                    context, MainActivity2::class.java
                ) // Create an intent to start another activity
                context.startActivity(newInt) // Start the intent
            }, modifier = Modifier.padding(start = 40.dp, end = 40.dp), enabled = switchState
        ) { // Enable button based on switch state
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                modifier = Modifier.padding(end = 10.dp)
            ) // Icon for info, with 10dp padding
            Text("Go To Activity 2") // Button text
        }

        // ToDo 4: Add a horizontal divider between the buttons
        // Horizontal divider to separate buttons
        HorizontalDivider(thickness = DividerDefaults.Thickness) // Divider between buttons
        // ToDo 6: when the switch is off, disable the buttons
        // Switch to enable or disable buttons
        Switch(
            checked = switchState, // The current state of the switch
            onCheckedChange = { switchState = it }, // Update the switch state when changed
            modifier = Modifier.padding(10.dp), // Padding for the switch
        )


    }
}

// Preview function to visualize the BasicOperations composable
@Preview(showBackground = true)
@Composable
fun BasicOperationsPreview() {
    BCS371_W7_Demo_NavTheme {
        BasicOperations("Android") // Preview with "Android" as the name
    }
}

