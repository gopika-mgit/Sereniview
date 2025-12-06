package com.example.sereniview.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sereniview.data.local.GroundEntry
import com.example.sereniview.data.local.SereniviewDatabase
import kotlinx.coroutines.launch

enum class GroundingStyle {
    REALITY_CHECK,
    SELF_COMPASSION,
    CONTROL,
    EVIDENCE,
    NORMALIZE,
    FUTURE
}

@Composable
fun GroundMeScreen(
    onBack: () -> Unit,
    onViewHistory: () -> Unit
) {
    var worryText by remember { mutableStateOf("") }
    var selectedStyle by remember { mutableStateOf(GroundingStyle.REALITY_CHECK) }
    var groundedText by remember { mutableStateOf<String?>(null) }

    // 🔹 Get DB + DAO once per composition
    val context = LocalContext.current
    val db = remember { SereniviewDatabase.getDatabase(context) }
    val dao = remember { db.groundDao() }
    val scope = rememberCoroutineScope()

    // 🔹 Soft animated glow
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    val glowScale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2200,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowScale"
    )

    fun generateGrounding(worry: String, style: GroundingStyle): String {
        val trimmed = worry.trim()
        if (trimmed.isEmpty()) {
            return "Start by writing even a small version of your worry. You do not have to make it perfect; just get it out of your head and onto the screen."
        }

        return when (style) {
            GroundingStyle.REALITY_CHECK -> {
                "You wrote: \"$trimmed\".\n\nReality check: one interview cannot define your whole worth or future. It is one moment, not your entire story. You have handled hard things before and you can handle this too."
            }
            GroundingStyle.SELF_COMPASSION -> {
                "You wrote: \"$trimmed\".\n\nIf a close friend said this to you, you would not be harsh with them. You are allowed to be kind to yourself here. Feeling this way does not mean you are weak; it means you care."
            }
            GroundingStyle.CONTROL -> {
                "You wrote: \"$trimmed\".\n\nSeparate what you can control from what you cannot. You can control your preparation, your breathing, and how honestly you answer. You cannot fully control their mood or decision, and that is okay."
            }
            GroundingStyle.EVIDENCE -> {
                "You wrote: \"$trimmed\".\n\nLook at the evidence: you have made it this far, you are still showing up, and you have learned from every step. The fact that you are preparing is proof that you are trying, not failing."
            }
            GroundingStyle.NORMALIZE -> {
                "You wrote: \"$trimmed\".\n\nMany people feel exactly like this before interviews but rarely say it out loud. Your reaction is human, not broken. Nerves mean the opportunity matters to you, not that you are not ready."
            }
            GroundingStyle.FUTURE -> {
                "You wrote: \"$trimmed\".\n\nThink about yourself a year from now looking back at this moment. This will be one step in a much bigger path. Whether this interview goes well or not, you will still have chances to grow and move forward."
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // 🔹 Top section: explanation + input
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Ground Me",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Write your worry, choose a grounding style, and let the app gently reframe your thought.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )

                OutlinedTextField(
                    value = worryText,
                    onValueChange = { worryText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 120.dp),
                    label = { Text("What is your worry right now?") },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp
                    ),
                    singleLine = false,
                    maxLines = 5
                )

                Text(
                    text = "Choose grounding style",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 4.dp)
                )

                GroundingStyleChips(
                    selectedStyle = selectedStyle,
                    onStyleSelected = { selectedStyle = it }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Bottom section: animated reframed card + buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (groundedText != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 180.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Soft glow behind card
                        Box(
                            modifier = Modifier
                                .scale(glowScale)
                                .size(260.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                                            Color.Transparent
                                        )
                                    ),
                                    shape = CircleShape
                                )
                        )

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            shape = RoundedCornerShape(18.dp),
                            tonalElevation = 8.dp,
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.98f)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Reframed grounding",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = groundedText ?: "",
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            val result = generateGrounding(worryText, selectedStyle)
                            groundedText = result

                            if (worryText.isNotBlank()) {
                                scope.launch {
                                    dao.insertEntry(
                                        GroundEntry(
                                            worry = worryText.trim(),
                                            response = result,
                                            timestamp = System.currentTimeMillis()
                                        )
                                    )
                                }
                            }
                        },
                        modifier = Modifier.weight(1.5f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Reframe My Thought")
                    }

                    Button(
                        onClick = onViewHistory,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Text("View History")
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onBack,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Text("Back")
                    }
                }
            }
        }
    }
}

@Composable
private fun GroundingStyleChips(
    selectedStyle: GroundingStyle,
    onStyleSelected: (GroundingStyle) -> Unit
) {
    val items = listOf(
        GroundingStyle.REALITY_CHECK to "Reality check",
        GroundingStyle.SELF_COMPASSION to "Self-compassion",
        GroundingStyle.CONTROL to "What I can control",
        GroundingStyle.EVIDENCE to "Positive evidence",
        GroundingStyle.NORMALIZE to "Normalize it",
        GroundingStyle.FUTURE to "Future perspective"
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.take(3).forEach { (style, label) ->
                GroundingChip(
                    label = label,
                    isSelected = style == selectedStyle,
                    onClick = { onStyleSelected(style) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.drop(3).forEach { (style, label) ->
                GroundingChip(
                    label = label,
                    isSelected = style == selectedStyle,
                    onClick = { onStyleSelected(style) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun GroundingChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bg = if (isSelected) {
        Brush.horizontalGradient(
            listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.secondary
            )
        )
    } else {
        Brush.horizontalGradient(
            listOf(
                MaterialTheme.colorScheme.surfaceVariant,
                MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }

    Surface(
        modifier = modifier
            .height(40.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        tonalElevation = if (isSelected) 6.dp else 2.dp,
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .background(bg, RoundedCornerShape(20.dp))
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = TextStyle(
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSurface,
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                )
            )
        }
    }
}