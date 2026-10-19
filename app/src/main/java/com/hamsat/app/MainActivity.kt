package com.hamsat.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val night = Color(0xFF100A1D)
private val gold = Color(0xFFF1C66D)
private val lilac = Color(0xFFD7B9FF)

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
 MaterialTheme(colorScheme = darkColorScheme(primary = gold, background = night, surface = Color(0xFF24172F))) {
  Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFF321347), night, Color(0xFF09070D))))) {
   when (page) {
    "welcome" -> Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
     Surface(
      color = Color(0xFF21152D),
      shape = RoundedCornerShape(36.dp),
      modifier = Modifier.size(148.dp)
     ) {
      Box(
       modifier = Modifier.fillMaxSize().background(
        Brush.verticalGradient(listOf(Color(0xFF523A63), Color(0xFF17101F)))
       ),
       contentAlignment = Alignment.Center
      ) {
       Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("♛", color = gold, fontSize = 64.sp)
        Text("H", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
       }
      }
     }
     Spacer(Modifier.height(22.dp))
     Text("همسات", color = gold, fontSize = 42.sp, fontWeight = FontWeight.Bold)
     Text("H A M S A T   G O L D", color = lilac, letterSpacing = 2.sp, fontSize = 12.sp)
     Spacer(Modifier.height(10.dp))
     Surface(color = Color(0x332A1B38), shape = RoundedCornerShape(50.dp)) {
      Text("غرف صوتية  •  أصدقاء  •  لحظات مميزة", color = Color.White, fontSize = 13.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp))
     }
     Spacer(Modifier.height(32.dp))
     GoldButton("دخول عالم همسات") { page = "login"; notice = "" }
     Spacer(Modifier.height(12.dp))
     OutlinedButton(
      onClick = { page = "register"; notice = "" },
      modifier = Modifier.fillMaxWidth().height(52.dp),
      shape = RoundedCornerShape(16.dp),
      colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
      border = androidx.compose.foundation.BorderStroke(1.dp, lilac)
     ) { Text("إنشاء حساب جديد", fontWeight = FontWeight.SemiBold) }
     Spacer(Modifier.height(20.dp))
     Text("نسخة أولية قيد التطوير", color = lilac, fontSize = 12.sp)
     Text("الصوت المباشر والحسابات الحقيقية ستُربط لاحقًا", color = Color(0xFFAAA0B8), fontSize = 10.sp)
    }
    "login" -> Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
     Text("♛", color = gold, fontSize = 54.sp)
     Text("تسجيل الدخول", color = gold, fontSize = 30.sp, fontWeight = FontWeight.Bold)
     Spacer(Modifier.height(8.dp)); Text("أهلًا بعودتك إلى همسات", color = lilac)
     Spacer(Modifier.height(24.dp))
     HamsatField(username, { username = it }, "اسم المستخدم")
     Spacer(Modifier.height(12.dp))
     HamsatField(password, { password = it }, "كلمة المرور", password = true)
     if (notice.isNotBlank()) { Spacer(Modifier.height(10.dp)); Text(notice, color = gold, fontSize = 13.sp) }
     Spacer(Modifier.height(20.dp))
     GoldButton("دخول تجريبي") {
      if (username.isBlank() || password.isBlank()) notice = "اكتبي اسم المستخدم وكلمة المرور أولًا"
      else { notice = ""; page = "home" }
     }
     Spacer(Modifier.height(12.dp)); Text("هذه تجربة محلية؛ لا يتم التحقق من الحساب أو حفظه على الإنترنت.", color = lilac, fontSize = 12.sp)
     Spacer(Modifier.height(18.dp)); Text("رجوع", color = Color.White, modifier = Modifier.clickable { page = "welcome" }.padding(8.dp))
    }
    "home" -> Column(Modifier.fillMaxSize().padding(18.dp)) {
     Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
      Column { Text("أهلًا ${username.ifBlank { "ضيف" }} في", color = lilac); Text("همسات ♛", color = gold, fontSize = 28.sp, fontWeight = FontWeight.Bold) }
      Text("🪙 $coins", color = gold, modifier = Modifier.clickable { page = "wallet" }.padding(10.dp))
     }
     Spacer(Modifier.height(20.dp)); Text("الغرف الصوتية", color = gold, fontSize = 22.sp, fontWeight = FontWeight.Bold)
     Text("غرف تجريبية للعرض فقط", color = lilac, fontSize = 12.sp); Spacer(Modifier.height(10.dp))
     Surface(color = Color(0xFF352044), shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) { Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) { Text("👸🏻", fontSize = 42.sp); Spacer(Modifier.width(12.dp)); Column(Modifier.weight(1f)) { Text("كوني نجمة السهرة", color = gold, fontWeight = FontWeight.Bold); Text("هدايا • أصدقاء • لحظات مميزة", color = Color.White, fontSize = 11.sp) }; Text("💎", fontSize = 26.sp) } }
     Spacer(Modifier.height(10.dp))
     val rooms = listOf("مجلس همسات" to "🎙️ 8 متصلين", "سهرات على الهوا" to "🌙 16 متصلًا", "قهوة الأصدقاء" to "☕ 5 متصلين", "فضفضة همسات" to "💜 3 متصلين")
     LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
      items(rooms) { item ->
       Surface(color = Color(0xFF24172F), shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth().clickable { room = item.first; page = "room" }) {
        Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
         Text("🎧", fontSize = 25.sp); Spacer(Modifier.width(12.dp))
         Column(Modifier.weight(1f)) { Text(item.first, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp); Text(item.second, color = lilac, fontSize = 12.sp) }
         Text("دخول ←", color = gold)
        }
       }
      }
     }
     Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
      Text("🏠\nالرئيسية", color = gold, fontSize = 11.sp)
      Text("👥\nالأصدقاء", color = Color.White, fontSize = 11.sp, modifier = Modifier.clickable { page = "friends" })
      Text("🪙\nالمحفظة", color = Color.White, fontSize = 11.sp, modifier = Modifier.clickable { page = "wallet" })
      Text("👤\nحسابي", color = Color.White, fontSize = 11.sp, modifier = Modifier.clickable { page = "profile" })
      Text("🎁\nالهدايا", color = Color.White, fontSize = 11.sp, modifier = Modifier.clickable { page = "gifts" })
      Text("🎮\nالألعاب", color = Color.White, fontSize = 11.sp, modifier = Modifier.clickable { page = "games" })
     }
    }
    "room" -> Column(Modifier.fillMaxSize().padding(22.dp)) {
     Text("‹ رجوع", color = gold, modifier = Modifier.clickable { page = "home" })
     Spacer(Modifier.height(26.dp)); Text(room, color = gold, fontSize = 28.sp, fontWeight = FontWeight.Bold)
     Text("غرفة تجريبية — البث الصوتي غير مفعّل بعد", color = lilac); Spacer(Modifier.height(36.dp))
     repeat(6) { i -> Surface(color = Color(0xFF382249), shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) { Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) { Text(listOf("👑","🎙️","🎧","🌸","💜","✨")[i], fontSize = 24.sp); Spacer(Modifier.width(12.dp)); Text(if (i == 0) "مقعد المضيف" else "مقعد " + (i + 1), color = Color.White) } } }
     Text("🎁 هدايا سريعة: 🌹  💖  👑  💎", color = Color(0xFFFFB7D5), fontSize = 16.sp)
     Spacer(Modifier.weight(1f)); GoldButton("طلب المايك (تجريبي)") {}; Spacer(Modifier.height(10.dp)); GoldButton("🎁 الهدايا والمحفظة") { page = "wallet" }
    }
    "gifts" -> Column(Modifier.fillMaxSize().padding(20.dp)) {
     Text("‹ رجوع", color = gold, modifier = Modifier.clickable { page = "home" }); Spacer(Modifier.height(20.dp))
     Text("متجر الهدايا ✨", color = gold, fontSize = 28.sp, fontWeight = FontWeight.Bold)
     Text("هدايا للعرض التجريبي — الإرسال غير مفعّل", color = lilac, fontSize = 12.sp)
     Spacer(Modifier.height(14.dp))
     listOf("🌹  وردة ملكية  •  🪙 50", "💖  قلب الحب  •  🪙 100", "🎂  كعكة عيد  •  🪙 250", "🧸  دبدوب لطيف  •  🪙 300", "👑  تاج ذهبي  •  🪙 500", "💎  جوهرة فاخرة  •  🪙 750", "🚘  سيارة فاخرة  •  🪙 1000", "🦋  فراشة سحرية  •  🪙 150").forEach { item ->
      Surface(color = Color(0xFF24172F), shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp).clickable { notice = "هذه هدية تجريبية؛ الإرسال الفعلي سيضاف لاحقًا." }) { Text(item, color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(15.dp)) }
     }
     if (notice.isNotBlank()) Text(notice, color = lilac, fontSize = 12.sp)
    }
    "games" -> Column(Modifier.fillMaxSize().padding(20.dp)) {
     Text("‹ رجوع", color = gold, modifier = Modifier.clickable { page = "home" }); Spacer(Modifier.height(20.dp))
     Text("ركن الألعاب 🎮", color = gold, fontSize = 28.sp, fontWeight = FontWeight.Bold)
     Text("ألعاب السهرة — نماذج أولية", color = lilac, fontSize = 12.sp); Spacer(Modifier.height(12.dp))
     listOf("🎡  عجلة الحظ", "🧠  اختبر ذكاءك", "🎲  النرد المحظوظ", "🎁  صندوق المفاجآت", "💞  أسئلة الأصدقاء", "🏆  تحدي السهرة").forEach { item ->
      Surface(color = Color(0xFF24172F), shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp).clickable { notice = "اللعبة $item نموذج عرض، وسيتم تفعيلها لاحقًا." }) { Text(item + "     ← ابدئي", color = Color.White, fontSize = 17.sp, modifier = Modifier.padding(16.dp)) }
     }
     if (notice.isNotBlank()) Text(notice, color = lilac, fontSize = 12.sp)
    }
    "wallet" -> Column(Modifier.fillMaxSize().padding(22.dp)) {
     Text("‹ رجوع", color = gold, modifier = Modifier.clickable { page = "home" }); Spacer(Modifier.height(24.dp))
     Text("محفظة همسات", color = gold, fontSize = 28.sp, fontWeight = FontWeight.Bold)
     Spacer(Modifier.height(18.dp))
     Surface(color = Color(0xFF352044), shape = RoundedCornerShape(24.dp), modifier = Modifier.fillMaxWidth()) { Column(Modifier.padding(24.dp)) { Text("رصيد تجريبي", color = lilac); Text("🪙 $coins", color = gold, fontSize = 36.sp, fontWeight = FontWeight.Bold); Text("لا تمثل هذه العملات رصيدًا حقيقيًا", color = Color.White, fontSize = 12.sp) } }
     Spacer(Modifier.height(20.dp)); listOf("🎁 سجل الهدايا", "✨ مكافآت يومية (قريبًا)", "＋ شراء كوينز (غير مفعّل)", "📜 سجل الحركات").forEach { Surface(color = Color(0xFF24172F), shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)) { Text(it, color = Color.White, modifier = Modifier.padding(16.dp)) } }
    }
    "register" -> Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
     Text("إنشاء حساب", color = gold, fontSize = 30.sp, fontWeight = FontWeight.Bold)
     Spacer(Modifier.height(20.dp))
     HamsatField(username, { username = it }, "اسم المستخدم")
     Spacer(Modifier.height(12.dp)); HamsatField(email, { email = it }, "البريد الإلكتروني", type = KeyboardType.Email)
     Spacer(Modifier.height(12.dp)); HamsatField(password, { password = it }, "كلمة المرور", password = true)
     if (notice.isNotBlank()) { Spacer(Modifier.height(10.dp)); Text(notice, color = gold, fontSize = 13.sp) }
     Spacer(Modifier.height(20.dp)); GoldButton("متابعة تجريبية") {
      if (username.isBlank() || email.isBlank() || password.isBlank()) notice = "املئي الخانات الثلاث أولًا"
      else { notice = ""; page = "home" }
     }
     Spacer(Modifier.height(12.dp)); Text("تنبيه: هذا نموذج تجريبي، لا ينشئ حسابًا حقيقيًا ولا يحفظ البيانات على الإنترنت.", color = lilac, fontSize = 12.sp)
     Spacer(Modifier.height(18.dp)); Text("رجوع", color = Color.White, modifier = Modifier.clickable { page = "welcome" }.padding(8.dp))
    }
    "profile" -> SimplePage("حسابي", listOf("اسم المستخدم: ${username.ifBlank { "ضيف" }}", "نبذة شخصية", "إعدادات الحساب"), { page = "home" })
    else -> SimplePage("الأصدقاء والرسائل", listOf("قائمة الأصدقاء", "طلبات الصداقة", "الرسائل الخاصة غير مفعّلة بعد"), { page = "home" })
   }
  }
 }
}

