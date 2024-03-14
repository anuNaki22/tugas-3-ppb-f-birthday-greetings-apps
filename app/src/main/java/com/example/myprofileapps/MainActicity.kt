package com.example.myprofileapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.animation.*
import androidx.compose.animation.core.*
//import kotlin.math.ceil


data class ProfileData(
    val name: String,
    val jobTitle: String,
    val location: String,
    val email: String,
    val phone: String,
    val about: String,
    val skills: List<String>,
    val projects: List<Project>
)

data class Project(
    val title: String,
    val description: String,
    val imageUrl: Int
)

val dummyProfile = ProfileData(
    name = "Rahmat Faris Akbar",
    jobTitle = "Frontend Developer",
    location = "Surabaya, Indonesia",
    email = "email@mail.com",
    phone = "+1234567890",
    about = "Passionate Frontend developer with experience in building, integrating, testing, and supporting web applications. Skilled in modern JavaScript frameworks and libraries, responsive design, and building user-centric interfaces.",
    skills = listOf("CSS", "HTML", "JavaScript", "React", "Responsive Design", "NextJS", "C/C++", "Mathematics"),
    projects = listOf(
        Project(
            title = "Project Aplikasi ToDo List App",
            description = "Sebuah website yang memudahkan kita mencatat hal yang perlu dilakukan.",
            imageUrl = R.drawable.project_1
        ),
        Project(
            title = "Project Website Landing Adapter HIX",
            description = "Sebuah website untuk branding dan berlangganan Adapter App.",
            imageUrl = R.drawable.project_2
        ),
        Project(
            title = "Project Aplikasi Adapter Satu Sehat",
            description = "Sebuah aplikasi adapter untuk menjembatani proses ETL data RS ke Satu Sehat.",
            imageUrl = R.drawable.project_3
        ),
        Project(
            title = "Project Parking Lot Detector & Counter",
            description = "Sebuah aplikasi pendeteksi dan penghitung area parkir yang masih kosong.",
            imageUrl = R.drawable.project_4
        ),
    )
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ProfileScreen(dummyProfile)
                }
            }
        }
    }
}

@Composable
fun TopProfileSection(profileData: ProfileData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Box {
            // Membuat background gradient
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFE1BEE7), // Warna ungu di atas
                                Color.White // Transparan di bawah
                            ),
                            startY = 10f // Atur nilai ini untuk menyesuaikan di mana warna mulai berubah menjadi transparan
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFFE1BEE7), CircleShape)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = profileData.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.wrapContentSize()
                )
                Text(text = profileData.jobTitle, style = MaterialTheme.typography.subtitle1)
                Text(text = profileData.location, style = MaterialTheme.typography.caption)

                // Menambahkan informasi kontak
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email: ${profileData.email}", style = MaterialTheme.typography.body1)
                Text(text = "Phone: ${profileData.phone}", style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Composable
fun AboutSection(profileData: ProfileData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("About Me", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = profileData.about, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun SkillsSection(skills: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Skills", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))

            // Assuming each row can fit 2 items for simplicity. Adjust based on your UI needs.
            val numRows = (skills.size + 1) / 2 // Calculate the number of rows needed
            Column {
                for (i in 0 until numRows) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        val startIndex = i * 2
                        for (j in startIndex until startIndex + 2) {
                            if (j < skills.size) {
                                ChipView(skill = skills[j])
                                Spacer(modifier = Modifier.width(8.dp)) // Space between chips
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp)) // Space between rows
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(profileData: ProfileData) {
    var showDialog by remember { mutableStateOf(true) } // Dialog ditampilkan saat pertama kali

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            // Komponen Header Anda
            TopProfileSection(profileData)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item { AboutSection(profileData) }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { SkillsSection(profileData.skills) }
        item { ProjectsSection(profileData.projects) }
    }

    if (showDialog) {
        HelloWorldDialog { showDialog = false }
    }
}

@Composable
fun HelloWorldDialog(onDismissRequest: () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(initialAlpha = 0.4f),
        exit = fadeOut()
    ) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(
                    text = "Welcome",
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            text = {
                Text(
                    "Hello World! This is My Profile Apps",
                    style = MaterialTheme.typography.body1
                )
            },
            confirmButton = {
                Button(
                    onClick = onDismissRequest,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text(
                        "Read My Profile  ⟫",
                        color = Color.White
                    )
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black,
        )
    }
}

@Composable
fun ProjectsSection(projects: List<Project>) {
    Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
        Text(
            "Projects",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        // Process projects in pairs
        projects.chunked(2).forEachIndexed { index, rowProjects ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                rowProjects.forEachIndexed { rowIndex, project ->
                    // Adjust modifier to add end padding to all cards except the last one in the row
                    val isLastInRow = rowIndex == rowProjects.size - 1
                    val cardModifier = Modifier
                        .weight(1f)
                        .let { if (isLastInRow && rowProjects.size > 1) it.padding(end = 8.dp) else it }
                    ProjectCard(project = project, modifier = cardModifier)
                }
                // Fill the remaining space if there's an odd number of projects to keep the layout consistent
                if (rowProjects.size == 1 && index != projects.chunked(2).lastIndex) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            // Add vertical spacing between rows if not the last row
            if (index != projects.chunked(2).lastIndex) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ProjectCard(project: Project, modifier: Modifier = Modifier) {
    // Use the modifier parameter passed to ProjectCard
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = project.imageUrl),
                contentDescription = project.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = project.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = project.description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun ChipView(skill: String) {
    Surface(
        modifier = Modifier
            .padding(end = 8.dp), // Add padding to the end of each chip for spacing
        shape = RoundedCornerShape(50.dp),
        color = MaterialTheme.colors.primary.copy(alpha = 0.2f)
    ) {
        Text(
            text = skill,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body2
        )
    }
}
