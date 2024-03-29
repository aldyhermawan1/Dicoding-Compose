package com.hermawan.composestarter.navdrawer

import android.content.Context
import android.widget.Toast
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult.ActionPerformed
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.hermawan.composestarter.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NavDrawerState(
    val scaffoldState: ScaffoldState,
    private val scope: CoroutineScope,
    private val context: Context
) {
    fun onMenuClick() {
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }

    fun onItemSelected(title: String) {
        scope.launch {
            scaffoldState.drawerState.close()
            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = context.resources.getString(R.string.coming_soon, title),
                actionLabel = context.resources.getString(R.string.subscribe_question)
            )
            if (snackbarResult == ActionPerformed) {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.subscribed_info),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun onBackPressed() {
        if (scaffoldState.drawerState.isOpen) {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        }
    }
}

@Composable
fun rememberNavDrawerState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current
): NavDrawerState {
   return remember(scaffoldState, coroutineScope, context) {
       NavDrawerState(scaffoldState, coroutineScope, context)
   }
}