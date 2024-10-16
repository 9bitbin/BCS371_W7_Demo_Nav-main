package edu.farmingdale.bcs371_w7_demo_nav

/**
 * Name: Himal Shrestha
 * Prof: ALrajab
 * Class: BCS371 Mobile Application Development
 * Week 07 Assignment
 */

import androidx.compose.foundation.Image // Import for image support
import androidx.compose.foundation.background // Import for background styling
import androidx.compose.foundation.layout.* // Import for layout components
import androidx.compose.material3.* // Import for Material Design components
import androidx.compose.runtime.* // Import for state management and composables
import androidx.compose.ui.Modifier // Import for modifying components
import androidx.compose.ui.graphics.Color // Import for color support
import androidx.compose.ui.res.painterResource // Import for resource loading
import androidx.compose.ui.unit.dp // Import for dimension units
import androidx.navigation.NavController // Import for navigation controller

// ToDo 10: Make this composable navigable and then add a button to navigate to a suitable screen
// Composable function for the GPA calculator screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun gpaappFun(navController: NavController) {

    // State variables to hold the grades for three courses
    var grade1 by remember { mutableStateOf("") } // Grade for Course 1
    var grade2 by remember { mutableStateOf("") } // Grade for Course 2
    var grade3 by remember { mutableStateOf("") } // Grade for Course 3

    // Declare variables for GPA result and background color
    var gpa by remember { mutableStateOf("") } // Variable to hold computed GPA
    var backColor by remember { mutableStateOf(Color(0xFFE0B0FF)) } // Light purple for retro theme background color
    var btnLabel by remember { mutableStateOf("Compute GPA") } // Button label to toggle between compute and clear

    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the maximum available size
            .background(color = backColor) // Set the background color
            .padding(16.dp), // Add padding for better spacing
        verticalArrangement = Arrangement.Center // Center elements vertically in the column
    ) {
        // Placeholder for the calculator image
        Image(
            painter = painterResource(id = R.drawable.calculator_image), // Use your calculator image resource here
            contentDescription = "Calculator", // Accessibility description for the image
            modifier = Modifier
                .fillMaxWidth() // Fill the width of the parent container
                .padding(bottom = 16.dp) // Add some space below the image
        )

        // Text field for Course 1 grade input
        TextField(
            value = grade1, // Current value of the first grade
            onValueChange = { grade1 = it }, // Update state on value change
            label = { Text("Course 1 Grade") }, // Label for the input field
            colors = TextFieldDefaults.textFieldColors( // Customize colors for the text field
                focusedIndicatorColor = Color(0xFF6200EE), // Purple color for focused state
                unfocusedIndicatorColor = Color(0xFFBDBDBD) // Grey color for unfocused state
            )
        )
        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between text fields

        // Text field for Course 2 grade input
        TextField(
            value = grade2, // Current value of the second grade
            onValueChange = { grade2 = it }, // Update state on value change
            label = { Text("Course 2 Grade") }, // Label for the input field
            colors = TextFieldDefaults.textFieldColors( // Customize colors for the text field
                focusedIndicatorColor = Color(0xFF6200EE),
                unfocusedIndicatorColor = Color(0xFFBDBDBD)
            )
        )
        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between text fields

        // Text field for Course 3 grade input
        TextField(
            value = grade3, // Current value of the third grade
            onValueChange = { grade3 = it }, // Update state on value change
            label = { Text("Course 3 Grade") }, // Label for the input field
            colors = TextFieldDefaults.textFieldColors( // Customize colors for the text field
                focusedIndicatorColor = Color(0xFF6200EE),
                unfocusedIndicatorColor = Color(0xFFBDBDBD)
            )
        )
        Spacer(modifier = Modifier.height(16.dp)) // Add spacing before button

        // Button to compute GPA or clear inputs
        Button(onClick = {
            // Check if the button label is "Compute GPA"
            if (btnLabel == "Compute GPA") {
                // Calculate GPA based on the input grades
                val gpaVal = calGPA(grade1, grade2, grade3)
                // Check if GPA calculation was successful
                if (gpaVal != null) {
                    gpa = gpaVal.toString() // Update GPA state with computed value

                    // Change background color based on GPA value
                    backColor = when {
                        gpaVal < 60 -> Color.Red // Red for failing GPA
                        gpaVal in 60.0..79.0 -> Color.Yellow // Yellow for average GPA
                        else -> Color.Green // Green for excellent GPA
                    }
                    btnLabel = "Clear" // Change button label to "Clear"
                } else {
                    gpa = "Invalid input" // Set error message for invalid input
                }
            } else {
                // Reset all values to none when "Clear" is clicked
                grade1 = "" // Clear first grade input
                grade2 = "" // Clear second grade input
                grade3 = "" // Clear third grade input
                gpa = "" // Clear GPA result
                backColor = Color(0xFFE0B0FF) // Reset to original retro color
                btnLabel = "Compute GPA" // Change button label back to "Compute GPA"
            }
        }) {
            Text(btnLabel) // Display current button label
        }

        Spacer(modifier = Modifier.height(16.dp)) // Add spacing before navigation button

        // ToDo 10: make this composable navigable and then add a button to navigate to the first_screen
        // Button to navigate to the first screen
        Button(onClick = {
            navController.navigate("first_screen") // Navigate to the first screen
        }) {
            Text("Go to First Screen") // Button label for navigation
        }

        // Display the computed GPA if it's not empty
        if (gpa.isNotEmpty()) {
            Text(text = "GPA: $gpa") // Show the computed GPA result
        }
    }
}

// Function to calculate GPA based on the three grades provided
fun calGPA(grade1: String, grade2: String, grade3: String): Double {
    // Convert grades from strings to doubles and store in a list
    val grades = listOf(grade1.toDouble(), grade2.toDouble(), grade3.toDouble())
    // Return the average of the grades
    return grades.average()
}
