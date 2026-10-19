package com.hamsat.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ink = Color(0xFF090710)
private val panel = Color(0xFF191323)
private val panel2 = Color(0xFF251934)
private val gold = Color(0xFFF3CD78)
private val paleGold = Color(0xFFFFE9B0)
private val lavender = Color(0xFFCDB7F2)
private val muted = Color(0xFF9C91AC)

class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { HamsatApp() } }
}

@Composable
fun HamsatApp() {
 var page by remember { mutableStateOf("welcome") }
 var room by remember { mutableStateOf("مجلس همسات") }
 var coins by remember { mutableIntStateOf(1200) }
 var username by remember { mutableStateOf("") }
 var email by remember { mutableStateOf("") }
 var password by remember { mutableStateOf("") }
 var notice by remember { mutableStateOf("") }
 val rooms = listOf(
  Triple("مجلس همسات", "حديث ولقاءات", "♛") ,
  Triple("سهرات على الهوا", "موسيقى وأصدقاء", "☾"),
  Triple("قهوة الأصدقاء", "تعرفوا على بعض", "✦"),
  Triple("فضفضة همسات", "مساحة هادئة", "♡")
 )
 MaterialTheme(colorScheme = darkColorScheme(primary = gold, background = ink, surface = panel)) {
  Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFF21142F), ink, Color(0xFF07060B))))) {
   when (page) {
    "welcome" -> RoyalWelcome(
      onLogin = { page = "login"; notice = "" },
      onRegister = { page = "register"; notice = "" }
    )
    "login" -> AuthPage("مرحبًا بعودتك", "سجّلي الدخول إلى عالم همسات", username, { username = it }, password, { password = it }, notice, { notice = it }, "دخول إلى التطبيق", submit = {
      if (username.isBlank() || password.isBlank()) notice = "اكتبي اسم المستخدم وكلمة المرور أولًا" else { notice = ""; page = "home" }
    }, back = { page = "welcome" })
    "register" -> AuthPage("انضمي إلى همسات", "اصنعي لحظات جميلة مع الأصدقاء", username, { username = it }, password, { password = it }, notice, { notice = it }, "إنشاء حساب تجريبي", email, { email = it }, submit = {
      if (username.isBlank() || email.isBlank() || password.isBlank()) notice = "املئي الخانات الثلاث أولًا" else { notice = ""; page = "home" }
    }, back = { page = "welcome" })
    "home" -> Column(Modifier.fillMaxSize()) {
     Row(Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 18.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
      Column {
       Text("مساء الورد ✨", color = lavender, fontSize = 12.sp)
       Text("أهلًا، ${username.ifBlank { "ضيف همسات" }}", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.SemiBold)
      }
      Surface(shape = RoundedCornerShape(50.dp), color = Color(0xFF30223D), border = BorderStroke(1.dp, Color(0xFF5C476C)), modifier = Modifier.clickable { page = "wallet" }) {
       Row(Modifier.padding(horizontal = 13.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) { Text("✦", color = gold, fontSize = 18.sp); Spacer(Modifier.width(6.dp)); Text("$coins", color = paleGold, fontWeight = FontWeight.Bold) }
      }
     }
     LazyColumn(Modifier.weight(1f).padding(horizontal = 18.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(top = 20.dp, bottom = 18.dp)) {
      item {
       Box(Modifier.fillMaxWidth().height(190.dp).clip(RoundedCornerShape(28.dp)).background(Brush.linearGradient(listOf(Color(0xFF59406B), Color(0xFF24152E), Color(0xFF11101A))))) {
        AsyncImage(model = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?auto=format&fit=crop&w=1100&q=85", contentDescription = "صورة فاخرة لعضوة همسات", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Box(Modifier.fillMaxSize().background(Brush.horizontalGradient(listOf(Color(0xF0090710), Color(0xBB130B1C), Color(0x22200F2A)))))
        Column(Modifier.align(Alignment.CenterStart).padding(start = 20.dp, end = 90.dp)) {
         Text("ليلتك تبدأ هنا", color = paleGold, fontSize = 24.sp, fontWeight = FontWeight.Bold)
         Spacer(Modifier.height(6.dp)); Text("اكتشفي غرفًا جديدة، واصنعي صداقات", color = Color.White, fontSize = 12.sp)
         Spacer(Modifier.height(15.dp))
         Surface(shape = RoundedCornerShape(30.dp), color = gold, modifier = Modifier.clickable { room = "مجلس همسات"; page = "room" }) { Text("اكتشفي الآن  ←", color = ink, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 15.dp, vertical = 9.dp)) }
        }
        Text("♛", Modifier.align(Alignment.CenterEnd).padding(end = 14.dp), color = Color(0x99F3CD78), fontSize = 86.sp)
       }
      }
      item {
       Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column { Text("الغرف الصوتية", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold); Text("اختاري أجواءك الليلة", color = muted, fontSize = 12.sp) }
        Text("عرض الكل  ›", color = gold, fontSize = 12.sp)
       }
      }
      itemsIndexed(rooms) { index, item ->
       Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(22.dp)).background(Brush.horizontalGradient(listOf(Color(0xFF30203A), Color(0xFF151019)))).clickable { room = item.first; page = "room" }.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(76.dp).clip(RoundedCornerShape(18.dp)).background(Brush.linearGradient(listOf(Color(0xFF77543D), Color(0xFF38264A)))), contentAlignment = Alignment.Center) {
         AsyncImage(model = listOf("https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=320&q=85","https://images.unsplash.com/photo-1529139574466-a303027c1d8?auto=format&fit=crop&w=320&q=85","https://images.unsplash.com/photo-1517841905240-472988babdf9?auto=format&fit=crop&w=320&q=85","https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?auto=format&fit=crop&w=320&q=85")[index], contentDescription = "صورة شخصية للغرفة", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
         Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color(0x99090710)))))
         Text("✦", Modifier.align(Alignment.BottomEnd).padding(4.dp), color = paleGold, fontSize = 16.sp)
        }
        Spacer(Modifier.width(13.dp))
        Column(Modifier.weight(1f)) {
         Text(item.first, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
         Spacer(Modifier.height(4.dp)); Text(item.second, color = lavender, fontSize = 11.sp)
         Spacer(Modifier.height(7.dp)); Row(verticalAlignment = Alignment.CenterVertically) { Box(Modifier.size(6.dp).clip(CircleShape).background(Color(0xFF7FE0B1))); Spacer(Modifier.width(5.dp)); Text("غرفة تجريبية", color = muted, fontSize = 10.sp) }
        }
        Text("دخول  ›", color = gold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
       }
      }
      item {
       Text("اكتشفي المزيد", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
       Spacer(Modifier.height(10.dp))
       Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        MiniTile("♙", "الأصدقاء", Modifier.weight(1f)) { page = "friends" }
        MiniTile("🎁", "الهدايا", Modifier.weight(1f)) { page = "gifts" }
        MiniTile("✦", "VIP", Modifier.weight(1f)) { page = "vip" }
       }
      }
     }
     BottomNav(page) { page = it }
    }
    "room" -> Column(Modifier.fillMaxSize().padding(horizontal = 18.dp, vertical = 16.dp)) {
     Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
      Text("‹  رجوع", color = gold, modifier = Modifier.clickable { page = "home" }.padding(8.dp))
      Text("غرفة صوتية", color = lavender, fontSize = 12.sp)
      Text("⋯", color = Color.White, fontSize = 25.sp)
     }
     Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(Brush.verticalGradient(listOf(Color(0xFF493155), Color(0xFF21152D)))).padding(18.dp)) {
      Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
       Text("♛", color = gold, fontSize = 37.sp)
       Text(room, color = paleGold, fontSize = 24.sp, fontWeight = FontWeight.Bold)
       Text("أهلًا بكم في سهرتنا", color = lavender, fontSize = 12.sp)
       Spacer(Modifier.height(8.dp)); Text("غرفة عرض — المايك المباشر غير مفعّل", color = muted, fontSize = 10.sp)
      }
     }
     Spacer(Modifier.height(18.dp))
     Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("مقاعد الصوت", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold); Text("6 مقاعد", color = lavender, fontSize = 12.sp) }
     Spacer(Modifier.height(14.dp))
     Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(16.dp)) {
      Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) { Seat("♛", "المضيف", true); Seat("♫", "مقعد 2"); Seat("♡", "مقعد 3") }
      Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) { Seat("✧", "مقعد 4"); Seat("♙", "مقعد 5"); Seat("✦", "مقعد 6") }
      Surface(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), color = panel) { Column(Modifier.padding(14.dp)) { Text("لحظات من الغرفة", color = paleGold, fontWeight = FontWeight.Bold); Spacer(Modifier.height(5.dp)); Text("الهدايا والدردشة الصوتية الفعلية ستُفعّل بعد ربط الخدمة.", color = muted, fontSize = 11.sp) } }
     }
     Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
      OutlinedButton(onClick = { notice = "الميكروفون غير مربوط بخدمة صوت مباشرة بعد." }, modifier = Modifier.weight(1f).height(52.dp), shape = RoundedCornerShape(16.dp), border = BorderStroke(1.dp, gold), colors = ButtonDefaults.outlinedButtonColors(contentColor = gold)) { Text("🎙 المايك") }
      Button(onClick = { page = "gifts" }, modifier = Modifier.weight(1f).height(52.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = gold, contentColor = ink)) { Text("🎁 الهدايا", fontWeight = FontWeight.Bold) }
     }
     if (notice.isNotBlank()) Text(notice, color = lavender, fontSize = 11.sp)
    }
    "wallet" -> DetailPage("محفظة همسات", "✦", "رصيد تجريبي", listOf("رصيدك الحالي: $coins كوينز", "المكافآت اليومية — قريبًا", "شراء الكوينز غير مفعّل", "العملات للعرض فقط وليست رصيدًا حقيقيًا"), { page = "home" })
    "gifts" -> DetailPage("معرض الهدايا", "✧", "اختاري هدية", listOf("🌹   وردة ملكية        • 50 كوينز", "💖   قلب الحب            • 100 كوينز", "🎂   كعكة احتفال      • 250 كوينز", "🧸   دبدوب لطيف       • 300 كوينز", "♛   تاج ذهبي            • 500 كوينز", "💎   جوهرة فاخرة     • 750 كوينز", "🦋   فراشة سحرية     • 150 كوينز", "ملاحظة: إرسال الهدايا غير مفعّل بعد"), { page = "home" })
    "friends" -> DetailPage("مجتمع همسات", "♡", "الأصدقاء", listOf("اكتشفي الأصدقاء في الغرف", "طلبات الصداقة — قريبًا", "الرسائل الخاصة تحتاج ربطًا فعليًا"), { page = "home" })
    "profile" -> DetailPage("ملفي الشخصي", "♙", username.ifBlank { "ضيف همسات" }, listOf("الاسم: ${username.ifBlank { "ضيف" }}", "نبذة شخصية — قريبًا", "إطارات الملف الشخصي", "إعدادات الحساب"), { page = "home" })
    "vip" -> DetailPage("نادي VIP", "♛", "أناقة تليق بك", listOf("إطارات ذهبية ملكية", "شارات VIP — معاينة", "مزايا العضوية قيد التطوير", "لا توجد مشتريات مفعّلة"), { page = "home" })
    "games" -> DetailPage("ألعاب السهرة", "✦", "لحظات مرحة", listOf("عجلة الحظ — قريبًا", "أسئلة الأصدقاء — قريبًا", "تحدي السهرة — قريبًا"), { page = "home" })
    else -> DetailPage("همسات", "♛", "أهلًا بك", listOf("اختاري غرفة وابدئي التعرف على الأصدقاء"), { page = "home" })
   }
  }
 }
}

