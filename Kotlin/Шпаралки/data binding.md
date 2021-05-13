# data binding
1. **Добавляем в *build.gradle(Module:app)*** :
```groovy
android {
    ...
    buildFeatures {
        dataBinding true
    }
}
```
2. **Изменить файл макета:**
``` XML
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable
            name="test"
            type="com.example.android.movies.test.Test" />
    </data>
...
...
...
</layout>
```
3. **Связать данные**
```kotlin
class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
```



