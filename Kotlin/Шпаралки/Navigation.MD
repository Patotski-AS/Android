
# [Компоненты навигации](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/index.html?authuser=1#11)


1. **Добавить [зависимости](https://developer.android.com/jetpack/androidx/releases/navigation?authuser=1#declaring_dependencies) в *build.gradle***
```groovy
dependencies {
    def nav_version = "2.3.5"
    ...
    // Kotlin Navigation
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
}
```
2. **Добавьте в проект навигационный граф:**
- щелкните правой кнопкой мыши папку res и выберите **New** > **Android Resource File**
- В диалоговом окне **New Resource File** выберите **Navigation** в качестве **Resource type**
- введите имя файла
- Убедитесь, что поле **Chosen qualifiers** пусто, и нажмите **ОК** .

3. **создание навигацонного графа (NavHostFragment).**
- добавляем в activity_main.xml:
- Добавьте app:navGraph атрибут и установите его для ресурса графа навигации, то есть @navigation/navigation.
- Добавьте app:defaultNavHost атрибут и установите для него значение "true".
 Теперь этот хост навигации является хостом по умолчанию и будет перехватывать системную кнопку «Назад».
    ```xml
    <fragment
                android:id="@+id/myNavHostFragment"
                android:name="androidx.navigation.fragment.NavHostFragment"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:navGraph="@navigation/navigation"
                app:defaultNavHost="true" />
    ```
4.  **добавляем фрагменты в навигационный граф**
- Откройте файл navigation.xml из папки ресурсов навигации .
- В навигационном редакторе, нажмите **New Destination**. Появится список фрагментов и действий.
- Выбрать фрагмент с которого запускаются приложение

5. **Соединяем фраменты, появляется атрибут типа *action_Fragment1_to_Fragment2***

    ![Картинка](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/66b05cb09acab955.png?authuser=1)

6. **Переход между фраментами по нажатию кнопки:**
    ```kotlin
       binding.buttonFindFragment.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_startFragment_to_findFragment)
        }
    ```
7. **Изменение назначения кнопки "Назад"**

    ![](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/19d528c831bc36e1.png)  
    ![Картинка](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/7a65cf0c73c9904d.png)

    |                                                                                             | popUpTo        | popUpToInclusive |
    |:--------------------------------------------------------------------------------------------|:---------------|:-----------------|
    | удаляет места назначения до game_fragment, но оставляет first_fragment в заднем стеке      | game_fragment | false            |
    | удаляет все назначения до game_fragment включительно из заднего стека | game_fragment | true             |
    | действие удаляет все назначения приложения из заднего стека. Кнопка «Назад» полностью выводит пользователя из приложения.| start_fragment | true             |


8.  **Добавление кнопки "Вверх" на панель приложения**

- В MainActivity.kt, внутри onCreate() метода добавьте код для поиска объекта контроллера навигации:
    ```kotlin
  val navController = this.findNavController(R.id.myNavHostFragment)  
  ```
- затем код для привязки контроллера навигации к панели приложения:
    ```kotlin
  NavigationUI.setupActionBarWithNavController(this,navController)  
  ```
- После onCreate() метода переопределите onSupportNavigateUp() метод для вызова navigateUp() в контроллере навигации:
    ```kotlin
  override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }  
  ```
9.    **Добавление меню опций**
     ![](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/4cdd17fa43bfbe6.png)

* Добавьте ресурс меню параметров:
     * На панели Android Studio Project щелкните правой кнопкой мыши папку **res** и выберите **New> Android Resource File** .
     * В диалоговом окне **New Resource File**  назовите файл *options_men*u
     * Выберите **Menu** в качестве **Resource type** и нажмите **ОК**
     * Откройте **options_menu.xml** файл из папки **res> menu** и щелкните вкладку **Design**, чтобы увидеть редактор макета.
     * Из панели **Palette** перетащите элемент **Menu Item** и отпустите его в любом месте панели редактора дизайна
     * В предварительном просмотре или в дереве компонентов щелкните элемент меню, чтобы отобразить его атрибуты.
     * Установите для **id** -> id фрагмента на который переходим и в **title** - имя в меню
* Добавьте обработчик onClick
  * Откройте StartFragment и внутри onCreateView() метода перед return вызовом вызовите setHasOptionsMenu() метод и передайте его true.
    ```kotlin
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?)
        : View? {
                  ...
                  setHasOptionsMenu(true)
                  return binding.root
                }
    ```
  * После onCreateView() метода переопределите onCreateOptionsMenu()
    метод. В методе добавьте меню параметров и увеличьте файл ресурсов
    меню.
     ```kotlin
     override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
             super.onCreateOptionsMenu(menu, inflater)
             inflater.inflate(R.menu.options_menu, menu)
     }
    ```
  * Переопределите onOptionsItemSelected()метод, чтобы выполнить
    соответствующее действие при касании пункта меню. В этом случае
    действие заключается в переходе к фрагменту, который совпадает idс
    выбранным пунктом меню.
     ```kotlin
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
     return NavigationUI.
            onNavDestinationSelected(item,requireView().findNavController())
            || super.onOptionsItemSelected(item)
    } 
    ```
10. **Добавление панели навиации**
* добавляем зависимость в *build.gradle*
     ```groovy
     dependencies {
     ...
     implementation "com.google.android.material:material:$version"
     ...
      }
     ```
* Создание панели навигации
     * Создать макет отображения панели навигации(layout/nav_header)
     * Добавьте ресурс меню параметров:
          * На панели Android Studio Project щелкните правой кнопкой мыши папку **res** и выберите **New> Android Resource File** .
          * В диалоговом окне **New Resource File**  назовите файл *navdrawer_menu*
          * Выберите **Menu** в качестве **Resource type** и нажмите **ОК**
          * Откройте **navdrawer_menu.xml** файл из папки **res> menu** и щелкните вкладку **Design**, чтобы увидеть редактор макета.
          * Из панели **Palette** перетащите элемент **Menu Item** в **Component Tree**
          * В предварительном просмотре или в дереве компонентов щелкните элемент меню, чтобы отобразить его атрибуты.
          * Установите для **id** -> id фрагмента на который переходим и в **title** - имя в меню, **icon** - картинку менюшки
     * В **activity_main.xml** добавить **DrawerLayout** в качестве корневого представления и код **NavigationView**
       ```<?xml version="1.0" encoding="utf-8"?>
           <layout xmlns:android="http://schemas.android.com/apk/res/android"
               xmlns:app="http://schemas.android.com/apk/res-auto"
               xmlns:tools="http://schemas.android.com/tools">
               <data></data>
               <androidx.drawerlayout.widget.DrawerLayout
                   android:id="@+id/drawerLayout"
                   android:layout_width="match_parent"
                   android:layout_height="match_parent">
           
                   <androidx.constraintlayout.widget.ConstraintLayout
                      ... 
                   </androidx.constraintlayout.widget.ConstraintLayout>
                   
                   <com.google.android.material.navigation.NavigationView
                       android:id="@+id/navView"
                       android:layout_width="wrap_content"
                       android:layout_height="match_parent"
                       android:layout_gravity="start"
                       app:headerLayout="@layout/nav_header"
                       app:menu="@menu/navdrawer_menu" />
                       app:navGraph="@navigation/navigation" />
               </androidx.drawerlayout.widget.DrawerLayout>
       </layout>    
       ```
* Отображение панели "Сдвиом слева"
    * Откройте MainActivity.kt файл Kotlin.
    * В onCreate() добавьте код, который позволяет пользователю отображать панель навигации:
         ```kotlin
             NavigationUI.setupWithNavController(binding.navView, navController)    
         ```
* Отображение панели с помощью кнопки "ящика" ![](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/7277f85db3a1ad13.png)
  * В MainActivity.kt файле Kotlin добавьте lateinit drawerLayout переменную-член для представления макета ящика:
    ```kotlin
     private lateinit var drawerLayout: DrawerLayout
    ```
  * Внутри onCreate()метода инициализировать drawerLayout после инициализации binding переменной.
    ```kotlin
    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
                R.layout.activity_main)

    drawerLayout = binding.drawerLayout
    ```
  * Добавьте в drawerLayout метод третий параметр setupActionBarWithNavController()
    ```kotlin
     NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    ```
  * Отредактируйте return в методе onSupportNavigateUp()
    ```kotlin
      override fun onSupportNavigateUp(): Boolean {
          val navController = this.findNavController(R.id.myNavHostFragment)
          return NavigationUI.navigateUp(navController, drawerLayout)
      }
    ```
