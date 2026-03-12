package com.suplz.vkeducation.applist

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.suplz.vkeducation.mapper.toUiString
import com.suplz.vkeducation.model.AppSummary
import com.suplz.vkeducation.model.Category
import com.suplz.vkeducation.ui.theme.RuStoreBlue
import com.suplz.vkeducation.ui.theme.VKEducationTheme

@Composable
fun AppListScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val apps = remember { getMockApps() }

    Scaffold(
        modifier = modifier,
        containerColor = RuStoreBlue,
        topBar = { AppListTopBar() }

    ) { innerPadding ->
        AppListContent(
            apps,
            innerPadding,
            onClick
        )
    }
}

// В будущем заменим этот метод на вызов API.
private fun getMockApps(): List<AppSummary> {
    return listOf(
        AppSummary(
            name = "Авито – услуги, работа, авто",
            category = Category.APP.toUiString(),
            iconUrl = "https://static.rustore.ru/imgproxy/7HOcGO9T6TglJ15g7aDv0CiensvQL4TYOQvtE46lR6E/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/1/27/d8/apk/2688703/content/ICON/ea0c42d8-934f-41a6-a3da-89798736f888.png@webp",
            description = "Объявления: услуги, товары, недвижимость, отели и гостиницы для путешествия."
        ),
        AppSummary(
            name = "VK Видео: кино, сериалы, шоу",
            category = Category.APP.toUiString(),
            iconUrl = "https://static.rustore.ru/imgproxy/-fb8TfFrlYjwu7wYCEPuKsm4wWkTLBTx_0_a9GpeKfc/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/2027823295/content/ICON/97486018-5785-424d-9cab-4fe496d27c76.png@webp",
            description = "Смотри шоу, мультики, ТВ, сериалы, спортивные трансляции и фильмы бесплатно."
        ),
        AppSummary(
            name = "ВКонтакте: чаты, видео, музыка",
            category = Category.APP.toUiString(),
            iconUrl = "https://static.rustore.ru/imgproxy/PTo8g-Giv9VHYo7_Rwxw_1wC07KtDM7eSJgAfMlv53s/preset:web_app_icon_160/plain/https://static.rustore.ru/3f3d7180-6eb9-45ad-8706-f467c6dcf82a@webp",
            description = "ВКонтакте — это общение, мессенджер и звонки, знакомства и игры, видео и музыка"
        ),
        AppSummary(
            name = "СберБанк Онлайн",
            category = Category.APP.toUiString(),
            iconUrl = "https://static.rustore.ru/imgproxy/qriFjN8OV6VBF4CCbWcxPm7SL0Y0YtMfxTeJSzWZ1Rc/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/462271/content/ICON/f1b3c68a-b734-48ce-b62f-490208d3fa0e.png@webp",
            description = "Больше чем банк"
        ),
        AppSummary(
            name = "OZON: товары, одежда, билеты",
            category = Category.APP.toUiString(),
            iconUrl = "https://static.rustore.ru/imgproxy/HDLPr1ElUH-tby31I3reMDXqNgb1cIxLMQQhGfLlZOA/preset:web_app_icon_160/plain/https://static.rustore.ru/2025/12/22/22/apk/514239/content/ICON/76a9e830-f493-4661-b55f-43a30a73408b.png@webp",
            description = "Покупайте миллионы товаров со скидками и быстрой доставкой от одного дня!"
        ),
        AppSummary(
            name = "ВТБ Онлайн",
            category = Category.APP.toUiString(),
            iconUrl = "https://static.rustore.ru/imgproxy/LguGaWMo4tYwKcTnnlWnDSSFDyUX1B_kxPoWNteyPeI/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/404415/content/ICON/867b6386-687d-4d6f-a686-4ee98c04b859.png@webp",
            description = "ВТБ Онлайн – в лидерах среди онлайн-банков по версии Markswebb"
        ),
        AppSummary(
            name = "MAX: общение, звонки, сервисы",
            category = Category.APP.toUiString(),
            iconUrl = "https://static.rustore.ru/imgproxy/JNQXxouJcssXg2hd8FaJX6Bj9OXC2l0Xd2KBtHWxo6c/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/1/24/6f/apk/2063618637/content/ICON/0564e0a3-a2be-414e-8fec-55257fedea8e.png@webp",
            description = "Все в одном приложении: мессенджер, звонки, чат-боты, мини-приложения и другое"
        ),
        AppSummary(
            name = "2ГИС: навигатор, транспорт",
            category = Category.APP.toUiString(),
            iconUrl = "https://static.rustore.ru/imgproxy/OYKFMgpckkNGCd1jKcnHKojgnsMJ09qjfr9TYyjkyx4/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/2/2/1e/apk/260799/content/ICON/8e439c58-11cf-4e0a-a3dc-9596eda08cfd.png@webp",
            description = "Геопозиция близких. Автомобильный и пешеходный навигатор. Расписание автобусов."
        ),
        AppSummary(
            name = "VK Музыка: аудиокниги, подкасты",
            category = Category.APP.toUiString(),
            iconUrl = "https://static.rustore.ru/imgproxy/UVOR3yUWAMokAu1Sx_GRohYCPrcEvWreCRkJJ3x_jGk/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/2/10/ae/apk/313279/content/ICON/62b9b0e2-0e7c-4855-a70d-9d6811fa3b4c.jpg@webp",
            description = "Музыка без интернета и рекламы, аудиокниги, песни, подкасты и радио"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AppListScreenPreview() {
    VKEducationTheme {
        AppListScreen(
            onClick = {}
        )
    }
}