@Composable
fun FeaturePill(text: String) { Surface(shape = RoundedCornerShape(30.dp), color = Color(0x552D203B), border = BorderStroke(1.dp, Color(0xFF574464))) { Text(text, color = lavender, fontSize = 11.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) } }

@Composable
fun RoyalWelcome(onLogin: () -> Unit, onRegister: () -> Unit) {
 Box(Modifier.fillMaxSize().background(ink)) {
  AsyncImage(
   model = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=1200&q=90",
   contentDescription = "صورة ملكية لهوية همسات",
   modifier = Modifier.fillMaxSize(),
   contentScale = ContentScale.Crop
  )
  Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xBB08060D), Color(0xAA100819), Color(0xFF090710)))))
  Box(Modifier.fillMaxSize().padding(18.dp).clip(RoundedCornerShape(32.dp)).background(Color(0x221C1326)))
  Column(Modifier.fillMaxSize().padding(horizontal = 26.dp, vertical = 32.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
   Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
    Box(Modifier.width(42.dp).height(1.dp).background(Brush.horizontalGradient(listOf(Color.Transparent, gold))))
    Text("  H A M S A T  ", color = paleGold, fontSize = 12.sp, letterSpacing = 3.sp, fontWeight = FontWeight.Medium)
    Box(Modifier.width(42.dp).height(1.dp).background(Brush.horizontalGradient(listOf(gold, Color.Transparent))))
   }
   Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Box(Modifier.size(112.dp).clip(CircleShape).background(Brush.radialGradient(listOf(Color(0xFF76532E), Color(0xDD24152E), Color(0xAA090710)))), contentAlignment = Alignment.Center) {
     Text("♛", color = paleGold, fontSize = 72.sp)
    }
    Spacer(Modifier.height(14.dp))
    Text("هَمسات", color = paleGold, fontSize = 46.sp, fontWeight = FontWeight.Light, letterSpacing = 2.sp)
    Spacer(Modifier.height(8.dp))
    Text("صوتكِ... عالمكِ... وصداقات تشبهكِ", color = Color.White, fontSize = 14.sp)
    Spacer(Modifier.height(18.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
     FeaturePill("غرف صوتية")
     FeaturePill("أصدقاء")
     FeaturePill("هدايا ملكية")
    }
   }
   Column(horizontalAlignment = Alignment.CenterHorizontally) {
    GoldButton("دخول عالم همسات  ↗", onLogin)
    Spacer(Modifier.height(12.dp))
    OutlinedButton(onClick = onRegister, modifier = Modifier.fillMaxWidth().height(54.dp), shape = RoundedCornerShape(18.dp), border = BorderStroke(1.dp, Color(0xFFD5B77D)), colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
     Text("إنشاء حساب جديد", fontWeight = FontWeight.SemiBold)
    }
    Spacer(Modifier.height(18.dp))
    Text("تجربة همسات الملكية", color = paleGold, fontSize = 11.sp, letterSpacing = 1.sp)
   }
  }
 }
}

