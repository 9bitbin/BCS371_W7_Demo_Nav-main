package edu.farmingdale.bcs371_w7_demo_nav

/**
 * Name: Himal Shrestha
 * Prof: ALrajab
 * Class: BCS371  Mobile Application Development
 * Week 07 Assignment
 */
import android.content.Intent
import android.transition.Fade
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

// Function that handles navigation between different screens
@Composable
fun Navigation() {
    // Create a navigation controller to handle screen transitions
    val navController = rememberNavController()

    // Define the routes (or destinations) in the navigation system
    NavHost(navController = navController, startDestination = "splash_screen") {
        // First screen displayed is the Splash Screen
        composable("splash_screen") {
            SpalshScreen(navController)
        }

        // Navigation route to the First Screen
        composable("first_screen") {
            FirstScreen(navController)
        }

        // Navigation route to the Second Screen
        composable("second_screen") {
            SecondScreen(navController)
        }
        // ToDo 7: Add more nav screens here for the pizza party and gpa calculator
        // New routes added for additional screens
        composable("pizza_party_screen") {
            PizzaPartyScreen(navController)
        }

        composable("gpa_calculator_screen") {
            GPACalculatorScreen(navController)
        }
    }
}

// Function for the First Screen UI
@Composable
fun FirstScreen(navController: NavController) {
    // Box layout centers the content in the middle of the screen
    Box(contentAlignment = Alignment.Center) {
        // A column layout for vertical arrangement of text and buttons
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display a simple text message
            Text(text = "First Screen")

            // Button to navigate to the Second Screen
            Button(onClick = { navController.navigate("second_screen") }) {
                Text(text = "Go to Second Screen")
            }

            // Button to navigate to Pizza Party Screen
            Button(onClick = { navController.navigate("pizza_party_screen") }) {
                Text(text = "Go to Pizza Party Screen")
            }

            // Button to navigate to GPA Calculator Screen
            Button(onClick = { navController.navigate("gpa_calculator_screen") }) {
                Text(text = "Go to GPA Calculator Screen")
            }
        }
    }
}

// Function for the Second Screen UI
@Composable
fun SecondScreen(navController: NavController) {
    // Remember the state of the slider value and checkbox
    var sliderValue by remember { mutableStateOf(0.5f) }
    var isSliderEnabled by remember { mutableStateOf(true) }

    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display text message
        Text(fontSize = 20.sp, text = "Second Screen")

        // Slider component allows the user to select a value (enabled or disabled based on checkbox)
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            modifier = Modifier.fillMaxWidth(),
            enabled = isSliderEnabled // Enable/Disable based on checkbox
        )

        // Button to launch another activity (MainActivity2)
        Button(onClick = { context.startActivity(Intent(context, MainActivity2::class.java)) }) {
            Text(fontSize = 20.sp, text = "Go to other Activity")
        }
        // ToDo 8: when the switch is off, disable the slider
        // Checkbox to enable/disable the slider
        Text(text = if (isSliderEnabled) "Slider Enabled" else "Slider Disabled")
        Checkbox(
            checked = isSliderEnabled,
            onCheckedChange = { isSliderEnabled = it }, // Toggles the checkbox and slider state
            modifier = Modifier.padding(10.dp)
        )
    }
}
// - I commented out this part because it kept overiding the PizzaPartyScreen and GPACalculatorScreen.
//// Function for the Pizza Party Screen UI
//@Composable
//fun PizzaPartyScreen(navController: NavController) {
//    Column(
//        modifier = Modifier.padding(20.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Simple text message for the Pizza Party Screen
//        Text(text = "Pizza Party Screen")
//
//        // Button to navigate back to the First Screen
//        Button(onClick = { navController.navigate("first_screen") }) {
//            Text(text = "Go to First Screen")
//        }
//    }
//}

// Function for the GPA Calculator Screen UI
@Composable
fun GPACalculatorScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Simple text message for the GPA Calculator Screen
        Text(text = "GPA Calculator Screen")

        // Button to navigate back to the First Screen
        Button(onClick = { navController.navigate("first_screen") }) {
            Text(text = "Go to First Screen")
        }
    }
}

// Function for the Splash Screen UI
@Composable
fun SpalshScreen(navController: NavController) {
    // Remember the animation state (scaling effect)
    val scale = remember { Animatable(0f, 1f) }

    // Launch the effect (animation + delay)
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f, // Final value for scaling
            animationSpec = tween(durationMillis = 1000, easing = {
                OvershootInterpolator(2f).getInterpolation(it) // Easing function for smooth bounce effect
            })
        )
        delay(3000) // Delay before moving to the first screen
        navController.navigate("first_screen") // Navigate to the first screen after delay
    }

    // Display the logo centered in the screen
    Box(contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.fsclogo), contentDescription = "")
    }
}
