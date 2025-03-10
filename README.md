# Описание тех задания
Дан текстовый файл с набором данных по строчно с разделителем «;»\
В файле **могут** содержаться данные в следующем порядке:
- Фамилия Имя Отчество (разделенные пробелом)
- Дата рождения в формате dd.MM.yyyy
- Номер телефона (может быть в любом формате, но валидным считается у которого имеется 10 символов без учета кода. Примеры валидных: +7(918)-00-00-00-0, 8(918)-00-00-00-0, 918-00-00-00-0, 9180000000 и т.д.)
- Заработная плата в месяц в **рублях**\
**Данные необходимо отвалидировать, т. к. могут присутствовать невалидные!**\
Набор вышеописанных данных характеризует объект User.\
Необходимо считать файл и записать данные в **объекты** User\
Вывести на консоль:\
- Собранные все объекты User с набором **имеющихся** данных
- Кол-во мужчин, кол-во женщин (требуется определить пол самостоятельно)
- Кол-во пользователей с возрастом **больше** 25 лет
- **Среднюю** заработанную плату
- Кол-во женщин, у которых **присутствует** **валидный** номер телефона
- Вывести пользователей, у которых имеются **невалидные** данные, с указанием полей не прошедших валидацию.
