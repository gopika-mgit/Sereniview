package com.example.sereniview.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CalmMessage(
    val fromUser: Boolean,
    val text: String
)

@Composable
fun CalmChatScreen(
    onBack: () -> Unit
) {
    var input by remember { mutableStateOf("") }

    val messages = remember {
        mutableStateListOf(
            CalmMessage(
                fromUser = false,
                text = "Hey, I’m here with you. What’s on your mind about interviews today?"
            )
        )
    }

    fun botReply(userText: String): String {
        val raw = userText.trim()
        val t = raw.lowercase()

        if (raw.isBlank()) {
            return "You don’t have to type a lot. Even a few words about how you feel is enough."
        }

        // 👋 greetings
        if (t == "hi" || t == "hey" || t == "hello" || t == "hey there") {
            return "Hey :) I’m glad you reached out. Tell me a bit more – what part of interviews is bothering you right now?"
        }

        // ❓ questions in general
        if (t.contains("?")) {
            return "Good question. You said: \"$raw\".\n\nTry to break that into something small you can control: one thing you can prepare, one thing you can practice, and one thing you can let go of."
        }

        // preparation
        if ("prepare" in t || "preparation" in t || "ready" in t || "get ready" in t) {
            return "You’re asking about preparation: \"$raw\".\n\nPick 2–3 stories from your experience (school, projects, work) that show effort or growth. Practice saying them out loud slowly, like you’re explaining them to a friend."
        }

        // nervous / anxious
        if ("nervous" in t || "anxious" in t || "anxiety" in t || "scared" in t || "fear" in t) {
            return "You said you’re feeling something like: \"$raw\".\n\nThat feeling is completely valid. Try placing your feet flat on the ground, take one slow breath, and remember: they invited you for a reason. You don’t have to hide your nerves to do well."
        }

        // blanking out
        if ("blank" in t || "blanking" in t || "forget" in t || "forgetting" in t) {
            return "You’re worried about blanking out: \"$raw\".\n\nIf your mind goes blank, it’s okay to pause and say, \"Let me think for a second.\" Practice that line now so it feels natural. Silence for a moment is not failure."
        }

        // english / communication
        if ("english" in t || "accent" in t || "speak" in t || "speaking" in t || "communication" in t) {
            return "You mentioned: \"$raw\" about English/communication.\n\nYou don’t need perfect English. Use simple sentences, speak a bit slower, and if you don’t understand, it’s completely fine to say, \"Could you repeat or rephrase that, please?\""
        }

        // confidence / not good enough
        if ("confidence" in t || "confident" in t || "self esteem" in t || "not good enough" in t || "insecure" in t) {
            return "You’re doubting yourself: \"$raw\".\n\nConfidence doesn’t mean never feeling scared; it means showing up anyway. Think of 2–3 times you handled something difficult — those are your proof that you’re more capable than you feel right now."
        }

        // rejection
        if ("rejection" in t || "reject" in t || "failed" in t || "failure" in t) {
            return "You’re afraid of rejection: \"$raw\".\n\nA \"no\" doesn’t cancel your whole story. It’s one data point, not your value. Every interview also teaches you something you can carry into the next one."
        }

        // first interview
        if ("first interview" in t || ("first" in t && "interview" in t)) {
            return "This being your first interview makes it feel huge: \"$raw\".\n\nRemember: they don’t expect perfection. They’re mostly trying to see how you think, how you learn, and if you can communicate honestly."
        }

        // tired / burnt out
        if ("tired" in t || "exhausted" in t || "burnt" in t || "burned" in t || "overwhelmed" in t) {
            return "You sound drained: \"$raw\".\n\nBe gentle with yourself. Instead of cramming, do one light review, then rest. Showing up slightly under-prepared but calmer is better than showing up burnt out and frozen."
        }

        // comparing
        if ("compare" in t || "comparing" in t || "others" in t || "everyone else" in t) {
            return "You’re comparing yourself: \"$raw\".\n\nIt’s so easy to feel smaller when you think everyone else is ahead. But they invited *you*. Your goal isn’t to be the best in the world — it’s to show your real story clearly."
        }

        // generic emotional / catch-all
        return "Thank you for sharing: \"$raw\".\n\nHowever this feels, it’s allowed. Try to talk to yourself the way you’d support a friend in the same situation — kinder, slower, and without judging every little mistake."
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background.copy(alpha = 0.0f)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Calm Chat",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                tonalElevation = 6.dp,
                shadowElevation = 4.dp,
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.96f)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(messages) { msg ->
                        val alignment =
                            if (msg.fromUser) Alignment.CenterEnd else Alignment.CenterStart
                        val bgColor =
                            if (msg.fromUser) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surfaceVariant
                        val textColor =
                            if (msg.fromUser) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurface

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = alignment
                        ) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = bgColor
                            ) {
                                Text(
                                    text = msg.text,
                                    modifier = Modifier.padding(10.dp),
                                    style = TextStyle(
                                        color = textColor,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Type what you want to share") },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp
                    ),
                    singleLine = false,
                    maxLines = 3
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (input.isNotBlank()) {
                                val userText = input.trim()
                                messages.add(CalmMessage(fromUser = true, text = userText))
                                val reply = botReply(userText)
                                messages.add(CalmMessage(fromUser = false, text = reply))
                                input = ""
                            }
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Send")
                    }

                    Button(
                        onClick = onBack,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Text("Back")
                    }
                }
            }
        }
    }
}