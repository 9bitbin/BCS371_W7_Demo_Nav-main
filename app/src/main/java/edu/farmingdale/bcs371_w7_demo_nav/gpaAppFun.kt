package edu.farmingdale.bcs371_w7_demo_nav

/**
 * Name: Himal Shrestha
 * Prof: ALrajab
 * Class: BCS371 Mobile Application Development
 * Week 07 Assignment
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

// ToDo 10: make this composable navigable and then add a button to navigate to a suitable screen

@Composable
fun gpaappFun(navController: NavController) {

    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }


    // Declare variables for GPA result and background color
    var gpa by remember { mutableStateOf("") }
    var backColor by remember { mutableStateOf(Color.White) }
    var btnLabel by remember { mutableStateOf("Calulate GPA") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backColor),
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = grade1,
            onValueChange = { grade1 = it },
            label = { Text("Course 1 Grade") },
        )
        TextField(
            value = grade2,
            onValueChange = { grade2 = it },
            label = { Text("Course 2 Grade") },
        )
        TextField(
            value = grade3,
            onValueChange = { grade3 = it },
            label = { Text("Course 3 Grade") },
        )


        Button(onClick = {
            if (btnLabel == "Compute GPA") {

                val gpaVal = calGPA(grade1, grade2, grade3)
                if (gpaVal != null) {
                    gpa = gpaVal.toString()

                    // Change background color based on GPA
                    backColor = when {
                        gpaVal < 60 -> Color.Red
                        gpaVal in 60.0..79.0 -> Color.Yellow
                        else -> Color.Green
                    }
                    btnLabel = "Clear"
                } else {
                    gpa = "Invalid input"
                }
            } else {
                // Reset all value to none
                grade1 = ""
                grade2 = ""
                grade3 = ""
                gpa = ""
                backColor = Color.White
                btnLabel = "Compute GPA"
            }
        }) {
            Text(btnLabel)
        }


        if (gpa.isNotEmpty()) {
            Text(text = "GPA: $gpa")
        }


    }
}


fun calGPA(grade1: String, grade2: String, grade3: String): Double {
    val grades = listOf(grade1.toDouble(), grade2.toDouble(), grade3.toDouble())
    return grades.average()
}

