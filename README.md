# BINLookupper
Реализовано Android-приложение, в котором:
1. Пользователь вводит [BIN](https://www.banki.ru/wikibank/bankovskiy_identifikatsionnyiy_nomer/) банковской карты и видит всю доступную информацию о нём, загруженную с [BINLIST.NET](https://binlist.net/)
2. История предыдущих запросов выводится списком
3. История предыдущих запросов не теряется при перезапуске приложения
4. Нажатие на URL банка, телефон банка, координаты страны отправляет пользователя в приложение, которое может обработать эти данные

## Архитектура
Реализованное приложение имеет следующую архитектуру:
![image](https://user-images.githubusercontent.com/36191580/209639240-b857b788-97cb-4c35-96a1-e1a0ace6e89c.png)

## Библиотеки
В проекте были использованы следующие библиотеки:
- Retrofit 2.9.0 в качестве REST клиента
- Room 2.4.3 для работы с sqlite (сохранение истории поиска)
- Koin 3.1.2 для dependency injection
- Jetpack Navigation 2.5.3 для навигации между фрагментами

## Скриншоты
![image](https://user-images.githubusercontent.com/36191580/209640382-c731d222-b6f7-41ba-89ed-be419ead2521.png)
![image](https://user-images.githubusercontent.com/36191580/209644412-fcb83e87-7e81-4be3-ac78-b8552f95faa3.png)
![image](https://user-images.githubusercontent.com/36191580/209640518-e828eda0-264f-4276-b6fd-06e092fb8f13.png)
![image](https://user-images.githubusercontent.com/36191580/209644520-a5dd9eb9-b211-4084-b66c-96ef9fbe3cf7.png)
![image](https://user-images.githubusercontent.com/36191580/209644924-d4bd18e3-2d5b-4bce-9e90-06581de94277.png)
![image](https://user-images.githubusercontent.com/36191580/209644964-361bebc1-5272-4d99-a4a1-c236b1dd9cb1.png)
