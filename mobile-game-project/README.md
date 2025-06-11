# Mobil Oyun Projesi

## Genel Bakış
Bu proje, Kotlin kullanılarak geliştirilen bir mobil oyun uygulamasıdır. Oyunda, köyleri kurtarmak için gerekli öğeleri toplayarak ilerlenir. Proje, yığın (stack), kuyruk (queue) ve ikili arama ağacı (BST) gibi veri yapılarının kullanımını sergilemektedir.

## Özellikler
- **Köy Kurtarma Mekaniği**: Oyuncular, köyleri kurtarmak için gerekli öğeleri toplar.
- **Envanter Yönetimi**: Öğeler, kapasite sınırlı bir yığın envanterde saklanır.
- **İkili Arama Ağacı (BST)**: Envanterdeki öğelerin hızlı bir şekilde aranması için kullanılır.
- **Kuyruk Sistemi**: Köyler, sırayla kurtarılmak üzere bir kuyrukta yönetilir.
- **Etkileşimli Arayüz**: Android tabanlı kullanıcı arayüzü ile oyun oynanabilir.

## Proje Yapısı
Proje şu şekilde organize edilmiştir:

```
mobile-game-project
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── example
│   │   │   │           └── mobilegame
│   │   │   │               ├── MainActivity.kt
│   │   │   │               └── GameEngine.kt
│   │   │   └── res
│   │   │       ├── drawable
│   │   │       ├── layout
│   │   │       │   └── activity_main.xml
│   │   │       ├── mipmap
│   │   │       ├── values
│   │   │       │   ├── colors.xml
│   │   │       │   ├── strings.xml
│   │   │       │   └── styles.xml
│   │   │       └── xml
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── example
│   │                   └── mobilegame
│   │                       └── ExampleUnitTest.kt
│   └── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

## Başlarken

### Ön Koşullar
- Bilgisayarınızda Android Studio yüklü olmalıdır.
- Kotlin ve Android geliştirme konusunda temel bilgiye sahip olunmalıdır.

### Kurulum
1. Depoyu klonlayın:
   ```
   git clone <repository-url>
   ```
2. Android Studio'da projeyi açın.
3. Gradle dosyalarıyla projeyi senkronize edin.
4. Uygulamayı bir Android cihazında veya emülatörde çalıştırın.


## Lisans
Bu proje MIT Lisansı altında lisanslanmıştır 