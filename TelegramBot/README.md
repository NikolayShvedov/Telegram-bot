# Структура проекта
Проект имеет микросервисную архитектуру и состоит из следующих модулей:
* `dispatcher` - микросервис для первичной валидации входящих данных и распределения сообщений по соответствующим очередям в брокере сообщений RabbitMQ;
* `common-rabbitmq` - модуль для размещения общего кода, связанного с брокером сообщений RabbitMQ, например названия очередей;
* `node` - микросервис для обработки сообщений из брокера RabbitMQ;
* `common-jpa` - модуль для размещения общего кода для сервисов, которые взаимодействуют с БД;
* `rest-service` - микросервис для скачивания файлов из БД по ссылке;
* `common-utils` - модуль для шифрования и дешифрования id ссылок;
* `mail-service` - микросервис для отправки электронного письма, содержащего ссылку для подтверждения регистрации.

# API
http://localhost:8085 -> Dispatcher  
http://localhost:8086 -> Node  
http://localhost:8087 -> Rest service  
http://localhost:8088 -> Mail service  
http://localhost:15672 -> RabbitMQ

# Docker
## 1. RabbitMQ

Скачать образ rabbitmq:  
`docker pull rabbitmq:3.11.0-management`

Создать volume:  
`docker volume create rabbitmq_data`

Проверить местонахождение volume:  
`docker volume inspect rabbitmq_data`  
Из представленного json из поля `Mountpoint` взять адрес и подставить в следующую команду.  
Или для Windows можно прописать путь следующим образом: `rabbitmq_data/:rabbitmq_data`. Тогда папка появится в корне диска C.  
Для Linux: `rabbitmq_data:/var/lib/rabbitmq`  

Запустить контейнер с rabbitmq:  
`docker run -d --hostname rabbitmq --name rabbitmq -p 5672:5672 -p 15672:15672 -v rabbitmq_data/:rabbitmq_data --restart=unless-stopped rabbitmq:3.11.0-management`

### Флаги:  

| Флаг                     |                                                              Описание                                                              |
|--------------------------|:----------------------------------------------------------------------------------------------------------------------------------:|
| --detach , -d            |                          Запустит контейнер в фоновом режиме и вернет идентификатор контейнера в терминал                          |
| --hostname               |                            Адрес контейнера для подключения к нему внутри docker из других контейнеров                             |
| --name                   |                                                           Имя контейнера                                                           |
| -p                       | Порты: первый порт — тот, по которому мы будет подключаться снаружи docker, а второй — тот, который используется внутри контейнера |
| -e                       |                                              Задает переменную окружения в контейнере                                              |
| -v                       |                                    Примонтировать volume (том), т. е. внешнее хранилище данных                                     |
| --restart=unless-stopped |                   Контейнер будет подниматься заново при каждом перезапуске системы (точнее, при запуске docker)                   |

Подключиться к контейнеру с rabbitmq:  
`docker exec -it rabbitmq /bin/bash`  
  
Внутри контейнера создать пользователя, сделать его админом и установить права:  
`rabbitmqctl add_user userok p@ssw0rd`  
`rabbitmqctl set_user_tags userok administrator`  
`rabbitmqctl set_permissions -p / userok ".*" ".*" ".*"` 

### Подключение к RabbitMQ через веб-интерфейс
URL: `http://localhost:15672`  
Username: `guest`  
Password: `guest`  

## 2. PostgreSQL

Создать volume:  
`docker volume create postgresql`

Проверить местонахождение volume:  
`docker volume inspect postgresql`  

Разворачиваем PostgreSQL в Docker:  
Windows: `docker run -d --hostname telegram_bot --name telegram_bot -p 5433:5432 -e POSTGRES_USER=userok -e POSTGRES_PASSWORD=p@ssw0rd -e POSTGRES_DB=telegram_bot -v postgresql:/var/lib/postgresql/data --restart=unless-stopped postgres:14.5`  
Linux: `docker run -d --hostname telegram_bot --name telegram_bot -p 5433:5432 -e POSTGRES_USER=userok -e POSTGRES_PASSWORD=p@ssw0rd -e POSTGRES_DB=telegram_bot -v /data:/var/lib/postgresql/data --restart=unless-stopped postgres:14.5`

Если нужно замаунтить к созданной папке в диске C, например C:\docker_volumes\postgresql, то вместо созданного volume пишем данный путь:  
`docker run -d --hostname telegram_bot --name telegram_bot -p 5433:5432 -e POSTGRES_USER=userok -e POSTGRES_PASSWORD=p@ssw0rd -e POSTGRES_DB=telegram_bot -v C:\docker_volumes\postgresql:/var/lib/postgresql/data --restart=unless-stopped postgres:14.5`

### Параметры для работы с БД

| Поле         |   Значение   |
|--------------|:------------:|
| База данных  | telegram_bot |
| Порт         |     5433     |
| Пользователь |    userok    |
| Пароль       |   p@ssw0rd   |

# Полезные ссылки (Useful links)

Установить docker на ubuntu:  
https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04-ru  
Запуск rabbitmq в docker-контейнере:  
https://blog.bayrell.org/ru/linux/docker/26-zapusk-rabbitmq-v-docker-konteynere.html  
О volume (томах) в Windows:  
https://rominirani.com/docker-on-windows-mounting-host-directories-d96f3f056a2c?gi=dc9c12358a93  
https://www.youtube.com/watch?v=jTeDNXLFYjE&t=0s  
Сравнение PostgreSQL VS MongoDB в контексте jsonb:  
https://www.youtube.com/watch?v=SNzOZKvFZ68  
Сайт для получения временной электронной почты:  
https://temp-mail.org/ru/  

