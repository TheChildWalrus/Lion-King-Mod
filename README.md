> [!CAUTION]
> Проект заморожен. Инструментарий разработки будет поддерживаться в актуальном и рабочем состоянии, но исходный код
> редактироваться не будет.

Код мода "Lion King" для Minecraft 1.6.4. Перенесён с Forge на Voldeloom и пригоден к разработке на текущий
момент.

## Основная информация

Репозиторий содержит три ветки.

* main - исправленный оригинальный код (содержит четыре исправления).
    * Закомментирован фрагмент кода в LKTickHandlerClient, который перестал работать из-за инкапсуляции полей.
    * Закомментирован фрагмент кода в LKItemGroundRhinoHorn, который перестал работать из-за инкапсуляции полей.
    * Исправлена ошибка с файлом LKRenderBug (файл был перемещён из common в client).
    * Исправлена ошибка с файлом LKRenderSkeletalHyenaHead (файл был перемещён из common в client).
* refact-cosmetic - исправленный код, прошедший косметический рефакторинг (никаких функциональных изменений).
* refact-full - исправленный код, прошедший полный рефакторинг (с функциональными изменениями).
