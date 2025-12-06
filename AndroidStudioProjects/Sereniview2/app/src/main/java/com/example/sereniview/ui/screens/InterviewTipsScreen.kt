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

data class TipMessage(
    val fromUser: Boolean,
    val text: String
)

@Composable
fun InterviewTipsScreen(
    onBack: () -> Unit
) {
    var input by remember { mutableStateOf("") }

    val messages = remember {
        mutableStateListOf(
            TipMessage(
                fromUser = false,
                text = "Ask me anything about interviews. I’ll share a focused tip and a small next step."
            )
        )
    }

    val tips = listOf(
        "Breathe out longer than you breathe in to calm your body.",
        "Have one strong story ready that shows how you handled a challenge.",
        "Pause before answering instead of rushing to fill silence.",
        "Use simple language; clarity matters more than big words.",
        "Write down three things you did well this week.",
        "Imagine explaining your experience to a kind friend, not a judge.",
        "Keep a small bottle of water nearby to slow yourself down.",
        "If you do not understand a question, ask them to repeat it.",
        "Think in terms of situation, action, result when answering.",
        "Make eye contact with one person at a time, not the whole room.",
        "Remind yourself: being nervous does not mean you are unqualified.",
        "Focus on the current question, not the whole interview at once.",
        "Smile gently before you start speaking; it relaxes your face.",
        "List three strengths that have nothing to do with perfection.",
        "Schedule your practice in small blocks instead of long cramming.",
        "Write your main points in bullet form instead of memorizing scripts.",
        "Remind yourself that the interviewer is also just a person at work.",
        "If your heart is racing, drop your shoulders and unclench your jaw.",
        "Practice answering one question into your phone and replay it once.",
        "Prepare one story where you made a mistake but learned from it.",
        "Have a closing line ready like: “Thank you for your time today.”",
        "Plan your outfit the day before, not the morning of the interview.",
        "Use notes only for structure, not full sentences.",
        "After each practice answer, name one thing you did well.",
        "Choose one strength you really want them to remember about you.",
        "Do a short walk before the interview to release nervous energy.",
        "Keep your hands resting on the table or in your lap, not hidden.",
        "Say your name out loud with confidence a few times before joining.",
        "Treat each interview as practice, not a final exam.",
        "Remember that you are also evaluating if the job is right for you.",
        "If you lose your words, calmly restart the sentence.",
        "Prepare one question about the team or work culture.",
        "Place both feet flat on the floor when you feel anxious.",
        "Think of one person in your life who believes in you and why.",
        "Focus on being honest, not impressive.",
        "If you ramble, stop, take a breath, and summarize your main point.",
        "Remind yourself: nobody notices your anxiety as much as you do.",
        "Use phrases like “from my experience” to ground your answer.",
        "Have a quiet space ready for online interviews.",
        "Check your tech setup at least 15 minutes before the call.",
        "Practice saying, “Let me think for a second,” out loud.",
        "Do not apologize for taking a moment to think.",
        "Accept that small mistakes will happen and that is normal.",
        "End answers with what you learned or how you grew.",
        "Keep your phone on silent and out of sight.",
        "Find one thing in your background that you are genuinely proud of.",
        "Take one slow breath before you answer each new question.",
        "After the interview, write down what went better than expected.",
        "Remember: they already saw potential in you when they invited you.",
        "Speak a little slower than you do in normal conversation.",
        "You are allowed to be learning; you do not need to be finished."
    )

    var tipIndex by remember { mutableStateOf(0) }

    fun nextTip(): String {
        val tip = tips[tipIndex % tips.size]
        tipIndex++
        return "Tip: $tip"
    }

    fun botReply(userText: String): String {
        val raw = userText.trim()
        val t = raw.lowercase()

        if (raw.isBlank()) {
            return "You don’t have to type a lot. Even a few words about your concern are enough.\n\n${nextTip()}"
        }

        if (t.contains("resume") || t.contains("cv")) {
            return "You asked about your resume: \"$raw\".\n\nKeep it simple and clear. Highlight projects or experiences that show impact, not just tasks.\n\n${nextTip()}"
        }

        if (t.contains("nervous") || t.contains("scared") || t.contains("anxious")) {
            return "You’re feeling nervous: \"$raw\".\n\nThat’s completely normal. Try one slow breath before each answer and remember they already saw potential in you.\n\n${nextTip()}"
        }

        if (t.contains("prepare") || t.contains("preparation") || t.contains("ready")) {
            return "You’re asking how to prepare: \"$raw\".\n\nChoose 2–3 stories that show your effort, problem solving, or growth and practice telling them out loud.\n\n${nextTip()}"
        }

        if (t.contains("weakness")) {
            return "You mentioned weakness: \"$raw\".\n\nPick a real but safe weakness and focus on what you’re doing to improve it. That shows honesty and growth.\n\n${nextTip()}"
        }

        if (t.contains("strength")) {
            return "You’re thinking about strengths: \"$raw\".\n\nChoose strengths that match the job: communication, problem solving, learning fast, reliability, etc., and back them up with examples.\n\n${nextTip()}"
        }

        if (t.contains("no experience") || t.contains("no exp") || t.contains("no work")) {
            return "You’re worried about not having experience: \"$raw\".\n\nUse school projects, volunteer work, or personal projects as your examples. Experience is not only a job title.\n\n${nextTip()}"
        }

        if (t.contains("?")) {
            return "Good question. You said: \"$raw\".\n\nTry to break it into something you can control: one thing to prepare, one thing to practice, one thing to let go of.\n\n${nextTip()}"
        }

        return "Thank you for sharing: \"$raw\".\n\nYour concern is valid. You don’t have to have everything figured out to take the next step.\n\n${nextTip()}"
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
                text = "Interview Tips Chat",
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
                    label = { Text("Ask something about interviews") },
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
                                messages.add(TipMessage(fromUser = true, text = userText))
                                val reply = botReply(userText)
                                messages.add(TipMessage(fromUser = false, text = reply))
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