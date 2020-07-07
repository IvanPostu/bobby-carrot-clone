# Клон игры Bobby Carrot

#### Таск-лист

- [x] Создать главное меню.
- [ ] Написать движок игры.
- [ ] Написать раунды.
- [x] Нарисовать обьекты, мир, карты, и т.д. ( paint )
- [x] Использовать design pattern MVC.
- [x] Использовать IoC, DI.
- [ ] Возможность преобразовать данные из файла в состояние раунда.
- [ ] Возможность сохранять текущ. состояние игру (save/load system)
- [ ] Написать тесты. 

### 1) Сборка проекта:
```
$ bash mvnw clean test compile assembly:single
```

### 2) Запуск:
```
$ java -jar ./target/bobbycarrot-1.0-SNAPSHOT-jar-with-dependencies.jar
```