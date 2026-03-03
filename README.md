# KtorArticle

Проект для научной статьи, сравнение фреймворков

## Как поднять

### Окружение

1. Нужно иметь docker на своей системе
2. docker run -d --name ktor-postgres -p 5432:5432 -e POSTGRES_DB=ktor-article -e POSTGRES_USER=test -e POSTGRES_PASSWORD=test -v ktor-postgres-data:/var/lib/postgresql/data postgres:15

### Ktor

1. Открыть приложение из любой IDE, где есть поддержка Java SDK
2. Точка входа ru.alkmanistik.ApplicationKt#main

### Spring

1. Открыть приложение из любой IDE, где есть поддержка Java SDK
2. Точка входа ru.alkmanistik.spring_article.SpringArticleApplication#main

### Net

1. Открыть приложение из Visual Studio Code
2. cd NetArticle
3. dotnet build
4. dotnet run

## Как пользоваться

Все приложения работают с единной базой данных на postgres, и у них для удобство одинаковые порты, поэтому все сразу можно запустить, если поменять конфигурацию порта

### EndPoints

Получение всех пользователей
`curl --location 'http://127.0.0.1:8080/client/all'`

Получение определенного пользователя
`curl --location 'http://127.0.0.1:8080/client/{client_id}'`

Создание пользователя
`curl --location 'http://127.0.0.1:8080/client'
--header 'Content-Type: application/json'
--data '{
    "name": {name}
}'`

Поиск пользователя
`curl --location 'http://127.0.0.1:8080/client/search'
--header 'Content-Type: application/json'
--data '{
    "name": {name}
}'`

Обновление пользователя
`curl --location --request PUT 'http://127.0.0.1:8080/client'
--header 'Content-Type: application/json'
--data '{
    "id": {client_id},
    "name": {name}
}'`

Удаление пользователя
`curl --location --request DELETE 'http://127.0.0.1:8080/client/{client_id}'`
