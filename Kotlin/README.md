
BrainTrainer/
Сохранение данных приложения используя SharedPreferences
val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext) val max = preferences.getInt("max",0) if (countRight>=max){ preferences.edit().putInt("max",countRight).apply() }

Notes/
RecyclerView