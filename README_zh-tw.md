今天要來說明Compose中的導覽欄位 **`NavigationBar`**

## Navigation
### NavigationBar內部程式碼
以下可以看到NavigationBar中有六個參數，當中可能最常碰到的除了modifier以外，我自己是比較常去使用`containerColor`和`contentColor`這兩個改變外觀顏色的參數，其他的參數我自己是不太常使用到。

- 這邊就稍微帶一下 **`NavigationBar`** 內部說明的用處吧
    - **modifier** - 若要套用於此導覽列的修改器
    - **containerColor** - 用於此導覽列背景的顏色。 使用 `Color.Transparent` 沒有顏色。
    - **contentColor** - 此導覽列中內容的首選顏色。 預設為`containerColor` 的匹配內容顏色，或者如果`containerColor` 不是主題中的顏色，則預設為當前的`LocalContentColor`。
    - **tonalElevation** - 當`containerColor`為`ColorScheme.surface`時，半透明的原色疊加層將應用在容器的頂部。 較高的色調高度值將導致淺色主題中顏色較深，深色主題中顏色較淺。
    - **windowInsets** - 導覽列的視窗插入。
    - **content** - 此導覽列的內容，通常為 3-5 個 **`NavigationBarItems`**
- 撰寫的格式
```kotlin
fun NavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .height(NavigationBarHeight)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(NavigationBarItemHorizontalPadding),
            content = content
        )
    }
}
```
### NavigationBarItem的內部程式碼說明
這個 **`NavigationBarItem`** 是包在 **`NavigationBar`** 中使用的，無法直接在外面使用，以下是按下`Alt`+`滑鼠左鍵`進入查看內部程式碼的資訊。
```kotlin
@Composable
fun RowScope.NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    // ...其他內部的function及Item的預設排版
}
```
> Params:
selected - whether this item is selected
onClick - called when this item is clicked
icon - icon for this item, typically an Icon
modifier - the Modifier to be applied to this item
enabled - controls the enabled state of this item. When false, this component will not respond to user input, and it will appear visually disabled and disabled to accessibility services.
label - optional text label for this item
alwaysShowLabel - whether to always show the label for this item. If false, the label will only be shown when this item is selected.
colors - NavigationBarItemColors that will be used to resolve the colors used for this item in different states. See NavigationBarItemDefaults.colors.
interactionSource - the MutableInteractionSource representing the stream of Interactions for this item. You can create and pass in your own remembered instance to observe Interactions and customize the appearance / behavior of this item in different states.
- 參數列表意思：
    - **selected** - 此項目是否被選中，若是true則是常駐Icon為選中的樣式，下圖的 **`Search`** 是`true`，反之 **`Profile`** 是`false`狀態。
    ![selected的選擇與否對比](https://github.com/windgod31202/ComposeTraining_NavigationBar/assets/88834703/f5fe0e5a-66ac-4047-a472-43f1e23a7381)
    - **onClick** - 點擊此項目時調用，與Button類的觸發功能一致。
    - **icon** - 該專案的圖標，通常是一個 Icon。
    - **modifier** - 要套用於此項目的修改器。
    - **enabled** - 控制此項目的啟用狀態。 當為 `false` 時，該元件將不會響應使用者輸入，並且它將顯示為視覺上停用並且對輔助服務停用。
    - **label** - 該項目的可選文字標籤
    - **alwaysShowLabel** - 是否始終顯示該項目的標籤。 如果為 false，則僅在選擇此項目時才會顯示標籤。
    - **color** - **`NavigationBarItemColors`** 將用於解析該項目在不同狀態下使用的顏色。 請參閱 **`NavigationBarItemDefaults.colors`** 。
### Navigation的撰寫方式
```kotlin
NavigationBar(
    modifier = modifier,
    contentColor = Color.White,
    containerColor = Color.Magenta
) {
    // 複數的Item排列成約莫3~5個Item列
    NavigationBarItem(...)
    NavigationBarItem(...)
    NavigationBarItem(...)
}
```
### NavigationBarItem的撰寫方式
```kotlin
NavigationBar(
    modifier = modifier,
    contentColor = Color.White,
    containerColor = Color.Magenta
) {
    NavigationBarItem(
        icon = {
            // 設定Icon圖示
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null
            )
        },
        label = {
            Text(
                text = "顯示文字"
            )
        },
        selected = true,
        onClick = {
            // ...點擊事件
        },
        colors = NavigationBarItemDefaults.colors(
            // 選擇時的文字與Icon顏色變化
            selectedIconColor = Color.Green,
            selectedTextColor = Color.Green,
            // 非選擇時的文字與Icon顏色變化
            unselectedIconColor = Color.Red,
            unselectedTextColor = Color.Red
        )
    )
}
```
- 設定的顏色變化
這邊可以看到前兩個因為已設定selected參數為true所以會常駐綠色，而後兩個則是false，所以會顯示為紅色。
![顏色對比](https://github.com/windgod31202/ComposeTraining_NavigationBar/assets/88834703/db657227-7d5a-4baa-b5ef-956028ca1ff7)


### 導航欄位全部程式碼
```kotlin
const val TAG : String = "SootheBottomNavigation"
// 底部導覽條
@Composable
private fun BottomNavigation(modifier: Modifier = Modifier, context:Context) {
    val resources = context.resources
    NavigationBar(
        modifier = modifier,
        contentColor = Color.White,
        containerColor = Color.Magenta
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {
                Log.e(TAG, "SootheBottomNavigation: Home" )
                Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.Red,
                unselectedTextColor = Color.Red
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_Search)
                )
            },
            selected = true,
            onClick = {
                Log.e(TAG, "SootheBottomNavigation: Search" )
                Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.Red,
                unselectedTextColor = Color.Red
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = false,
            onClick = {
                Log.e(TAG, "SootheBottomNavigation: Account" )
                Toast.makeText(context, "Account", Toast.LENGTH_SHORT).show()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.Red,
                unselectedTextColor = Color.Red
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_Settings)
                )
            },
            selected = false,
            onClick = {
                Log.e(TAG, "SootheBottomNavigation: Settings" )
                Toast.makeText(context, "Setting", Toast.LENGTH_SHORT).show()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.Red,
                unselectedTextColor = Color.Red
            )
        )
    }
}
```
## Preview&執行程式碼
這邊可以看到我們不需要再將畫面事先組合排版，這邊直接使用`Scaffold`的方法來填入`bottomBar`底部欄位使其為剛剛所設定的底部導航欄的**function**。

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_MaterialTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 就是下面這個方法引入並padding前天組合而成的版面。
                    // 使用Scaffold將底部欄位的參數設為剛剛建立的導覽條函式
                    Scaffold(
                        bottomBar = { BottomNavigation(context = this) }
                    ){padding ->
                        GreetingBackground()
                    }
                }
            }
        }
    }
}

// 預覽
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val context = LocalContext.current // 獲取context在Preview中顯示
    Compose_MaterialTestTheme {
        Scaffold(
            bottomBar = { BottomNavigation(context = context) }
        ){padding ->
            GreetingBackground()
        }
    }
}
```
- 這邊再沿用前面第2天的程式碼並結合來看看導航欄的結果預覽顯示
![整體預覽結果](https://github.com/windgod31202/ComposeTraining_NavigationBar/assets/88834703/a88356f9-dfbd-444c-abeb-c69c368a76b8)

- 導航欄點擊結果
![導航欄點擊結果](https://github.com/windgod31202/ComposeTraining_NavigationBar/assets/88834703/61f6c06c-8124-4bc9-88d1-f0ff177097b2)
![導航欄點擊結果2](https://github.com/windgod31202/ComposeTraining_NavigationBar/assets/88834703/23ec70df-b346-472f-a28d-19722a161b2a)
![導航欄點擊結果3](https://github.com/windgod31202/ComposeTraining_NavigationBar/assets/88834703/7cf974af-e552-42b2-95a5-a372a99b1acb)


以上是今天在Compose中使用底部導航欄的使用說明以及導航欄以及導航欄Icon的參數說明。
