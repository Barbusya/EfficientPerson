package com.bbbrrr8877.efficientperson

/**
Главный экран:
Состояние
- Список всех привычек:
Титул, чек бокс (сделал или нет)
- Кнопка создания новой привычки

Поведение
- Клик на кнопку создать привычку переходит на фрагмент создания новой привычки (HabitDetailsFragment)
- Клик назад закрывает приложение
- Клик на привычку переходит на фрагмент редактирования привычки (HabitDetailsFragment)
- Клик на чек бокс меняет состояние конкретной привыки, т.е. меняет макет (цвет фона и текста)
-

HabitDetailsFragment
State:
- Фон по-умолчанию бледно зеленый (хорошая привычки)
- Едит текст для титула
- Едит текст для описания
- Радио кнопка для выбора плохой или хорошей привычки
- Чек бокс для выбора (сделал или нет)
- Кнопка сохранить

Behaviour
- Ввод текста в едит текст титула.
- Ввод текста в удит текст описания
- Радио кнопка для выбора качества привычки меняет фон фрагмента на бледно красный (плохая)
- Радио кнопка для выбора качества привычки меняет фон фрагмента на бледно зеленый (хорошая)
- Чек бокс отмечает привычка сделана или нет
- Кнопка сохранить сохраняет привычку в базе данных и переводит на главный экран со списком привычек
- Кнопка назад возвращает на главный экран со списком привычек
 */