@Composable
fun HamsatField(value: String, onValueChange: (String) -> Unit, label: String, password: Boolean = false, type: KeyboardType = KeyboardType.Text) {
 OutlinedTextField(
  value = value,
  onValueChange = onValueChange,
  label = { Text(label, color = lilac) },
  singleLine = true,
  modifier = Modifier.fillMaxWidth(),
  shape = RoundedCornerShape(14.dp),
  keyboardOptions = KeyboardOptions(keyboardType = if (password) KeyboardType.Password else type),
  visualTransformation = if (password) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
  colors = OutlinedTextFieldDefaults.colors(
   focusedTextColor = Color.White, unfocusedTextColor = Color.White,
   focusedBorderColor = gold, unfocusedBorderColor = lilac,
   cursorColor = gold, focusedLabelColor = gold, unfocusedLabelColor = lilac
  )
 )
}

@Composable
fun GoldButton(label: String, action: () -> Unit) {
 Button(onClick = action, modifier = Modifier.fillMaxWidth().height(54.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = gold, contentColor = night)) { Text(label, fontWeight = FontWeight.Bold, fontSize = 16.sp) }
}

@Composable
fun SimplePage(title: String, rows: List<String>, back: () -> Unit) {
 Column(Modifier.fillMaxSize().padding(22.dp)) {
  Text("‹ رجوع", color = gold, modifier = Modifier.clickable { back() })
  Spacer(Modifier.height(24.dp)); Text(title, color = gold, fontSize = 28.sp, fontWeight = FontWeight.Bold)
  Spacer(Modifier.height(16.dp))
  rows.forEach { Surface(color = Color(0xFF24172F), shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)) { Text(it, color = Color.White, modifier = Modifier.padding(16.dp)) } }
 }
}
