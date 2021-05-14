
1. Создание пустого фрагмента
    ```
    File > New > Fragment > Fragment (Blank).
    ```
2. Удалить все кроме:
    ```kotlin
   override fun onCreateView(inflater: LayoutInflater,
                                container: ViewGroup?,
                         savedInstanceState: Bundle?): View? {
    }
   ```
3. Создать объект привязки:
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
4. Добавление фрагмента в основной файл макета:
    ```xml
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">
    
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical">
                <fragment
                    android:id="@+id/titleFragment"
                    android:name="com.example.android.navigation.TitleFragment"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    />
            </LinearLayout>
    
    </layout>
    ```
