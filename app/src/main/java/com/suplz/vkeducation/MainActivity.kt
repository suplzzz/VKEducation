package com.suplz.vkeducation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.suplz.vkeducation.ui.theme.VKEducationTheme
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("") }
    val phoneUtil = remember { PhoneNumberUtil.createInstance(context) }

    val isValidPhoneNumber by remember {
        derivedStateOf {
            runCatching {
                val number = phoneUtil.parse(
                    inputText,
                    Locale.getDefault().country
                )
                phoneUtil.isValidNumber(number)
            }.getOrDefault(false)
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { newText ->
                inputText = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(32.dp),
            singleLine = true,
        )



        CapsuleButton(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra(SecondActivity.EXTRA_TEXT, inputText)
                context.startActivity(intent)
            },
            text = stringResource(R.string.open_second_activity),
            enabled = true
        )



        CapsuleButton(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL, "tel:${inputText}".toUri())
                runCatching {
                    context.startActivity(intent)
                }.onFailure {
                    Toast.makeText(
                        context,
                        context.getString(R.string.failed_to_make_a_call),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            },
            text = stringResource(R.string.call_a_friend),
            enabled = isValidPhoneNumber
        )



        CapsuleButton(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_TEXT, inputText)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent, null)
                context.startActivity(shareIntent)
            },
            text = stringResource(R.string.share_text),
            enabled = inputText.isNotBlank()
        )
    }
}

@Composable
fun CapsuleButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(32.dp),
        enabled = enabled,
    ) {
        Text(text = text)
    }
}



