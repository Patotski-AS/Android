# **data binding**
## Создание
1. **Включить привязку данных *build.gradle(Module:app)*** :
    ```groovy
    android {
        ...
        buildFeatures {
            dataBinding true
        }
    }
    ```
2. **Преобразуйте макет в макет привязки данных:**
      Чтобы преобразовать обычный макет в макет привязки данных:
   * Оберните свой макет *layout* тегом
   * Добавить переменные макета (необязательно)
   * Добавить выражения макета (необязательно)

   ``` XML
   <?xml version="1.0" encoding="utf-8"?>
   <layout>
      <LinearLayout ... >
      ...
      </LinearLayout>
   </layout>
   ```
   или щелкните правой кнопкой мыши корневой элемент и выберите:
   Show Context Actions -> Convert to data binding layout:

2. **Связать данные**
    ```kotlin
    class StartActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        }
    }
    ```

##  Использование
1. **Используйте объект привязки:**
    ```kotlin
    binding.nicknameText.text = binding.nicknameEdit.text
    binding.nicknameEdit.visibility = View.GONE
    binding.doneButton.visibility = View.GONE
    binding.nicknameText.visibility = View.VISIBLE
    ```
    или

    ```kotlin
    binding.apply {
       nicknameText.text = nicknameEdit.text.toString()
       nicknameEdit.visibility = View.GONE
       doneButton.visibility = View.GONE    }
    ```


2. **Использование привязку данных для отображения данных**

* Создайте класс данных
    ```kotlin
    data class MyName(var name: String = "", var nickname: String = "")
    ```
* Добавьте данные в макет
    ```XML
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools">
        <data>
            <variable
                name="myName"
                type="com.example.android.aboutme.MyName" />
        </data>
        ...
    </layout>
    ```
* Cвязываем данные:
  *  Выше onCreate()создайте частную переменную
        ```kotlin
         private val myName: MyName = MyName("Aleks Haecky")
     ```
  * В onCreate()установите значение myNameпеременной в файле макета равным значению myNameпеременной, которую вы только что объявили. Вы не можете напрямую получить доступ к переменной в XML. Вам нужно получить к нему доступ через объект привязки.
     ```kotlin
    binding.myName = myName
     ```
* Переменные макета используются для написания выражений макета
    ```xml
    android:text="@{myName.name}"
    android:visibility="@{age < 13 ? View.GONE : View.VISIBLE}"
    android:transitionName='@{"image_" + id}'
  ```
3. **Наблюдение за данными (обновление данных)**
* Создаем ViewModel:
    ```kotlin
  class Test : ViewModel() {
    private val _text = MutableLiveData(0)

    val text : LiveData<Int> = _text

    fun addPicture() {
        _text.value = _text.value?.inc()
     }
  }
    ```
* установливаем владельца жизненного цикла в binding объекте (lifecycleOwner):
    ```kotlin
  class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val test by lazy {
        ViewModelProviders.of(this).get(Test::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner=this
        binding.test = test
        }
    }
    ```
* Связываем данные ViewModel с макетом
    ```XML
          <TextView
            android:text="@{test.text.toString()}"
            />
    ```
4. **Использование адаптеров привязки для создания настраиваемых атрибутов.**
*  Функции - адаптеры создаем в файле  BindingAdapters.kt
     ```kotlin
    @BindingAdapter("bind:imgRes")
    fun setImageResource(imageView: ImageView, resource: Int) {
        when (resource) {
            0 -> imageView.setImageResource(android.R.drawable.ic_delete)
            10 -> imageView.setImageResource(R.mipmap.ic_launcher_foreground)
            else -> imageView.setImageResource(android.R.drawable.btn_star_big_on)
        }
    }
    ```
* Связываем данные с макетом
    ```xml
           <ImageView
            android:id="@+id/imageView"
            app:imgRes="@{test.text}"
            ... />
    ```
* Адаптеры с несколькими параметрами
    ```kotlin
    @BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)
    fun setProgress(progressBar: ProgressBar, likes: Int, max: Int) {
       progressBar.progress = (likes * max / 5).coerceAtMost(max)
    }
    ```
    ```xml
    <ProgressBar
                android:id="@+id/progressBar"
                app:hideIfZero="@{viewmodel.likes}"
                app:progressScaled="@{viewmodel.likes}"
                android:max="@{100}"
    ...  
    ```
5. **Создать объект привязки в Fragments:**
  * В onCreateView()методе фраменты создайте binding переменную ( val binding).
  * Чтобы расширить представление фрагмента, вызовите DataBindingUtil.inflate()метод Binding объекта фрагмента.
  * Передайте в DataBindingUtil.inflate метод четыре аргумента :
    * inflater, который LayoutInflater используется для расширения макета привязки.
    * Ресурс макета XML для раздуваемого макета. Используйте один из уже определенных макетов R.layout.fragment_title.
    * container для родителя ViewGroup. (Этот   параметр не является обязательным.)
    * false по attachToParent стоимости.
 * Назначьте привязку, которая DataBindingUtil.inflate возвращается к binding переменной.
 * Возврат binding.root из метода, который содержит завышенное представление.
    ```kotlin
   class FindFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil
            .inflate<FragmentFindBinding>(
                inflater
                ,R.layout.fragment_find
                ,container
                ,false)
        return binding.root
      }
    }  
    ```



  # Ссылки
  * [Основы привязки данных](https://developer.android.com/codelabs/kotlin-android-training-data-binding-basics/index.html?authuser=1#0)
  *  [Привязка данных в Android](https://developer.android.com/codelabs/android-databinding?authuser=1#0)
  *  [Библиотека привязки данных](https://developer.android.com/topic/libraries/data-binding/?authuser=1)


