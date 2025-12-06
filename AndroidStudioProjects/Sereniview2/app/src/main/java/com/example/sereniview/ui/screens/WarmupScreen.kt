package com.example.sereniview.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WarmupScreen(
    onBack: () -> Unit
) {
    val feelingOptions = listOf(
        "Not confident",
        "Overthinking",
        "Rejection fear",
        "Blanking out",
        "Imposter syndrome",
        "Worried about English",
        "Tired / low energy",
        "Socially awkward",
        "Comparing myself",
        "Scared of hard questions"
    )

    var selectedFeeling by remember { mutableStateOf<String?>(null) }
    var customText by remember { mutableStateOf("") }
    var advice by remember { mutableStateOf("") }

    fun buildAdvice(feeling: String?, custom: String): String {
        return when {
            custom.isNotBlank() && feeling == null -> {
                "You wrote: \"$custom\".\n\nFirst, accept that this worry is real. " +
                        "Take one slow breath and imagine explaining this to a kind friend, not an interviewer. " +
                        "You don’t need a perfect answer — just a simple, honest way to describe what you’ve tried and what you learned."
            }

            feeling == "Not confident" -> {
                "When you don’t feel confident, rely on evidence instead of feelings. " +
                        "Pick two stories that show effort or growth and practice saying them out loud. " +
                        "You don’t have to sound powerful — just real and clear."
            }
            feeling == "Overthinking" -> {
                "Overthinking means your brain is trying to protect you. " +
                        "Choose only 3 things to focus on: a strength, a story, and one question you’ll ask them. " +
                        "Everything else is extra, not required."
            }
            feeling == "Rejection fear" -> {
                "Fear of rejection is heavy, but remember: interviews are two-sided. " +
                        "You’re also seeing if the company fits you. A \"no\" doesn’t erase your progress or your worth."
            }
            feeling == "Blanking out" -> {
                "If you blank out, don’t apologize or panic. Say, \"Let me think for a second.\" " +
                        "Take a breath, then share even a small example. Calm beats speed every time."
            }
            feeling == "Imposter syndrome" -> {
                "Imposter syndrome shows up when you forget your receipts. " +
                        "List three moments when you faced something hard and still showed up. " +
                        "Those are proof that you belong in rooms like this."
            }
            feeling == "Worried about English" -> {
                "You don’t need perfect English to be good at your job. " +
                        "Use simple sentences, speak a little slower, and if you miss something, say, " +
                        "\"Could you please repeat or rephrase that?\" That’s professional, not embarrassing."
            }
            feeling == "Tired / low energy" -> {
                "When your energy is low, keep answers short and structured. " +
                        "Use a simple pattern: situation, what you did, result. " +
                        "Drink water, roll your shoulders back, and remember that being human is allowed."
            }
            feeling == "Socially awkward" -> {
                "If you feel socially awkward, prepare a few small phrases ahead of time, like " +
                        "\"Thank you for taking the time\" or \"That’s a good question.\" " +
                        "These little anchors make the conversation feel less scary."
            }
            feeling == "Comparing myself" -> {
                "Comparing yourself to others will always make you feel smaller. " +
                        "They invited *you* for a reason. Your only job is to share your story clearly, not to outshine anyone else."
            }
            feeling == "Scared of hard questions" -> {
                "For tough questions, it’s okay to pause. Say, \"Let me think for a moment.\" " +
                        "Focus on how you reason things out, how you learn, and how you would approach the situation — not on having a perfect, memorized answer."
            }

            feeling != null && custom.isNotBlank() -> {
                "You’re feeling: $feeling\nAnd you wrote: \"$custom\".\n\n" +
                        "Take one deep breath and imagine what you would say to a friend in the same situation. " +
                        "Turn your fear into one simple sentence, then pair it with a real example of how you try, learn, or adapt."
            }

            else -> {
                "Tap a feeling above or describe what’s bothering you below. " +
                        "Use this space as a reminder: the goal is not perfection, it’s to show up as yourself and speak honestly."
            }
        }
    }

    LaunchedEffect(Unit) {
        advice = buildAdvice(null, "")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = MaterialTheme.colorScheme.background.copy(alpha = 0.0f)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Mini Interview Warmup",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Pick how you feel or type your worry. Sereniview gives you a quick mental reset.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(feelingOptions.chunked(2)) { _, rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowItems.forEach { label ->
                            val isSelected = selectedFeeling == label
                            Button(
                                onClick = {
                                    selectedFeeling = label
                                    advice = buildAdvice(label, customText)
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurface
                                )
                            ) {
                                Text(
                                    text = label,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            OutlinedTextField(
                value = customText,
                onValueChange = { customText = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Type what’s worrying you") },
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp
                ),
                singleLine = false,
                maxLines = 3
            )

            Button(
                onClick = {
                    selectedFeeling = null
                    advice = buildAdvice(null, customText)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Give me advice on this")
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                tonalElevation = 6.dp,
                shadowElevation = 4.dp,
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.96f)
            ) {
                Text(
                    text = advice,
                    modifier = Modifier.padding(14.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                Text("Back")
            }
        }
    }
}