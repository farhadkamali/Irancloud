package com.irancloud.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF142B4A)
private val Blue = Color(0xFF3478D4)
private val Pale = Color(0xFFF2F6FC)
private val Muted = Color(0xFF718096)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { IranCloudApp() }
    }
}

@Composable
fun IranCloudApp() {
    var query by remember { mutableStateOf("") }
    var playing by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(0) }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Blue,
            secondary = Navy,
            background = Pale,
            surface = Color.White
        )
    ) {
        CompositionLocalProvider(androidx.compose.ui.platform.LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Rtl) {
            Scaffold(
                containerColor = Pale,
                bottomBar = {
                    NavigationBar(containerColor = Color.White) {
                        val tabs = listOf("خانه", "کشف", "علاقه‌مندی", "کتابخانه")
                        val icons = listOf(Icons.Default.Home, Icons.Default.Explore, Icons.Default.FavoriteBorder, Icons.Default.LibraryMusic)
                        tabs.forEachIndexed { index, label ->
                            NavigationBarItem(
                                selected = selectedTab == index,
                                onClick = { selectedTab = index },
                                icon = { Icon(icons[index], contentDescription = label) },
                                label = { Text(label, fontSize = 10.sp) }
                            )
                        }
                    }
                }
            ) { padding ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 22.dp, bottom = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Column {
                                Text("سلام، خوش اومدی", color = Muted, fontSize = 13.sp)
                                Text("IranCloud", color = Navy, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                            }
                            Box(Modifier.size(46.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.NotificationsNone, contentDescription = "اعلان‌ها", tint = Navy)
                            }
                        }
                    }
                    item {
                        OutlinedTextField(
                            value = query,
                            onValueChange = { query = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("جستجوی آهنگ، هنرمند یا پادکست") },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                            shape = RoundedCornerShape(18.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Blue
                            )
                        )
                    }
                    item {
                        Box(
                            Modifier.fillMaxWidth().height(158.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(Brush.linearGradient(listOf(Color(0xFF4B91E2), Color(0xFF193B68))))
                                .padding(20.dp)
                        ) {
                            Column(Modifier.align(Alignment.CenterStart)) {
                                Text("موسیقی، همیشه همراه تو", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Spacer(Modifier.height(8.dp))
                                Text("آهنگ‌های تازه را کشف کن", color = Color.White.copy(alpha = .85f), fontSize = 13.sp)
                                Spacer(Modifier.height(14.dp))
                                Text("به‌زودی در IranCloud", color = Color.White, fontSize = 11.sp)
                            }
                            Icon(Icons.Default.GraphicEq, contentDescription = null, tint = Color.White.copy(alpha = .22f), modifier = Modifier.size(100.dp).align(Alignment.CenterEnd))
                        }
                    }
                    item {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("سبک‌های موسیقی", color = Navy, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text("مشاهده همه", color = Blue, fontSize = 12.sp)
                        }
                    }
                    item {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("پاپ", "رپ", "سنتی", "بی‌کلام").forEach { genre ->
                                Surface(color = Color.White, shape = RoundedCornerShape(14.dp)) {
                                    Text(genre, modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp), color = Navy, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                    item {
                        Text("پیشنهاد برای تو", color = Navy, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                            Box(Modifier.size(58.dp).clip(RoundedCornerShape(14.dp)).background(Color(0xFFDCE9F8)), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.MusicNote, contentDescription = null, tint = Blue, modifier = Modifier.size(30.dp))
                            }
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text("هنوز آهنگی اضافه نشده", color = Navy, fontWeight = FontWeight.SemiBold)
                                Text("پس از اتصال سرویس موسیقی، آهنگ‌ها اینجا نمایش داده می‌شوند", color = Muted, fontSize = 11.sp)
                            }
                            Icon(Icons.Default.MoreVert, contentDescription = "بیشتر", tint = Muted)
                        }
                    }
                    item {
                        Surface(color = Color.White, shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth()) {
                            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                Box(Modifier.size(42.dp).clip(RoundedCornerShape(12.dp)).background(Pale), contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.MusicNote, contentDescription = null, tint = Blue)
                                }
                                Spacer(Modifier.width(10.dp))
                                Column(Modifier.weight(1f)) {
                                    Text("پخش‌کننده IranCloud", color = Navy, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text("اتصال به منبع موسیقی هنوز انجام نشده", color = Muted, fontSize = 10.sp)
                                }
                                IconButton(onClick = { playing = !playing }) {
                                    Icon(if (playing) Icons.Default.Pause else Icons.Default.PlayArrow, contentDescription = if (playing) "مکث" else "پخش", tint = Blue)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
