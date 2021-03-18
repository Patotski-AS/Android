package com.example.android.notes

import android.provider.BaseColumns
import java.time.DayOfWeek

class NotesContract {
    companion object{
        class NotesEntry : BaseColumns{
            companion object{
                const val TABLE_NAME = "notes"
                const val COLUMN_TITLE = "title"
                const val COLUMN_DESCRIPTION = "description"
                const val COLUMN_DAY_OF_WEEK = "day_of_week"
                const val COLUMN_PRIORITY = "priority"

                const val TYPE_TEXT = "TEXT"
                const val TYPE_INTEGER = "INTEGER"

                /**
                 *  CREATE TABLE - создать новую таблицу
                 *  IF NOT EXISTS - если она не существует
                 *  AUTOINCREMENT - автозаполнение (дает BaseColumns)
                 *  далее в скобках, через запятую, перечень столбцов с типами
                 */
                const val CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS $TABLE_NAME(" +
                        "_ID $TYPE_INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COLUMN_TITLE $TYPE_TEXT, " +
                        "$COLUMN_DESCRIPTION $TYPE_TEXT, " +
                        "$COLUMN_DAY_OF_WEEK $TYPE_TEXT, " +
                        "$COLUMN_PRIORITY $TYPE_INTEGER)"

                /**
                 * DROP TABLE - удалить таблицу
                 * IF NOT EXISTS - если она существует
                 */
                const val DROP_COMMAND = "DROP TABLE OF EXISTS $TABLE_NAME"

            }
        }
    }
}