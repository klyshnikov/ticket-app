# Сервис по покупке билетов

## Сборка и запуск

Есть 2 способа запустить приложение:

1. Запустить через gradle. Для этого надо выполнить в парвой панели IntelliJIdea ordering/Tasks/application/bootRun или выполнить команду ./gradlew ordering:bootRun
2. Запустить через docker-compouse. Предварительно надо собрать проект. Для этого надо в IntelliJIdea в правой панели gradle выполнить ordering/Tasks/build/assembly.
После этого соберутся нужные jar файлы. 
Далее нужно выполнить команду sudo docker-compose -f docker-compouse.yaml up
Теперь можно запускать приложение через swagger http://localhost:8080/swagger-ui/index.html

## Краткое описание сервиса

Чтобы начать пользоваться функциями приложения, надо зарегистрироваться по пути auth/sign-up. Далее после получения токена его нужно вставить в зеленое окошко
Справа сверху страницы (Authorize) и нажать (Authorize). После этого вы зарегистрированы и можно выполнять функции контролера order-controller. 

Часть станций уже доступны, остальные можно создавать самому.

В процессе работы программы каждые 10 секунд обработчик билетов выбирает билет, которому не присвоен статус, и с вероятностью 80% присваивает ему положительный статус.
Иначе отрицательный.

## auth-controller

### /auth/sign-up
Регистрация нового пользователя
Request body
{
  "nickname": "Misha",
  "email": "misha@gmail.com",
  "password": "S1ngLetoN!"
}

### /auth/sign-in
Вход по имени и пароли
Request body
{
  "username": "Misha",
  "password": "**S1nGleON!**"
}

### /auth/get-user-info
Получить информацию о текущем пользователе.
Request body
{}

### /auth/get-all
Получить всех пользователей (для упрощеннго тестирования).

## order-controller

### /order/make-order
Сделать заказ на новый билет.
Request body
{
  "stationFrom": "string",
  "stationTo": "string"
}

### /order/add-station
Добавить станцию.
Request body
{
  "stationName": "string"
}

### /order/get-my-orders
Получить все заказы текущего пользователя.
Request body
{}

### /order/get-all-stations
Получить список всех доступных станций.
Request body
{}
