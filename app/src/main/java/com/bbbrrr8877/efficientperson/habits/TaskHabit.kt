package com.bbbrrr8877.efficientperson.habits

/**

General logic:
+ Создание привычки
- Изменение положения привычки для расставления пользователем приоритета
+ Поле isDone (привычка выполнена) сбрасывается на false раз в сутки в 00:00. (WorkManager)
- При удалении привычки у привычки запускаетсятаймер на 5 дней, что бы пользователь мог одуматься
- Есть возможность восстановления привычки, если таймер еще тикает
+ При повароте экрана: экран делится на 2 части. Часть со списком и деталями

Главный экран:
Состояние
- Когда нет ни одной привычки (пустой экран), видно надпись: "Создайте привычку"
- Список всех привычек:
- У удаленной привычки видно таймер до удаления
+ Титул, чек бокс (сделал или нет)
+ Кнопка создания новой привычки
- Верхний тулбар с иконкой-пояснением
Поведение
- Клик на иконку-пояснение: выплывает текст пояснения как работать с приложением
+ Клик на кнопку создать привычку переходит на фрагмент создания новой привычки (HabitDetailsFragment)
+ Клик назад закрывает приложение
+ Клик на привычку переходит на фрагмент редактирования привычки (HabitDetailsFragment)
+ Клик на чек бокс меняет состояние конкретной привыки, т.е. меняет макет (цвет фона и текста)
- Удаленная привычка пермещается вниз списка
-
HabitDetailsFragment
State:
- У удаленной привычки видно таймер до удаления
+ Фон по-умолчанию бледно зеленый (хорошая привычки)
+ Эдит текст для титула
+ Эдит текст для описания
+ Свитч кнопка для выбора плохой или хорошей привычки
+ При переключении на плохую привычку фон меняется на бледно красный и обратно
+ Чек бокс для выбора (сделал или нет)
+ Кнопка сохранить
Behaviour
+ Ввод текста в эдит текст титула.
+ Ввод текста в эдит текст описания
+ Свитч кнопка для выбора качества привычки меняет фон фрагмента на бледно красный (плохая)
+ Свитч кнопка для выбора качества привычки меняет фон фрагмента на бледно зеленый (хорошая)
+ В эдит моде Свитч кнопка отсутствует
+ Чек бокс отмечает привычка сделана или нет
+ Кнопка сохранить сохраняет привычку в базе данных и переводит на главный экран со списком привычек
+ Кнопка назад возвращает на главный экран со списком привычек


 Tasks:
 + Сдулать градиент айтемов на ресайклере
 - Сделать айтемы поменьше
 */