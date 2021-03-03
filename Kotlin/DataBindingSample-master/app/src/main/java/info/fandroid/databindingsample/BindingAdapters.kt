package info.fandroid.databindingsample

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter


@BindingAdapter("app:hideOfZero")
fun hideOfZero(view: View, number: Int) {
    view.visibility = if (number == 0) View.GONE else View.VISIBLE
}

/**
 * Для значения прогресса мы будем использовать адаптер привязки,
 * который принимает максимальное значение и количество лайков.
 * Этот адаптер привязки не используется, если какие-либо атрибуты отсутствуют.
 * Это определяется во время компиляции. Теперь метод принимает 3 параметра
 * (представление, к которому он применяется + количество атрибутов, определенных в аннотации).
 *
 * Параметр requireAll определяет , когда используется связывающий адаптер:
 * Если true все элементы должны присутствовать в определении XML.
 * Если false отсутствующие атрибуты будут иметь значение null, false, если логическое значение, или 0, если примитивы.
 */
@BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)
fun setProgress(progressBar: ProgressBar, likes: Int, max: Int) {
    progressBar.progress = (likes * max / 20).coerceAtMost(max)
}