@Composable
fun AuthPage(title: String, subtitle: String, username: String, onUsername: (String)->Unit, password: String, onPassword: (String)->Unit, notice: String, onNotice: (String)->Unit, button: String, email: String? = null, onEmail: ((String)->Unit)? = null, submit: ()->Unit, back: ()->Unit) {
 Box(Modifier.fillMaxSize().background(ink)) {
  AsyncImage(model = "https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?auto=format&fit=crop&w=1100&q=85", contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
  Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xDD090710), Color(0xC9090710), Color(0xFF090710)))))
  Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
   Box(Modifier.size(84.dp).clip(CircleShape).background(Brush.radialGradient(listOf(Color(0xFF73522F), Color(0xFF291B35), ink))), contentAlignment = Alignment.Center) {
    Text("♛", color = paleGold, fontSize = 54.sp)
   }
   Spacer(Modifier.height(18.dp))
   Text(title, color = paleGold, fontSize = 29.sp, fontWeight = FontWeight.Bold)
   Spacer(Modifier.height(6.dp))
   Text(subtitle, color = Color.White, fontSize = 13.sp)
   Spacer(Modifier.height(26.dp))
   Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(26.dp)).background(Color(0xDD17111F)).padding(18.dp)) {
    HamsatField(username, onUsername, "اسم المستخدم")
    if (email != null && onEmail != null) { Spacer(Modifier.height(12.dp)); HamsatField(email, onEmail, "البريد الإلكتروني", type = KeyboardType.Email) }
    Spacer(Modifier.height(12.dp))
    HamsatField(password, onPassword, "كلمة المرور", password = true)
    if (notice.isNotBlank()) { Spacer(Modifier.height(10.dp)); Text(notice, color = gold, fontSize = 12.sp) }
    Spacer(Modifier.height(18.dp))
    GoldButton(button, submit)
   }
   Spacer(Modifier.height(14.dp))
   Text("نموذج تجريبي محلي؛ لا ينشئ حسابًا حقيقيًا عبر الإنترنت.", color = Color(0xFFD2C7D9), fontSize = 11.sp)
   Spacer(Modifier.height(12.dp))
   Text("‹  رجوع", color = paleGold, modifier = Modifier.clickable { back() }.padding(10.dp))
  }
 }
}

