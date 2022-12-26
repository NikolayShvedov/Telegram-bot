# API
http://localhost:8085 -> Dispatcher  
http://localhost:8086 -> Node  
http://localhost:15672 -> RabbitMQ

# Docker

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

## Флаги:  

| Флаг                     |                                                              Описание                                                              |
|--------------------------|:----------------------------------------------------------------------------------------------------------------------------------:|
| --detach , -d            |                          Запустит контейнер в фоновом режиме и вернет идентификатор контейнера в терминал                          |
| --hostname               |                            Адрес контейнера для подключения к нему внутри docker из других контейнеров                             |
| --name                   |                                                           Имя контейнера                                                           |
| -p                       | Порты: первый порт — тот, по которому мы будет подключаться снаружи docker, а второй — тот, который используется внутри контейнера |
| -v                       |                                    Примонтировать volume (том), т. е. внешнее хранилище данных                                     |
| --restart=unless-stopped |                   Контейнер будет подниматься заново при каждом перезапуске системы (точнее, при запуске docker)                   |

Подключиться к контейнеру с rabbitmq:  
`docker exec -it rabbitmq /bin/bash`  
  
Внутри контейнера создать пользователя, сделать его админом и установить права:  
`rabbitmqctl add_user userok p@ssw0rd`  
`rabbitmqctl set_user_tags userok administrator`  
`rabbitmqctl set_permissions -p / userok ".*" ".*" ".*"` 

# Подключение к RabbitMQ через веб-интерфейс
URL: `http://localhost:15672`  
Username: `guest`  
Password: `guest`  

# Полезные ссылки (Useful links)

Установить docker на ubuntu:  
`https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04-ru`  
Запуск rabbitmq в docker-контейнере:  
`https://blog.bayrell.org/ru/linux/docker/26-zapusk-rabbitmq-v-docker-konteynere.html`  
О volume (томах) в Windows:  
`https://rominirani.com/docker-on-windows-mounting-host-directories-d96f3f056a2c?gi=dc9c12358a93`  
`https://www.youtube.com/watch?v=jTeDNXLFYjE&t=0s`  
