package edu.farmingdale.bcs371_w7_demo_nav

/**
 * Name: Himal Shrestha
 * Prof: ALrajab
 * Class: BCS371 Mobile Application Development
 * Week 07 Assignment
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.ceil

// ToDo 9: make this composable navigable and then add a button to navigate to the GPA calculator
// Done: Added button to navigate to GPA Calculator Screen
@Composable
fun PizzaPartyScreen(navController: NavController, modifier: Modifier = Modifier) {
    // Declare state variables for total pizzas, number of people input, and hunger level
    var totalPizzas by remember { mutableIntStateOf(0) } // Total pizzas to be calculated
    var numPeopleInput by remember { mutableStateOf("") } // Input for number of people
    var hungerLevel by remember { mutableStateOf("Medium") } // Selected hunger level

    // Define a vertical column layout for the Pizza Party screen
    Column(
        modifier = modifier
            .fillMaxSize() // Fill the maximum size of the parent container
            .background(Color.Red) // Set background color to red
            .padding(10.dp), // Add padding around the column
        horizontalAlignment = Alignment.CenterHorizontally // Center align contents horizontally
    ) {
        // Add Pizza Image at the Top
        Image(
            painter = painterResource(id = R.drawable.pizza_image), // Load pizza image resource
            contentDescription = "Pizza", // Accessibility description
            modifier = Modifier
                .fillMaxWidth() // Make the image fill the width
                .height(200.dp) // Set the height of the image
        )

        // Title Text for the Pizza Party
        Text(
            text = "Pizza Party", // Text to display
            fontSize = 38.sp, // Set font size
            color = Color.Yellow, // Set text color to yellow
            modifier = modifier.padding(bottom = 16.dp) // Add bottom padding
        )

        // Input field for the number of people
        NumberField(
            labelText = "Number of people?", // Label for the input field
            textInput = numPeopleInput, // Current input text
            onValueChange = { numPeopleInput = it }, // Update input state on change
            modifier = modifier
                .padding(bottom = 16.dp) // Add bottom padding
                .fillMaxWidth() // Fill the width of the parent
        )

        // Radio buttons for hunger level selection
        RadioGroup(
            labelText = "How hungry?", // Label for the radio group
            radioOptions = listOf("Light", "Medium", "Very hungry"), // Options for hunger level
            selectedOption = hungerLevel, // Currently selected option
            onSelected = { hungerLevel = it }, // Update selected option on change
            modifier = modifier // Pass the modifier
        )

        // Display total number of pizzas calculated
        Text(
            text = "Total pizzas: $totalPizzas", // Text to display total pizzas
            fontSize = 22.sp, // Set font size
            color = Color.Yellow, // Set text color to yellow
            modifier = modifier.padding(top = 16.dp, bottom = 16.dp) // Add top and bottom padding
        )

        // Button to calculate total pizzas
        Button(
            onClick = {
                totalPizzas = calculateNumPizzas(
                    numPeopleInput.toInt(), hungerLevel
                ) // Calculate pizzas on click
            }, modifier = modifier
                .fillMaxWidth() // Fill the width of the parent
                .background(Color.Yellow) // Set button background color to yellow
        ) {
            Text("Calculate", color = Color.Yellow) // Set button text and color
        }

        // ToDo 9: Added button to navigate to GPA Calculator Screen
        // Button to navigate to GPA calculator
        Button(
            onClick = {
                navController.navigate("gpa_calculator_screen") // Navigate to GPA calculator on click
            }, modifier = modifier
                .padding(top = 16.dp) // Add top padding
                .fillMaxWidth() // Fill the width of the parent
                .background(Color.Yellow) // Set button background color to yellow
        ) {
            Text("Go to GPA Calculator", color = Color.Yellow) // Set button text and color
        }
        Button(
            onClick = {
                navController.navigate("first_screen") // Navigate to GPA calculator on click
            }, modifier = modifier
                .padding(top = 16.dp) // Add top padding
                    .fillMaxWidth() // Fill the width of the parent
                    .background(Color.Yellow) // Set button background color to yellow
        ) {
            Text("Go to First Screen", color = Color.Yellow) //set button text and color
        }
    }
}

// Remaining functions remain unchanged

@Composable
fun NumberField(
    labelText: String, // Label for the text field
    textInput: String, // Current input text
    onValueChange: (String) -> Unit, // Callback for value changes
    modifier: Modifier = Modifier // Modifier for customization
) {
    TextField(
        value = textInput, // Set the current value
        onValueChange = onValueChange, // Callback to handle changes
        label = { Text(labelText) }, // Label to display
        singleLine = true, // Allow only single line input
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number // Set keyboard type to number
        ), modifier = modifier // Pass the modifier
    )
}

@Composable
fun RadioGroup(
    labelText: String, // Label for the radio group
    radioOptions: List<String>, // List of options for selection
    selectedOption: String, // Currently selected option
    onSelected: (String) -> Unit, // Callback for selected option
    modifier: Modifier = Modifier, // Modifier for customization
) {
    // Function to check if an option is selected
    val isSelectedOption: (String) -> Boolean = { selectedOption == it }

    Column {
        // Display the label for the radio group
        Text(labelText, color = Color.Yellow) // Set text color to yellow
        // Iterate through radio options to create buttons
        radioOptions.forEach { option ->
            Row(
                modifier = modifier
                    .selectable(
                        selected = isSelectedOption(option), // Check if this option is selected
                        onClick = { onSelected(option) }, // Update selected option on click
                        role = Role.RadioButton // Set role for accessibility
                    )
                    .padding(8.dp) // Add padding around the row
            ) {
                // Display the radio button
                RadioButton(
                    selected = isSelectedOption(option), // Check if this radio button is selected
                    onClick = null, // No action needed as onClick is handled by Row
                    modifier = modifier.padding(end = 8.dp) // Add right padding
                )
                // Display the text for the radio button
                Text(
                    text = option, // Text to display for the option
                    modifier = modifier.fillMaxWidth(), // Fill the width of the parent
                    color = Color.Yellow // Set text color to yellow
                )
            }
        }
    }
}

// Function to calculate the total number of pizzas needed
fun calculateNumPizzas(
    numPeople: Int, // Number of people at the pizza party
    hungerLevel: String // Level of hunger among the attendees
): Int {
    val slicesPerPizza = 8 // Number of slices per pizza
    // Determine slices per person based on hunger level
    val slicesPerPerson = when (hungerLevel) {
        "Light" -> 2 // Light hunger consumes 2 slices
        "Medium" -> 3 // Medium hunger consumes 3 slices
        else -> 4 // Very hungry consumes 4 slices
    }

    // Calculate and return the total number of pizzas needed
    return ceil(numPeople * slicesPerPerson / slicesPerPizza.toDouble()).toInt() // Round up to the nearest whole number
}