@Composable
fun Seat(symbol: String, label: String, host: Boolean = false) {
 Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(88.dp)) {
  Box(Modifier.size(68.dp).clip(CircleShape).background(Brush.linearGradient(if (host) listOf(Color(0xFFB78B48), Color(0xFF493052)) else listOf(Color(0xFF443152), Color(0xFF21182B)))).then(if (host) Modifier else Modifier), contentAlignment = Alignment.Center) {
   Box(Modifier.size(60.dp).clip(CircleShape).background(Color(0xFF1B1424)), contentAlignment = Alignment.Center) { Text(symbol, color = if (host) gold else lavender, fontSize = 30.sp) }
  }
  Spacer(Modifier.height(7.dp)); Text(label, color = if (host) paleGold else Color.White, fontSize = 11.sp)
 }
}

@Composable
fun MiniTile(symbol: String, title: String, modifier: Modifier = Modifier, action: () -> Unit) {
 Column(modifier.clip(RoundedCornerShape(18.dp)).background(panel).clickable { action() }.padding(vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
  Text(symbol, color = gold, fontSize = 28.sp); Spacer(Modifier.height(6.dp)); Text(title, color = Color.White, fontSize = 11.sp)
 }
}

@Composable
fun BottomNav(page: String, navigate: (String)->Unit) {
 Row(Modifier.fillMaxWidth().background(Color(0xFF100C17)).padding(top = 11.dp, bottom = 13.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
  NavItem("⌂", "الرئيسية", page == "home") { navigate("home") }
  NavItem("♡", "الأصدقاء", page == "friends") { navigate("friends") }
  NavItem("✧", "الهدايا", page == "gifts") { navigate("gifts") }
  NavItem("♛", "VIP", page == "vip") { navigate("vip") }
  NavItem("♙", "حسابي", page == "profile") { navigate("profile") }
 }
}
@Composable
fun NavItem(symbol: String, title: String, active: Boolean, action: ()->Unit) {
 Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { action() }.padding(horizontal = 4.dp)) {
  Text(symbol, color = if (active) gold else muted, fontSize = 22.sp)
  Text(title, color = if (active) paleGold else muted, fontSize = 9.sp)
 }
}

@Composable
fun DetailPage(title: String, symbol: String, subtitle: String, rows: List<String>, back: ()->Unit) {
 Column(Modifier.fillMaxSize().padding(20.dp)) {
  Text("‹  رجوع", color = gold, modifier = Modifier.clickable { back() }.padding(vertical = 8.dp))
  Spacer(Modifier.height(22.dp))
  Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(26.dp)).background(Brush.linearGradient(listOf(Color(0xFF483054), Color(0xFF1B1425)))).padding(22.dp)) {
   Column {
    Text(symbol, color = gold, fontSize = 42.sp)
    Text(title, color = paleGold, fontSize = 27.sp, fontWeight = FontWeight.Bold)
    Text(subtitle, color = lavender, fontSize = 13.sp)
   }
  }
  Spacer(Modifier.height(20.dp))
  rows.forEachIndexed { i, row ->
   Row(Modifier.fillMaxWidth().padding(vertical = 5.dp).clip(RoundedCornerShape(17.dp)).background(panel).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
    Text(if (i == 0) "✦" else "◇", color = gold, fontSize = 19.sp); Spacer(Modifier.width(12.dp)); Text(row, color = Color.White, fontSize = 14.sp)
   }
  }
 }
}

@Composable
fun HamsatField(value: String, onValueChange: (String) -> Unit, label: String, password: Boolean = false, type: KeyboardType = KeyboardType.Text) {
 OutlinedTextField(value = value, onValueChange = onValueChange, label = { Text(label, color = lavender) }, singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), keyboardOptions = KeyboardOptions(keyboardType = if (password) KeyboardType.Password else type), visualTransformation = if (password) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None, colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = gold, unfocusedBorderColor = Color(0xFF5B496C), cursorColor = gold, focusedLabelColor = gold, unfocusedLabelColor = lavender))
}
@Composable
fun GoldButton(label: String, action: () -> Unit) {
 Button(onClick = action, modifier = Modifier.fillMaxWidth().height(54.dp), shape = RoundedCornerShape(18.dp), colors = ButtonDefaults.buttonColors(containerColor = gold, contentColor = ink)) { Text(label, fontWeight = FontWeight.Bold, fontSize = 15.sp) }
}
